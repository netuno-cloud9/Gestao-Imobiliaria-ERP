package aluno_unisenai.gestao_imobiliaria;

import java.util.Date;
import java.util.Scanner;

public class GestaoImobiliariaApp {

    public static void main(String[] args) {
        System.out.println("=== GESTAO IMOBILIARIA ===");
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("Deseja inserir dados manualmente ou gerar relatorio de teste automaticamente?");
        System.out.println("Digite '1' para inserir manualmente ou '2' para gerar automaticamente:");
        int escolha = scanner.nextInt();
        scanner.nextLine(); // consumir a quebra de linha
        
        Corretor corretor = null;
        Proprietario proprietario = null;
        Locatario locatario = null;
        Imovel imovel = null;
        Locacao locacao = null;
        
        switch (escolha) {
            case 1 -> {
                // Insercao manual de dados
                System.out.println("\n=== Insercao de Dados Manual ===");
                corretor = criarCorretor(scanner);
                proprietario = criarProprietario(scanner);
                locatario = criarLocatario(scanner);
                // Perguntar se o imovel e casa ou apartamento
                System.out.println("O imovel e uma casa ou um apartamento?");
                System.out.println("Digite '1' para Casa ou '2' para Apartamento:");
                int tipoImovel = scanner.nextInt();
                scanner.nextLine(); // consumir a quebra de linha
                switch (tipoImovel) {
                    case 1:
                        imovel = criarCasa(scanner);
                        break;
                    case 2:
                        imovel = criarApartamento(scanner);
                        break;
                    default:
                        System.out.println("Opcao invalida para tipo de imovel.");
                        scanner.close();
                        return; // Criacao de locacao
                }
                locacao = new Locacao(imovel, locatario, corretor, proprietario, new Date());
            }

            case 2 -> {
                // Geracao automatica de dados de teste com contexto de Feira de Santana, Bahia
                
                // Criacao de um corretor
                corretor = new Corretor("John Lenon", "(75) 98765-4321", "Rua das Flores, 123, Feira de Santana, Bahia",
                        "123.456.789-00", "1234-BA", new Date(), 0.05);
                // Criacao de um proprietario
                proprietario = new Proprietario("Bob Dylan", "(75) 91234-5678",
                        "Rua dos Pinheiros, 456, Feira de Santana, Bahia", "987.654.321-00", "12345-6", "001");
                // Criacao de um locatario
                locatario = new Locatario("Garfield o Gato", "(75) 99876-5432",
                        "Av. Getulio Vargas, 789, Feira de Santana, Bahia", "321.654.987-00", "garfield@gmail.com", 5000);
                // Criacao de um imovel (por exemplo, Casa)
                imovel = new Casa(101, "Rua das Palmeiras, 123, Feira de Santana, Bahia", 2000, 2, 3, 2, 1);
                // Criacao de uma locacao
                locacao = new Locacao(imovel, locatario, corretor, proprietario, new Date());
            }
            default -> {
                System.out.println("Opcao invalida.");
                scanner.close();
                return;
            }
        }
        
        // Codigo comum para ambas as opcoes
        
        // Calculo do aluguel do imovel
        System.out.println("\nCalculo do aluguel do imovel");
        System.out.println("Aluguel calculado: R$" + imovel.calcularAluguel());
    
        // Exibicao de detalhes do imovel
        System.out.println("\nDetalhes do imovel");
        System.out.println(imovel); // Sobrescrita do metodo toString
    
        // Envio de cobranca para o locatario
        System.out.println("\nCobranca enviada para o locatario");
        locacao.enviarCobranca();
    
        // Pagamento ao proprietario
        System.out.println("\nPagamento realizado ao proprietario");
        locacao.pagarProprietario();
    
        // Registro de recebimento pelo corretor
        System.out.println("\nRecebimento registrado pelo corretor");
        corretor.receber(imovel.calcularAluguel()); // Valor do aluguel para verificar comportamento
        System.out.println("Saldo de comissoes acumuladas: R$" + corretor.getSaldoComissoes());
    
        // Saque da comissao do corretor
        System.out.println("\nSaque de comissao do corretor");
        corretor.sacarComissoes(100); // Valor valido
        corretor.sacarComissoes(10000); // Valor invalido (excede saldo)
    
        // Verificacao do saldo do proprietario
        System.out.println("\nSaldo do proprietario");
        System.out.println("Saldo de recebimentos: R$" + proprietario.getSaldoRecebimentos());
    
        // Geracao de relatorio personalizado
        System.out.println("\nGeracao de relatorio para corretor");
        RelatorioService relatorioService = new RelatorioService();
        try {
            System.out.println(relatorioService.gerarRelatorio(corretor.getNome()));
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao gerar relatorio: " + e.getMessage());
        }
    
        // Finalizacao do programa
        System.out.println("\n=== FIM DA GESTAO IMOBILIARIA ===");
        
        scanner.close();
    }
    
