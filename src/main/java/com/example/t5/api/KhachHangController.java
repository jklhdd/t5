package com.example.t5.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.t5.data.HoTenRepository;
import com.example.t5.data.ThongTinRepository;
import com.example.t5.dto.ThanhVien;
import com.example.t5.data.TaiKhoanRepository;
import com.example.t5.model.HoTen;
import com.example.t5.model.TaiKhoan;
import com.example.t5.model.ThongTinCaNhan;

import java.util.Comparator;
import java.util.List;


@RestController
@RequestMapping(path = "/account" , produces = "application/json" )
public class KhachHangController {
    @Autowired
    TaiKhoanRepository tkRepository;
    @Autowired
    HoTenRepository hoTenRepository;
    @Autowired
    ThongTinRepository thongTinRepository;

    @GetMapping
    public List<ThanhVien> getAll(){
        return tkRepository.getAllAccount();
    }
    @GetMapping("/staff")
    public List<ThanhVien> getAllStaff(){
        List<ThanhVien> list = tkRepository.getAllAccount("Nhan Vien Giao Dich");
        list.addAll(tkRepository.getAllAccount("Nhan Vien Tin Dung"));
        return list;
    }

    @GetMapping("/{id}")
    public ThanhVien getAccountById(@PathVariable int id){
        return tkRepository.getAccountById(id);
    }

    @GetMapping("/customer")
    public List<ThanhVien> getAllCustomer(){
        return tkRepository.getAllAccount("KhachHang");
    }

    @GetMapping("/login/{taikhoan}/{matkhau}")
    public ThanhVien login(@PathVariable("taikhoan") String tk, @PathVariable("matkhau") String mk){
        return tkRepository.getAccountByTaiKhoanAndMatKhau(tk,mk);
    }

    @GetMapping("/search/{id}")
    public TaiKhoan searchAccountById(@PathVariable int id){
        return tkRepository.findById(id).get();
    }

    @PostMapping("/create/{pass}")
    public String create(@PathVariable("pass") String mk, @RequestBody ThanhVien tv){
        TaiKhoan tk = new TaiKhoan();
        HoTen hoTen = new HoTen();
        ThongTinCaNhan ttcn = new ThongTinCaNhan();
        tk.setChucVu(tv.getChucvu());
        tk.setTaiKhoan(tv.getTaikhoan());
        tk.setMatKhau(mk);
        tk = tkRepository.save(tk);

        hoTen.setHo(tv.getHo());
        hoTen.setDem(tv.getDem());
        hoTen.setTen(tv.getTen());
        hoTen.setTk(tk);
        hoTenRepository.save(hoTen);

        ttcn.setCmt(tv.getCmt());
        ttcn.setSdt(tv.getSdt());
        ttcn.setTk(tk);
        thongTinRepository.save(ttcn);

        return "Dang ki thanh cong";
    }
}
