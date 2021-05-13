package com.example.t5.data;

import com.example.t5.model.DoanhSo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import java.sql.Date;
import java.util.List;

@Repository
public interface DoanhSoRepository extends CrudRepository<DoanhSo, Integer> {
    public List<DoanhSo> findAllByNgayTaoBetween(Date startDate, Date endDate);
}
