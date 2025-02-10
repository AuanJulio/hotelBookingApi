package com.auanjulio.hotelbookingapi.dao.usuario.dto;

import com.auanjulio.hotelbookingapi.dao.role.TabRoleObj;

import java.util.List;

public record RecoveryUserDTO(
        Integer cdUsuario,
        String txEmail,
        List<TabRoleObj> tabRoleObj
) {
}
