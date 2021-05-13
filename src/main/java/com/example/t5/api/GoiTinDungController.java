package com.example.t5.api;

import com.example.t5.data.GoiTinDungRepository;
import com.example.t5.model.GoiTinDung;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping(path = "/credit-type" , produces = "application/json" )
public class GoiTinDungController {
    @Autowired
    GoiTinDungRepository gtdRepo;

    @GetMapping
    public List<GoiTinDung> getAll(){
        return (List<GoiTinDung>) gtdRepo.findAll();
    }

    @GetMapping("/{id}")
    public GoiTinDung getAll(@PathVariable("id") int id){
        return gtdRepo.findById(id).get();
    }

    @PostMapping
    public void save(@RequestBody GoiTinDung gtd){
    	gtdRepo.save(gtd);
    }
}
