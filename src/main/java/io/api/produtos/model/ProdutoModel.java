package io.api.produtos.model;

import java.math.BigDecimal;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_produto")
public class ProdutoModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "codigo", unique = true)
	private Long codigo;
	
	@Column(name = "nome", length = 255)
	private String nome;
	
	@Column(name = "preco")
	private BigDecimal preco;
	
	@PrePersist
    public void prePersist() {
        if (this.preco == null) {
            this.preco = BigDecimal.ZERO;
        }
    }

	
	@Override
	public String toString() {
		return "ProdutoModel [produtoId=" + id + ", codigo=" + codigo + ", nome=" + nome + ", preco=" + preco
				+ "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	

	public Long getProdutoId() {
		return id;
	}


	public Long getCodigo() {
		return codigo;
	}


	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public BigDecimal getPreco() {
		return preco;
	}


	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProdutoModel other = (ProdutoModel) obj;
		return Objects.equals(id, other.id);
	}

	
}

