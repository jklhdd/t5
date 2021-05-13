package com.example.t5.model;
import java.sql.Date;

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
@Table(name = "vay")
public class Vay {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private double khoanVay;
	private Date ngayVay;
	private float laiSuat;
	private int status;

	@OneToOne
	@JoinColumn(name = "taikhoan_id", referencedColumnName = "id")
	private TaiKhoan tk;

	public Date getNgayVay() {
		return ngayVay;
	}

	public void setNgayVay(Date ngayVay) {
		this.ngayVay = ngayVay;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getKhoanVay() {
		return khoanVay;
	}

	public void setKhoanVay(double khoanVay) {
		this.khoanVay = khoanVay;
	}

	public float getLaiSuat() {
		return laiSuat;
	}

	public void setLaiSuat(float laiSuat) {
		this.laiSuat = laiSuat;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public TaiKhoan getTk() {
		return tk;
	}

	public void setTk(TaiKhoan tk) {
		this.tk = tk;
	}
}
