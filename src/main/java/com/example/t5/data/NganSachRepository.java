package com.example.t5.data;

import com.example.t5.model.NganSach;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface NganSachRepository extends CrudRepository<NganSach, Integer> {
    @Query("select e from NganSach e where year(e.ngayTao) = ?1 and month(e.ngayTao) = ?2")
    public NganSach getByYearAndMonth(int year, int month);
}
