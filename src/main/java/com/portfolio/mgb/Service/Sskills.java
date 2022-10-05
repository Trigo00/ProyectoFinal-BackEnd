/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.mgb.Service;

import com.portfolio.mgb.Entity.Skills;
import com.portfolio.mgb.Repository.Rskills;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class Sskills {
    @Autowired
    Rskills rskills;
    
    public List<Skills> list(){
        return rskills.findAll();
    }
    
    public Optional<Skills> getOne(int id){
        return rskills.findById(id);
    }
    
    public Optional<Skills> getByNombre(String nombre){
        return rskills.findByNombre(nombre);
    }
    
    public void save (Skills zkill){
        rskills.save(zkill);
    }
    
    public void delete(int id){
        rskills.deleteById(id);
    }
    
    public boolean existsById(int id){
        return rskills.existsById(id);
    }
    
    public boolean existsByNombre(String nombre){
        return rskills.existsByNombre(nombre);
    }
}
