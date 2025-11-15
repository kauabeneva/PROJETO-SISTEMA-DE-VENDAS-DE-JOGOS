public class ClientePremium extends Cliente {

    public ClientePremium(String nome, String email, double saldo) {
        super(nome, email, saldo);
    }

    @Override
    public double calcularDesconto(double preco) {
        return preco * 0.15;
    }

    @Override
    public String toString() {
        return super.toString() + " (Desconto: 15%)";
    }
}


