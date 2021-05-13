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
@Table(name = "goitindung")
public class GoiTinDung {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String tenGoi;
	private float laiSuat;
	private int kyHan;

	public int getKyHan() {
		return kyHan;
	}

	public void setKyHan(int kyHan) {
		this.kyHan = kyHan;
	}

	public float getLaiSuat() {
		return laiSuat;
	}

	public void setLaiSuat(float laiSuat) {
		this.laiSuat = laiSuat;
	}
}
