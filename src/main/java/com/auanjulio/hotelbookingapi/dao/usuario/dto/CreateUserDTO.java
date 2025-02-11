package com.auanjulio.hotelbookingapi.dao.usuario.dto;

import com.auanjulio.hotelbookingapi.dao.role.RoleName;

public record CreateUserDTO(
        String txEmail,
        String txSenha,
        RoleName role,
        String txNome
) {
}
