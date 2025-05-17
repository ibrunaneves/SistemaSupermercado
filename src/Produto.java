import java.util.Objects;

public class Produto {

    private String nome;
    private double preco;

    public Produto(String nome, double preco) {
        // Validações defensivas diretamente no construtor
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do produto precisa ser preenchido.");
        }

        if (preco < 0) {
            throw new IllegalArgumentException("O preço não pode ser negativo.");
        }

        this.nome = nome;
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return Double.compare(produto.preco, preco) == 0 &&
                Objects.equals(nome, produto.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, preco);
    }
}
