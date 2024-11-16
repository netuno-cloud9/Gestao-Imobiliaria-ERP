package aluno_unisenai.gestao_imobiliaria;

import java.util.Date;

/**
 * Classe Corretor, representa um corretor de imóveis.
 * Herda atributos e métodos da classe abstrata Pessoa.
 * Implementa a interface RecebeValor.
 * 
 * @author instrutor
 */
public class Corretor extends Pessoa implements RecebeValor {
    private String registro; // Registro do corretor
    private Date dataAdmissao; // Data de admissão do corretor
    private double comissao; // Percentual de comissão
    private double totalComissaoAcumulada; // Total acumulado de comissões

    /**
     * Construtor da classe Corretor.
     * 
     * @param nome Nome do corretor.
     * @param telefone Telefone do corretor.
     * @param endereco Endereço do corretor.
     * @param cpf CPF do corretor.
     * @param registro Registro do corretor.
     * @param dataAdmissao Data de admissão.
     * @param comissao Percentual de comissão.
     */
    public Corretor(String nome, String telefone, String endereco, String cpf, String registro, Date dataAdmissao, double comissao) {
        super(nome, telefone, endereco, cpf); // Chama o construtor da classe Pessoa
        this.registro = registro;
        this.dataAdmissao = dataAdmissao;
        this.comissao = comissao;
        this.totalComissaoAcumulada = 0;
    }

    @Override
    public void receber(double valor) {
        totalComissaoAcumulada += valor;
        System.out.println("O corretor " + nome + " recebeu o valor de R$" + valor);
    }

    public void sacarComissao(double valor) {
        if (valor <= totalComissaoAcumulada) {
            totalComissaoAcumulada -= valor;
            System.out.println("O corretor " + nome + " sacou R$" + valor);
        } else {
            System.out.println("Saldo insuficiente para saque de comissões.");
        }
    }

    public double getSaldoComissoes() {
        return totalComissaoAcumulada;
    }

    public double getComissao() {
        return comissao;
    }

    @Override
    public String toString() {
        return "Corretor: " + nome + "\n" +
               "Telefone: " + telefone + "\n" +
               "Endereço: " + endereco + "\n" +
               "CPF: " + cpf + "\n" +
               "Registro: " + registro + "\n" +
               "Data de Admissão: " + dataAdmissao + "\n" +
               "Comissão (%): " + (comissao * 100) + "\n" +
               "Total de Comissões Acumuladas: R$" + totalComissaoAcumulada;
    }
}
