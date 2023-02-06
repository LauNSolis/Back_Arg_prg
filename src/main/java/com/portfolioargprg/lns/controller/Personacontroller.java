
package com.portfolioargprg.lns.controller;

import com.portfolioargprg.lns.entity.Persona;
import com.portfolioargprg.lns.interface_.IPersonaservice;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Personacontroller {
    @Autowired IPersonaservice ipersonaservice;

    @GetMapping ("persona/traer")
    public List<Persona> getPersona(){
    return ipersonaservice.getPersona();
    }
    
    @PostMapping("persona/crear")
    public String createPersona(@RequestBody Persona persona) {
    ipersonaservice.savePersona(persona);
    return "La persona fue creada de manera sastifactoria";
    }
    
    @DeleteMapping("persona/eliminar/{id}")
    public String deletePersona(@PathVariable Long id) {
    ipersonaservice.deletePersona(id);
    return "La persona fue eliminda de manera sastifactoria";
    }
    
    @PutMapping("persona/modificar/{id}")
    public Persona editPersona(@PathVariable Long id, @RequestParam("nombre") String nuevoNombre, @RequestParam("apellido") String nuevoApellido, @RequestParam("img") String nuevoImg) {
        Persona persona = ipersonaservice.findPersona(id);
        persona.setNombre(nuevoNombre);
        persona.setApellido(nuevoApellido);
        persona.setImg(nuevoImg);
    
        ipersonaservice.savePersona(persona);
        return persona;
    }
    
    @GetMapping("persona/traer/perfil")
    public Persona findPersona() {
        return ipersonaservice.findPersona((long)3);
    }
}
