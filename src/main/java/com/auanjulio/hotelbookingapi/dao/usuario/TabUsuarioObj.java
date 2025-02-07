package com.auanjulio.hotelbookingapi.dao.usuario;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tab_usuario")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TabUsuarioObj {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cd_usuario", unique = true, nullable = false)
    private Integer cdUsuario;

    @Column(name = "tx_nome")
    private String txNome;

    @Column(name = "tx_email")
    private String txEmail;

    @Column(name = "tx_senha")
    private String txSenha;

    @Column(name = "vl_saldo")
    private Double vlSaldo;
}
