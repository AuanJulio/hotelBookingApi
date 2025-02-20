package com.auanjulio.hotelbookingapi.modulos.usuario.service;

import com.auanjulio.hotelbookingapi.dao.role.TabRoleObj;
import com.auanjulio.hotelbookingapi.dao.role.repository.TabRoleRepository;
import com.auanjulio.hotelbookingapi.dao.usuario.TabUsuarioObj;
import com.auanjulio.hotelbookingapi.dao.usuario.dto.CreateUserDTO;
import com.auanjulio.hotelbookingapi.dao.usuario.dto.LoginUserDTO;
import com.auanjulio.hotelbookingapi.dao.usuario.repository.TabUsuarioRepository;
import com.auanjulio.hotelbookingapi.exceptions.EmailAlredyExistsException;
import com.auanjulio.hotelbookingapi.security.UserDetailsImpl;
import com.auanjulio.hotelbookingapi.security.configuration.SecurityConfiguration;
import com.auanjulio.hotelbookingapi.security.dto.RecoveryJwtTokenDTO;
import com.auanjulio.hotelbookingapi.security.service.JwtTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TabUsuarioService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenService jwtTokenService;

    @Autowired
    private TabUsuarioRepository tabUsuarioRepository;

    @Autowired
    private TabRoleRepository tabRoleRepository;

    @Autowired
    private SecurityConfiguration securityConfiguration;

    public RecoveryJwtTokenDTO authenticateUser(LoginUserDTO loginUserDTO) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                = new UsernamePasswordAuthenticationToken(loginUserDTO.txEmail(), loginUserDTO.txSenha());

        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        return new RecoveryJwtTokenDTO(jwtTokenService.gerarToken(userDetails));
    }

    public void createUser(CreateUserDTO createUserDTO) {

        if (tabUsuarioRepository.existsByTxEmail(createUserDTO.txEmail())) {
            throw new EmailAlredyExistsException();
        }

        var tabRoleObj = tabRoleRepository.findByTxNome(createUserDTO.role());

        TabUsuarioObj tabUsuarioNovoObj = TabUsuarioObj.builder()
                .txEmail(createUserDTO.txEmail())
                .txSenha(securityConfiguration.passwordEncoder().encode(createUserDTO.txSenha()))
                .tabRoleObj(List.of(tabRoleObj))
                .txNome(createUserDTO.txNome())
                .vlSaldo(0.0)
                .build();

        tabUsuarioRepository.saveAndFlush(tabUsuarioNovoObj);
    }
}
