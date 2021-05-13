package com.example.t5.api;

import java.util.List;

import com.example.t5.data.DauTuRepository;
import com.example.t5.model.DauTu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/invest" , produces = "application/json" )
public class DauTuController {
	@Autowired
    DauTuRepository dtRepo;

    @GetMapping
    public List<DauTu> getAll(){
        return (List<DauTu>) dtRepo.findAll();
    }

    @GetMapping("/{id}")
    public DauTu getAll(@PathVariable("id") int id){
        return dtRepo.findById(id).get();
    }

    @PostMapping
    public void save(@RequestBody DauTu dt){
    	dtRepo.save(dt);
    }

    @PutMapping("/{id}")
    public void update(@RequestBody DauTu dt) {
        dtRepo.save(dt);
    }
}
