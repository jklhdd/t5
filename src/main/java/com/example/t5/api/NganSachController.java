package com.example.t5.api;

import java.util.List;

import com.example.t5.data.NganSachRepository;
import com.example.t5.model.NganSach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping(path = "/budget" , produces = "application/json" )
public class NganSachController {
	@Autowired
    NganSachRepository nsRepo;

    @GetMapping
    public List<NganSach> getAll(){
        return (List<NganSach>) nsRepo.findAll();
    }

    @GetMapping("/{id}")
    public NganSach getAll(@PathVariable("id") int id){
        return nsRepo.findById(id).get();
    }

    @GetMapping("/{month}/{year}")
    public NganSach getAllByMonthAndYear(@PathVariable("month") int month, @PathVariable("year") int year){
        return nsRepo.getByYearAndMonth(year,month);
    }

    @PostMapping
    public void save(@RequestBody NganSach ns){
    	nsRepo.save(ns);
    }

    @PutMapping("/{id}")
    public void update(@RequestBody NganSach ns) {
        nsRepo.save(ns);
    }
}