    private static Corretor criarCorretor(Scanner scanner) {
        System.out.println("\nInsira os dados do corretor:");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        System.out.print("Endereco: ");
        String endereco = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("CRECI: ");
        String creci = scanner.nextLine();
        System.out.print("Data de contratacao (dd/mm/yyyy): ");
        String dataStr = scanner.nextLine();
        Date dataContratacao = parseDate(dataStr);
        System.out.print("Comissao (%): ");
        double comissao = scanner.nextDouble() / 100;
        scanner.nextLine(); // consumir a quebra de linha
        return new Corretor(nome, telefone, endereco, cpf, creci, dataContratacao, comissao);
    }
    
    private static Proprietario criarProprietario(Scanner scanner) {
        System.out.println("\nInsira os dados do proprietario:");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        System.out.print("Endereco: ");
        String endereco = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("RG: ");
        String rg = scanner.nextLine();
        System.out.print("Codigo do proprietario: ");
        String codigoProprietario = scanner.nextLine();
        return new Proprietario(nome, telefone, endereco, cpf, rg, codigoProprietario);
    }
    
    private static Locatario criarLocatario(Scanner scanner) {
        System.out.println("\nInsira os dados do locatario:");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        System.out.print("Endereco: ");
        String endereco = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Renda mensal: ");
        double rendaMensal = scanner.nextDouble();
        scanner.nextLine(); // consumir a quebra de linha
        return new Locatario(nome, telefone, endereco, cpf, email, rendaMensal);
    }
    
    private static Casa criarCasa(Scanner scanner) {
        System.out.println("\nInsira os dados da casa:");
        System.out.print("Codigo do imovel: ");
        int codigo = scanner.nextInt();
        scanner.nextLine(); // consumir a quebra de linha
        System.out.print("Endereco: ");
        String endereco = scanner.nextLine();
        System.out.print("Valor do aluguel base: ");
        double valorAluguelBase = scanner.nextDouble();
        System.out.print("Numero de vagas de garagem: ");
        int vagasGaragem = scanner.nextInt();
        System.out.print("Numero de quartos: ");
        int quartos = scanner.nextInt();
        System.out.print("Numero de banheiros: ");
        int banheiros = scanner.nextInt();
        System.out.print("Numero de suites: ");
        int suites = scanner.nextInt();
        scanner.nextLine(); // consumir a quebra de linha
        return new Casa(codigo, endereco, valorAluguelBase, vagasGaragem, quartos, banheiros, suites);
    }
    
    private static Apartamento criarApartamento(Scanner scanner) {
        System.out.println("\nInsira os dados do apartamento:");
        System.out.print("Codigo do imovel: ");
        int codigo = scanner.nextInt();
        scanner.nextLine(); // consumir a quebra de linha
        System.out.print("Endereco: ");
        String endereco = scanner.nextLine();
        System.out.print("Valor do aluguel base: ");
        double valorAluguelBase = scanner.nextDouble();
        System.out.print("Numero de vagas de garagem: ");
        int vagasGaragem = scanner.nextInt();
        System.out.print("Numero de quartos: ");
        int quartos = scanner.nextInt();
        System.out.print("Numero de banheiros: ");
        int banheiros = scanner.nextInt();
        System.out.print("Andar: ");
        int andar = scanner.nextInt();
        System.out.print("Numero do apartamento: ");
        int numeroApartamento = scanner.nextInt();
        System.out.print("Valor do condominio: ");
        double valorCondominio = scanner.nextDouble();
        System.out.print("Area privativa: ");
        double areaPrivativa = scanner.nextDouble();
        System.out.print("Area total: ");
        double areaTotal = scanner.nextDouble();
        scanner.nextLine(); // consumir a quebra de linha
        return new Apartamento(codigo, endereco, valorAluguelBase, vagasGaragem, quartos, banheiros,
                andar, numeroApartamento, valorCondominio, areaPrivativa, areaTotal);
    }
    
    private static Date parseDate(String dateStr) {
        // Implementar conversao de String para Date, assumindo formato dd/mm/yyyy
        // Simplesmente retornando a data atual por simplicidade
        return new Date();
    }
}
