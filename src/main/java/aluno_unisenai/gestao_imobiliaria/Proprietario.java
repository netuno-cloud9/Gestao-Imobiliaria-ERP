package aluno_unisenai.gestao_imobiliaria;

/**
 * Classe Proprietario, que representa o proprietário de um imóvel.
 * Herda atributos e métodos da classe abstrata Pessoa.
 * Implementa a interface RecebeValor.
 * 
 * @author instrutor
 */
public class Proprietario extends Pessoa implements RecebeValor {
    private String conta; // Conta bancária do proprietário
    private String agencia; // Agência bancária do proprietário
    private double saldoRecebimentos; // Saldo acumulado de recebimentos

    /**
     * Construtor da classe Proprietario.
     * 
     * @param nome Nome do proprietário.
     * @param telefone Telefone do proprietário.
     * @param endereco Endereco do proprietário.
     * @param cpf CPF do proprietário.
     * @param conta Conta bancária.
     * @param agencia Agência bancária.
     */
    public Proprietario(String nome, String telefone, String endereco, String cpf, String conta, String agencia) {
        super(nome, telefone, endereco, cpf); // Chama o construtor da classe Pessoa
        this.conta = conta;
        this.agencia = agencia;
        this.saldoRecebimentos = 0;
    }

    @Override
    public void receber(double valor) {
        saldoRecebimentos += valor;
        System.out.println("O proprietario " + nome + " recebeu o valor de R$" + valor);
    }

    public double getSaldoRecebimentos() {
        return saldoRecebimentos;
    }

    @Override
    public String toString() {
        return "Proprietario: " + nome + "\n" +
               "Telefone: " + telefone + "\n" +
               "Endereco: " + endereco + "\n" +
               "CPF: " + cpf + "\n" +
               "Conta Bancária: " + conta + "\n" +
               "Agencia: " + agencia + "\n" +
               "Saldo de Recebimentos: R$" + saldoRecebimentos;
    }
}
