package com.example.t5.api;

import java.util.List;

import com.example.t5.data.RuiRoRepository;
import com.example.t5.model.RuiRo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping(path = "/risk" , produces = "application/json" )
public class RuiRoController {
	@Autowired
    RuiRoRepository rrRepo;

    @GetMapping
    public List<RuiRo> getAll(){
        return (List<RuiRo>) rrRepo.findAll();
    }

    @GetMapping("/{id}")
    public RuiRo getAll(@PathVariable("id") int id){
        return rrRepo.findById(id).get();
    }

    @PostMapping
    public void save(@RequestBody RuiRo rr){
    	rrRepo.save(rr);
    }

    @PutMapping("/{id}")
    public void update(@RequestBody RuiRo rr) {
        rrRepo.save(rr);
    }
}
