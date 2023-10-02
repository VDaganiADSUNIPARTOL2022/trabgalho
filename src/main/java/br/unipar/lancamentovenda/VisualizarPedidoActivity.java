package br.unipar.lancamentovenda;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import br.unipar.lancamentovenda.bancodedados.PedidoBD;
import br.unipar.lancamentovenda.modelo.Pedido;

public class VisualizarPedidoActivity extends AppCompatActivity {

    private TextView tvListaPedidos;
    private EditText edNumPedido;
    private PedidoBD pedidoBD = new PedidoBD();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Pedidos");
        setContentView(R.layout.visualizarpedido);

        tvListaPedidos = findViewById(R.id.tvListaPedidos);
        edNumPedido = findViewById(R.id.edNumPedido);

        edNumPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirActivity(DetalhePedidoActivity.class);
            }
        });

        carregarPedidos();
    }
    private void abrirActivity(Class<?> activity){
        Intent intent = new Intent(VisualizarPedidoActivity.this, activity);
        intent.putExtra("codPedido", new Integer(edNumPedido.getText().toString()));
        startActivity(intent);
    }
    private void carregarPedidos() {
        ArrayList<Pedido> pedidos = pedidoBD.getPedidos();
        String texto = "";
        for (Pedido pedido : pedidos) {
            texto += pedido.getCodPedido() + " --- " + pedido.getCliente().getNome() + " --- " + pedido.getValorTotal() + "\n";
        }
        tvListaPedidos.setText(texto);
    }
}