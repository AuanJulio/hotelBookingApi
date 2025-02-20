package com.auanjulio.hotelbookingapi.dao.usuario.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateUserDTO(

        @NotBlank(message = "E-mail campo obrigatório!")
        String txEmail,

        @NotBlank(message = "Senha campo obrigatório")
        String txSenha,

        @NotBlank(message = "Role campo obrigatório")
        String role,

        @NotBlank(message = "Nome campo obrigatório")
        @Size(max = 35, message = "Nome tamanho máximo de 35 caracteres")
        String txNome
) {
}
