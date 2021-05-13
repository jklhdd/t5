package com.example.t5.data;


import com.example.t5.model.Luong;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LuongRepository extends CrudRepository<Luong, Integer> {

}
