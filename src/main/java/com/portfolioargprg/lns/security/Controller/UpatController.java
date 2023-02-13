package com.portfolioargprg.lns.security.Controller;

import com.portfolioargprg.lns.security.DTO.JwtDTO;
import com.portfolioargprg.lns.security.DTO.LoginUsuario;
import com.portfolioargprg.lns.security.DTO.NuevoUsuario;
import com.portfolioargprg.lns.security.JWT.JWTProvider;
import com.portfolioargprg.lns.security.Service.RoleService;
import com.portfolioargprg.lns.security.Service.UserService;
import com.portfolioargprg.lns.security.enouns.rolname;
import com.portfolioargprg.lns.security.entity.User;
import com.portfolioargprg.lns.security.entity.role;
import jakarta.validation.Valid;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/upat")
@CrossOrigin
public class UpatController {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;
    @Autowired
    JWTProvider jwtProvider;
    
    @PostMapping("/nuevo")
    public ResponseEntity<?> nuevo(@Valid @RequestBody NuevoUsuario nuevoUsuario, BindingResult bindingResults){
        if(bindingResults.hasErrors())
            return new ResponseEntity(new Msj ("No se acepta, verifique datos colocados"), HttpStatus.BAD_REQUEST); 
    
        if(userService.existsByNombreUsuario(nuevoUsuario.getNombreUsuario()))
            return new ResponseEntity(new Msj ("El nombre de usuario elegido ya está en uso"), HttpStatus.BAD_REQUEST); 
        
         if(userService.existsByEmail(nuevoUsuario.getEmail()))
            return new ResponseEntity(new Msj ("El correo electronico elegido ya está en uso"), HttpStatus.BAD_REQUEST);
    
         User usuario = new User(nuevoUsuario.getNombre(), nuevoUsuario.getNombreUsuario(), nuevoUsuario.getEmail(), passwordEncoder.encode(nuevoUsuario.getPassword()));
         
         Set<role> roles = new HashSet<>();
         roles.add(roleService.getByRolNombre(rolname.ROLE_USER).get());
         
         if(nuevoUsuario.getRoles().contains("admin"))roles.add(roleService.getByRolNombre(rolname.ROLE_USER).get());
         usuario.setRoles(roles);
         
         userService.save(usuario);
         return new ResponseEntity(new Msj("El usuario ha sido guardado"), HttpStatus.CREATED);
    } 
    @PostMapping("/login")
    public ResponseEntity<JwtDTO> login (@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult) {
        if(bindingResult.hasErrors())
            return new ResponseEntity(new Msj("Hubo un error"), HttpStatus.BAD_REQUEST);
        
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUsuario.getNombreUsuario(), loginUsuario.getPassword()));
        
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        
        JwtDTO jwtDTO = new JwtDTO (jwt, userDetails.getUsername(), userDetails.getAuthorities());
        
        return new ResponseEntity(jwtDTO, HttpStatus.OK);
    
    }
}
