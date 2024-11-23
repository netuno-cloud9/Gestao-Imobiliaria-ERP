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
    private final String registro; // Registro do corretor
    private final Date dataAdmissao; // Data de admissão do corretor
    private final double comissao; // Percentual de comissão
    private double totalComissaoAcumulada; // Total acumulado de comissões

    /**
     * Construtor da classe Corretor.
     * 
     * @param nome Nome do corretor.
     * @param telefone Telefone do corretor.
     * @param endereco Endereco do corretor.
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

    /**
     * Método para registrar o recebimento de um valor, adicionando à comissão acumulada.
     * 
     * @param valor Valor recebido (base para o cálculo da comissão).
     */
    @Override
    public void receber(double valor) {
        if (valor <= 0) {
            System.out.println("O valor recebido deve ser positivo.");
            return;
        }
        double valorComissao = valor * comissao; // Calcula a comissão com base no percentual
        totalComissaoAcumulada += valorComissao;
        System.out.println("O corretor " + nome + " recebeu R$" + valor + " com comissao de R$" + valorComissao);
    }

    /**
     * Método para sacar valores da comissão acumulada do corretor.
     * 
     * @param valor Valor a ser sacado.
     */
    public void sacarComissoes(double valor) {
        if (valor <= 0) {
            System.out.println("O valor de saque deve ser positivo.");
            return;
        }
        if (valor <= totalComissaoAcumulada) {
            totalComissaoAcumulada -= valor;
            System.out.println("O corretor " + nome + " sacou R$" + valor + ". Saldo restante: R$" + totalComissaoAcumulada);
        } else {
            System.out.println("Saldo insuficiente para saque de R$" + valor + ". Saldo disponivel: R$" + totalComissaoAcumulada);
        }
    }

    /**
     * Retorna o saldo atual de comissões acumuladas do corretor.
     * 
     * @return Saldo de comissões acumuladas.
     */
    public double getSaldoComissoes() {
        return totalComissaoAcumulada;
    }

    /**
     * Retorna o percentual de comissão do corretor.
     * 
     * @return Percentual de comissão.
     */
    public double getComissao() {
        return comissao;
    }

    @Override
    public String toString() {
        return "Corretor: " + nome + "\n" +
               "Telefone: " + telefone + "\n" +
               "Endereco: " + endereco + "\n" +
               "CPF: " + cpf + "\n" +
               "Registro: " + registro + "\n" +
               "Data de Admissao: " + dataAdmissao + "\n" +
               "Comissao (%): " + (comissao * 100) + "%\n" +
               "Total de Comissoes Acumuladas: R$" + totalComissaoAcumulada;
    }
}
