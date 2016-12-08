package phillmonteiro.com.br.carrinhodemercado.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import phillmonteiro.com.br.carrinhodemercado.R;

/**
 * Created by philipe.monteiro on 30/11/2016.
 */

public class MercadoViewHolder extends RecyclerView.ViewHolder{

    TextView produtoNome;
    TextView produtoPreco;
    TextView produtoTipo;
    CheckBox marcarProduto;
    ImageView gravarPreco;
    Switch deletarProduto;

    public MercadoViewHolder(View itemView) {
        super(itemView);

        produtoNome = (TextView) itemView.findViewById(R.id.produtoNome);
        produtoPreco = (TextView) itemView.findViewById(R.id.produtoPreco);
        produtoTipo = (TextView) itemView.findViewById(R.id.produtoTipo);
        marcarProduto = (CheckBox) itemView.findViewById(R.id.marcarProduto);
        gravarPreco = (ImageView) itemView.findViewById(R.id.gravarPreco);
        deletarProduto = (Switch) itemView.findViewById(R.id.deletar_produto);

    }

}
