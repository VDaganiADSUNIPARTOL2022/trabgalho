package br.unipar.lancamentovenda.bancodedados;

import java.util.ArrayList;
import java.util.List;

import br.unipar.lancamentovenda.modelo.Cliente;

public class ClienteBD {

    private static List<Cliente> clientes  = new ArrayList<>();

    public ClienteBD() {
        if (clientes == null) {
            clientes = new ArrayList<>();
        }
    }

    public Integer getId() {
        return clientes.size()+1;
    }

    public void salvarCliente(Cliente cliente){
        clientes.add(cliente);
    }
    public List<Cliente> getClientes(){
        return clientes;
    }

}
