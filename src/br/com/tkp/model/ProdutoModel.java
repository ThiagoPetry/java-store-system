package br.com.tkp.model;

public class ProdutoModel {

	// Atributos
	private String nomeProd;
	private double precoProd;
	private int qtdProd;
	private double saldoEstoque;

	// Construtores
	public ProdutoModel() {

	}

	public ProdutoModel(String nomeProd, double precoProd, int qtdProd, double saldoEstoque) {
		super();
		this.nomeProd = nomeProd;
		this.precoProd = precoProd;
		this.qtdProd = qtdProd;
		this.saldoEstoque = saldoEstoque;
	}

	// Métodos
	public String getNomeProd() {
		return nomeProd;
	}

	public void setNomeProd(String nomeProd) {
		this.nomeProd = nomeProd;
	}

	public double getPrecoProd() {
		return precoProd;
	}

	public void setPrecoProd(double precoProd) {
		this.precoProd = precoProd;
	}

	public int getQtdProd() {
		return qtdProd;
	}

	public void setQtdProd(int qtdProd) {
		this.qtdProd = qtdProd;
	}

	public double getSaldoEstoque() {
		return saldoEstoque;
	}

	public void setSaldoEstoque(double saldoEstoque) {
		this.saldoEstoque = saldoEstoque;
	}
}
