public class Cliente {
    private static int contador = 1;
    private int id;
    private String nome;

    public Cliente(String nome) {
        validarNome(nome);
        this.id = contador++;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        validarNome(nome);
        this.nome = nome;
    }

    private void validarNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do cliente precisa ser preenchido.");
        }
    }
}