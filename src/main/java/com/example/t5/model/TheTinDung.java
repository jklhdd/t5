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

import java.sql.Date;


@Data
@Entity
@NoArgsConstructor(access = AccessLevel.PUBLIC,force = true)
@Table(name = "thetindung")
public class TheTinDung {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private  String maThe;
	private int soNgayVay;
	private double tonNo;
	private double phiDuyTri;
	private Date ngayVay;
	private int status;

	@OneToOne
	@JoinColumn(name = "taikhoan_id", referencedColumnName = "id")
	private TaiKhoan tk;

	@OneToOne
	@JoinColumn(name = "goitindung_id", referencedColumnName = "id")
	private GoiTinDung gtd;

	public GoiTinDung getGtd() {
		return gtd;
	}

	public void setGtd(GoiTinDung gtd) {
		this.gtd = gtd;
	}
}
