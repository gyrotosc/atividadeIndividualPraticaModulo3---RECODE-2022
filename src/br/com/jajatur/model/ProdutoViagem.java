package br.com.jajatur.model;

import java.util.Date;

public class ProdutoViagem {

	private int codProdutoViagem;
	private float valorPrevioProdutoViagem;
	private Date dataPartidaProdutoViagem = new Date();
	private String localPartidaProdutoViagem;
	
	public int getCodProdutoViagem() {
		return codProdutoViagem;
	}
	public void setCodProdutoViagem(int codProdutoViagem) {
		this.codProdutoViagem = codProdutoViagem;
	}
	public float getValorPrevioProdutoViagem() {
		return valorPrevioProdutoViagem;
	}
	public void setValorPrevioProdutoViagem(float valorPrevioProdutoViagem) {
		this.valorPrevioProdutoViagem = valorPrevioProdutoViagem;
	}
	public Date getDataPartidaProdutoViagem() {
		return dataPartidaProdutoViagem;
	}
	public void setDataPartidaProdutoViagem(Date dataPartidaProdutoViagem) {
		this.dataPartidaProdutoViagem = dataPartidaProdutoViagem;
	}
	public String getLocalPartidaProdutoViagem() {
		return localPartidaProdutoViagem;
	}
	public void setLocalPartidaProdutoViagem(String localPartidaProdutoViagem) {
		this.localPartidaProdutoViagem = localPartidaProdutoViagem;
	}
	
	
}
