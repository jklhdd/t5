package com.example.t5.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.t5.model.HoTen;

@Repository
public interface HoTenRepository extends CrudRepository<HoTen,Integer> {
}
