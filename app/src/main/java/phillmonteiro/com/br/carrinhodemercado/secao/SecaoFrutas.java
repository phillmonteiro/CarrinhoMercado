package phillmonteiro.com.br.carrinhodemercado.secao;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import java.util.List;

import phillmonteiro.com.br.carrinhodemercado.R;
import phillmonteiro.com.br.carrinhodemercado.produto.IncluirProduto;
import phillmonteiro.com.br.carrinhodemercado.produto.ListaMercadoHelper;
import phillmonteiro.com.br.carrinhodemercado.produto.Produto;

/**
 * Created by philipe.monteiro on 30/11/2016.
 */

public class SecaoFrutas extends Activity implements View.OnClickListener {

    private static final String SECAO = "Frutass";

    private List<Produto> produtos;
    private ListaMercadoHelper listaHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_produtos);

        LinearLayout adicionarItem = (LinearLayout) findViewById(R.id.adicionarItem);
        adicionarItem.setOnClickListener(this);

        listaHelper = new ListaMercadoHelper(this);

        produtos = listaHelper.popularLista(SECAO);
        listaHelper.quantidadeCarrinho();

    }

    @Override
    protected void onResume() {
        super.onResume();
        produtos = listaHelper.popularLista(SECAO);
        listaHelper.quantidadeCarrinho();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.adicionarItem:

                Intent adicionarItem = new Intent(this, IncluirProduto.class);
                adicionarItem.putExtra("SECAO", SECAO);
                startActivity(adicionarItem);

                break;
            default:
                break;
        }
    }


}
