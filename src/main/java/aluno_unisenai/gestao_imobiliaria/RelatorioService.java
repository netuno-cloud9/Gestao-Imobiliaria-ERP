package aluno_unisenai.gestao_imobiliaria;

public class RelatorioService {

    /**
     * Gera um relatório baseado no nome do corretor.
     * 
     * @param nomeCorretor Nome do corretor
     * @return String contendo o relatório
     * @throws IllegalArgumentException se o nome for inválido
     */
    public String gerarRelatorio(String nomeCorretor) {
        if (nomeCorretor == null || nomeCorretor.isBlank()) {
            throw new IllegalArgumentException("Nome do corretor não pode ser vazio.");
        }
        return "Relatório emitido para: " + nomeCorretor;
    }
}
