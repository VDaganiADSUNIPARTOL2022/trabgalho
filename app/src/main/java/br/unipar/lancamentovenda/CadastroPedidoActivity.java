package br.unipar.lancamentovenda;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import br.unipar.lancamentovenda.bancodedados.ClienteBD;
import br.unipar.lancamentovenda.bancodedados.ItemBD;
import br.unipar.lancamentovenda.modelo.Cliente;
import br.unipar.lancamentovenda.modelo.Item;
import br.unipar.lancamentovenda.modelo.Pedido;

public class CadastroPedidoActivity extends AppCompatActivity {

    private Spinner spCliente;
    private List<Cliente> clientes  = new ArrayList<>();
    private ClienteBD clienteBD = new ClienteBD();
    private Pedido pedido = new Pedido();
    private ItemBD itemBD = new ItemBD();
    private List<Item> itens  = new ArrayList<>();
    private EditText edDescricao;
    private Button btPesquisar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastropedido);

        spCliente = findViewById(R.id.spCliente);
        edDescricao = findViewById(R.id.edDescricao);
        btPesquisar = findViewById(R.id.btPesquisar);

        carregarCliente();

        spCliente.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i>0){
                    pedido.setCliente(clientes.get(i-1));
                    Toast.makeText(CadastroPedidoActivity.this, pedido.getCliente().getNome(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btPesquisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pesquisarItem();
            }
        });
    }
    private void carregarCliente(){
        clientes = clienteBD.getClientes();
        String[]vetClientes = new String[clientes.size() + 1];
        vetClientes[0] = "Selecione o Cliente";
        for (int i = 0; i < clientes.size(); i++) {
            Cliente cliente = clientes.get(i);
            vetClientes[i+1] = cliente.getId() + " - " + cliente.getNome();
        }
        ArrayAdapter adapter = new ArrayAdapter(
                CadastroPedidoActivity.this,
                android.R.layout.simple_dropdown_item_1line,
                vetClientes);

        spCliente.setAdapter(adapter);
    }
     private void pesquisarItem(){
        pedido.getItens().add(itemBD.getItem(edDescricao.getText().toString()));
         Toast.makeText(CadastroPedidoActivity.this, pedido.getItens().get(0).getDescricao(), Toast.LENGTH_SHORT).show();
     }
}