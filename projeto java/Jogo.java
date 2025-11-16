public class Jogo {
    private String id;
    private String titulo;
    private String genero;
    private String plataforma;
    private int anoLancamento;
    private double valorAluguel;
    private boolean disponivel;
    private static int proximoId = 1;

    public Jogo(String titulo, String genero, String plataforma, int anoLancamento, double valorAluguel) {
        this.id = String.valueOf(proximoId++);
        this.titulo = titulo;
        this.genero = genero;
        this.plataforma = plataforma;
        this.setAnoLancamento(anoLancamento);
        this.valorAluguel = valorAluguel;
        this.disponivel = true;
    }

    public String getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getGenero() {
        return genero;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public int getAnoLancamento() {
        return anoLancamento;
    }

    public double getValorAluguel() {
        return valorAluguel;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }

    public void setAnoLancamento(int anoLancamento) {
        if (anoLancamento > 2025) {
            throw new IllegalArgumentException("Ano de lançamento não pode ser superior a 2025!");
        }
        this.anoLancamento = anoLancamento;
    }

    public void setValorAluguel(double valorAluguel) {
        if (valorAluguel < 0) {
            throw new IllegalArgumentException("Valor de aluguel não pode ser negativo!");
        }
        this.valorAluguel = valorAluguel;
    }

    public void setDisponibilidade(boolean disponivel) {
        this.disponivel = disponivel;
    }

    // Métodos do UML
    public String getDetalhes() {
        return String.format("ID: %s | Título: %s | Gênero: %s | Plataforma: %s | Ano: %d | Valor: R$ %.2f | Status: %s",
                id, titulo, genero, plataforma, anoLancamento, valorAluguel,
                disponivel ? "Disponível" : "Indisponível");
    }

    public double calcularValorAluguel(Cliente cliente) {
        double valorBase = this.valorAluguel;
        double desconto = cliente.calcularDesconto();
        return valorBase * (1 - desconto);
    }

    @Override
    public String toString() {
        return getDetalhes();
    }
}
