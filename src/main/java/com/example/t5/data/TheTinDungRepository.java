package com.example.t5.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.t5.model.TheTinDung;

import java.util.List;

@Repository
public interface TheTinDungRepository extends CrudRepository<TheTinDung, Integer> {
    public TheTinDung findFirstByTkIdAndStatus(int id, int status);
    public TheTinDung findFirstByMaThe(String maThe);

}
