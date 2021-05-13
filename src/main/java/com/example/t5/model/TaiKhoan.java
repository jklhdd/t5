package com.example.t5.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor(access = AccessLevel.PUBLIC,force = true)
@Table(name = "taikhoan")
public class TaiKhoan {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String taiKhoan;
	private String matKhau;
	private String chucVu;

}
