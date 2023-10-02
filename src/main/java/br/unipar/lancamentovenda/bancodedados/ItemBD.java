package br.unipar.lancamentovenda.bancodedados;

import java.util.ArrayList;
import java.util.List;

import br.unipar.lancamentovenda.modelo.Cliente;
import br.unipar.lancamentovenda.modelo.Item;

public class ItemBD {

    private static ArrayList<Item> itens  = new ArrayList<>();

    public ItemBD() {
        if (itens == null) {
            itens = new ArrayList<>();
        }

    }
    public Integer getId() {
        return itens.size()+1;
    }
    public void salvarItem(Item item){
        itens.add(item);
    }

    public ArrayList<Item> getItens() {
        return itens;
    }

    public Item getItem(String descricao){
     for (Item item:itens){
         if(item.getDescricao().equals(descricao)){
             return item;
         }
     }
     return null;
    }
}
