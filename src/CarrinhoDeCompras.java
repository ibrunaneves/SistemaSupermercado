import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class CarrinhoDeCompras {

    private final List<ItemCarrinho> itens;
    private final HashMap<Integer, ItemCarrinho> mapaItens;
    private final Stack<ItemCarrinho> historico;

    public CarrinhoDeCompras() {
        this.itens = new ArrayList<>();
        this.mapaItens = new HashMap<>();
        this.historico = new Stack<>();
    }

    public void adicionarProduto(Produto produto, int quantidade) {
        validarProduto(produto);
        validarQuantidade(quantidade);

        int id = produto.getId();

        if (mapaItens.containsKey(id)) {
            ItemCarrinho item = mapaItens.get(id);
            item.setQuantidade(item.getQuantidade() + quantidade);
        } else {
            ItemCarrinho novoItem = new ItemCarrinho(produto, quantidade);
            itens.add(novoItem);
            mapaItens.put(id, novoItem);
            historico.push(novoItem);
        }
    }

    public List<ItemCarrinho> getItens() {
        return new ArrayList<>(itens); // Cópia defensiva
    }

    public String listarItens() {
        if (itens.isEmpty()) {
            return "O carrinho está vazio!";
        }

        String resultado = "Itens no carrinho:\n";

        for (ItemCarrinho item : itens) {
            resultado += item.toString() + "\n";
        }

        resultado += "Total: R$ " + String.format("%.2f", calcularTotal());

        return resultado;
    }

    public void exibirItens() {
        System.out.println(listarItens());
    }

    public boolean desfazerUltimaAdicao() {
        if (historico.isEmpty()) {
            return false;
        }

        ItemCarrinho removido = historico.pop();
        int id = removido.getProduto().getId();

        itens.remove(removido);
        mapaItens.remove(id);

        return true;
    }

    public double calcularTotal() {
        double total = 0.0;

        for (ItemCarrinho item : itens) {
            total += item.calcularSubtotal();
        }

        return total;
    }

    public int getQuantidadeItens() {
        return itens.size();
    }

    public boolean estaVazio() {
        return itens.isEmpty();
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

    @Override
    public String toString() {
        return "CarrinhoDeCompras com " + itens.size() + " itens - Total: R$ " +
                String.format("%.2f", calcularTotal());
    }
}
