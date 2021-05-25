package com.example.t5.api;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import com.example.t5.data.ChiTieuRepository;
import com.example.t5.data.LuongRepository;
import com.example.t5.data.NganSachRepository;
import com.example.t5.model.ChiTieu;
import com.example.t5.model.Luong;
import com.example.t5.model.NganSach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping(path = "/salary" , produces = "application/json" )
public class LuongController {
	@Autowired
    LuongRepository luongRepo;
    @Autowired
    NganSachRepository nganSachRepository;
    @Autowired
    ChiTieuRepository chiTieuRepository;

    @GetMapping
    public List<Luong> getAll(){
        return (List<Luong>) luongRepo.findAll();
    }

    @GetMapping("/{id}")
    public Luong getAll(@PathVariable("id") int id){
        return luongRepo.findById(id).get();
    }

    @GetMapping("/staff/{id}")
    public Luong getSalaryByStaffId(@PathVariable("id") int id){
        return luongRepo.findFirstByTkId(id);
    }

    // them luong
    @PostMapping
    public void save(@RequestBody Luong luong){
        NganSach n = nganSachRepository.getByYearAndMonth(LocalDate.now().getYear(),LocalDate.now().getMonthValue());
        n.setSoDu(n.getSoDu()-luong.getLuong());
        ChiTieu c = new ChiTieu();
        c.setTien(luong.getLuong());
        c.setMota("Luong nhan vien");
        c.setNgayTao(Date.valueOf(LocalDate.now()));

        chiTieuRepository.save(c);
        nganSachRepository.save(n);
        luongRepo.save(luong);
    }

    // update luong
    @PutMapping("/{id}")
    public void update(@PathVariable("id") int id, @RequestBody Luong luong){
        Luong luongCu = luongRepo.findById(id).get();
        NganSach n = nganSachRepository.getByYearAndMonth(LocalDate.now().getYear(),LocalDate.now().getMonthValue());
        n.setSoDu(n.getSoDu()-luong.getLuong()+luongCu.getLuong());
        ChiTieu c = new ChiTieu();
        c.setTien(luong.getLuong()-luongCu.getLuong());
        c.setMota("Dieu chinh luong nhan vien");
        c.setNgayTao(Date.valueOf(LocalDate.now()));

        chiTieuRepository.save(c);
        nganSachRepository.save(n);
        luongRepo.save(luong);
    }
}
