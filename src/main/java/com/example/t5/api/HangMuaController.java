package com.example.t5.api;

import com.example.t5.data.ChiTieuKHRepository;
import com.example.t5.data.HangMuaRepository;
import com.example.t5.data.TheNganHangRepository;
import com.example.t5.data.TheTinDungRepository;
import com.example.t5.model.ChiTieu;
import com.example.t5.model.ChiTieuKH;
import com.example.t5.model.HangMua;
import com.example.t5.model.TheNganHang;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/product" , produces = "application/json" )
public class HangMuaController {
    @Autowired
    HangMuaRepository hangMuaRepository;
    @Autowired
    TheNganHangRepository tnnRepository;
    @Autowired
    ChiTieuKHRepository chiTieuKHRepository;

    @GetMapping
    public List<HangMua> getAll(){
        return (List<HangMua>) hangMuaRepository.findAll();
    }

    @GetMapping("/buy/{tk_id}-{hang_id}")
    public String buyItem(@PathVariable("tk_id") int tk_id, @PathVariable("hang_id") int hang_id){
        TheNganHang tnn = tnnRepository.findFirstByTkIdAndStatus(tk_id, 1);
        HangMua hangMua = hangMuaRepository.findById(hang_id).get();

        if (tnn.getSoDu() > hangMua.getTien()){
            tnn.setSoDu(tnn.getSoDu()-hangMua.getTien());
            tnnRepository.save(tnn);
            ChiTieuKH chiTieuKH = new ChiTieuKH();
            chiTieuKH.setSoTien(hangMua.getTien());
            chiTieuKH.setTen("Mua " + hangMua.getTenMatHang());
            chiTieuKH.setTk(tnn.getTk());
            chiTieuKHRepository.save(chiTieuKH);
        }
        else return "Tai khoan hien khong du tien!";

        return "Thanh toan thanh cong";
    }

    @PostMapping
    public String save(@RequestBody HangMua hangMua){
        hangMuaRepository.save(hangMua);
        return "Them mat hang thanh cong";
    }
}
