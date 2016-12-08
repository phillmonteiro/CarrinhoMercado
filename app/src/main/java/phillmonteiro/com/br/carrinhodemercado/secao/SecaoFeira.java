package phillmonteiro.com.br.carrinhodemercado.secao;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;

import phillmonteiro.com.br.carrinhodemercado.R;

/**
 * Created by philipe.monteiro on 28/11/2016.
 */
public class SecaoFeira extends Activity implements View.OnClickListener{

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
