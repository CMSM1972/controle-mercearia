package com.mercearia.controle_mercearia.repository;

import com.mercearia.controle_mercearia.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}