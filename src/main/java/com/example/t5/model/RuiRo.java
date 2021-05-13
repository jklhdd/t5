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
@Table(name = "ruiro")
public class RuiRo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private double chiPhi;
	private String phuongThuc;
	private String chiTiet;

}
