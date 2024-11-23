package aluno_unisenai.gestao_imobiliaria;

/**
 * Classe Apartamento que representa um tipo específico de imóvel.
 * Estende a classe abstrata Imovel e implementa o método calcularAluguel.
 */
public class Apartamento extends Imovel {
    private int andar;
    private int numero;
    private double condominio;
    private double fundoReserva;
    private double investimentos;

    /**
     * Construtor da classe Apartamento.
     *
     * @param codigo        Código do apartamento.
     * @param endereco      Endereco do apartamento.
     * @param valorLocacao  Valor base da locacão.
     * @param vagasGaragem  Número de vagas na garagem.
     * @param quartos       Número de quartos.
     * @param banheiros     Número de banheiros.
     * @param andar         Andar do apartamento.
     * @param numero        Número do apartamento.
     * @param condominio    Valor do condomínio.
     * @param fundoReserva  Valor destinado ao fundo de reserva.
     * @param investimentos Valor destinado a investimentos.
     */
    public Apartamento(int codigo, String endereco, double valorLocacao, int vagasGaragem, int quartos, int banheiros, 
                       int andar, int numero, double condominio, double fundoReserva, double investimentos) {
        super(codigo, endereco, valorLocacao, vagasGaragem, quartos, banheiros);
        this.andar = andar;
        this.numero = numero;
        this.condominio = condominio;
        this.fundoReserva = fundoReserva;
        this.investimentos = investimentos;
    }

    /**
     * Sobrescreve o método calcularAluguel da classe Imovel.
     * Calcula o valor do aluguel com base no valor de locacão,
     * adicionando o condomínio e subtraindo o fundo de reserva e investimentos.
     *
     * @return O valor calculado do aluguel.
     */
    @Override
    public double calcularAluguel() {
        return valorLocacao + condominio - fundoReserva - investimentos;
    }

    /**
     * Sobrescreve o método toString para fornecer uma representacão textual detalhada do apartamento.
     *
     * @return String contendo os detalhes do apartamento.
     */
    @Override
    public String toString() {
        return "Apartamento {" +
                "Codigo: " + codigo +
                ", Endereco: " + endereco +
                ", Valor de Locacao: R$" + valorLocacao +
                ", Vagas na Garagem: " + vagasGaragem +
                ", Quartos: " + quartos +
                ", Banheiros: " + banheiros +
                ", Andar: " + andar +
                ", Numero: " + numero +
                ", Condominio: R$" + condominio +
                ", Fundo de Reserva: R$" + fundoReserva +
                ", Investimentos: R$" + investimentos +
                '}';
    }

    // Getters e Setters opcionais, dependendo da necessidade

    public int getAndar() {
        return andar;
    }

    public void setAndar(int andar) {
        this.andar = andar;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public double getCondominio() {
        return condominio;
    }

    public void setCondominio(double condominio) {
        this.condominio = condominio;
    }

    public double getFundoReserva() {
        return fundoReserva;
    }

    public void setFundoReserva(double fundoReserva) {
        this.fundoReserva = fundoReserva;
    }

    public double getInvestimentos() {
        return investimentos;
    }

    public void setInvestimentos(double investimentos) {
        this.investimentos = investimentos;
    }
}
