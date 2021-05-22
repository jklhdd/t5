package com.example.t5.data;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.t5.dto.ThanhVien;
import com.example.t5.model.TaiKhoan;

import java.util.List;

@Repository
public interface TaiKhoanRepository extends CrudRepository<TaiKhoan,Integer> {
    @Query("select new com.example.t5.dto.ThanhVien(t.id, t.taiKhoan, t.chucVu, h.ho, h.dem, h.ten, d.sdt, d.cmt) " +
            "from TaiKhoan t, HoTen h, ThongTinCaNhan d where t.id = h.tk.id and t.id = d.tk.id")
    public List<ThanhVien> getAllAccount();
    @Query("select new com.example.t5.dto.ThanhVien(t.id, t.taiKhoan, t.chucVu, h.ho, h.dem, h.ten, d.sdt, d.cmt) " +
            "from TaiKhoan t, HoTen h, ThongTinCaNhan d where t.chucVu =?1 and t.id = h.tk.id and t.id = d.tk.id")
    public List<ThanhVien> getAllAccount(String chucvu);

    @Query("select new com.example.t5.dto.ThanhVien(t.id, t.taiKhoan, t.chucVu, h.ho, h.dem, h.ten, d.sdt, d.cmt) " +
            "from TaiKhoan t, HoTen h, ThongTinCaNhan d where t.id = ?1 and d.tk.id = ?1 and h.tk.id = ?1")
    public ThanhVien getAccountById(int cid);

    @Query("select new com.example.t5.dto.ThanhVien(t.id, t.taiKhoan, t.chucVu, h.ho, h.dem, h.ten, d.sdt, d.cmt) " +
            "from TaiKhoan t, HoTen h, ThongTinCaNhan d where t.taiKhoan =?1 and t.matKhau=?2 and t.id = h.tk.id and t.id = d.tk.id")
    public ThanhVien getAccountByTaiKhoanAndMatKhau(String tk, String mk);


}
