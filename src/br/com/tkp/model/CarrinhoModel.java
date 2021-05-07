package br.com.tkp.model;

public class CarrinhoModel {

	private int idDoProduto;
	private int quantidadeDeItensNoCarrinho;
	private double valorTotalPorItem;
	private ProdutoModel produtoModel;

	public CarrinhoModel() {

	}
	
	public CarrinhoModel(int idDoProduto, int quantidadeDeItensNoCarrinho, double valorTotalPorItem, ProdutoModel produtoModel) {
		super();
		this.idDoProduto = idDoProduto;
		this.quantidadeDeItensNoCarrinho = quantidadeDeItensNoCarrinho;
		this.valorTotalPorItem = valorTotalPorItem;
		this.produtoModel = produtoModel;
	}

	public int getIdDoProduto() {
		return idDoProduto;
	}
	
	public void setIdDoProduto(int idDoProduto) {
		this.idDoProduto = idDoProduto;
	}
	
	public int getQuantidadeDeItensNoCarrinho() {
		return quantidadeDeItensNoCarrinho;
	}
	
	public void setQuantidadeDeItensNoCarrinho(int quantidadeDeItensNoCarrinho) {
		this.quantidadeDeItensNoCarrinho = quantidadeDeItensNoCarrinho;
	}
	
	public double getValorTotalPorItem() {
		return valorTotalPorItem;
	}
	
	public void setValorTotalPorItem(double valorTotalPorItem) {
		this.valorTotalPorItem = valorTotalPorItem;
	}
	
	public ProdutoModel getProdutoModel() {
		return produtoModel;
	}
	
	public void setProdutoModel(ProdutoModel produtoModel) {
		this.produtoModel = produtoModel;
	}
}
