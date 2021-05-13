package com.example.t5.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.t5.model.Vay;
@Repository
public interface VayRepository extends CrudRepository<Vay, Integer> {
    public Vay findFirstByTkIdAndStatus(int id, int status);
}
