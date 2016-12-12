package phillmonteiro.com.br.carrinhodemercado.produto;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.List;

import phillmonteiro.com.br.carrinhodemercado.R;
import phillmonteiro.com.br.carrinhodemercado.adapter.MercadoAdapter;
import phillmonteiro.com.br.carrinhodemercado.dao.MercadoDAO;
import phillmonteiro.com.br.carrinhodemercado.secao.SecaoBebidas;
import phillmonteiro.com.br.carrinhodemercado.secao.SecaoCarnes;
import phillmonteiro.com.br.carrinhodemercado.secao.SecaoCereais;
import phillmonteiro.com.br.carrinhodemercado.secao.SecaoFrutas;
import phillmonteiro.com.br.carrinhodemercado.secao.SecaoLegumes;
import phillmonteiro.com.br.carrinhodemercado.secao.SecaoLimpeza;
import phillmonteiro.com.br.carrinhodemercado.secao.SecaoPerfumaria;
import phillmonteiro.com.br.carrinhodemercado.secao.SecaoVerduras;
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

    //Construtor Frutas
    public ListaMercadoHelper(SecaoFrutas secaoFrutas) {
        this.context = secaoFrutas;

        this.quantidadeComprados = (TextView) secaoFrutas.findViewById(R.id.quantidadeComprados);
        this.valorTotal = (TextView) secaoFrutas.findViewById(R.id.valorTotal);
        this.nomeSecao = (TextView) secaoFrutas.findViewById(R.id.nomeSecao);
        this.listaProdutos = (RecyclerView) secaoFrutas.findViewById(R.id.listaProdutos);
    }

    //Construtor Bebidas
    public ListaMercadoHelper(SecaoBebidas secaoBebidas) {
        this.context = secaoBebidas;

        this.quantidadeComprados = (TextView) secaoBebidas.findViewById(R.id.quantidadeComprados);
        this.valorTotal = (TextView) secaoBebidas.findViewById(R.id.valorTotal);
        this.nomeSecao = (TextView) secaoBebidas.findViewById(R.id.nomeSecao);
        this.listaProdutos = (RecyclerView) secaoBebidas.findViewById(R.id.listaProdutos);
    }

    //Construtor Carnes
    public ListaMercadoHelper(SecaoCarnes secaoCarnes) {
        this.context = secaoCarnes;

        this.quantidadeComprados = (TextView) secaoCarnes.findViewById(R.id.quantidadeComprados);
        this.valorTotal = (TextView) secaoCarnes.findViewById(R.id.valorTotal);
        this.nomeSecao = (TextView) secaoCarnes.findViewById(R.id.nomeSecao);
        this.listaProdutos = (RecyclerView) secaoCarnes.findViewById(R.id.listaProdutos);
    }

    //Construtor Cereais
    public ListaMercadoHelper(SecaoCereais secaoCereais) {
        this.context = secaoCereais;

        this.quantidadeComprados = (TextView) secaoCereais.findViewById(R.id.quantidadeComprados);
        this.valorTotal = (TextView) secaoCereais.findViewById(R.id.valorTotal);
        this.nomeSecao = (TextView) secaoCereais.findViewById(R.id.nomeSecao);
        this.listaProdutos = (RecyclerView) secaoCereais.findViewById(R.id.listaProdutos);
    }

    //Construtor Legumes
    public ListaMercadoHelper(SecaoLegumes secaoLegumes) {
        this.context = secaoLegumes;

        this.quantidadeComprados = (TextView) secaoLegumes.findViewById(R.id.quantidadeComprados);
        this.valorTotal = (TextView) secaoLegumes.findViewById(R.id.valorTotal);
        this.nomeSecao = (TextView) secaoLegumes.findViewById(R.id.nomeSecao);
        this.listaProdutos = (RecyclerView) secaoLegumes.findViewById(R.id.listaProdutos);
    }

    //Construtor Limpeza
    public ListaMercadoHelper(SecaoLimpeza secaoLimpeza) {
        this.context = secaoLimpeza;

        this.quantidadeComprados = (TextView) secaoLimpeza.findViewById(R.id.quantidadeComprados);
        this.valorTotal = (TextView) secaoLimpeza.findViewById(R.id.valorTotal);
        this.nomeSecao = (TextView) secaoLimpeza.findViewById(R.id.nomeSecao);
        this.listaProdutos = (RecyclerView) secaoLimpeza.findViewById(R.id.listaProdutos);
    }

    //Construtor Perfumaria
    public ListaMercadoHelper(SecaoPerfumaria secaoPerfumaria) {
        this.context = secaoPerfumaria;

        this.quantidadeComprados = (TextView) secaoPerfumaria.findViewById(R.id.quantidadeComprados);
        this.valorTotal = (TextView) secaoPerfumaria.findViewById(R.id.valorTotal);
        this.nomeSecao = (TextView) secaoPerfumaria.findViewById(R.id.nomeSecao);
        this.listaProdutos = (RecyclerView) secaoPerfumaria.findViewById(R.id.listaProdutos);
    }

    //Construtor Verduras
    public ListaMercadoHelper(SecaoVerduras secaoVerduras) {
        this.context = secaoVerduras;

        this.quantidadeComprados = (TextView) secaoVerduras.findViewById(R.id.quantidadeComprados);
        this.valorTotal = (TextView) secaoVerduras.findViewById(R.id.valorTotal);
        this.nomeSecao = (TextView) secaoVerduras.findViewById(R.id.nomeSecao);
        this.listaProdutos = (RecyclerView) secaoVerduras.findViewById(R.id.listaProdutos);
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
