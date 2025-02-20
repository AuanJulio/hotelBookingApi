package com.auanjulio.hotelbookingapi.dao.role.repository;

import com.auanjulio.hotelbookingapi.dao.role.TabRoleObj;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TabRoleRepository extends JpaRepository<TabRoleObj, Integer> {

    TabRoleObj findByTxNome(String txNome);
}
