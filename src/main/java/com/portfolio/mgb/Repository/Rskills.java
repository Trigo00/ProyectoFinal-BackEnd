/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.portfolio.mgb.Repository;

import com.portfolio.mgb.Entity.Skills;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Rskills extends JpaRepository<Skills, Integer> {
    Optional<Skills> findByNombre(String nombre);
    public boolean existsByNombre(String nombre);
}
