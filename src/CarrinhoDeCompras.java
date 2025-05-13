import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class CarrinhoDeCompras {

    private ArrayList<ItemCarrinho> itens;
    private HashMap<Integer, ItemCarrinho> mapaItens;
    private Stack<ItemCarrinho> historico;

    public CarrinhoDeCompras() {
        this.itens = new ArrayList<>();
        this.mapaItens = new HashMap<>();
        this.historico = new Stack<>();
    }

    public void adicionarProduto(Produto produto, int quantidade) {
        int idProduto = produto.getId();

        if(mapaItens.containsKey(idProduto)) {
            ItemCarrinho itemExistente = mapaItens.get(idProduto);
            itemExistente.setQuantidade(itemExistente.getQuantidade() + quantidade);
        } else {

            ItemCarrinho novoItem = new ItemCarrinho(produto, quantidade);
            itens.add(novoItem);
            mapaItens.put(idProduto, novoItem);
            historico.push(novoItem);
        }
    }

    public void listarItens() {
        if (itens.isEmpty()) {
            System.out.println("O carrinho está vazio!");
            return;
        }

        System.out.println("Itens no carrinho:");

        for (ItemCarrinho item : itens) {
            Produto produto = item.getProduto();
            System.out.println("- " + produto.getNome() +
                    " | Quantidade: " + item.getQuantidade() +
                    " | Preço: R$ " + produto.getPreco() +
                    " | Subtotal: R$ " + item.getQuantidade() * produto.getPreco());
        }
    }
}
