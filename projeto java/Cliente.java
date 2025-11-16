import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class Cliente {
    private String id;
    private String nome;
    private String email;
    private Date dataCadastro;
    private List<Aluguel> jogosAlugados;
    private static int proximoId = 1;

    public Cliente(String nome, String email) {
        this.id = String.valueOf(proximoId++);
        this.nome = nome;
        this.email = email;
        this.dataCadastro = new Date();
        this.jogosAlugados = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public List<Aluguel> getJogosAlugados() {
        return new ArrayList<>(jogosAlugados);
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    // Métodos do UML
    public void cadastrar() {
        // Implementação do cadastro
        System.out.println("Cliente cadastrado: " + this.nome);
    }

    public void atualizarDados() {
        // Implementação para atualizar dados
        System.out.println("Dados atualizados para: " + this.nome);
    }

    public boolean alugarJogo(Jogo jogo) {
        if (jogo == null || !jogo.isDisponivel()) {
            return false;
        }
        
        int limite = getLimiteJogos();
        if (jogosAlugados.size() >= limite) {
            System.out.println("Limite de jogos alugados atingido!");
            return false;
        }
        
        Aluguel aluguel = new Aluguel(this, jogo);
        jogosAlugados.add(aluguel);
        jogo.setDisponibilidade(false);
        return true;
    }

    public void devolverJogo(Jogo jogo) {
        Aluguel aluguelParaDevolver = null;
        for (Aluguel aluguel : jogosAlugados) {
            if (aluguel.getJogo().equals(jogo)) {
                aluguelParaDevolver = aluguel;
                break;
            }
        }
        
        if (aluguelParaDevolver != null) {
            aluguelParaDevolver.finalizarAluguel();
            jogosAlugados.remove(aluguelParaDevolver);
            jogo.setDisponibilidade(true);
        }
    }

    public List<Jogo> listarJogosAlugados() {
        List<Jogo> jogos = new ArrayList<>();
        for (Aluguel aluguel : jogosAlugados) {
            jogos.add(aluguel.getJogo());
        }
        return jogos;
    }

    // Métodos abstratos do UML
    public abstract double calcularDesconto();
    public abstract int getLimiteJogos();

    @Override
    public String toString() {
        return String.format("ID: %s | Nome: %s | Email: %s | Tipo: %s",
                id, nome, email, this.getClass().getSimpleName());
    }
}
