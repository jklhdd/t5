package com.example.t5.data;

import com.example.t5.model.GoiTinDung;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface GoiTinDungRepository extends CrudRepository<GoiTinDung,Integer> {

}
