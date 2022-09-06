package br.com.jajatur.model;

import java.util.Date;

public class Contrata {

	private int codCliente;
	private int codProdutoViagem;
	private int codContrato;
	private Date dataAquisicaoProdutoViagem = new Date();
	
	public int getCodCliente() {
		return codCliente;
	}
	public void setCodCliente(int codCliente) {
		this.codCliente = codCliente;
	}
	public int getCodProdutoViagem() {
		return codProdutoViagem;
	}
	public void setCodProdutoViagem(int codProdutoViagem) {
		this.codProdutoViagem = codProdutoViagem;
	}
	public int getCodContrato() {
		return codContrato;
	}
	public void setCodContrato(int codContrato) {
		this.codContrato = codContrato;
	}
	public Date getDataAquisicaoProdutoViagem() {
		return dataAquisicaoProdutoViagem;
	}
	public void setDataAquisicaoProdutoViagem(Date dataAquisicaoProdutoViagem) {
		this.dataAquisicaoProdutoViagem = dataAquisicaoProdutoViagem;
	}
	
	
	
}
