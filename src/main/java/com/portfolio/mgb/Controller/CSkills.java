/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.mgb.Controller;

import com.portfolio.mgb.Dto.dtoSkills;
import com.portfolio.mgb.Entity.Skills;
import com.portfolio.mgb.Security.Controller.Mensaje;
import com.portfolio.mgb.Service.Sskills;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/skill")
public class CSkills {
    @Autowired 
    Sskills sskills;
    
    @GetMapping("/lista")
    public ResponseEntity<List<Skills>> list(){
        List<Skills> list = sskills.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoSkills dtoskills){
       if(StringUtils.isBlank(dtoskills.getNombre())) 
           return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
       if(sskills.existsByNombre(dtoskills.getNombre()))
           return new ResponseEntity( new Mensaje("Esa skill ya existe"), HttpStatus.BAD_REQUEST);
       
       Skills SkyLls = new Skills(dtoskills.getNombre(), dtoskills.getPorcentaje());
       sskills.save(SkyLls);
       
       return new ResponseEntity(new Mensaje("Skill agregada"), HttpStatus.OK);
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoSkills dtoskills){
        if(!sskills.existsById(id))
            return new ResponseEntity(new Mensaje("El id no existe"), HttpStatus.BAD_REQUEST);
        
        if(sskills.existsByNombre(dtoskills.getNombre()) && sskills.getByNombre(dtoskills.getNombre()).get().getId() != id)
            return new ResponseEntity(new Mensaje("Esa skill ya existe"), HttpStatus.BAD_REQUEST);
        
        if(StringUtils.isBlank(dtoskills.getNombre()))
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST); 
        
        Skills SkiLls = sskills.getOne(id).get();
        SkiLls.setNombre(dtoskills.getNombre());
        SkiLls.setPorcentaje(dtoskills.getPorcentaje());
        
        sskills.save(SkiLls);
        return new ResponseEntity(new Mensaje("Skill actualizada"), HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!sskills.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        }
        sskills.delete(id);
        return new ResponseEntity(new Mensaje("Skill eliminada"), HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Skills> getById(@PathVariable("id") int id){
        if(!sskills.existsById(id))
            return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        Skills SkiLls = sskills.getOne(id).get();
        return new ResponseEntity(SkiLls, HttpStatus.OK);
    }
}
