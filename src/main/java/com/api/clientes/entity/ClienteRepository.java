package com.api.clientes.entity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {


   // Consulta jpql
   @Query("SELECT e FROM Cliente e WHERE e.email = :email ")
   Optional<Cliente> buscarPorEmail(@Param("email") String email);

   @Query("SELECT c FROM Cliente c WHERE c.id =:id")
   Cliente buscaPorId(@Param("id")Long id);

   @Query("FROM Cliente c " +
           "WHERE LOWER(c.nome) like %:busca% " +
           "OR LOWER(c.email) like %:busca% ")
   Page<Cliente> buscaPaginada(
           @Param("busca") String busca,
           Pageable paginavel);



   // Consulta nativa
   // Consulta direta do banco
   @Modifying
   @Transactional
   @Query(value = "DELETE FROM clientes c WHERE c.id = ?1", nativeQuery = true)
   void excluirCliente(@Param("id")Long id);
}
