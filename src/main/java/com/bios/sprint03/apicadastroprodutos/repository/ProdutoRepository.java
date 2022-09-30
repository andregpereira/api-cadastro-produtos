package com.bios.sprint03.apicadastroprodutos.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.bios.sprint03.apicadastroprodutos.model.Produto;

@Repository
public interface ProdutoRepository extends MongoRepository<Produto, String> {

}
