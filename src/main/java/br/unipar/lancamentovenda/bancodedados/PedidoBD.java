package br.unipar.lancamentovenda.bancodedados;

import java.util.ArrayList;

import br.unipar.lancamentovenda.modelo.Cliente;
import br.unipar.lancamentovenda.modelo.Pedido;

public class PedidoBD {

    private static ArrayList<Pedido> pedidos  = new ArrayList<>();

    public PedidoBD() {
        if (pedidos == null) {
            pedidos = new ArrayList<>();
        }
    }

    public Integer getId() {
        return pedidos.size()+1;
    }

    public Pedido getPedido(Integer codPedido){
        for (Pedido pedido : pedidos) {
            if (pedido.getCodPedido().equals(codPedido)) {
                return pedido;
            }
        }
        return null;
    }
    public void salvarPedido(Pedido pedido){
        pedidos.add(pedido);
    }
    public ArrayList<Pedido> getPedidos(){
        return pedidos;
    }

}
