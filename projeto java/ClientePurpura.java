public class ClientePurpura extends Cliente {

    public ClientePurpura(String nome, String email, double saldo) {
        super(nome, email, saldo);
    }

    @Override
    public double calcularDesconto(double preco) {
        return preco * 0.05;
    }

    @Override
    public String toString() {
        return super.toString() + " (Desconto: 5%)";
    }
}

