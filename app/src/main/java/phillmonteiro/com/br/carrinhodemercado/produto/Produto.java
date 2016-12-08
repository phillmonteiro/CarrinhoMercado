package phillmonteiro.com.br.carrinhodemercado.produto;

/**
 * Created by philipe.monteiro on 30/11/2016.
 */
public class Produto {

    protected Integer id;
    protected String nome;
    protected String secao;
    protected double preco;
    protected String tipo;
    protected double peso;
    protected int quantidade;
    protected boolean adicionado;

    public Produto(){}

    public Produto(String nome, String secao, double preco, String tipo, double peso, int quantidade, boolean adicionado) {
        this.nome = nome;
        this.secao = secao;
        this.preco = preco;
        this.tipo = tipo;
        this.peso = peso;
        this.quantidade = quantidade;
        this.adicionado = adicionado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public boolean isAdicionado() {
        return adicionado;
    }

    public void setAdicionado(boolean adicionado) {
        this.adicionado = adicionado;
    }

    public String getSecao() {
        return secao;
    }

    public void setSecao(String secao) {
        this.secao = secao;
    }


    @Override
    public String toString() {
        return "Produto{" +
                "nome='" + nome + '\'' +
                ", secao='" + secao + '\'' +
                ", preco=" + preco +
                ", tipo='" + tipo + '\'' +
                ", peso=" + peso +
                ", quantidade=" + quantidade +
                ", adicionado=" + adicionado +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Produto produto = (Produto) o;

        if (Double.compare(produto.preco, preco) != 0) return false;
        if (Double.compare(produto.peso, peso) != 0) return false;
        if (quantidade != produto.quantidade) return false;
        if (adicionado != produto.adicionado) return false;
        if (!nome.equals(produto.nome)) return false;
        if (!secao.equals(produto.secao)) return false;
        return tipo != null ? tipo.equals(produto.tipo) : produto.tipo == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = nome.hashCode();
        result = 31 * result + secao.hashCode();
        temp = Double.doubleToLongBits(preco);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (tipo != null ? tipo.hashCode() : 0);
        temp = Double.doubleToLongBits(peso);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + quantidade;
        result = 31 * result + (adicionado ? 1 : 0);
        return result;
    }
}
