package com.auanjulio.hotelbookingapi.dao.usuario.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginUserDTO(

        @NotBlank(message = "E-mail campo obrigatório")
        String txEmail,

        @NotBlank(message = "Senha campo obrigatório")
        String txSenha
) {
}
