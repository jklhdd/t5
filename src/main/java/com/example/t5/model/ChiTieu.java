package com.example.t5.model;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor(access = AccessLevel.PUBLIC,force = true)
@Table(name = "chitieu")
public class ChiTieu {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private double tien;
	private String mota;
	private Date ngayTao;

	public double getTien() {
		return tien;
	}

	public void setTien(double tien) {
		this.tien = tien;
	}

	public String getMota() {
		return mota;
	}

	public void setMota(String mota) {
		this.mota = mota;
	}

	public Date getNgayTao() {
		return ngayTao;
	}

	public void setNgayTao(Date ngayTao) {
		this.ngayTao = ngayTao;
	}
}
