public class ItemCarrinho {

    private Produto produto;
    private int quantidade;

    public ItemCarrinho(Produto produto, int quantidade) {
        validarProduto(produto);
        validarQuantidade(quantidade);

        this.produto = produto;
        this.quantidade = quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        validarProduto(produto);
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        validarQuantidade(quantidade);
        this.quantidade = quantidade;
    }

    // Criando métodos

    public double calcularSubtotal() {
        return produto.getPreco() * quantidade;
    }

    private void validarProduto(Produto produto) {
        if (produto == null) {
            throw new IllegalArgumentException("O produto não pode ser nulo.");
        }
    }

    private void validarQuantidade(int quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade deve ser maior que zero.");
        }
    }

    // Usando o toString para facilitar a visualização do objeto durante o desenvolvimento

    @Override
    public String toString() {
        return quantidade + "x " + produto.getNome() +
                " (R$ " + String.format("%.2f", produto.getPreco()) + " cada) - " +
                "Subtotal: R$ " + String.format("%.2f", calcularSubtotal());
    }
}