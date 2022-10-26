package io.github.brufz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.brufz.model.Servico;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Integer>{

}
