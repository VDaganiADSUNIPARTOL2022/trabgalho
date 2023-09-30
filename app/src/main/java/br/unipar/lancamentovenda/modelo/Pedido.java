package br.unipar.lancamentovenda.modelo;

import java.util.ArrayList;
import java.util.List;

public class Pedido {

    private Integer coditem;
    private Cliente cliente;
    private List<Item> itens  = new ArrayList<>();
    private Pagamento pagamento;
    private Double valorTotal;

    public Pedido(){}

    public Pedido(Integer coditem, Cliente cliente, Pagamento pagamento, Double valorTotal) {
        this.coditem = coditem;
        this.cliente = cliente;
        this.pagamento = pagamento;
        this.valorTotal = valorTotal;
    }

    public Integer getCoditem() {
        return coditem;
    }

    public void setCoditem(Integer coditem) {
        this.coditem = coditem;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Item> getItens() {
        return itens;
    }

    public void addItem(Item item) {
        itens.add(item);
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }
}
