package phillmonteiro.com.br.carrinhodemercado.produto;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import phillmonteiro.com.br.carrinhodemercado.R;
import phillmonteiro.com.br.carrinhodemercado.dao.MercadoDAO;
import phillmonteiro.com.br.carrinhodemercado.validador.Validadores;

/**
 * Created by philipe.monteiro on 02/12/2016.
 */

public class IncluirProduto extends Activity implements View.OnClickListener {

    private Produto produto;
    private FormularioHelper formularioHelper;
    private String secao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.incluir_produto);

        LinearLayout adicionarProduto = (LinearLayout) findViewById(R.id.adicionarProduto);
        adicionarProduto.setOnClickListener(this);

        formularioHelper = new FormularioHelper(this);

        Bundle extra = getIntent().getExtras();
        secao = extra.getString("SECAO");

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.adicionarProduto:

                produto = formularioHelper.escreverProduto(secao);

                if(Validadores.notNullOrEmpty(produto.getNome())){

                    MercadoDAO mercadoDAO = new MercadoDAO(this);

                    mercadoDAO.incluirProduto(produto);
                    mercadoDAO.close();

                    finish();
                }else{
                    alertaNomeProduto();
                }

                break;
            default:
                break;
        }
    }

    private void alertaNomeProduto() {
        new AlertDialog.Builder(this)
            .setMessage(getString(R.string.msg_adicionar_produto))
            .setNeutralButton(getString(R.string.ok), null)
            .show();
    }

}
