package phillmonteiro.com.br.carrinhodemercado.produto;

import android.widget.EditText;

import phillmonteiro.com.br.carrinhodemercado.R;
import phillmonteiro.com.br.carrinhodemercado.validador.Validadores;

/**
 * Created by philipe.monteiro on 02/12/2016.
 */
public class FormularioHelper {

    private EditText nomeProduto;
    private EditText tipoProduto;

    public FormularioHelper(IncluirProduto incluirProduto) {

        nomeProduto = (EditText) incluirProduto.findViewById(R.id.nomeProduto);
        tipoProduto = (EditText) incluirProduto.findViewById(R.id.tipoProduto);

    }

    public Produto escreverProduto(String secao) {

        Produto produto = new Produto();

        produto.setNome(nomeProduto.getText().toString());
        produto.setTipo(Validadores.validarNull(tipoProduto.getText().toString()));
        produto.setSecao(secao);
        produto.setQuantidade(1);
        produto.setPreco(0);
        produto.setPeso(0);
        produto.setAdicionado(false);

        return produto;
    }

}
