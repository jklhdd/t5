package com.example.t5.data;

import com.example.t5.model.GiaoDich;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GiaoDichRepository extends CrudRepository<GiaoDich, Integer>{
    public List<GiaoDich> findAllByTkId(int id);
}
