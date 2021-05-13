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
@Table(name = "doanhso")
public class DoanhSo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private double tien;
	private String mota;
	private Date ngayTao;

}
