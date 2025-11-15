import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Venda {
    private Cliente cliente;
    private Jogo jogo;
    private double valorPago;
    private LocalDateTime dataVenda;
    private static int proximoId = 1;
    private int id;

    public Venda(Cliente cliente, Jogo jogo, double valorPago) {
        this.id = proximoId++;
        this.cliente = cliente;
        this.jogo = jogo;
        this.valorPago = valorPago;
        this.dataVenda = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Jogo getJogo() {
        return jogo;
    }

    public double getValorPago() {
        return valorPago;
    }

    public LocalDateTime getDataVenda() {
        return dataVenda;
    }

    public String getDataVendaFormatada() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return dataVenda.format(formatter);
    }

    @Override
    public String toString() {
        return String.format("Venda #%d | Cliente: %s | Jogo: %s | Valor: R$ %.2f | Data: %s",
                id, cliente.getNome(), jogo.getGenero(), valorPago, getDataVendaFormatada());
    }
}


