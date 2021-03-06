package com.example.t5.model;

import javax.persistence.*;

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
	@Column(unique = true)
	private  String maThe;
	private double vayToiDa;
	private double phiDuyTri;
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
