package br.unipar.lancamentovenda.modelo;

public class Item {

    private Integer coditem;
    private String descricao;
    private Double vlunitario;

    public Item(){}

    public Item(Integer coditem, String descricao, Double vlunitario) {
        this.coditem = coditem;
        this.descricao = descricao;
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

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getVlunitario() {
        return vlunitario;
    }

    public void setVlunitario(Double vlunitario) {
        this.vlunitario = vlunitario;
    }
}
