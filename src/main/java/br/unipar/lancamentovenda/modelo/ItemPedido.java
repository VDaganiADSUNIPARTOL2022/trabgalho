package br.unipar.lancamentovenda.modelo;

public class ItemPedido {

    private Integer coditem;
    private String descricao;
    private Integer qtdItem;
    private Double vltotal;
    private Double vlunitario;

    public ItemPedido(){}

    public ItemPedido(Integer coditem, String descricao, Integer qtdItem, Double vlunitario) {
        this.coditem = coditem;
        this.descricao = descricao;
        this.qtdItem = qtdItem;
        this.vlunitario = vlunitario;
    }

    public Integer getCoditem() {
        return coditem;
    }

    public void setCoditem(Integer coditem) {
        this.coditem = coditem;
    }

    public String getDescricao() {
        return descricao;
    }

    public Integer getQtdItem() {
        return qtdItem;
    }

    public void setQtdItem(Integer qtdItem) {
        this.qtdItem = qtdItem;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getVltotal() {
        return qtdItem * vlunitario;
    }

    public Double getVlunitario() {
        return vlunitario;
    }

    public void setVlunitario(Double vlunitario) {
        this.vlunitario = vlunitario;
    }
}
