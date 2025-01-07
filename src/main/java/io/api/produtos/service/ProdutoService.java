package io.api.produtos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.api.produtos.model.ProdutoModel;
import io.api.produtos.repository.ProdutoRepository;
import jakarta.transaction.Transactional;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	public List<ProdutoModel> findAll(){
		return produtoRepository.findAll();
	}
	
	public Optional<ProdutoModel> findById(Long id) {
		return produtoRepository.findById(id);
	}
	
	public Optional<ProdutoModel> findByCodigo(Long codigo){
		return produtoRepository.findByCodigo(codigo);
	}
	
	@Transactional
	public ProdutoModel saveOne(ProdutoModel produto) {
		return produtoRepository.save(produto);
	}
	
	@Transactional
	public List<ProdutoModel> saveAll(List<ProdutoModel> produtos){
		return produtoRepository.saveAll(produtos);
	}
	
	
	
}
