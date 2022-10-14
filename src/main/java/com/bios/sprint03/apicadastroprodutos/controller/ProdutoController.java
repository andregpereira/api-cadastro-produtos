package com.bios.sprint03.apicadastroprodutos.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin(origins = "http://localhost:8084")
@RequestMapping("/produto")
public class ProdutoController {

	@Autowired
	private ProdutoRepository produtoRepository;

	@GetMapping("/{id}")
	public ResponseEntity<?> lerProduto(@PathVariable String id) {
		if (!produtoRepository.existsById(id)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado!");
		}

		Optional<Produto> produto = produtoRepository.findById(id);

		return produto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
	}

	@GetMapping("/lista")
	public ResponseEntity<List<Produto>> listarProdutos() {
		List<Produto> listaProdutos = produtoRepository.findAll();

		return ResponseEntity.status(HttpStatus.OK).body(listaProdutos);
	}

	@PostMapping
	public ResponseEntity<Produto> criarProduto(@RequestBody Produto produto) {
		Produto produtoCriado = produtoRepository.save(produto);

		return ResponseEntity.status(HttpStatus.OK).body(produtoCriado);
	}

	@PutMapping
	public ResponseEntity<?> atualizarProduto(@RequestBody Produto produtoAtualizado) {
		if (!produtoRepository.existsById(produtoAtualizado.getId())) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado!");
		}

		Optional<Produto> produto = produtoRepository.findById(produtoAtualizado.getId());

		produtoAtualizado.setId(produto.get().getId());

		produtoRepository.save(produtoAtualizado);

		return ResponseEntity.status(HttpStatus.OK).body(produtoAtualizado);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletarProduto(@PathVariable String id) {
		if (!produtoRepository.existsById(id)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado!");
		}

		produtoRepository.deleteById(id);

		return ResponseEntity.status(HttpStatus.OK).body("Produto deletado!");
	}

}
