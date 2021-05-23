package com.example.t5.data;

import com.example.t5.model.ChiTieuKH;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChiTieuKHRepository extends CrudRepository<ChiTieuKH, Integer> {
    List<ChiTieuKH> findAllByTk_Id(int id);
}
