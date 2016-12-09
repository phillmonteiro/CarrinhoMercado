package phillmonteiro.com.br.carrinhodemercado.adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import phillmonteiro.com.br.carrinhodemercado.R;
import phillmonteiro.com.br.carrinhodemercado.dao.MercadoDAO;
import phillmonteiro.com.br.carrinhodemercado.produto.Produto;
import phillmonteiro.com.br.carrinhodemercado.validador.Validadores;

/**
 * Created by philipe.monteiro on 30/11/2016.
 */
public class MercadoAdapter extends RecyclerView.Adapter implements CompoundButton.OnCheckedChangeListener, View.OnClickListener{

    List<Produto> produtos;
    Context context;
    Produto produto;
    TextView quantidadeComprados, produtoNome, valorTotal;
    NumberPicker precoReal, precoCentavo;

    Dialog dialog;

    public MercadoAdapter(List<Produto> produtos, Context context, TextView quantidadeComprados, TextView valorTotal) {
        this.produtos = produtos;
        this.context = context;
        this.quantidadeComprados = quantidadeComprados;
        this.valorTotal = valorTotal;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.lista_card, parent, false);

        MercadoViewHolder holder = new MercadoViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        MercadoViewHolder mercadoViewHolder = (MercadoViewHolder) holder;

        produto  = produtos.get(position) ;

        mercadoViewHolder.produtoNome.setText(produto.getNome());
        mercadoViewHolder.produtoPreco.setText(Validadores.formatarMoeda(produto.getPreco()));
        mercadoViewHolder.produtoTipo.setText(produto.getTipo());

        //Gravar Preço
        mercadoViewHolder.gravarPreco.setTag(position);
        mercadoViewHolder.gravarPreco.setOnClickListener(this);

        //Marcar Produto
        mercadoViewHolder.marcarProduto.setTag(position);
        mercadoViewHolder.marcarProduto.setInputType(0);
        mercadoViewHolder.marcarProduto.setChecked(produto.isAdicionado());
        mercadoViewHolder.marcarProduto.setOnCheckedChangeListener(this);

        //Deletar Produto
        mercadoViewHolder.deletarProduto.setTag(position);
        mercadoViewHolder.deletarProduto.setInputType(1);
        mercadoViewHolder.deletarProduto.setChecked(true);
        mercadoViewHolder.deletarProduto.setOnCheckedChangeListener(this);

    }

    @Override
    public int getItemCount() {
        return produtos.size();
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        switch (buttonView.getInputType()){
            case 0:
                //Marcar Produto
                if (isChecked){
                    buttonView.setChecked(true);
                    alterarProdutoCheckbox(true, (Integer) buttonView.getTag());

                    Toast.makeText(context, R.string.add_produto_carrinho, Toast.LENGTH_SHORT).show();
                }else{
                    buttonView.setChecked(false);
                    alterarProdutoCheckbox(false, (Integer) buttonView.getTag());

                    Toast.makeText(context, R.string.ret_produto_carrinho, Toast.LENGTH_SHORT).show();
                }

                break;
            case 1:
                //Deletar Produto
                avisoDelecao((Integer) buttonView.getTag(), buttonView);

                break;
            default:
                break;
        }

    }

    private void avisoDelecao(final int id, final CompoundButton buttonView) {

        final String nomeProduto = produtos.get(id).getNome();

        if(!buttonView.isChecked()) {

            new AlertDialog.Builder(context)

                .setMessage(context.getString(R.string.msg_delecao_1) + nomeProduto + context.getString(R.string.msg_delecao_2))

                .setPositiveButton(context.getString(R.string.sim), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deletarProduto(id);
                        Toast.makeText(context, nomeProduto + context.getString(R.string.msg_produto_retirado), Toast.LENGTH_SHORT).show();
                    }
                })

                .setNegativeButton(context.getString(R.string.nao), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        buttonView.setChecked(true);
                    }
                })

                .show();

        }
    }

    private void deletarProduto(int id) {
        MercadoDAO mercadoDAO = new MercadoDAO(context);

        produto = produtos.get(id);

        mercadoDAO.excluirProduto(produto.getId());
        mercadoDAO.close();

        produtos.remove(id);
        notifyItemRemoved(id);
        notifyItemRangeChanged(id, getItemCount());

        quantidadeCheck();

    }

    private void alterarProdutoCheckbox(boolean boo, int id) {
        MercadoDAO mercadoDAO = new MercadoDAO(context);

        produto = produtos.get(id);

        produto.setAdicionado(boo);
        mercadoDAO.alterarProduto(produto);
        mercadoDAO.close();

        quantidadeCheck();
    }


    private void quantidadeCheck() {
        Integer contadorItens = 0;
        Double valorCalculado = 0.0;

        for(Produto produto : produtos){
            if(produto.isAdicionado()){
                contadorItens += 1;
                valorCalculado += produto.getPreco();
            }
        }

        quantidadeComprados.setText(String.valueOf(contadorItens + " de " + produtos.size()));
        valorTotal.setText(Validadores.formatarMoeda(valorCalculado));
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.gravarPreco:

                produto = produtos.get((Integer) v.getTag());
                inserirPreco(produto);

                break;
            case R.id.gravarPrecoProduto:

                capturarPreco(produto);

                break;
            default:
                break;
        }
    }

    private void capturarPreco(Produto produto) {

        int precoRealProduto = precoReal.getValue();
        int precoCentavoProduto = precoCentavo.getValue();

        double decimal = (double) precoCentavoProduto / 100;
        double valorProduto = precoRealProduto + decimal;

        produto.setPreco(valorProduto);

        alterarPrecoProduto(produto);

        dialog.dismiss();

        notifyDataSetChanged();

    }

    private void inserirPreco(Produto produto) {

        dialog = new Dialog(context);
        dialog.getWindow().setLayout(RecyclerView.LayoutParams.MATCH_PARENT, 500);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.alterar_preco);

        produtoNome = (TextView) dialog.findViewById(R.id.produtoNome);
        produtoNome.setText(produto.getNome());

        precoReal = (NumberPicker) dialog.findViewById(R.id.precoReal);
        precoReal.setMaxValue(99);
        precoCentavo = (NumberPicker) dialog.findViewById(R.id.precoCentavos);
        precoCentavo.setMaxValue(99);

        Button gravarPrecoProduto = (Button) dialog.findViewById(R.id.gravarPrecoProduto);
        gravarPrecoProduto.setOnClickListener(this);

        dialog.show();

    }

    private void alterarPrecoProduto(Produto produto) {
        MercadoDAO mercadoDAO = new MercadoDAO(context);

        mercadoDAO.alterarProduto(produto);
        mercadoDAO.close();
    }
}
