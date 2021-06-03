package com.example.t5.api;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.t5.data.*;
import com.example.t5.dto.GiaoDichDto;
import com.example.t5.model.*;

@RestController
@RequestMapping(path = "/loan" , produces = "application/json" )
public class VayController {
	@Autowired
    VayRepository vayRepository;
	@Autowired
    NganSachRepository nganSachRepository;
	@Autowired
    ChiTieuRepository chiTieuRepository;
	@Autowired
    GiaoDichRepository giaoDichRepository;
	@Autowired
    TheNganHangRepository theNganHangRepository;
	@Autowired
    DoanhSoRepository doanhSoRepository;

    @GetMapping
    public List<Vay> getAll(){
        return (List<Vay>) vayRepository.findAll();
    }

    @GetMapping("/{taikhoan_id}")
    public Vay getAllByCustomerId(@PathVariable("taikhoan_id") int id){
        return vayRepository.findFirstByTkIdAndStatus(id, 1);
    }

    @PutMapping("/approve/{id}")
    public void update(@PathVariable("id") int id){
        Vay vay = vayRepository.findById(id).get();
        vay.setStatus(1);
        int month = vay.getNgayVay().toLocalDate().getMonthValue();
        int year = vay.getNgayVay().toLocalDate().getYear();
        NganSach n = nganSachRepository.getByYearAndMonth(year, month);

        n.setSoDu(n.getSoDu()-vay.getKhoanVay());

        ChiTieu c = new ChiTieu();
        c.setTien(vay.getKhoanVay());
        c.setMota("Cho Vay");
        c.setNgayTao(vay.getNgayVay());

        chiTieuRepository.save(c);
        nganSachRepository.save(n);
        vayRepository.save(vay);
    }

    @PostMapping
    public void save(@RequestBody Vay vay){
        vayRepository.save(vay);
    }

    @PostMapping("/tra-no")
    public String traNo(@RequestBody GiaoDichDto dto){
        TheNganHang tk_gui = theNganHangRepository.findFirstByMaThe(dto.getMathe_gui());
        Vay v = vayRepository.findById(Integer.parseInt(dto.getMathe_nhan())).get();
        NganSach n = nganSachRepository.getByYearAndMonth(LocalDate.now().getYear(), LocalDate.now().getMonthValue());
        GiaoDich gd = new GiaoDich();
        DoanhSo ds = new DoanhSo();
        double tientra = 0;

        long millis = System.currentTimeMillis();
        long diffInMillies = Math.abs(millis - v.getNgayVay().getTime());
        long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

        tientra = v.getKhoanVay()*(v.getLaiSuat()/30)*diff + v.getKhoanVay();

        if (tk_gui.getSoDu() < tientra) return "Khong du tien trong tai khoan!";

        tk_gui.setSoDu(tk_gui.getSoDu()-tientra);
        v.setStatus(-1);

        gd.setDiaChi("Default");
        gd.setSoTien(tientra);
        gd.setPhuongThuc("Tra No");
        gd.setTk(v.getTk());

        n.setSoDu(n.getSoDu()+tientra);
        ds.setTien(tientra);
        ds.setMota("Tra no");
        ds.setNgayTao(new Date(millis));

        theNganHangRepository.save(tk_gui);
        vayRepository.save(v);
        giaoDichRepository.save(gd);
        nganSachRepository.save(n);
        doanhSoRepository.save(ds);
        return "Tra no thanh cong!";
    }
}
