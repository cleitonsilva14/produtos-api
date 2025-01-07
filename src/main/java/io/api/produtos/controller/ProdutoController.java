package io.api.produtos.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.api.produtos.dto.ProdutoDto;
import io.api.produtos.dto.ProdutoUpdateNomeDto;
import io.api.produtos.model.ProdutoModel;
import io.api.produtos.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.NO_CONTENT;

import java.util.List;
import java.util.Optional;

@Tag(name = "API produtos", description = "produtos api")
@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;
	
	@Operation(
            summary = "Busca todos os produtos",
            description = "Buscar todos os produtos cadastrados na tb_produtos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful")
    })
    @GetMapping(value = "", produces = "application/json")
	public ResponseEntity<List<ProdutoModel>> findAll() {
		return ResponseEntity.status(OK).body(produtoService.findAll());
	}
	
//	@GetMapping("/{id}")
//	public ResponseEntity<Optional<ProdutoModel>> findById(@PathVariable Long id){
//		return ResponseEntity.status(OK).body(produtoService.findById(id));
//	}
	
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Object> findByCodigo(@PathVariable Long codigo){
		
		Optional<ProdutoModel> produtoOptional = produtoService.findByCodigo(codigo);
		
		if(!produtoOptional.isPresent()) {
			return ResponseEntity.status(NOT_FOUND).body("Produto não encontrado");
		}
		
		return ResponseEntity.status(OK).body(produtoOptional.get());
	}
	
	@PostMapping
	public ResponseEntity<ProdutoModel> saveProduto(@Valid @RequestBody ProdutoDto produtoDto){
		ProdutoModel produto = new ProdutoModel();
		
		BeanUtils.copyProperties(produtoDto, produto);
		
		System.out.println(produtoDto.toString());

		return ResponseEntity.status(OK).body(produtoService.saveOne(produto));
	}
	
	@PatchMapping("/{codigo}")
	public ResponseEntity<Object> updateProduto(@PathVariable(value = "codigo") Long codigo, @RequestBody ProdutoUpdateNomeDto produtoDto){

		Optional<ProdutoModel> produtoOptional = produtoService.findByCodigo(codigo);
		
		if(produtoOptional.isEmpty()) {
			return ResponseEntity.status(NOT_FOUND).body("Produto não encontrado");
		}
		
		ProdutoModel produto = produtoOptional.get();
		
		if(produtoDto.nome().isBlank()) {
			return ResponseEntity.status(NO_CONTENT).body("Nada para ser alterado");
		}
		
		produto.setNome(produtoDto.nome());
		
		return ResponseEntity.status(OK).body(produtoService.saveOne(produto));
	}
		
	
}
