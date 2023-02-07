
package com.portfolioargprg.lns.security.entity;

import java.util.Collection;




public class MainUser implements UserDetails{
    private String nombre;
    private String nombredeUsuario;
    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;
