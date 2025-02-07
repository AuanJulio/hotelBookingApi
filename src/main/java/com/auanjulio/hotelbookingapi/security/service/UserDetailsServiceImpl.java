package com.auanjulio.hotelbookingapi.security.service;

import com.auanjulio.hotelbookingapi.dao.usuario.TabUsuarioObj;
import com.auanjulio.hotelbookingapi.dao.usuario.repository.TabUsuarioRepository;
import com.auanjulio.hotelbookingapi.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private TabUsuarioRepository tabUsuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        TabUsuarioObj tabUsuarioObj = tabUsuarioRepository.findByTxEmail(username)
                .orElseThrow(() -> new RuntimeException("Usuário no encontrado"));
        return new UserDetailsImpl(tabUsuarioObj);
    }
}
