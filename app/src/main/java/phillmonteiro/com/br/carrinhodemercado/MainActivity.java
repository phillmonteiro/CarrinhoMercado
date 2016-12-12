package phillmonteiro.com.br.carrinhodemercado;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import phillmonteiro.com.br.carrinhodemercado.constante.Constantes;
import phillmonteiro.com.br.carrinhodemercado.dao.MercadoDAO;
import phillmonteiro.com.br.carrinhodemercado.produto.Produto;
import phillmonteiro.com.br.carrinhodemercado.secao.SecaoBebidas;
import phillmonteiro.com.br.carrinhodemercado.secao.SecaoCarnes;
import phillmonteiro.com.br.carrinhodemercado.secao.SecaoCereais;
import phillmonteiro.com.br.carrinhodemercado.secao.SecaoFeira;
import phillmonteiro.com.br.carrinhodemercado.secao.SecaoLimpeza;
import phillmonteiro.com.br.carrinhodemercado.secao.SecaoPerfumaria;
import phillmonteiro.com.br.carrinhodemercado.validador.Validadores;

public class MainActivity extends Activity implements View.OnClickListener{

    TextView totalFeira, totalBebidas, totalCereais, totalCarnes, totalPerfumaria, totalLimpeza;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout botaoFeira = (LinearLayout) findViewById(R.id.feira);
        botaoFeira.setOnClickListener(this);

        LinearLayout botaoBebidas = (LinearLayout) findViewById(R.id.bebidas);
        botaoBebidas.setOnClickListener(this);

        LinearLayout botaoCereais = (LinearLayout) findViewById(R.id.cereais);
        botaoCereais.setOnClickListener(this);

        LinearLayout botaoCarnes = (LinearLayout) findViewById(R.id.carnes);
        botaoCarnes.setOnClickListener(this);

        LinearLayout botaoPerfumaria = (LinearLayout) findViewById(R.id.perfumaria);
        botaoPerfumaria.setOnClickListener(this);

        LinearLayout botaoLimpeza = (LinearLayout) findViewById(R.id.limpeza);
        botaoLimpeza.setOnClickListener(this);

        totalFeira = (TextView) findViewById(R.id.totalFeira);
        totalBebidas = (TextView) findViewById(R.id.totalBebidas);
        totalCereais = (TextView) findViewById(R.id.totalCereais);
        totalCarnes = (TextView) findViewById(R.id.totalCarnes);
        totalPerfumaria = (TextView) findViewById(R.id.totalPerfumaria);
        totalLimpeza = (TextView) findViewById(R.id.totalLimpeza);

        calcularValores();
    }

    @Override
    protected void onResume() {
        super.onResume();
        calcularValores();
    }

    private void calcularValores() {
        MercadoDAO mercadoDAO = new MercadoDAO(this);

        popularValoresFeira(mercadoDAO);

        String[] secoes = {Constantes.BEBIDAS, Constantes.CEREAIS, Constantes.CARNES, Constantes.PERFURMARIA, Constantes.LIMPEZA};
        TextView[] total = {totalBebidas, totalCereais, totalCarnes, totalPerfumaria, totalLimpeza};

        for(int i=0;i<secoes.length;i++){
            popularValores(mercadoDAO.listarProdutos(secoes[i]), total[i]);
        }

        mercadoDAO.close();
    }

    private void popularValoresFeira(MercadoDAO mercadoDAO) {

        double feira = 0.0;

        feira += contarFeira(mercadoDAO.listarProdutos(Constantes.FRUTAS));
        feira += contarFeira(mercadoDAO.listarProdutos(Constantes.LEGUMES));
        feira += contarFeira(mercadoDAO.listarProdutos(Constantes.VERDURAS));

        totalFeira.setText(Validadores.formatarMoeda(feira));
    }

    private double contarFeira(List<Produto> produtos) {

        double valor = 0.0;

        for (Produto prod : produtos){
            if(prod.isAdicionado()){
                valor += prod.getPreco();
            }
        }

        return valor;
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
            case R.id.feira:

                secaoIntent(SecaoFeira.class);

                return;
            case R.id.bebidas:

                secaoIntent(SecaoBebidas.class);

                return;
            case R.id.cereais:

                secaoIntent(SecaoCereais.class);

                return;
            case R.id.carnes:

                secaoIntent(SecaoCarnes.class);

                return;
            case R.id.perfumaria:

                secaoIntent(SecaoPerfumaria.class);

                return;
            case R.id.limpeza:

                secaoIntent(SecaoLimpeza.class);

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
