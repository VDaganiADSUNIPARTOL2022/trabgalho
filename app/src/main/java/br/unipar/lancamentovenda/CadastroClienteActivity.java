package br.unipar.lancamentovenda;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import br.unipar.lancamentovenda.bancodedados.ClienteBD;
import br.unipar.lancamentovenda.modelo.Cliente;

public class CadastroClienteActivity extends AppCompatActivity {

    private EditText edNomeCliente;
    private EditText edCpfCliente;
    private Button btSalvar;
    private Cliente cliente;
    private ClienteBD clienteBD = new ClienteBD();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastrocliente);

        edNomeCliente = findViewById(R.id.edNomeCliente);
        edCpfCliente = findViewById(R.id.edCpfCliente);
        btSalvar = findViewById(R.id.btSalvar);

    btSalvar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
           cliente = new Cliente(clienteBD.getId(), edNomeCliente.getText().toString(), edCpfCliente.getText().toString());
           clienteBD.salvarCliente(cliente);
           Toast.makeText(CadastroClienteActivity.this, "Cliente Salvo com Sucesso", Toast.LENGTH_SHORT).show();
        }
    });
    }
}