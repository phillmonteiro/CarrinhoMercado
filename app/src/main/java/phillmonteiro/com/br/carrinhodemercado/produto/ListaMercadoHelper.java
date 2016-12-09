package phillmonteiro.com.br.carrinhodemercado.produto;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.List;

import phillmonteiro.com.br.carrinhodemercado.R;
import phillmonteiro.com.br.carrinhodemercado.adapter.MercadoAdapter;
import phillmonteiro.com.br.carrinhodemercado.dao.MercadoDAO;
import phillmonteiro.com.br.carrinhodemercado.secao.SecaoFrutas;
import phillmonteiro.com.br.carrinhodemercado.validador.Validadores;

/**
 * Created by philipe.monteiro on 05/12/2016.
 */
public class ListaMercadoHelper {

    private Context context;

    private TextView quantidadeComprados;
    private TextView valorTotal;
    private TextView nomeSecao;
    private RecyclerView listaProdutos;

    private List<Produto> produtos;

    public ListaMercadoHelper(SecaoFrutas secaoFrutas) {
        this.context = secaoFrutas;

        this.quantidadeComprados = (TextView) secaoFrutas.findViewById(R.id.quantidadeComprados);
        this.valorTotal = (TextView) secaoFrutas.findViewById(R.id.valorTotal);
        this.nomeSecao = (TextView) secaoFrutas.findViewById(R.id.nomeSecao);
        this.listaProdutos = (RecyclerView) secaoFrutas.findViewById(R.id.listaProdutos);
    }

    public List<Produto> popularLista(String secao) {

        MercadoDAO mercadoDAO = new MercadoDAO(context);
        produtos = mercadoDAO.listarProdutos(secao);

        nomeSecao.setText(secao);
        listaProdutos.setAdapter(new MercadoAdapter(produtos, context, quantidadeComprados, valorTotal));

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        listaProdutos.setLayoutManager(layoutManager);

        return produtos;
    }

    public void quantidadeCarrinho() {
        Integer contadorItens = 0;
        Double calcularValorTotal = 0.0;

        for(Produto produto : produtos){
            if(produto.isAdicionado()){
                contadorItens += 1;
                calcularValorTotal += produto.getPreco();
            }
        }

        quantidadeComprados.setText(String.valueOf(contadorItens + " de " + produtos.size()));
        valorTotal.setText(Validadores.formatarMoeda(calcularValorTotal));
    }

}
