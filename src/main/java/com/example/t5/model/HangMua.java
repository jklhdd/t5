package com.example.t5.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor(access = AccessLevel.PUBLIC,force = true)
@Table(name = "hangmua")
public class HangMua {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double tien;
    private String tenMatHang;
}