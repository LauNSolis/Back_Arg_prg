package com.portfolioargprg.lns.security.Service;

import com.portfolioargprg.lns.security.Repository.IUserRepository;
import com.portfolioargprg.lns.security.entity.User;
import jakarta.transaction.Transactional;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserService {
    @Autowired
    IUserRepository iuserRepository;

    public Optional<User> getByNombredeUsuario(String nombredeUsuario) {
        return iuserRepository.findByNombredeUsuario(nombredeUsuario);
    }
    public boolean existsByNombreUsuario(String nombredeUsuario) {
        return iuserRepository.existsByNombredeUsuario(nombredeUsuario);
    }
    public boolean existsByEmail(String email){
        return iuserRepository.existsByEmail(email);
    }
    public void save(User usuario){
        iuserRepository.save(usuario);
    }
}
