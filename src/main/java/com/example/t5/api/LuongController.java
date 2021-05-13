package com.example.t5.api;

import java.util.List;

import com.example.t5.data.LuongRepository;
import com.example.t5.model.Luong;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping(path = "/salary" , produces = "application/json" )
public class LuongController {
	@Autowired
    LuongRepository luongRepo;

    @GetMapping
    public List<Luong> getAll(){
        return (List<Luong>) luongRepo.findAll();
    }

    @GetMapping("/{id}")
    public Luong getAll(@PathVariable("id") int id){
        return luongRepo.findById(id).get();
    }

    @PostMapping
    public void save(@RequestBody Luong luong){
    	luongRepo.save(luong);
    }

    @PutMapping("/{id}")
    public void update(@RequestBody Luong luong) {
        luongRepo.save(luong);
    }
}
