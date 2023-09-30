package br.unipar.lancamentovenda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btCadCliente;
    private Button btCadItem;
    private Button btCadPedido;
    private Button btVisPedido;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btCadCliente= findViewById(R.id.btCadCliente);
        btCadItem= findViewById(R.id.btCadItem);
        btCadPedido= findViewById(R.id.btCadPedido);
        btVisPedido= findViewById(R.id.btVisPedido);

        btCadCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirActivity(CadastroClienteActivity.class);
            }
        });
        btCadItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirActivity(CadastroItemActivity.class);
            }
        });
        btCadPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirActivity(CadastroPedidoActivity.class);
            }
        });
        btVisPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {abrirActivity(VisualizarPedidoActivity.class);
            }
        });
    }
    private void abrirActivity(Class<?> activity){
        Intent intent = new Intent(MainActivity.this, activity);
        startActivity(intent);
    }
}