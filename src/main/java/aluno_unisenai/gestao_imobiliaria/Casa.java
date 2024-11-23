package aluno_unisenai.gestao_imobiliaria;

/**
 * Classe Casa que representa um imóvel do tipo casa.
 * Sobrescreve o método calcularAluguel e adiciona uma implementação para toString.
 */
public class Casa extends Imovel {

    // Atributo específico de Casa
    private final int numeroAndares;

    /**
     * Construtor da classe Casa.
     * 
     * @param codigo Código único da casa.
     * @param endereco Endereço da casa.
     * @param valorLocacao Valor base da locação.
     * @param vagasGaragem Número de vagas na garagem.
     * @param quartos Número de quartos.
     * @param banheiros Número de banheiros.
     * @param numeroAndares Número de andares da casa.
     */
    public Casa(int codigo, String endereco, double valorLocacao, int vagasGaragem, int quartos, int banheiros, int numeroAndares) {
        super(codigo, endereco, valorLocacao, vagasGaragem, quartos, banheiros);
        this.numeroAndares = numeroAndares;
    }

    /**
     * Implementação do método calcularAluguel.
     * Calcula o aluguel com base no valor da locação.
     * 
     * @return Valor do aluguel.
     */
    @Override
    public double calcularAluguel() {
        return valorLocacao;
    }

    /**
     * Sobrescreve o método toString para fornecer informações detalhadas da casa.
     * 
     * @return String contendo os detalhes da casa.
     */
    @Override
    public String toString() {
        return "Casa {" +
                "Codigo: " + codigo +
                ", Endereço: " + endereco +
                ", Valor de Locacao: R$" + valorLocacao +
                ", Vagas na Garagem: " + vagasGaragem +
                ", Quartos: " + quartos +
                ", Banheiros: " + banheiros +
                ", Numero de Andares: " + numeroAndares +
                '}';
    }
}
