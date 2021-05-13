package com.example.t5.data;

import com.example.t5.model.DauTu;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DauTuRepository extends CrudRepository<DauTu, Integer> {

}
