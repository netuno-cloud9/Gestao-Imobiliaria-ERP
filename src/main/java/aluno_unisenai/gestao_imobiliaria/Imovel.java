/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aluno_unisenai.gestao_imobiliaria;

/**
 * Classe abstrata Imovel que representa um imóvel genérico.
 * Fornece atributos e métodos comuns para subclasses como Casa ou Apartamento.
 * Possui um método abstrato para cálculo do aluguel.
 * 
 * @author instrutor
 */
public abstract class Imovel {
    // Atributos protegidos, acessíveis por subclasses
    protected int codigo; // Código único do imóvel
    protected String endereco; // Endereco do imóvel
    protected double valorLocacao; // Valor base da locacão
    protected int vagasGaragem; // Número de vagas na garagem
    protected int quartos; // Número de quartos
    protected int banheiros; // Número de banheiros

    /**
     * Construtor da classe Imovel.
     * 
     * @param codigo Código único do imóvel.
     * @param endereco Endereco do imóvel.
     * @param valorLocacao Valor base da locacão.
     * @param vagasGaragem Número de vagas na garagem.
     * @param quartos Número de quartos.
     * @param banheiros Número de banheiros.
     */
    public Imovel(int codigo, String endereco, double valorLocacao, int vagasGaragem, int quartos, int banheiros) {
        this.codigo = codigo;
        this.endereco = endereco;
        this.valorLocacao = valorLocacao;
        this.vagasGaragem = vagasGaragem;
        this.quartos = quartos;
        this.banheiros = banheiros;
    }

    /**
     * Método abstrato que deve ser implementado pelas subclasses para calcular o valor do aluguel.
     * 
     * @return O valor do aluguel calculado.
     */
    public abstract double calcularAluguel();

    /**
     * Retorna o endereco do imóvel.
     * 
     * @return Endereco do imóvel.
     */
    public String getEndereco() {
        return endereco;
    }

    // (Opcional) Adicione mais getters, caso necessário, para acessar outros atributos.
    /**
     * Retorna o código único do imóvel.
     * 
     * @return Código do imóvel.
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * Retorna o valor base da locacão.
     * 
     * @return Valor base da locacão.
     */
    public double getValorLocacao() {
        return valorLocacao;
    }

    /**
     * Retorna o número de vagas na garagem.
     * 
     * @return Número de vagas na garagem.
     */
    public int getVagasGaragem() {
        return vagasGaragem;
    }

    /**
     * Retorna o número de quartos.
     * 
     * @return Número de quartos.
     */
    public int getQuartos() {
        return quartos;
    }

    /**
     * Retorna o número de banheiros.
     * 
     * @return Número de banheiros.
     */
    public int getBanheiros() {
        return banheiros;
    }
}
