package com.auanjulio.hotelbookingapi.modulos.auth.controller;

import com.auanjulio.hotelbookingapi.dao.usuario.dto.CreateUserDTO;
import com.auanjulio.hotelbookingapi.dao.usuario.dto.LoginUserDTO;
import com.auanjulio.hotelbookingapi.dao.usuario.repository.TabUsuarioRepository;
import com.auanjulio.hotelbookingapi.modulos.usuario.service.TabUsuarioService;
import com.auanjulio.hotelbookingapi.security.dto.RecoveryJwtTokenDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private TabUsuarioService tabUsuarioService;

    @PostMapping("/login")
    public ResponseEntity<RecoveryJwtTokenDTO> authenticateUser (@RequestBody @Valid LoginUserDTO loginUserDTO) {
        RecoveryJwtTokenDTO token = tabUsuarioService.authenticateUser(loginUserDTO);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<Void> createUser (@RequestBody @Valid CreateUserDTO createUserDTO) {
        tabUsuarioService.createUser(createUserDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
