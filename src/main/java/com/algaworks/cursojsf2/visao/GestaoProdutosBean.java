package com.algaworks.cursojsf2.visao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import com.algaworks.cursojsf2.dominio.Produto;

@ManagedBean(name = "gestaoProdutosBean")
@SessionScoped
public class GestaoProdutosBean implements Serializable {

	private String nomePesquisa;
	private List<Produto> produtos;
	private List<Produto> nomeFiltrados;
	private Produto produto;
	private Produto produtoSelecionado;
	
	public GestaoProdutosBean() {
		this.produtos = new ArrayList<Produto>();
		this.nomeFiltrados = new ArrayList<Produto>();
		this.produto = new Produto();
	}

	public String obterAjuda() {
		if(this.produtos.isEmpty()) {
			return "AjudaGestaoProdutos?faces-redirect=true";
		} else {
			return "AjudaGestaoProdutosTelefone?faces-redirect=true";
		}
	}
	
	public void nomePesquisaAlterada(ValueChangeEvent event) {
		this.nomeFiltrados.clear();
		for(Produto produto : this.produtos) {
			if(produto.getNome().toLowerCase().startsWith(event.getNewValue().toString().toLowerCase())) {
				this.nomeFiltrados.add(produto);
			}
		}
	}
	
	public void verificarInclusao(ActionEvent event) {
		if("".equals(this.produto.getFabricante())) {
			this.produto.setFabricante("Sem fabricante");
		}
	}
	
	public void incluir() {
		this.produtos.add(this.produto);
		this.produto = new Produto();
	}
	
	public void excluir() {
		this.produtos.remove(this.produtoSelecionado);
	}
	
	@PostConstruct
	public void inicializar() {
		System.out.println("Inicializou bean");
	}
	
	@PreDestroy
	public void finalizar() {
		System.out.println("Finalizou bean");
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public Produto getProduto() {
		return produto;
	}
	
	public Produto getProdutoSelecionado() {
		return produtoSelecionado;
	}

	public void setProdutoSelecionado(Produto produtoSelecionado) {
		this.produtoSelecionado = produtoSelecionado;
	}
	
	public String getNomePesquisa() {
		return nomePesquisa;
	}

	public void setNomePesquisa(String nomePesquisa) {
		this.nomePesquisa = nomePesquisa;
	}	
	
	public List<Produto> getNomeFiltrados() {
		return nomeFiltrados;
	}
	
}
