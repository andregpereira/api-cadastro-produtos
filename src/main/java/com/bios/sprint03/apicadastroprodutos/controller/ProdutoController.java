package com.bios.sprint03.apicadastroprodutos.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bios.sprint03.apicadastroprodutos.model.Produto;
import com.bios.sprint03.apicadastroprodutos.repository.ProdutoRepository;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

	@Autowired
	private ProdutoRepository produtoRepository;

	@GetMapping("/{id}")
	public ResponseEntity<Produto> lerProduto(@PathVariable String id) {
		Optional<Produto> produto = produtoRepository.findById(id);

		return produto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
	}

	@GetMapping("/lista")
	public ResponseEntity<List<Produto>> listarProdutos() {
		List<Produto> listaProdutos = produtoRepository.findAll();

		return new ResponseEntity<List<Produto>>(listaProdutos, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Produto> criarProduto(@RequestBody Produto produto) {
//		Produto produto = new Produto();

		return new ResponseEntity<Produto>(produto, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Produto> atualizarProduto(@PathVariable String id) {
		Optional<Produto> produto = produtoRepository.findById(id);

		return produto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletarProduto(@PathVariable String id) {
		produtoRepository.deleteById(id);
		return new ResponseEntity<String>("Produto deletado!", HttpStatus.OK);
	}

}
