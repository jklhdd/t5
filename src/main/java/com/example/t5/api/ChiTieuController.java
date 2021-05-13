package com.example.t5.api;

import java.util.List;

import com.example.t5.data.ChiTieuRepository;
import com.example.t5.model.ChiTieu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping(path = "/spend" , produces = "application/json" )
public class ChiTieuController {
	@Autowired
    ChiTieuRepository chitieuRepo;

    @GetMapping
    public List<ChiTieu> getAll(){
        return (List<ChiTieu>) chitieuRepo.findAll();
    }

    @GetMapping("/{id}")
    public ChiTieu getAll(@PathVariable("id") int id){
        return chitieuRepo.findById(id).get();
    }

    @PostMapping
    public void save(@RequestBody ChiTieu chitieu){
    	chitieuRepo.save(chitieu);
    }
}
