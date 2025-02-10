package com.auanjulio.hotelbookingapi.dao.usuario.repository;

import com.auanjulio.hotelbookingapi.dao.usuario.TabUsuarioObj;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TabUsuarioRepository extends JpaRepository<TabUsuarioObj, Integer> {

    Optional<TabUsuarioObj> findByTxEmail(String txEmail);
}
