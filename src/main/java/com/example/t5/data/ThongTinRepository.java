package com.example.t5.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.t5.model.ThongTinCaNhan;

@Repository
public interface ThongTinRepository extends CrudRepository<ThongTinCaNhan, Integer> {
}
