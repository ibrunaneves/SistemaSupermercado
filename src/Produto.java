public class Produto {

    private static int contadorId = 1;

    private int id;
    private String nome;
    private double preco;

    public Produto(String nome, double preco) {
        validarNome(nome); // Validações defensivas
        validarPreco(preco);

        this.id = contadorId++;
        this.nome = nome;
        this.preco = preco;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setNome(String nome) {
        validarNome(nome);
        this.nome = nome;
    }

    public void setPreco(double preco) {
        validarPreco(preco);
        this.preco = preco;
    }

    // Métodos privados pra evitar repetições de código

    private void validarNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O produto precisa ter um nome.");
        }
    }

    private void validarPreco(double preco) {
        if (preco < 0) {
            throw new IllegalArgumentException("O preço não pode ser negativo.");
        }
    }
}