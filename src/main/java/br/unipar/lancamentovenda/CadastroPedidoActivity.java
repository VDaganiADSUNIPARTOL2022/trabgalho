package br.unipar.lancamentovenda;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import br.unipar.lancamentovenda.bancodedados.ClienteBD;
import br.unipar.lancamentovenda.bancodedados.ItemBD;
import br.unipar.lancamentovenda.bancodedados.PedidoBD;
import br.unipar.lancamentovenda.modelo.Cliente;
import br.unipar.lancamentovenda.modelo.Item;
import br.unipar.lancamentovenda.modelo.ItemPedido;
import br.unipar.lancamentovenda.modelo.Pagamento;
import br.unipar.lancamentovenda.modelo.Pedido;

public class CadastroPedidoActivity extends AppCompatActivity {

    private Spinner spCliente;
    private Spinner spQtdParcelas;
    private TextView tvDescricao;
    private TextView tvVlunitario;
    private TextView tvVlTotalItem;
    private TextView tvListaItens;
    private TextView tvQtItens;
    private TextView tvVlTotalPedido;
    private TextView tvParcelas;
    private RadioGroup rgFormaPagamento;
    private RadioButton rbVista;
    private RadioButton rbPrazo;
    private EditText edQtdItem;
    private Button btAddPedido;
    private Button btSalvar;
    private ArrayList<Cliente> clientes  = new ArrayList<>();
    private ClienteBD clienteBD = new ClienteBD();
    private Pedido pedido = new Pedido();
    private ItemBD itemBD = new ItemBD();
    private ArrayList<Item> itens  = new ArrayList<>();
    private AutoCompleteTextView actvDescricao;
    private Item item = new Item();
    private Pagamento pagamento;
    private ArrayList<String> qtdParcelas;
    private PedidoBD pedidoBD = new PedidoBD();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Lançamento Venda");
        setContentView(R.layout.cadastropedido);

        spCliente = findViewById(R.id.spCliente);
        actvDescricao = findViewById(R.id.actvDescricao);
        tvDescricao = findViewById(R.id.tvDescricao);
        edQtdItem = findViewById(R.id.edQtdItem);
        tvVlunitario = findViewById(R.id.tvVlunitario);
        tvVlTotalItem = findViewById(R.id.tvVlTotalItem);
        btAddPedido = findViewById(R.id.btAddPedido);
        tvListaItens = findViewById(R.id.tvListaItens);
        tvQtItens = findViewById(R.id.tvQtItens);
        tvVlTotalPedido = findViewById(R.id.tvVlTotalPedido);
        rgFormaPagamento = findViewById(R.id.rgFormaPagamento);
        rbVista = findViewById(R.id.rbVista);
        rbPrazo = findViewById(R.id.rbPrazo);
        tvParcelas = findViewById(R.id.tvParcelas);
        btSalvar = findViewById(R.id.btSalvar);
        spQtdParcelas = findViewById(R.id.spQtdParcelas);

        carregarCliente();
        carregarItens();
        carregarParcelas();

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
        actvDescricao.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                item = itens.get(i);
                carregarItem();
            }
        });
        edQtdItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvVlTotalItem.setText(String.valueOf(new Integer(edQtdItem.getText().toString()) * new Double(tvVlunitario.getText().toString())));
            }
        });
        btAddPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pedido.addItem(new ItemPedido(item.getCoditem(), item.getDescricao(), new Integer(edQtdItem.getText().toString()), item.getVlunitario()));
                String itens = "";
                for (ItemPedido item : pedido.getItens()) {
                    itens += item.getCoditem() + " --- " + item.getDescricao() + " --- " + item.getQtdItem() + " --- " + item.getVlunitario() + " --- " + item.getVltotal() + "\n";
                }
                tvListaItens.setText(itens);
                tvQtItens.setText(pedido.getQtdItens().toString());
                tvVlTotalPedido.setText(pedido.getValorTotal().toString());
                atualizarFormaPagamentoAvista();
            }
        });

        rgFormaPagamento.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == rbVista.getId()) {
                    carregarParcelas();
                    if (!pedido.getItens().isEmpty()) {
                        atualizarFormaPagamentoAvista();
                    }
                } else if (i == rbPrazo.getId()) {
                    spQtdParcelas.setEnabled(true);
                    atualizarFormaPagamentoAprazo(1);
                }
            }
        });
        spQtdParcelas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                atualizarFormaPagamentoAprazo(new Integer(qtdParcelas.get(i)));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pedido.setCodPedido(pedidoBD.getId());
                pedidoBD.salvarPedido(pedido);
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
    private void carregarItens() {
        itens = itemBD.getItens();
        String[]vetItens = new String[itens.size()];
        for (int i = 0; i < itens.size(); i++) {
            Item item = itens.get(i);
            vetItens[i] = item.getCoditem() + " - " + item.getDescricao();
        }
        ArrayAdapter adapter = new ArrayAdapter(
                CadastroPedidoActivity.this,
                android.R.layout.simple_dropdown_item_1line,
                vetItens);

        actvDescricao.setAdapter(adapter);
    }

    private void carregarParcelas() {
        qtdParcelas = new ArrayList<>();
        qtdParcelas.add("1");
        qtdParcelas.add("2");
        qtdParcelas.add("3");
        qtdParcelas.add("4");
        qtdParcelas.add("5");
        qtdParcelas.add("6");
        ArrayAdapter adapter = new ArrayAdapter(
                CadastroPedidoActivity.this,
                android.R.layout.simple_dropdown_item_1line,
                qtdParcelas);
        spQtdParcelas.setAdapter(adapter);
        spQtdParcelas.setEnabled(false);
    }

    private void carregarItem() {
        tvDescricao.setText(item.getDescricao());
        tvVlunitario.setText(item.getVlunitario().toString());
    }
    private void atualizarFormaPagamentoAprazo(Integer qtdParcelas) {
        pagamento = new Pagamento(null, "À Prazo", 0.05, null, pedido.getValorTotal(), qtdParcelas);
        exibirParcelas(pagamento);
        pedido.setPagamento(pagamento);
    }
    private void atualizarFormaPagamentoAvista() {
        pagamento = new Pagamento(null, "À Avista", null, 0.05, pedido.getValorTotal(), 1);
        exibirParcelas(pagamento);
        pedido.setPagamento(pagamento);
    }
    private void exibirParcelas(Pagamento pagamento) {
        String parcelas = "";
        for (int i = 0; i < pagamento.getQtdParcelas(); i++) {
            parcelas += (1+i) + "º parcela: R$ " + (pagamento.getVlTotalLiquido() / pagamento.getQtdParcelas()) + "\n";
        }
        tvParcelas.setText(parcelas);
    }
}