package com.example.t5.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.t5.model.SoTietKiem;

import java.util.List;

@Repository
public interface SoTietKiemRepository extends CrudRepository<SoTietKiem, Integer>{
    public SoTietKiem findFirstByTkIdAndStatus(int id, int status);
    public List<SoTietKiem> findAllByStatus(int status);
}
