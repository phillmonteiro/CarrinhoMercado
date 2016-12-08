package phillmonteiro.com.br.carrinhodemercado;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import phillmonteiro.com.br.carrinhodemercado.secao.SecaoBebidas;
import phillmonteiro.com.br.carrinhodemercado.secao.SecaoCarnes;
import phillmonteiro.com.br.carrinhodemercado.secao.SecaoCereais;
import phillmonteiro.com.br.carrinhodemercado.secao.SecaoFeira;
import phillmonteiro.com.br.carrinhodemercado.secao.SecaoLimpeza;
import phillmonteiro.com.br.carrinhodemercado.secao.SecaoPerfumaria;

public class MainActivity extends Activity implements View.OnClickListener{

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
