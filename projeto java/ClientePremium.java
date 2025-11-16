import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ClientePremium extends Cliente {
    private Date dataVencimento;
    private double descontoMensal;

    public ClientePremium(String nome, String email, double descontoMensal) {
        super(nome, email);
        this.descontoMensal = descontoMensal;
        // Define vencimento para 30 dias a partir de hoje
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 30);
        this.dataVencimento = cal.getTime();
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public double getDescontoMensal() {
        return descontoMensal;
    }

    public void renovarAssinatura() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dataVencimento);
        cal.add(Calendar.DAY_OF_MONTH, 30);
        this.dataVencimento = cal.getTime();
        System.out.println("Assinatura renovada até: " + dataVencimento);
    }

    public boolean isAssinaturaValida() {
        return new Date().before(dataVencimento);
    }

    @Override
    public double calcularDesconto() {
        if (isAssinaturaValida()) {
            return descontoMensal;
        }
        return 0.0; // Sem desconto se assinatura vencida
    }

    @Override
    public int getLimiteJogos() {
        if (isAssinaturaValida()) {
            return 10; // Cliente premium pode alugar até 10 jogos
        }
        return 0; // Sem limite se assinatura vencida
    }

    public List<Jogo> acessarJogosExclusivos() {
        // Retorna lista de jogos exclusivos (implementação simplificada)
        // Em um sistema real, isso buscaria jogos marcados como exclusivos
        List<Jogo> jogosExclusivos = new ArrayList<>();
        if (isAssinaturaValida()) {
            // Lógica para buscar jogos exclusivos seria implementada aqui
        }
        return jogosExclusivos;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | Desconto: %.1f%% | Limite: %d jogos | Vencimento: %s",
                descontoMensal * 100, getLimiteJogos(), dataVencimento);
    }
}

