
package com.portfolioargprg.lns.service;

import com.portfolioargprg.lns.entity.Persona;
import com.portfolioargprg.lns.interface_.IPersonaservice;
import com.portfolioargprg.lns.repository.IPersonarepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImpPersonaservice implements IPersonaservice{
@Autowired IPersonarepository ipersonarepository;
    @Override
    public List<Persona> getPersona() {
    List<Persona> persona  = ipersonarepository.findAll();
    return persona;
    }

    @Override
    public void savePersona(Persona persona) {
        ipersonarepository.save(persona);
    }

    @Override
    public void deletePersona(Long id) {
        ipersonarepository.deleteById(id);
    }

    @Override
    public Persona findPersona(Long id) {
        Persona persona = ipersonarepository.findById(id).orElse(null);
        return persona;
    }
    
}
