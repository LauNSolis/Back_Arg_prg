package com.portfolioargprg.lns.security.entity;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MainUser implements UserDetails {

    private String nombre;
    private String nombredeUsuario;
    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    // Constructor
    public MainUser(String nombre, String nombredeUsuario, String email, String password, Collection<? extends GrantedAuthority> authorities) {
        this.nombre = nombre;
        this.nombredeUsuario = nombredeUsuario;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    public static MainUser build(User usuario) {
        List<GrantedAuthority> authorities = usuario.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getRolName().name())).collect(Collectors.toList());
        return new MainUser(usuario.getNombre(), usuario.getNombredeUsuario(), usuario.getEmail(), usuario.getPassword(), authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return nombredeUsuario;
    }

    public String getNombre() {
        return nombre;
    }
    
    public String getEmail() {
        return email;
    }
    
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
