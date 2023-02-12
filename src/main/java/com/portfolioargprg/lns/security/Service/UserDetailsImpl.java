package com.portfolioargprg.lns.security.Service;

import com.portfolioargprg.lns.security.entity.MainUser;
import com.portfolioargprg.lns.security.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsImpl implements UserDetailsService {
    @Autowired
    UserService userService;

    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String nombredeUsuario) throws UsernameNotFoundException {
        User usuario = userService.getByNombredeUsuario(nombredeUsuario).get();
        return MainUser.build(usuario);
    }
    
}
