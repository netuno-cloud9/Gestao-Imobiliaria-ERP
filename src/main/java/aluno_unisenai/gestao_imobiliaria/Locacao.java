/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aluno_unisenai.gestao_imobiliaria;

import java.util.Date;

/**
 * Classe Locacao que gerencia o aluguel de imóveis.
 * Contém informações sobre a locação, como imóvel, locatário, corretor e proprietário.
 * Permite enviar cobranças e realizar pagamentos.
 * 
 * @author instrutor
 */
public class Locacao {
    private Date dataInicio; // Data de início da locação
    private Date dataTermino; // Data de término da locação (opcional)
    private Date dataPagamento; // Data do último pagamento (opcional)
    private Imovel imovel; // Imóvel locado
    private Locatario locatario; // Locatário responsável pelo aluguel
    private Corretor corretor; // Corretor envolvido na locação
    private Proprietario proprietario; // Proprietário do imóvel

    /**
     * Construtor da classe Locacao.
     * 
     * @param imovel O imóvel locado.
     * @param locatario O locatário responsável.
     * @param corretor O corretor envolvido.
     * @param proprietario O proprietário do imóvel.
     * @param dataInicio A data de início da locação.
     */
    public Locacao(Imovel imovel, Locatario locatario, Corretor corretor, Proprietario proprietario, Date dataInicio) {
        this.imovel = imovel;
        this.locatario = locatario;
        this.corretor = corretor;
        this.proprietario = proprietario;
        this.dataInicio = dataInicio;
    }

    /**
     * Envia uma cobrança para o locatário.
     * Inclui o valor do aluguel calculado.
     */
    public void enviarCobranca() {
        double valorAluguel = imovel.calcularAluguel();
        System.out.println("E-mail do locatário: " + locatario.getEmail());
        System.out.println("O valor referente ao seu aluguel neste mês foi de R$" + valorAluguel);
    }

    /**
     * Realiza o pagamento ao proprietário e ao corretor.
     * Deduz a comissão do corretor e transfere o valor líquido ao proprietário.
     * Inclui mensagens no console informando o pagamento.
     */
    public void pagarProprietario() {
        double valorAluguel = imovel.calcularAluguel();
        double comissaoCorretor = valorAluguel * corretor.getComissao(); // Percentual de comissão do corretor
        double valorLiquido = valorAluguel - comissaoCorretor;

        // Transferir valores para o proprietário e o corretor
        proprietario.receber(valorLiquido);
        corretor.receber(comissaoCorretor);

        // Mensagens no console
        System.out.println("Pagamento realizado ao proprietário " + proprietario.getNome() +
                           " no valor de R$" + valorLiquido);
        System.out.println("Comissão de R$" + comissaoCorretor + " paga ao corretor " + corretor.getNome());
    }

    /**
     * Retorna o imóvel associado à locação.
     * 
     * @return O imóvel locado.
     */
    public Imovel getImovel() {
        return imovel;
    }

    /**
     * Retorna o locatário associado à locação.
     * 
     * @return O locatário responsável.
     */
    public Locatario getLocatario() {
        return locatario;
    }

    /**
     * Retorna o proprietário associado à locação.
     * 
     * @return O proprietário do imóvel.
     */
    public Proprietario getProprietario() {
        return proprietario;
    }

    /**
     * Retorna o corretor associado à locação.
     * 
     * @return O corretor envolvido na locação.
     */
    public Corretor getCorretor() {
        return corretor;
    }

    /**
     * Retorna a data de início da locação.
     * 
     * @return A data de início da locação.
     */
    public Date getDataInicio() {
        return dataInicio;
    }

    /**
     * (Opcional) Define a data de término da locação.
     * 
     * @param dataTermino A data de término da locação.
     */
    public void setDataTermino(Date dataTermino) {
        this.dataTermino = dataTermino;
    }

    /**
     * (Opcional) Retorna a data de término da locação.
     * 
     * @return A data de término da locação, ou null se não definida.
     */
    public Date getDataTermino() {
        return dataTermino;
    }

    /**
     * (Opcional) Define a data do último pagamento.
     * 
     * @param dataPagamento A data do pagamento.
     */
    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    /**
     * (Opcional) Retorna a data do último pagamento.
     * 
     * @return A data do último pagamento, ou null se não definida.
     */
    public Date getDataPagamento() {
        return dataPagamento;
    }
}
