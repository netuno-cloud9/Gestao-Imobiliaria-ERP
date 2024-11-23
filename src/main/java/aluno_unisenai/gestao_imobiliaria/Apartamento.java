/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aluno_unisenai.gestao_imobiliaria;

/**
 *
 * @author instrutor
 */
public class Apartamento extends Imovel {
    private int andar;
    private int numero;
    private double condominio;
    private double fundoReserva;
    private double investimentos;

    public Apartamento(int codigo, String endereco, double valorLocacao, int vagasGaragem, int quartos, int banheiros, int andar, int numero, double condominio, double fundoReserva, double investimentos) {
        super(codigo, endereco, valorLocacao, vagasGaragem, quartos, banheiros);
        this.andar = andar;
        this.numero = numero;
        this.condominio = condominio;
        this.fundoReserva = fundoReserva;
        this.investimentos = investimentos;
    }

    @Override
    public double calcularAluguel() {
        return valorLocacao + condominio - fundoReserva - investimentos;
    }
}


