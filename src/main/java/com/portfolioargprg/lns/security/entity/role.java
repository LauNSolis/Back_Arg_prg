
package com.portfolioargprg.lns.security.entity;

import com.portfolioargprg.lns.security.enouns.rolname;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

@Entity
public class role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    @Enumerated(EnumType.STRING)
    private rolname rolName;

    //Acá hay un constructor 
    public role() {
    }

    public role(rolname rolName) {
        this.rolName = rolName;
    }
    
    // Acá hay getters y setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public rolname getRolName() {
        return rolName;
    }

    public void setRolName(rolname rolName) {
        this.rolName = rolName;
    }
    
}
