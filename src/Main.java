public class Main {
    public static void main(String[] args) {

        Produto arroz = new Produto("Arroz", 5.99);
        Produto feijao = new Produto("Feijão", 6.89);
        Produto leite = new Produto("Café", 9.49);

        Cliente cliente = new Cliente("Bruna");

        CarrinhoDeCompras carrinho = new CarrinhoDeCompras();

        carrinho.adicionarProduto(arroz, 2);
        carrinho.adicionarProduto(feijao, 1);
        carrinho.adicionarProduto(leite, 3);

        System.out.println("\n--- ITENS NO CARRINHO ---");
        carrinho.exibirItens();

        System.out.println("\n--- DESFAZENDO A ÚLTIMA AÇÃO ---");
        if (carrinho.desfazerUltimaAdicao()) {
            System.out.println("Último item removido com sucesso!");
        } else {
            System.out.println("Não há itens para remover.");
        }

        System.out.println("\n--- ITENS ATUALIZADOS ---");
        carrinho.exibirItens();

        System.out.printf("\n--- VALOR TOTAL DA COMPRA: R$ %.2f ---\n", carrinho.calcularTotal());
    }
}
