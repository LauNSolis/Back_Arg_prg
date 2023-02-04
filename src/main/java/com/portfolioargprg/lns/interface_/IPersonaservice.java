
package com.portfolioargprg.lns.interface_;

import com.portfolioargprg.lns.entity.Persona;
import java.util.List;


public interface IPersonaservice {
    //metdo para traer una lista de personas
    public List<Persona> getPersona();
    
    // Guardar una nueva persona
    public void savePersona(Persona persona);
            
    // Para elimnar un usuario buscansdo por id
    public void deletePersona(Long id);
    
    // Para buscar una persona por id
    public Persona findPersona(Long id);
}
