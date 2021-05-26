package com.example.t5.api;

import com.example.t5.data.*;
import com.example.t5.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(path = "/saving" , produces = "application/json" )
public class SoTietKiemController {
    @Autowired
    SoTietKiemRepository stkRepository;
    @Autowired
    NganSachRepository nganSachRepository;
    @Autowired
    DoanhSoRepository doanhSoRepository;
    @Autowired
    TheNganHangRepository theNganHangRepository;
    @Autowired
    GiaoDichRepository giaoDichRepository;
    @Autowired
    ChiTieuRepository chiTieuRepository;

    @GetMapping
    public List<SoTietKiem> getAll(){
        List<SoTietKiem> list = (List<SoTietKiem>) stkRepository.findAll();
        for (SoTietKiem s : list){
            capNhatSoNgayGui(s);
        }
        return list;
    }

    @GetMapping("/detail/{id}")
    public SoTietKiem getById(@PathVariable("id") int id){
        SoTietKiem s = stkRepository.findById(id).get();
        if (s.getStatus() == 1) capNhatSoNgayGui(s);
        return s;
    }

    @GetMapping("/{taikhoan_id}")
    public SoTietKiem getAllByCustomerId(@PathVariable("taikhoan_id") int id){
        SoTietKiem s = stkRepository.findFirstByTkIdAndStatus(id, 1);
        capNhatSoNgayGui(s);
        return s;
    }

    @GetMapping("/status-list/{status}")
    public List<SoTietKiem> getByStatus(@PathVariable("status") int status) {
        if (status == 1){
            List<SoTietKiem> list =  stkRepository.findAllByStatus(status);
            for (SoTietKiem s : list){
                capNhatSoNgayGui(s);
            }
            return list;
        }
        return stkRepository.findAllByStatus(status);
    }

    @PutMapping("/approve/{id}")
    public void update(@PathVariable("id") int id){

        SoTietKiem soTietKiem = stkRepository.findById(id).get();
        soTietKiem.setStatus(1);
        stkRepository.save(soTietKiem);

        TheNganHang theNganHang = theNganHangRepository.findFirstByTkIdAndStatus(soTietKiem.getTk().getId(), 1);
        theNganHang.setSoDu(theNganHang.getSoDu() - soTietKiem.getSoTien());
        theNganHangRepository.save(theNganHang);

        GiaoDich gd = new GiaoDich();
        gd.setDiaChi("Default");
        gd.setSoTien(soTietKiem.getSoTien());
        gd.setPhuongThuc("Gui tiet kiem");
        gd.setTk(soTietKiem.getTk());
        giaoDichRepository.save(gd);

        NganSach n = nganSachRepository.getByYearAndMonth(LocalDate.now().getYear(),LocalDate.now().getMonthValue());
        n.setSoDu(n.getSoDu()+soTietKiem.getSoTien());
        nganSachRepository.save(n);

        DoanhSo ds = new DoanhSo();
        ds.setTien(soTietKiem.getSoTien());
        ds.setMota("Gui Tiet Kiem");
        ds.setNgayTao(new Date(System.currentTimeMillis()));
        doanhSoRepository.save(ds);
    }

    @GetMapping("/rut-tien/{id}")
    public void rutTien(@PathVariable ("id") int id){
        double tongTien;
        SoTietKiem soTietKiem = stkRepository.findById(id).get();
        soTietKiem.setStatus(-1);
        stkRepository.save(soTietKiem);

        TheNganHang theNganHang = theNganHangRepository.findFirstByTkIdAndStatus(soTietKiem.getTk().getId(), 1);
        if (soTietKiem.getSoNgayGui() < soTietKiem.getKyHan()){
            tongTien = soTietKiem.getSoTien();
        }
        else {
            tongTien = soTietKiem.getSoTien() + soTietKiem.getSoTien()*soTietKiem.getLaiSuat();
        }
        theNganHang.setSoDu(theNganHang.getSoDu() + tongTien);
        theNganHangRepository.save(theNganHang);

        GiaoDich gd = new GiaoDich();
        gd.setDiaChi("Default");
        gd.setSoTien(tongTien);
        gd.setPhuongThuc("Rut tiet kiem");
        gd.setTk(soTietKiem.getTk());
        giaoDichRepository.save(gd);

        NganSach n = nganSachRepository.getByYearAndMonth(LocalDate.now().getYear(),LocalDate.now().getMonthValue());
        n.setSoDu(n.getSoDu()-tongTien);
        nganSachRepository.save(n);

        ChiTieu c = new ChiTieu();
        c.setTien(tongTien);
        c.setMota("Khach hang rut so tiet kiem");
        c.setNgayTao(Date.valueOf(LocalDate.now()));

        chiTieuRepository.save(c);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable ("id") int id) {
        SoTietKiem soTietKiem = stkRepository.findById(id).get();
        soTietKiem.setStatus(0);
        stkRepository.save(soTietKiem);
    }

    @PostMapping
    public void save(@RequestBody SoTietKiem ttd){
    	stkRepository.save(ttd);
    }

    private void capNhatSoNgayGui(SoTietKiem stk){
        long millis = System.currentTimeMillis();
        long diffInMillies = Math.abs(millis - stk.getNgayMo().getTime());
        long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

        stk.setSoNgayGui((int)diff);
        stkRepository.save(stk);
    }
}
