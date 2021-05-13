package com.example.t5.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import com.example.t5.dto.GiaoDichDto;
import com.example.t5.model.GiaoDich;
import com.example.t5.model.TheNganHang;
import com.example.t5.data.GiaoDichRepository;
import com.example.t5.data.TheNganHangRepository;

import java.util.List;

@RestController
@RequestMapping(path = "/atm-card" , produces = "application/json" )
public class TheNganHangController {
    @Autowired
    TheNganHangRepository tnhRepository;
    @Autowired
    GiaoDichRepository giaoDichRepository;

    @GetMapping
    public List<TheNganHang> getAll(){
        return (List<TheNganHang>) tnhRepository.findAll();
    }

    @GetMapping("/detail/{id}")
    public TheNganHang getById(@PathVariable("id") int id){
        return tnhRepository.findById(id).get();
    }

    @GetMapping("/{taikhoan_id}")
    public TheNganHang getAllByCustomerId(@PathVariable("taikhoan_id") int id){
        return tnhRepository.findFirstByTkIdAndStatus(id, 1);
    }

    @GetMapping("/status-list/{status}")
    public List<TheNganHang> getByStatus(@PathVariable("status") int status) {
        return tnhRepository.findAllByStatus(status);
    }

    @PutMapping("/approve/{id}")
    public void update(@PathVariable("id") int id){
        TheNganHang theNganHang = tnhRepository.findById(id).get();
        theNganHang.setStatus(1);
        tnhRepository.save(theNganHang);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable ("id") int id) {
        TheNganHang theNganHang = tnhRepository.findById(id).get();
        theNganHang.setStatus(0);
        tnhRepository.save(theNganHang);
    }

    @PostMapping
    public void save(@RequestBody TheNganHang theNganHang){
    	tnhRepository.save(theNganHang);
    }

    @PostMapping("/giao-dich")
    public void giaoDichTien(@RequestBody GiaoDichDto dto){
    	switch (dto.getPhuongThuc()){
            case "Chuyen Tien":
                chuyenTien(dto);
                break;
            case "Rut Tien":
                rutTien(dto);
                break;
            case "Gui Tien":
                guiTien(dto);
                break;
        }
    }

    public void chuyenTien(GiaoDichDto dto){
        TheNganHang tk_gui = tnhRepository.findFirstByMaThe(dto.getMathe_gui());
        TheNganHang tk_nhan = tnhRepository.findFirstByMaThe(dto.getMathe_nhan());

        tk_gui.setSoDu(tk_gui.getSoDu()-dto.getSoTien());
        tk_nhan.setSoDu(tk_nhan.getSoDu()+dto.getSoTien());
        GiaoDich giaoDichGui = new GiaoDich();
        GiaoDich giaoDichNhan = new GiaoDich();

        giaoDichGui.setDiaChi(dto.getMathe_nhan());
        giaoDichGui.setPhuongThuc(dto.getPhuongThuc());
        giaoDichGui.setSoTien(dto.getSoTien());
        giaoDichGui.setTk(tk_gui.getTk());

        giaoDichNhan.setDiaChi(dto.getMathe_gui());
        giaoDichNhan.setPhuongThuc("Nhan Tien");
        giaoDichNhan.setSoTien(dto.getSoTien());
        giaoDichNhan.setTk(tk_nhan.getTk());

        giaoDichRepository.save(giaoDichGui);
        giaoDichRepository.save(giaoDichNhan);
        tnhRepository.save(tk_gui);
        tnhRepository.save(tk_nhan);
    }

    public void guiTien(GiaoDichDto dto){
        TheNganHang tk_nhan = tnhRepository.findFirstByMaThe(dto.getMathe_nhan());
        tk_nhan.setSoDu(tk_nhan.getSoDu()+dto.getSoTien());
        GiaoDich gd = new GiaoDich();
        gd.setDiaChi("Default");
        gd.setSoTien(dto.getSoTien());
        gd.setPhuongThuc("Gui Tien");
        gd.setTk(tk_nhan.getTk());

        giaoDichRepository.save(gd);
        tnhRepository.save(tk_nhan);
    }

    public void rutTien(GiaoDichDto dto){
        TheNganHang tk_gui = tnhRepository.findFirstByMaThe(dto.getMathe_gui());
        tk_gui.setSoDu(tk_gui.getSoDu()-dto.getSoTien());
        GiaoDich gd = new GiaoDich();
        gd.setDiaChi("Default");
        gd.setSoTien(dto.getSoTien());
        gd.setPhuongThuc("Rut Tien");
        gd.setTk(tk_gui.getTk());

        giaoDichRepository.save(gd);
        tnhRepository.save(tk_gui);
    }

}
