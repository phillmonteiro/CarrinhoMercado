package phillmonteiro.com.br.carrinhodemercado.secao;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import phillmonteiro.com.br.carrinhodemercado.R;
import phillmonteiro.com.br.carrinhodemercado.constante.Constantes;
import phillmonteiro.com.br.carrinhodemercado.dao.MercadoDAO;
import phillmonteiro.com.br.carrinhodemercado.produto.Produto;
import phillmonteiro.com.br.carrinhodemercado.validador.Validadores;

/**
 * Created by philipe.monteiro on 28/11/2016.
 */
public class SecaoFeira extends Activity implements View.OnClickListener{

    TextView totalFrutas, totalLegumes, totalVerduras;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.secao_feira);

        LinearLayout secaoFrutas = (LinearLayout) findViewById(R.id.frutas);
        secaoFrutas.setOnClickListener(this);

        LinearLayout secaoLegumes = (LinearLayout) findViewById(R.id.legumes);
        secaoLegumes.setOnClickListener(this);

        LinearLayout secaoVerduras = (LinearLayout) findViewById(R.id.verduras);
        secaoVerduras.setOnClickListener(this);

        totalFrutas = (TextView) findViewById(R.id.totalFrutas);
        totalLegumes = (TextView) findViewById(R.id.totalLegumes);
        totalVerduras = (TextView) findViewById(R.id.totalVerduras);

        calcularValores();

    }

    @Override
    protected void onResume() {
        super.onResume();
        calcularValores();
    }

    private void calcularValores() {
        MercadoDAO mercadoDAO = new MercadoDAO(this);

        String[] feira = {Constantes.FRUTAS, Constantes.LEGUMES, Constantes.VERDURAS};
        TextView[] total = {totalFrutas, totalLegumes, totalVerduras};

        for(int i=0;i<feira.length;i++){
            popularValores(mercadoDAO.listarProdutos(feira[i]), total[i]);
        }

        mercadoDAO.close();
    }

    private void popularValores(List<Produto> produto, TextView textView) {
        double preco = 0.0;

        for(Produto prod : produto){
            if(prod.isAdicionado()) {
                preco += prod.getPreco();
            }
        }

        textView.setText(Validadores.formatarMoeda(preco));
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.frutas:

                secaoIntent(SecaoFrutas.class);

                return;
            case R.id.legumes:

                secaoIntent(SecaoLegumes.class);

                return;
            case R.id.verduras:

                secaoIntent(SecaoVerduras.class);

                return;
            default:
                return;
        }

    }

    private void secaoIntent(Class secaoClass) {
        Intent intent = new Intent(this, secaoClass);
        startActivity(intent);
    }
}
