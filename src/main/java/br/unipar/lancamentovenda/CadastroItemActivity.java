package br.unipar.lancamentovenda;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import br.unipar.lancamentovenda.bancodedados.ItemBD;
import br.unipar.lancamentovenda.modelo.Cliente;
import br.unipar.lancamentovenda.modelo.Item;

public class CadastroItemActivity extends AppCompatActivity {
    private EditText edDescricaoItem;
    private EditText edValorItem;
    private Button btSalvar;
    private Item item;
    private ItemBD itemBD = new ItemBD();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Cadastro Item");
        setContentView(R.layout.cadastroitem);

        edDescricaoItem = findViewById(R.id.edDescricaoItem);
        edValorItem = findViewById(R.id.edValorItem);
        btSalvar = findViewById(R.id.btSalvar);
        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item = new Item(itemBD.getId(), edDescricaoItem.getText().toString(),new Double(edValorItem.getText().toString()));
                itemBD.salvarItem(item);
                edDescricaoItem.setText("");
                edValorItem.setText("");
                edDescricaoItem.requestFocus();
                Toast.makeText(CadastroItemActivity.this, "Item Salvo com Sucesso", Toast.LENGTH_SHORT).show();
            }
        });
    }
}