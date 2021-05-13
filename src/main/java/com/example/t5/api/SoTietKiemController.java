package com.example.t5.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.t5.data.DoanhSoRepository;
import com.example.t5.data.NganSachRepository;
import com.example.t5.data.SoTietKiemRepository;
import com.example.t5.model.DoanhSo;
import com.example.t5.model.NganSach;
import com.example.t5.model.SoTietKiem;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
@RestController
@RequestMapping(path = "/saving" , produces = "application/json" )
public class SoTietKiemController {
    @Autowired
    SoTietKiemRepository stkRepository;
    @Autowired
    NganSachRepository nganSachRepository;
    @Autowired
    DoanhSoRepository doanhSoRepository;

    @GetMapping
    public List<SoTietKiem> getAll(){
        return (List<SoTietKiem>) stkRepository.findAll();
    }

    @GetMapping("/detail/{id}")
    public SoTietKiem getById(@PathVariable("id") int id){
        return stkRepository.findById(id).get();
    }

    @GetMapping("/{taikhoan_id}")
    public SoTietKiem getAllByCustomerId(@PathVariable("taikhoan_id") int id){
        return stkRepository.findFirstByTkIdAndStatus(id, 1);
    }

    @GetMapping("/status-list/{status}")
    public List<SoTietKiem> getByStatus(@PathVariable("status") int status) {
        return stkRepository.findAllByStatus(status);
    }

    @PutMapping("/approve/{id}")
    public void update(@PathVariable("id") int id){
        SoTietKiem soTietKiem = stkRepository.findById(id).get();
        soTietKiem.setStatus(1);
        stkRepository.save(soTietKiem);
        NganSach n = nganSachRepository.getByYearAndMonth(LocalDate.now().getYear(),LocalDate.now().getMonthValue());
        n.setSoDu(n.getSoDu()+soTietKiem.getSoTien());
        nganSachRepository.save(n);
        DoanhSo ds = new DoanhSo();
        ds.setTien(soTietKiem.getSoTien());
        ds.setMota("Gui Tiet Kiem");
        ds.setNgayTao(new Date(System.currentTimeMillis()));
        doanhSoRepository.save(ds);
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
}
