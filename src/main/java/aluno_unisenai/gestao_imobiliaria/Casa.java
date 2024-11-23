/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aluno_unisenai.gestao_imobiliaria;

/**
 *
 * @author instrutor
 */
public class Casa extends Imovel {
    private int qtdeAndares;

    public Casa(int codigo, String endereco, double valorLocacao, int vagasGaragem, int quartos, int banheiros, int qtdeAndares) {
        super(codigo, endereco, valorLocacao, vagasGaragem, quartos, banheiros);
        this.qtdeAndares = qtdeAndares;
    }

    @Override
    public double calcularAluguel() {
        return valorLocacao;
    }
}
