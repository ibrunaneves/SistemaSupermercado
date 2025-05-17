import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CarrinhoDeCompras carrinho = new CarrinhoDeCompras();

        Map<Integer, Produto> produtosDisponiveis = new HashMap<>();
        System.out.println("Bem-vindo, informe seu nome:");
        String nomeCliente = scanner.nextLine();
        Cliente cliente = new Cliente(nomeCliente);
        System.out.println("Olá, " + cliente.getNome() + "! Vamos às compras?\n");

        boolean executando = true;
        while (executando) {
            System.out.println("""
                --- MENU ---
                1 - Cadastrar novo produto
                2 - Adicionar produto ao carrinho
                3 - Listar itens do carrinho
                4 - Remover última adição
                5 - Ver total da compra
                0 - Sair
                """);

            System.out.print("Escolha uma opção: ");
            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1":
                    System.out.print("Digite o nome do produto: ");
                    String nome = scanner.nextLine();

                    boolean jaExiste = produtosDisponiveis.values().stream()
                            .anyMatch(p -> p.getNome().equalsIgnoreCase(nome));

                    if (jaExiste) {
                        System.out.println("Já existe um produto com esse nome.");
                        break;
                    }

                    System.out.print("Digite o preço do produto: ");
                    try {
                        double preco = Double.parseDouble(scanner.nextLine());
                        Produto novoProduto = new Produto(nome, preco);
                        produtosDisponiveis.put(novoProduto.hashCode(), novoProduto);
                        System.out.println("Produto cadastrado com sucesso!");
                    } catch (Exception e) {
                        System.out.println("Erro ao cadastrar produto: " + e.getMessage());
                    }
                    break;

                case "2":
                    if (produtosDisponiveis.isEmpty()) {
                        System.out.println("Nenhum produto cadastrado ainda.");
                        break;
                    }

                    System.out.println("Produtos disponíveis:");
                    for (Produto p : produtosDisponiveis.values()) {
                        System.out.println("- " + p.getNome() + " | R$ " + String.format("%.2f", p.getPreco()));
                    }

                    System.out.print("Digite o nome do produto que deseja adicionar: ");
                    String nomeBusca = scanner.nextLine().trim().toLowerCase();

                    Produto escolhido = null;
                    for (Produto p : produtosDisponiveis.values()) {
                        if (p.getNome().trim().toLowerCase().equals(nomeBusca)) {
                            escolhido = p;
                            break;
                        }
                    }

                    if (escolhido == null) {
                        System.out.println("Produto não encontrado.");
                        break;
                    }

                    try {
                        System.out.print("Digite a quantidade: ");
                        int qtd = Integer.parseInt(scanner.nextLine());
                        carrinho.adicionarProduto(escolhido, qtd);
                        System.out.println("Produto adicionado ao carrinho!");
                    } catch (Exception e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                    break;

                case "3":
                    carrinho.exibirItens();
                    break;

                case "4":
                    if (carrinho.desfazerUltimaAdicao()) {
                        System.out.println("Último item removido com sucesso!");
                    } else {
                        System.out.println("Não há itens para remover.");
                    }
                    break;

                case "5":
                    System.out.printf("Valor total da compra: R$ %.2f\n", carrinho.calcularTotal());
                    break;

                case "0":
                    executando = false;
                    System.out.println("Compra finalizada. Obrigado por usar o sistema, " + cliente.getNome() + "!");
                    break;

                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }

        scanner.close();
    }
}
