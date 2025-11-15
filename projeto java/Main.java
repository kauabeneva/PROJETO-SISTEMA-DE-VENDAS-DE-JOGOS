/**
 * INTEGRANTES DO GRUPO:
 * - Heitor de Oliveira Alves da Silva RA: 925111457    
 * - Isabeli Ayres Aragão RA: 925103668
 * - Kauã de Souza Benevides RA: 925100241
 * - Kelvin Demetrio dos Santos Pereira RA: 923109986
 * - Leticia Melo Silva RA: 925111960
 * - Lucas Bernardes RA: 925109252
 * - Matheus Caetano de Souza RA: 925110653
 * - Thais de Aguiar Vicente RA: 925100177
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<Jogo> jogos = new ArrayList<>();
    private static List<Cliente> clientes = new ArrayList<>();
    private static List<Venda> vendas = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;
        
        System.out.println("=== SISTEMA DE GERENCIAMENTO DE JOGOS ===");
        
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
                        realizarVenda();
                        break;
                    case 12:
                        exibirHistoricoCompras();
                        break;
                    case 13:
                        System.out.println("Saindo do sistema...");
                        break;
                    default:
                        System.out.println("Opção inválida! Tente novamente.");
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
            
            if (opcao != 13) {
                System.out.println("\nPressione Enter para continuar...");
                scanner.nextLine();
            }
            
        } while (opcao != 13);
        
        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("\n========== MENU PRINCIPAL ==========");
        System.out.println("1  - Cadastrar Jogo");
        System.out.println("2  - Cadastrar Cliente");
        System.out.println("3  - Editar Jogo");
        System.out.println("4  - Editar Cliente");
        System.out.println("5  - Atualizar Jogo");
        System.out.println("6  - Atualizar Cliente");
        System.out.println("7  - Deletar Jogo");
        System.out.println("8  - Deletar Cliente");
        System.out.println("9  - Listar Jogos");
        System.out.println("10 - Listar Clientes");
        System.out.println("11 - Realizar Venda");
        System.out.println("12 - Exibir Histórico de Compras");
        System.out.println("13 - Sair");
        System.out.println("===================================");
    }

    private static void cadastrarJogo() {
        System.out.println("\n--- CADASTRO DE JOGO ---");
        String genero = lerString("Gênero do jogo: ");
        int ano = lerInteiro("Ano de lançamento: ");
        double preco = lerDouble("Preço: R$ ");
        
        try {
            Jogo jogo = new Jogo(genero, ano, preco);
            jogos.add(jogo);
            System.out.println("Jogo cadastrado com sucesso!");
            System.out.println(jogo);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao cadastrar jogo: " + e.getMessage());
        }
    }

    private static void editarJogo() {
        System.out.println("\n--- EDITAR JOGO ---");
        listarJogos();
        int id = lerInteiro("Digite o ID do jogo para editar: ");
        
        Jogo jogo = buscarJogoPorId(id);
        if (jogo == null) {
            System.out.println("Jogo não encontrado!");
            return;
        }
        
        System.out.println("Jogo encontrado: " + jogo);
        System.out.println("\nNovos dados:");
        String genero = lerString("Gênero (" + jogo.getGenero() + "): ");
        int ano = lerInteiro("Ano de lançamento (" + jogo.getAnoLancamento() + "): ");
        double preco = lerDouble("Preço (R$ " + jogo.getPreco() + "): ");
        
        try {
            jogo.setGenero(genero.isEmpty() ? jogo.getGenero() : genero);
            jogo.setAnoLancamento(ano);
            jogo.setPreco(preco);
            System.out.println("Jogo editado com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao editar jogo: " + e.getMessage());
        }
    }

    private static void atualizarJogo() {
        System.out.println("\n--- ATUALIZAR STATUS DO JOGO ---");
        listarJogos();
        int id = lerInteiro("Digite o ID do jogo: ");
        
        Jogo jogo = buscarJogoPorId(id);
        if (jogo == null) {
            System.out.println("Jogo não encontrado!");
            return;
        }
        
        System.out.println("Status atual: " + (jogo.isDisponivel() ? "Disponível" : "Indisponível"));
        int opcao = lerInteiro("1 - Disponível | 2 - Indisponível: ");
        jogo.setDisponivel(opcao == 1);
        System.out.println("Status atualizado com sucesso!");
    }

    private static void deletarJogo() {
        System.out.println("\n--- DELETAR JOGO ---");
        listarJogos();
        int id = lerInteiro("Digite o ID do jogo para deletar: ");
        
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
                System.out.println(jogo);
            }
        }
    }

    private static Jogo buscarJogoPorId(int id) {
        for (Jogo jogo : jogos) {
            if (jogo.getId() == id) {
                return jogo;
            }
        }
        return null;
    }

    private static void cadastrarCliente() {
        System.out.println("\n--- CADASTRO DE CLIENTE ---");
        String nome = lerString("Nome: ");
        String email = lerString("Email: ");
        double saldo = lerDouble("Saldo inicial: R$ ");
        
        System.out.println("Tipo de cliente:");
        System.out.println("1 - Purpura");
        System.out.println("2 - Gold");
        int tipo = lerInteiro("Escolha: ");
        
        try {
            Cliente cliente;
            if (tipo == 1) {
                cliente = new ClientePurpura(nome, email, saldo);
            } else if (tipo == 2) {
                cliente = new ClienteGold(nome, email, saldo);
            } else {
                System.out.println("Tipo inválido! Criando como Purpura.");
                cliente = new ClientePurpura(nome, email, saldo);
            }
            
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
        int id = lerInteiro("Digite o ID do cliente para editar: ");
        
        Cliente cliente = buscarClientePorId(id);
        if (cliente == null) {
            System.out.println("Cliente não encontrado!");
            return;
        }
        
        System.out.println("Cliente encontrado: " + cliente);
        System.out.println("\nNovos dados:");
        String nome = lerString("Nome (" + cliente.getNome() + "): ");
        String email = lerString("Email (" + cliente.getEmail() + "): ");
        
        try {
            cliente.setNome(nome.isEmpty() ? cliente.getNome() : nome);
            cliente.setEmail(email.isEmpty() ? cliente.getEmail() : email);
            System.out.println("Cliente editado com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao editar cliente: " + e.getMessage());
        }
    }

    private static void atualizarCliente() {
        System.out.println("\n--- ATUALIZAR SALDO DO CLIENTE ---");
        listarClientes();
        int id = lerInteiro("Digite o ID do cliente: ");
        
        Cliente cliente = buscarClientePorId(id);
        if (cliente == null) {
            System.out.println("Cliente não encontrado!");
            return;
        }
        
        System.out.println("Saldo atual: R$ " + cliente.getSaldo());
        double novoSaldo = lerDouble("Novo saldo: R$ ");
        
        try {
            cliente.setSaldo(novoSaldo);
            System.out.println("Saldo atualizado com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao atualizar saldo: " + e.getMessage());
        }
    }

    private static void deletarCliente() {
        System.out.println("\n--- DELETAR CLIENTE ---");
        listarClientes();
        int id = lerInteiro("Digite o ID do cliente para deletar: ");
        
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

    private static Cliente buscarClientePorId(int id) {
        for (Cliente cliente : clientes) {
            if (cliente.getIdUsuario() == id) {
                return cliente;
            }
        }
        return null;
    }
    
    private static void realizarVenda() {
        System.out.println("\n--- REALIZAR VENDA ---");
        
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado!");
            return;
        }
        
        if (jogos.isEmpty()) {
            System.out.println("Nenhum jogo cadastrado!");
            return;
        }
        
        listarClientes();
        int idCliente = lerInteiro("Digite o ID do cliente: ");
        Cliente cliente = buscarClientePorId(idCliente);
        
        if (cliente == null) {
            System.out.println("Cliente não encontrado!");
            return;
        }
        
        listarJogos();
        int idJogo = lerInteiro("Digite o ID do jogo: ");
        Jogo jogo = buscarJogoPorId(idJogo);
        
        if (jogo == null) {
            System.out.println("Jogo não encontrado!");
            return;
        }
        
        if (!jogo.isDisponivel()) {
            System.out.println("Jogo indisponível para venda!");
            return;
        }
        
        try {
            double desconto = cliente.calcularDesconto(jogo.getPreco());
            double valorFinal = jogo.getPreco() - desconto;
            
            System.out.println("\n--- DETALHES DA VENDA ---");
            System.out.println("Cliente: " + cliente.getNome());
            System.out.println("Jogo: " + jogo.getGenero());
            System.out.println("Preço original: R$ " + String.format("%.2f", jogo.getPreco()));
            System.out.println("Desconto: R$ " + String.format("%.2f", desconto));
            System.out.println("Valor final: R$ " + String.format("%.2f", valorFinal));
            System.out.println("Saldo atual: R$ " + String.format("%.2f", cliente.getSaldo()));
            
            cliente.debitarSaldo(valorFinal);
            jogo.setDisponivel(false);
            cliente.adicionarJogoComprado(jogo);
            
            Venda venda = new Venda(cliente, jogo, valorFinal);
            vendas.add(venda);
            
            System.out.println("\nVenda realizada com sucesso!");
            System.out.println("Saldo restante: R$ " + String.format("%.2f", cliente.getSaldo()));
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao realizar venda: " + e.getMessage());
        }
    }

    private static void exibirHistoricoCompras() {
        System.out.println("\n--- HISTÓRICO DE COMPRAS ---");
        
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado!");
            return;
        }
        
        listarClientes();
        int idCliente = lerInteiro("Digite o ID do cliente: ");
        Cliente cliente = buscarClientePorId(idCliente);
        
        if (cliente == null) {
            System.out.println("Cliente não encontrado!");
            return;
        }
        
        List<Jogo> jogosComprados = cliente.getJogosComprados();
        
        if (jogosComprados.isEmpty()) {
            System.out.println("Cliente não possui compras registradas.");
        } else {
            System.out.println("\nCompras de " + cliente.getNome() + ":");
            System.out.println("----------------------------------------");
            
            for (Venda venda : vendas) {
                if (venda.getCliente().getIdUsuario() == cliente.getIdUsuario()) {
                    System.out.println(venda);
                }
            }
            
            System.out.println("\nTotal de jogos comprados: " + jogosComprados.size());
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



