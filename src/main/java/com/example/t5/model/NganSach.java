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
@Table(name = "ngansach")
public class NganSach {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private double soDu;
	private double tienGoc;
	private Date ngayTao;

	public double getSoDu() {
		return soDu;
	}

	public void setSoDu(double soDu) {
		this.soDu = soDu;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getTienGoc() {
		return tienGoc;
	}

	public void setTienGoc(double tienGoc) {
		this.tienGoc = tienGoc;
	}

	public Date getNgayTao() {
		return ngayTao;
	}

	public void setNgayTao(Date ngayTao) {
		this.ngayTao = ngayTao;
	}
}
