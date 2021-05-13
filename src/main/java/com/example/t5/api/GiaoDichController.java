package com.example.t5.api;

import java.util.List;

import com.example.t5.data.GiaoDichRepository;
import com.example.t5.model.GiaoDich;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "/giaodich" , produces = "application/json" )
public class GiaoDichController {
	@Autowired
    GiaoDichRepository giaoDichRepository;

    @GetMapping
    public List<GiaoDich> getAll(){
        return (List<GiaoDich>) giaoDichRepository.findAll();
    }

    @GetMapping("/{customer}")
    public List<GiaoDich> getCustomerHistory(@PathVariable("customer") int id){
        return giaoDichRepository.findAllByTkId(id);
    }

    @PostMapping
    public void save(@RequestBody GiaoDich giaoDich){
    	giaoDichRepository.save(giaoDich);
    }

}
