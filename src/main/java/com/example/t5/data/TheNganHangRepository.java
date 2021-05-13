package com.example.t5.data;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.t5.model.TheNganHang;

@Repository
public interface TheNganHangRepository extends CrudRepository<TheNganHang, Integer> {

	public TheNganHang findFirstByMaThe(String stk);
	public TheNganHang findFirstByTkIdAndStatus(int id, int status);
	public List<TheNganHang> findAllByStatus(int status);
}
