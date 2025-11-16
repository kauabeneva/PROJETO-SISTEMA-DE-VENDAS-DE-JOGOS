/**
 * INTEGRANTES DO GRUPO:
 * - Heitor de Oliveira Alves da Silva
 * - Isabeli Ayres Aragão
 * - Kauã de Souza Benevides
 * - Kelvin Demetrio dos Santos Pereira
 * - Leticia Melo Silva
 * - Lucas Bernardes
 * - Matheus Caetano de Souza
 * - Thais de Aguiar Vicente
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<Jogo> jogos = new ArrayList<>();
    private static List<Cliente> clientes = new ArrayList<>();
    private static List<Aluguel> alugueis = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;
        
        System.out.println("=== SISTEMA DE GERENCIAMENTO DE ALUGUEL DE JOGOS ===");
        
        do {
            exibirMenu();
            opcao = lerInteiro("Escolha uma opção: ");
            
            try {
                switch (opcao) {
                    case 1:
                        cadastrarJogo();
                        break;
                    case 2:
                        cadastrarCliente();
                        break;
                    case 3:
                        editarJogo();
                        break;
                    case 4:
                        editarCliente();
                        break;
                    case 5:
                        atualizarJogo();
                        break;
                    case 6:
                        atualizarCliente();
                        break;
                    case 7:
                        deletarJogo();
                        break;
                    case 8:
                        deletarCliente();
                        break;
                    case 9:
                        listarJogos();
                        break;
                    case 10:
                        listarClientes();
                        break;
                    case 11:
                        alugarJogo();
                        break;
                    case 12:
                        devolverJogo();
                        break;
                    case 13:
                        listarAlugueis();
                        break;
                    case 14:
                        exibirJogosAlugadosCliente();
                        break;
                    case 15:
                        System.out.println("Saindo do sistema...");
                        break;
                    default:
                        System.out.println("Opção inválida! Tente novamente.");
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
            
            if (opcao != 15) {
                System.out.println("\nPressione Enter para continuar...");
                scanner.nextLine();
            }
            
        } while (opcao != 15);
        
        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("\n========== MENU PRINCIPAL ==========");
        System.out.println("1  - Cadastrar Jogo");
        System.out.println("2  - Cadastrar Cliente");
        System.out.println("3  - Editar Jogo");
        System.out.println("4  - Editar Cliente");
        System.out.println("5  - Atualizar Status do Jogo");
        System.out.println("6  - Atualizar Dados do Cliente");
        System.out.println("7  - Deletar Jogo");
        System.out.println("8  - Deletar Cliente");
        System.out.println("9  - Listar Jogos");
        System.out.println("10 - Listar Clientes");
        System.out.println("11 - Alugar Jogo");
        System.out.println("12 - Devolver Jogo");
        System.out.println("13 - Listar Aluguéis");
        System.out.println("14 - Exibir Jogos Alugados por Cliente");
        System.out.println("15 - Sair");
        System.out.println("===================================");
    }

    private static void cadastrarJogo() {
        System.out.println("\n--- CADASTRO DE JOGO ---");
        String titulo = lerString("Título do jogo: ");
        String genero = lerString("Gênero do jogo: ");
        String plataforma = lerString("Plataforma: ");
        int ano = lerInteiro("Ano de lançamento: ");
        double valorAluguel = lerDouble("Valor do aluguel: R$ ");
        
        try {
            Jogo jogo = new Jogo(titulo, genero, plataforma, ano, valorAluguel);
            jogos.add(jogo);
            System.out.println("Jogo cadastrado com sucesso!");
            System.out.println(jogo.getDetalhes());
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao cadastrar jogo: " + e.getMessage());
        }
    }

    private static void editarJogo() {
        System.out.println("\n--- EDITAR JOGO ---");
        listarJogos();
        String id = lerString("Digite o ID do jogo para editar: ");
        
        Jogo jogo = buscarJogoPorId(id);
        if (jogo == null) {
            System.out.println("Jogo não encontrado!");
            return;
        }
        
        System.out.println("Jogo encontrado: " + jogo.getDetalhes());
        System.out.println("\nNovos dados (pressione Enter para manter o valor atual):");
        String titulo = lerString("Título (" + jogo.getTitulo() + "): ");
        String genero = lerString("Gênero (" + jogo.getGenero() + "): ");
        String plataforma = lerString("Plataforma (" + jogo.getPlataforma() + "): ");
        int ano = lerInteiro("Ano de lançamento (" + jogo.getAnoLancamento() + "): ");
        double valorAluguel = lerDouble("Valor do aluguel (R$ " + jogo.getValorAluguel() + "): ");
        
        try {
            if (!titulo.isEmpty()) jogo.setTitulo(titulo);
            if (!genero.isEmpty()) jogo.setGenero(genero);
            if (!plataforma.isEmpty()) jogo.setPlataforma(plataforma);
            jogo.setAnoLancamento(ano);
            jogo.setValorAluguel(valorAluguel);
            System.out.println("Jogo editado com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao editar jogo: " + e.getMessage());
        }
    }

    private static void atualizarJogo() {
        System.out.println("\n--- ATUALIZAR STATUS DO JOGO ---");
        listarJogos();
        String id = lerString("Digite o ID do jogo: ");
        
        Jogo jogo = buscarJogoPorId(id);
        if (jogo == null) {
            System.out.println("Jogo não encontrado!");
            return;
        }
        
        System.out.println("Status atual: " + (jogo.isDisponivel() ? "Disponível" : "Indisponível"));
        int opcao = lerInteiro("1 - Disponível | 2 - Indisponível: ");
        jogo.setDisponibilidade(opcao == 1);
        System.out.println("Status atualizado com sucesso!");
    }

    private static void deletarJogo() {
        System.out.println("\n--- DELETAR JOGO ---");
        listarJogos();
        String id = lerString("Digite o ID do jogo para deletar: ");
        
        Jogo jogo = buscarJogoPorId(id);
        if (jogo == null) {
            System.out.println("Jogo não encontrado!");
            return;
        }
        
        jogos.remove(jogo);
        System.out.println("Jogo deletado com sucesso!");
    }

    private static void listarJogos() {
        System.out.println("\n--- LISTA DE JOGOS ---");
        if (jogos.isEmpty()) {
            System.out.println("Nenhum jogo cadastrado.");
        } else {
            for (Jogo jogo : jogos) {
                System.out.println(jogo.getDetalhes());
            }
        }
    }

    private static Jogo buscarJogoPorId(String id) {
        for (Jogo jogo : jogos) {
            if (jogo.getId().equals(id)) {
                return jogo;
            }
        }
        return null;
    }

    private static void cadastrarCliente() {
        System.out.println("\n--- CADASTRO DE CLIENTE ---");
        String nome = lerString("Nome: ");
        String email = lerString("Email: ");
        
        System.out.println("Tipo de cliente:");
        System.out.println("1 - Regular");
        System.out.println("2 - Premium");
        int tipo = lerInteiro("Escolha: ");
        
        try {
            Cliente cliente;
            if (tipo == 1) {
                cliente = new ClienteRegular(nome, email);
            } else if (tipo == 2) {
                double descontoMensal = lerDouble("Desconto mensal (ex: 0.20 para 20%): ");
                cliente = new ClientePremium(nome, email, descontoMensal);
            } else {
                System.out.println("Tipo inválido! Criando como Regular.");
                cliente = new ClienteRegular(nome, email);
            }
            
            cliente.cadastrar();
            clientes.add(cliente);
            System.out.println("Cliente cadastrado com sucesso!");
            System.out.println(cliente);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao cadastrar cliente: " + e.getMessage());
        }
    }

    private static void editarCliente() {
        System.out.println("\n--- EDITAR CLIENTE ---");
        listarClientes();
        String id = lerString("Digite o ID do cliente para editar: ");
        
        Cliente cliente = buscarClientePorId(id);
        if (cliente == null) {
            System.out.println("Cliente não encontrado!");
            return;
        }
        
        System.out.println("Cliente encontrado: " + cliente);
        System.out.println("\nNovos dados (pressione Enter para manter o valor atual):");
        String nome = lerString("Nome (" + cliente.getNome() + "): ");
        String email = lerString("Email (" + cliente.getEmail() + "): ");
        
        try {
            if (!nome.isEmpty()) cliente.setNome(nome);
            if (!email.isEmpty()) cliente.setEmail(email);
            cliente.atualizarDados();
            System.out.println("Cliente editado com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao editar cliente: " + e.getMessage());
        }
    }

    private static void atualizarCliente() {
        System.out.println("\n--- ATUALIZAR DADOS DO CLIENTE ---");
        listarClientes();
        String id = lerString("Digite o ID do cliente: ");
        
        Cliente cliente = buscarClientePorId(id);
        if (cliente == null) {
            System.out.println("Cliente não encontrado!");
            return;
        }
        
        if (cliente instanceof ClienteRegular) {
            ClienteRegular clienteRegular = (ClienteRegular) cliente;
            System.out.println("Pontos de fidelidade atuais: " + clienteRegular.getPontosFidelidade());
            int pontos = lerInteiro("Adicionar pontos: ");
            clienteRegular.acumularPontos(pontos);
            System.out.println("Pontos atualizados com sucesso!");
        } else if (cliente instanceof ClientePremium) {
            ClientePremium clientePremium = (ClientePremium) cliente;
            System.out.println("Data de vencimento atual: " + clientePremium.getDataVencimento());
            int opcao = lerInteiro("1 - Renovar assinatura | 2 - Cancelar: ");
            if (opcao == 1) {
                clientePremium.renovarAssinatura();
            }
        }
    }

    private static void deletarCliente() {
        System.out.println("\n--- DELETAR CLIENTE ---");
        listarClientes();
        String id = lerString("Digite o ID do cliente para deletar: ");
        
        Cliente cliente = buscarClientePorId(id);
        if (cliente == null) {
            System.out.println("Cliente não encontrado!");
            return;
        }
        
        clientes.remove(cliente);
        System.out.println("Cliente deletado com sucesso!");
    }

    private static void listarClientes() {
        System.out.println("\n--- LISTA DE CLIENTES ---");
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
        } else {
            for (Cliente cliente : clientes) {
                System.out.println(cliente);
            }
        }
    }

    private static Cliente buscarClientePorId(String id) {
        for (Cliente cliente : clientes) {
            if (cliente.getId().equals(id)) {
                return cliente;
            }
        }
        return null;
    }
    
    private static void alugarJogo() {
        System.out.println("\n--- ALUGAR JOGO ---");
        
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado!");
            return;
        }
        
        if (jogos.isEmpty()) {
            System.out.println("Nenhum jogo cadastrado!");
            return;
        }
        
        listarClientes();
        String idCliente = lerString("Digite o ID do cliente: ");
        Cliente cliente = buscarClientePorId(idCliente);
        
        if (cliente == null) {
            System.out.println("Cliente não encontrado!");
            return;
        }
        
        listarJogos();
        String idJogo = lerString("Digite o ID do jogo: ");
        Jogo jogo = buscarJogoPorId(idJogo);
        
        if (jogo == null) {
            System.out.println("Jogo não encontrado!");
            return;
        }
        
        if (!jogo.isDisponivel()) {
            System.out.println("Jogo indisponível para aluguel!");
            return;
        }
        
        boolean sucesso = cliente.alugarJogo(jogo);
        if (sucesso) {
            Aluguel aluguel = cliente.getJogosAlugados().get(cliente.getJogosAlugados().size() - 1);
            alugueis.add(aluguel);
            System.out.println("\n--- DETALHES DO ALUGUEL ---");
            System.out.println("Cliente: " + cliente.getNome());
            System.out.println("Jogo: " + jogo.getTitulo());
            System.out.println("Valor pago: R$ " + String.format("%.2f", aluguel.getValorPago()));
            System.out.println("Data de devolução prevista: " + aluguel.getDataDevolucaoPrevista());
            System.out.println("\nAluguel realizado com sucesso!");
        }
    }

    private static void devolverJogo() {
        System.out.println("\n--- DEVOLVER JOGO ---");
        
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado!");
            return;
        }
        
        listarClientes();
        String idCliente = lerString("Digite o ID do cliente: ");
        Cliente cliente = buscarClientePorId(idCliente);
        
        if (cliente == null) {
            System.out.println("Cliente não encontrado!");
            return;
        }
        
        List<Jogo> jogosAlugados = cliente.listarJogosAlugados();
        if (jogosAlugados.isEmpty()) {
            System.out.println("Cliente não possui jogos alugados.");
            return;
        }
        
        System.out.println("\nJogos alugados:");
        for (int i = 0; i < jogosAlugados.size(); i++) {
            System.out.println((i + 1) + " - " + jogosAlugados.get(i).getTitulo());
        }
        
        int escolha = lerInteiro("Escolha o jogo para devolver (número): ");
        if (escolha < 1 || escolha > jogosAlugados.size()) {
            System.out.println("Opção inválida!");
            return;
        }
        
        Jogo jogo = jogosAlugados.get(escolha - 1);
        cliente.devolverJogo(jogo);
        
        // Atualizar aluguel na lista
        for (Aluguel aluguel : alugueis) {
            if (aluguel.getJogo().equals(jogo) && aluguel.getStatus().equals("ATIVO")) {
                double multa = aluguel.calcularMulta();
                if (multa > 0) {
                    System.out.println("Multa por atraso: R$ " + String.format("%.2f", multa));
                }
                break;
            }
        }
        
        System.out.println("Jogo devolvido com sucesso!");
    }

    private static void listarAlugueis() {
        System.out.println("\n--- LISTA DE ALUGUÉIS ---");
        if (alugueis.isEmpty()) {
            System.out.println("Nenhum aluguel registrado.");
        } else {
            for (Aluguel aluguel : alugueis) {
                System.out.println(aluguel);
            }
        }
    }

    private static void exibirJogosAlugadosCliente() {
        System.out.println("\n--- JOGOS ALUGADOS POR CLIENTE ---");
        
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado!");
            return;
        }
        
        listarClientes();
        String idCliente = lerString("Digite o ID do cliente: ");
        Cliente cliente = buscarClientePorId(idCliente);
        
        if (cliente == null) {
            System.out.println("Cliente não encontrado!");
            return;
        }
        
        List<Jogo> jogosAlugados = cliente.listarJogosAlugados();
        
        if (jogosAlugados.isEmpty()) {
            System.out.println("Cliente não possui jogos alugados.");
        } else {
            System.out.println("\nJogos alugados por " + cliente.getNome() + ":");
            System.out.println("----------------------------------------");
            for (Jogo jogo : jogosAlugados) {
                System.out.println(jogo.getDetalhes());
            }
            System.out.println("\nTotal de jogos alugados: " + jogosAlugados.size());
            System.out.println("Limite de jogos: " + cliente.getLimiteJogos());
        }
    }

    private static String lerString(String mensagem) {
        System.out.print(mensagem);
        return scanner.nextLine().trim();
    }

    private static int lerInteiro(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                int valor = Integer.parseInt(scanner.nextLine().trim());
                return valor;
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido! Digite um número inteiro.");
            }
        }
    }

    private static double lerDouble(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                double valor = Double.parseDouble(scanner.nextLine().trim().replace(",", "."));
                return valor;
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido! Digite um número válido.");
            }
        }
    }
}
