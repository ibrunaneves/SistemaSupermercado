public class Cliente {

    private static int contador = 1;

    private int id;
    private String nome;

    public Cliente(String nome) {
        this.id = contador;
        contador++;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
