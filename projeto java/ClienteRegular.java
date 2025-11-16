public class ClienteRegular extends Cliente {
    private int pontosFidelidade;

    public ClienteRegular(String nome, String email) {
        super(nome, email);
        this.pontosFidelidade = 0;
    }

    public int getPontosFidelidade() {
        return pontosFidelidade;
    }

    public void acumularPontos(int pontos) {
        if (pontos > 0) {
            this.pontosFidelidade += pontos;
        }
    }

    @Override
    public double calcularDesconto() {
        // Desconto baseado em pontos de fidelidade
        // 1 ponto = 0.1% de desconto, máximo de 10%
        double descontoPercentual = Math.min(pontosFidelidade * 0.001, 0.10);
        return descontoPercentual;
    }

    @Override
    public int getLimiteJogos() {
        return 3; // Cliente regular pode alugar até 3 jogos
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | Pontos: %d | Limite: %d jogos",
                pontosFidelidade, getLimiteJogos());
    }
}

