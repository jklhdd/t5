package com.example.t5.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.t5.data.GiaoDichRepository;
import com.example.t5.data.TheNganHangRepository;
import com.example.t5.dto.GiaoDichDto;
import com.example.t5.model.GiaoDich;
import com.example.t5.model.TheNganHang;
import com.example.t5.model.TheTinDung;
import com.example.t5.data.TheTinDungRepository;

import java.sql.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(path = "/credit-card" , produces = "application/json" )
public class TheTinDungController {
    @Autowired
    TheTinDungRepository ttdRepository;
    @Autowired
    GiaoDichRepository giaoDichRepository;

    @GetMapping
    public List<TheTinDung> getAll(){
        List<TheTinDung> list = (List<TheTinDung>) ttdRepository.findAll();
        return list;
    }

    @GetMapping("/detail/{id}")
    public TheTinDung getById(@PathVariable("id") int id){
        TheTinDung ttd = ttdRepository.findById(id).get();
        return ttd;
    }

    @GetMapping("/{taikhoan_id}")
    public TheTinDung getByCustomerId(@PathVariable("taikhoan_id") int id){
        TheTinDung ttd = ttdRepository.findFirstByTkIdAndStatus(id, 1);
        return ttd;
    }

    @PutMapping("/approve/{id}")
    public void update(@PathVariable("id") int id){
        TheTinDung theTinDung = ttdRepository.findById(id).get();
        theTinDung.setStatus(1);
        ttdRepository.save(theTinDung);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable ("id") int id) {
        TheTinDung theTinDung = ttdRepository.findById(id).get();
        theTinDung.setStatus(0);
        ttdRepository.save(theTinDung);
    }

    @PostMapping
    public void save(@RequestBody TheTinDung ttd){
    	ttdRepository.save(ttd);
    }

//    @PostMapping("/tra-no")
//    public String traNo(@RequestBody GiaoDichDto dto){
//        TheNganHang tk_gui = theNganHangRepository.findFirstByMaThe(dto.getMathe_gui());
//        TheTinDung ttd = ttdRepository.findFirstByMaThe(dto.getMathe_nhan());
//        GiaoDich gd = new GiaoDich();
//        double lai = 0;
//
//        if (ttd.getSoNgayVay() > ttd.getGtd().getKyHan()){
//            int ngay = ttd.getSoNgayVay() - ttd.getGtd().getKyHan();
//            lai = ttd.getGtd().getLaiSuat()*ttd.getTonNo();
//        }
//
//        if (tk_gui.getSoDu() < dto.getSoTien()+tk_gui.getPhiDuyTri()+lai) return "Khong du tien trong tai khoan!";
//
//        tk_gui.setSoDu(tk_gui.getSoDu()-dto.getSoTien()-lai);
//        ttd.setTonNo(0);
//        ttd.setSoNgayVay(0);
//        long millis = System.currentTimeMillis();
//        ttd.setNgayVay(new Date(millis));
//
//        gd.setDiaChi("Default");
//        gd.setSoTien(dto.getSoTien()+lai);
//        gd.setPhuongThuc("Tra No");
//        gd.setTk(ttd.getTk());
//
//        theNganHangRepository.save(tk_gui);
//        ttdRepository.save(ttd);
//        giaoDichRepository.save(gd);
//
//        return "Tra no thanh cong!";
//    }

}
