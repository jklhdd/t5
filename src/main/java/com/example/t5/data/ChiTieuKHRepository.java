package com.example.t5.data;

import com.example.t5.model.ChiTieuKH;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChiTieuKHRepository extends CrudRepository<ChiTieuKH, Integer> {

}
