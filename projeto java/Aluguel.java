import java.util.Calendar;
import java.util.Date;

public class Aluguel {
    private String id;
    private Date dataAluguel;
    private Date dataDevolucaoPrevista;
    private Date dataDevolucaoReal;
    private double valorPago;
    private String status;
    private Cliente cliente;
    private Jogo jogo;
    private static int proximoId = 1;
    private static final int DIAS_ALUGUEL = 7; // Prazo padrão de 7 dias

    public Aluguel(Cliente cliente, Jogo jogo) {
        this.id = String.valueOf(proximoId++);
        this.cliente = cliente;
        this.jogo = jogo;
        this.dataAluguel = new Date();
        this.status = "ATIVO";
        
        // Define data de devolução prevista (7 dias a partir de hoje)
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, DIAS_ALUGUEL);
        this.dataDevolucaoPrevista = cal.getTime();
        
        // Calcula valor do aluguel com desconto
        double valorBase = jogo.calcularValorAluguel(cliente);
        double desconto = cliente.calcularDesconto();
        this.valorPago = valorBase * (1 - desconto);
    }

    public String getId() {
        return id;
    }

    public Date getDataAluguel() {
        return dataAluguel;
    }

    public Date getDataDevolucaoPrevista() {
        return dataDevolucaoPrevista;
    }

    public Date getDataDevolucaoReal() {
        return dataDevolucaoReal;
    }

    public double getValorPago() {
        return valorPago;
    }

    public String getStatus() {
        return status;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Jogo getJogo() {
        return jogo;
    }

    public double calcularMulta() {
        if (status.equals("FINALIZADO") || dataDevolucaoReal == null) {
            return 0.0;
        }
        
        if (dataDevolucaoReal.after(dataDevolucaoPrevista)) {
            long diasAtraso = (dataDevolucaoReal.getTime() - dataDevolucaoPrevista.getTime()) / (1000 * 60 * 60 * 24);
            return diasAtraso * 5.0; // R$ 5,00 por dia de atraso
        }
        
        return 0.0;
    }

    public void finalizarAluguel() {
        this.dataDevolucaoReal = new Date();
        this.status = "FINALIZADO";
    }

    public void estenderPrazo(int dias) {
        if (status.equals("ATIVO")) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(dataDevolucaoPrevista);
            cal.add(Calendar.DAY_OF_MONTH, dias);
            this.dataDevolucaoPrevista = cal.getTime();
            System.out.println("Prazo estendido em " + dias + " dias. Nova data: " + dataDevolucaoPrevista);
        } else {
            System.out.println("Não é possível estender prazo de aluguel finalizado.");
        }
    }

    @Override
    public String toString() {
        return String.format("Aluguel #%s | Cliente: %s | Jogo: %s | Status: %s | Valor: R$ %.2f",
                id, cliente.getNome(), jogo.getTitulo(), status, valorPago);
    }
}

