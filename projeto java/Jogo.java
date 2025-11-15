public class Jogo {
    private String genero;
    private int anoLancamento;
    private double preco;
    private boolean disponivel;
    private int id;
    private static int proximoId = 1;

    public Jogo(String genero, int anoLancamento, double preco) {
        this.id = proximoId++;
        this.genero = genero;
        this.setAnoLancamento(anoLancamento);
        this.preco = preco;
        this.disponivel = true;
    }
    public int getId() {
        return id;
    }

    public String getGenero() {
        return genero;
    }

    public int getAnoLancamento() {
        return anoLancamento;
    }

    public double getPreco() {
        return preco;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setAnoLancamento(int anoLancamento) {
        if (anoLancamento > 2025) {
            throw new IllegalArgumentException("Ano de lançamento não pode ser superior a 2025!");
        }
        this.anoLancamento = anoLancamento;
    }

    public void setPreco(double preco) {
        if (preco < 0) {
            throw new IllegalArgumentException("Preço não pode ser negativo!");
        }
        this.preco = preco;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    @Override
    public String toString() {
        return String.format("ID: %d | Gênero: %s | Ano: %d | Preço: R$ %.2f | Status: %s",
                id, genero, anoLancamento, preco, disponivel ? "Disponível" : "Indisponível");
    }
}


