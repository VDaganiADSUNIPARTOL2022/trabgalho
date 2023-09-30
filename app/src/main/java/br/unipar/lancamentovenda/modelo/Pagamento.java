package br.unipar.lancamentovenda.modelo;

public class Pagamento {

    private Integer id;
    private String formaPgto;
    private Double acressimo;
    private Double desconto;
    private Double vlTotal;

    public Pagamento(){}

    public Pagamento(Integer id, String formaPgto, Double acressimo, Double desconto, Double vlTotal) {
        this.id = id;
        this.formaPgto = formaPgto;
        this.acressimo = acressimo;
        this.desconto = desconto;
        this.vlTotal = vlTotal;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFormaPgto() {
        return formaPgto;
    }

    public void setFormaPgto(String formaPgto) {
        this.formaPgto = formaPgto;
    }

    public Double getAcressimo() {
        return acressimo;
    }

    public void setAcressimo(Double acressimo) {
        this.acressimo = acressimo;
    }

    public Double getDesconto() {
        return desconto;
    }

    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }

    public Double getVlTotal() {
        return vlTotal;
    }

    public void setVlTotal(Double vlTotal) {
        this.vlTotal = vlTotal;
    }
}
