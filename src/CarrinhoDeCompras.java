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
        if (produto == null) {
            throw new IllegalArgumentException("O produto não pode ser nulo.");
        }
        if (quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade deve ser maior que zero.");
        }

        int id = produto.hashCode();

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
        int id = removido.getProduto().hashCode();

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

    @Override
    public String toString() {
        return "CarrinhoDeCompras com " + itens.size() + " itens - Total: R$ " +
                String.format("%.2f", calcularTotal());
    }
}
