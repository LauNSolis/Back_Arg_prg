
package com.portfolioargprg.lns.repository;

import com.portfolioargprg.lns.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPersonarepository extends JpaRepository<Persona, Long> {
    
}
