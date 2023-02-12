package com.portfolioargprg.lns.security.Controller;

import com.portfolioargprg.lns.security.JWT.JWTProvider;
import com.portfolioargprg.lns.security.Service.RoleService;
import com.portfolioargprg.lns.security.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
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
    
    public ResponseEntity<?> nuevo(@Valid @RequestBody NuevoUsuario nuevoUsuario, BindingResult bindingResults){
        if(bindingResults.hasErrors())
            return new ResponseEntity(new Mensaje ("No se acepta, verifique datos colocados"), HttpStatus.BAD_REQUEST); 
    
        if(userService.existsByNombreUsuario(nombredeUsuario.getNombreUsuario()))
            return new ResponseEntity(new Mensaje ("El nombre de usuario elegido ya está en uso"), HttpStatus.BAD_REQUEST); 
        
         if(userService.existsByEmail(email.getEmail()))
            return new ResponseEntity(new Mensaje ("El correo electronico elegido ya está en uso"), HttpStatus.BAD_REQUEST);
    
         Usuario usuario = new Usuario(nuevoUsuario.geetNombre(), nuevoUsuario.getNombreUsuario(), nuevoUsuario.getEmail(), passwordEncoder.encode(nuevoUsuario.getPassword()));
    } 
}
