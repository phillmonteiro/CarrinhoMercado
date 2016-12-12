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
    TextView produtoQuantidade;
    CheckBox marcarProduto;
    ImageView gravarQuantidade;
    ImageView gravarPreco;
    Switch deletarProduto;

    public MercadoViewHolder(View itemView) {
        super(itemView);

        produtoNome = (TextView) itemView.findViewById(R.id.produtoNome);
        produtoTipo = (TextView) itemView.findViewById(R.id.produtoTipo);
        produtoPreco = (TextView) itemView.findViewById(R.id.produtoPreco);
        produtoQuantidade = (TextView) itemView.findViewById(R.id.quantidadeProduto);
        gravarQuantidade = (ImageView) itemView.findViewById(R.id.gravarQuantidade);
        gravarPreco = (ImageView) itemView.findViewById(R.id.gravarPreco);
        marcarProduto = (CheckBox) itemView.findViewById(R.id.marcarProduto);
        deletarProduto = (Switch) itemView.findViewById(R.id.deletar_produto);

    }

}
