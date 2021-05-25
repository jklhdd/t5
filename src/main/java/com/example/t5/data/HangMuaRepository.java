package com.example.t5.data;

import com.example.t5.model.HangMua;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HangMuaRepository extends CrudRepository<HangMua, Integer> {
}
