package com.example.t5.model;
import java.sql.Date;

import javax.persistence.*;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor(access = AccessLevel.PUBLIC,force = true)
@Table(name = "sotietkiem")
public class SoTietKiem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(unique = true)
	private String maSo;
	private double soTien;
	private Date ngayMo;
	private int kyHan;
	private float laiSuat;
	private int soNgayGui;
	private int status;

	@OneToOne
	@JoinColumn(name = "taikhoan_id", referencedColumnName = "id")
	private TaiKhoan tk;
}