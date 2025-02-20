package com.auanjulio.hotelbookingapi.security;

import com.auanjulio.hotelbookingapi.dao.usuario.TabUsuarioObj;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class UserDetailsImpl implements UserDetails {

    private TabUsuarioObj tabUsuarioObj;

    public UserDetailsImpl(TabUsuarioObj tabUsuarioObj) {
        this.tabUsuarioObj = tabUsuarioObj;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return tabUsuarioObj.getTabRoleObj()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getTxNome()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return tabUsuarioObj.getTxSenha();
    }

    @Override
    public String getUsername() {
        return tabUsuarioObj.getTxEmail();
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
