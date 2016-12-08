package phillmonteiro.com.br.carrinhodemercado.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import phillmonteiro.com.br.carrinhodemercado.produto.Produto;

/**
 * Created by philipe.monteiro on 01/12/2016.
 */

public class MercadoDAO extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "PHILLMARKET";

    public MercadoDAO(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String ddl = "CREATE TABLE tb_lista_mercado( id_produto INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                                    "nome_produto TEXT NOT NULL, " +
                                                    "secao_produto TEXT NOT NULL, " +
                                                    "preco_produto DECIMAL, " +
                                                    "tipo_produto TEXT, " +
                                                    "peso_produto DECIMAL, " +
                                                    "quantidade_produto INTEGER, " +
                                                    "produto_adicionado INTEGER DEFAULT 0 )";

        db.execSQL(ddl);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL( "DROP TABLE IF EXISTS tb_lista_mercado" );
        this.onCreate(db);
    }

    public void incluirProduto(Produto produto) {

        ContentValues dados = new ContentValues();

        dados.put("nome_produto", produto.getNome());
        dados.put("secao_produto", produto.getSecao());
        dados.put("preco_produto", produto.getPreco());
        dados.put("tipo_produto", produto.getTipo());
        dados.put("peso_produto", produto.getPeso());
        dados.put("quantidade_produto", produto.getQuantidade());
        dados.put("produto_adicionado", produto.isAdicionado());

        this.getWritableDatabase().insert("tb_lista_mercado", null, dados);
    }

    public void alterarProduto(Produto produto) {

        ContentValues dados = new ContentValues();

        dados.put("nome_produto", produto.getNome());
        dados.put("secao_produto", produto.getSecao());
        dados.put("preco_produto", produto.getPreco());
        dados.put("tipo_produto", produto.getTipo());
        dados.put("peso_produto", produto.getPeso());
        dados.put("quantidade_produto", produto.getQuantidade());
        dados.put("produto_adicionado", produto.isAdicionado());

        this.getWritableDatabase().update("tb_lista_mercado", dados, "id_produto = " + produto.getId(), null);

    }

    public void excluirProduto(Integer id) {
        this.getWritableDatabase().delete("tb_lista_mercado", "id_produto = ?", new String[]{String.valueOf(id)});
    }

    public Produto pesquisarProdutos(Integer id) {

        Cursor registros = this.getWritableDatabase().query("tb_lista_mercado", null, "id_produto = " + id, null, null, null, null);

        if (registros.moveToNext()) {
            String nomeProduto = registros.getString(registros.getColumnIndex("nome_produto"));
            String secaoProduto = registros.getString(registros.getColumnIndex("secao_produto"));
            Double precoProduto = registros.getDouble(registros.getColumnIndex("preco_produto"));
            String tipoProduto = registros.getString(registros.getColumnIndex("tipo_produto"));
            Double pesoProduto = registros.getDouble(registros.getColumnIndex("peso_produto"));
            Integer quantidadeProduto = registros.getInt(registros.getColumnIndex("quantidade_produto"));
            Boolean produtoAdicionado = registros.getInt(registros.getColumnIndex("produto_adicionado")) == 1;

            Produto produto = new Produto();

            produto.setId(id);
            produto.setNome(nomeProduto);
            produto.setSecao(secaoProduto);
            produto.setPreco(precoProduto);
            produto.setTipo(tipoProduto);
            produto.setPeso(pesoProduto);
            produto.setQuantidade(quantidadeProduto);
            produto.setAdicionado(produtoAdicionado);

            return produto;
        } else {
            return null;
        }

    }

    public List<Produto> listarProdutos(String secao) {

        String[] colunas = {"id_produto", "nome_produto", "secao_produto", "preco_produto", "tipo_produto", "peso_produto", "quantidade_produto", "produto_adicionado"};

        Cursor cursor = this.getWritableDatabase().query("tb_lista_mercado", colunas, "secao_produto = '" + secao + "'", null, null, null, null);


        ArrayList<Produto> produtos = new ArrayList<>();

        while (cursor.moveToNext()){

            Produto produto = new Produto();

            produto.setId(cursor.getInt(0));
            produto.setNome(cursor.getString(1));
            produto.setSecao(cursor.getString(2));
            produto.setPreco(cursor.getDouble(3));
            produto.setTipo(cursor.getString(4));
            produto.setPeso(cursor.getDouble(5));
            produto.setQuantidade(cursor.getInt(6));
            produto.setAdicionado(cursor.getInt(7)==1);

            produtos.add(produto);

        }

        return produtos;
    }

}
