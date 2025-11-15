import java.util.ArrayList;
import java.util.List;

public abstract class Cliente {
    private String nome;
    private int idUsuario;
    private String email;
    private double saldo;
    private List<Jogo> jogosComprados;
    private static int proximoId = 1;

    public Cliente(String nome, String email, double saldo) {
        this.idUsuario = proximoId++;
        this.nome = nome;
        this.email = email;
        this.saldo = saldo;
        this.jogosComprados = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public String getEmail() {
        return email;
    }

    public double getSaldo() {
        return saldo;
    }

    public List<Jogo> getJogosComprados() {
        return new ArrayList<>(jogosComprados);
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSaldo(double saldo) {
        if (saldo < 0) {
            throw new IllegalArgumentException("Saldo não pode ser negativo!");
        }
        this.saldo = saldo;
    }

    public abstract double calcularDesconto(double preco);

    public void adicionarJogoComprado(Jogo jogo) {
        this.jogosComprados.add(jogo);
    }

    public boolean temSaldoSuficiente(double valor) {
        return this.saldo >= valor;
    }

    public void debitarSaldo(double valor) {
        if (!temSaldoSuficiente(valor)) {
            throw new IllegalArgumentException("Saldo insuficiente para realizar a compra!");
        }
        this.saldo -= valor;
    }

    @Override
    public String toString() {
        return String.format("ID: %d | Nome: %s | Email: %s | Saldo: R$ %.2f | Tipo: %s",
                idUsuario, nome, email, saldo, this.getClass().getSimpleName());
    }
}


