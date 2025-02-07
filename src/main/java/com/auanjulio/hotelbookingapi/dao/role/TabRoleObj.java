package com.auanjulio.hotelbookingapi.dao.role;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "tab_role")
public class TabRoleObj {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cdRole;

    @Enumerated(EnumType.STRING)
    private RoleName roleName;
}
