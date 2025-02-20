package com.auanjulio.hotelbookingapi.dao.role;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "tab_role")
public class TabRoleObj {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cd_role")
    private Integer cdRole;

    @Column(name = "tx_nome", unique = true, nullable = false)
    private String txNome;

    public enum Values {
        ROLE_ADMIN(1),
        ROLE_CUSTOMER(2);

        Integer cdRole;

        Values(Integer cdRole) {
            this.cdRole = cdRole;
        }

        public Integer getCdRole() {
            return cdRole;
        }
    }
}
