package br.unipar.lancamentovenda;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import br.unipar.lancamentovenda.bancodedados.PedidoBD;
import br.unipar.lancamentovenda.modelo.Item;
import br.unipar.lancamentovenda.modelo.ItemPedido;
import br.unipar.lancamentovenda.modelo.Pedido;

public class DetalhePedidoActivity extends AppCompatActivity {

    private Integer codPedido;
    private Pedido pedido;
    private PedidoBD pedidoBD = new PedidoBD();
    private TextView tvFormaPag;
    private TextView tvVlTotalPago;
    private TextView tvVlTotalPedido;
    private TextView tvListaItens;
    private TextView tvCliente;
    private TextView tvCodPedido;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Detalhes dp Pedido");
        setContentView(R.layout.detalhepedido);
        Intent intent = getIntent();
        codPedido = new Integer(intent.getSerializableExtra("codPedido").toString());
        tvFormaPag = findViewById(R.id.tvFormaPag);
        tvVlTotalPago = findViewById(R.id.tvVlTotalPago);
        tvVlTotalPedido = findViewById(R.id.tvVlTotalPedido);
        tvListaItens = findViewById(R.id.tvListaItens);
        tvCliente = findViewById(R.id.tvCliente);
        tvCodPedido = findViewById(R.id.tvCodPedido);
        carregarPedido();
    }
    private void carregarPedido() {
        pedido = pedidoBD.getPedido(codPedido);
        tvFormaPag.setText(pedido.getPagamento().getFormaPgto());
        tvVlTotalPago.setText(pedido.getPagamento().getVlTotalLiquido().toString());
        tvVlTotalPedido.setText(pedido.getValorTotal().toString());
        tvCliente.setText(pedido.getCliente().getNome());
        tvCodPedido.setText(pedido.getCodPedido().toString());
        String itens = "";
        for (ItemPedido item : pedido.getItens()) {
            itens +=
                    "Descrição: " + item.getDescricao() +
                    " ---- Valor Unitário: R$ " + item.getVlunitario() +
                    " ---- Quantidade: " + item.getQtdItem() +
                    " ---- Valor Total: R$ " + item.getVltotal();
        }
    }
}