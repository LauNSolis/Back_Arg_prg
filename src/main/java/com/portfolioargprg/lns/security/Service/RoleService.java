package com.portfolioargprg.lns.security.Service;

import com.portfolioargprg.lns.security.Repository.IRoleRepository;
import com.portfolioargprg.lns.security.enouns.rolname;
import com.portfolioargprg.lns.security.entity.role;
import jakarta.transaction.Transactional;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class RoleService {
    @Autowired 
    IRoleRepository iroleRepository;
    
    public Optional<role> getByRolNombre(rolname rolNombre){
        return iroleRepository.findByRoleName(rolNombre);
    }
    public void save (role rol){
        iroleRepository.save(rol);
    }
}
