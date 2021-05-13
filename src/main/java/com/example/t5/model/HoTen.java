package com.example.t5.model;

import javax.persistence.*;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor(access = AccessLevel.PUBLIC,force = true)
@Table(name = "hoten")
public class HoTen {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String ho;
	private String dem;
	private String ten;

	@OneToOne
	@JoinColumn(name = "taikhoan_id", referencedColumnName = "id")
	private TaiKhoan tk;

}
