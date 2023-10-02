package br.unipar.lancamentovenda.modelo;

public class Pagamento {

    private Integer id;
    private String formaPgto;
    private Double acressimo;
    private Double desconto;
    private Double vlTotalBruto;
    private Double vlTotalLiquido;
    private Integer qtdParcelas;

    public Pagamento(){}

    public Pagamento(Integer id, String formaPgto, Double acressimo, Double desconto, Double vlTotalBruto, Integer qtdParcelas) {
        this.id = id;
        this.formaPgto = formaPgto;
        this.acressimo = acressimo;
        this.desconto = desconto;
        this.vlTotalBruto = vlTotalBruto;
        this.qtdParcelas = qtdParcelas;
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

    public Double getVlTotalBruto() {
        return vlTotalBruto;
    }

    public void setVlTotalBruto(Double vlTotalBruto) {
        this.vlTotalBruto = vlTotalBruto;
    }

    public Double getVlTotalLiquido() {
        if (acressimo != null) {
            return vlTotalBruto + (vlTotalBruto * acressimo);
        } else if (desconto != null) {
            return vlTotalBruto - (vlTotalBruto * desconto);
        }
        return null;
    }

    public Integer getQtdParcelas() {
        return qtdParcelas;
    }

    public void setQtdParcelas(Integer qtdParcelas) {
        this.qtdParcelas = qtdParcelas;
    }
}
