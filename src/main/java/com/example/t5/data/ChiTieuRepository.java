package com.example.t5.data;

import com.example.t5.model.ChiTieu;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ChiTieuRepository extends CrudRepository<ChiTieu, Integer> {

}
