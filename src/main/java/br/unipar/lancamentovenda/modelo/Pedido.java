package br.unipar.lancamentovenda.modelo;

import java.util.ArrayList;

public class Pedido {

    private Integer codPedido;
    private Cliente cliente;
    private ArrayList<ItemPedido> itens  = new ArrayList<>();
    private Pagamento pagamento;
    private Double valorTotal;

    public Pedido(){}

    public Pedido(Integer codPedido, Cliente cliente, Pagamento pagamento, Double valorTotal) {
        this.codPedido = codPedido;
        this.cliente = cliente;
        this.pagamento = pagamento;
        this.valorTotal = valorTotal;
    }

    public Integer getCodPedido() {
        return codPedido;
    }

    public void setCodPedido(Integer codPedido) {
        this.codPedido = codPedido;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public ArrayList<ItemPedido> getItens() {
        return itens;
    }

    public void addItem(ItemPedido item) {
        itens.add(item);
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }

    public Double getValorTotal() {
        Double valorTotal = 0.0;
        for (ItemPedido item : itens) {
            valorTotal += item.getVltotal();
        }
        return valorTotal;
    }

    public Integer getQtdItens() {
        Integer qtd = 0;
        for (ItemPedido item : itens) {
            qtd += item.getQtdItem();
        }
        return qtd;
    }
}
