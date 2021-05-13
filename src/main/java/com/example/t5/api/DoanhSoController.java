package com.example.t5.api;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import com.example.t5.data.DoanhSoRepository;
import com.example.t5.model.DoanhSo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;




@RestController
@RequestMapping(path = "/sale" , produces = "application/json" )
public class DoanhSoController {
	@Autowired
    DoanhSoRepository dsRepo;

    @GetMapping
    public List<DoanhSo> getAll(){
        return (List<DoanhSo>) dsRepo.findAll();
    }

    @GetMapping("/{date_start}/{date_end}")
    public List<DoanhSo> getBetweenDate(@PathVariable("date_start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateStart,
                                        @PathVariable("date_end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateEnd){
        Date sd = Date.valueOf(dateStart);
        Date ed = Date.valueOf(dateEnd);
        return dsRepo.findAllByNgayTaoBetween(sd,ed);
    }

    @GetMapping("/{id}")
    public DoanhSo getAll(@PathVariable("id") int id){
        return dsRepo.findById(id).get();
    }

    @PostMapping
    public void save(@RequestBody DoanhSo ds){
    	dsRepo.save(ds);
    }
}
