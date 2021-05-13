package com.example.t5.api;

import java.util.List;

import com.example.t5.data.ChiTieuKHRepository;
import com.example.t5.model.ChiTieuKH;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping(path = "/spend-customer" , produces = "application/json" )
public class ChiTieuKHController {
	@Autowired
    ChiTieuKHRepository chitieukhRepo;

    @GetMapping
    public List<ChiTieuKH> getAll(){
        return (List<ChiTieuKH>) chitieukhRepo.findAll();
    }

    @GetMapping("/{id}")
    public ChiTieuKH getAll(@PathVariable("id") int id){
        return chitieukhRepo.findById(id).get();
    }

    @PostMapping
    public void save(@RequestBody ChiTieuKH chitieukh){
    	chitieukhRepo.save(chitieukh);
    }
}
