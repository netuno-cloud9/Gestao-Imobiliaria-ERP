package aluno_unisenai.gestao_imobiliaria;

/**
 * Classe abstrata Pessoa, representando um conceito genérico de pessoa.
 * Fornece atributos e métodos básicos que podem ser reutilizados por subclasses.
 * 
 * @author instrutor
 */
public abstract class Pessoa {
    protected String nome; // Nome da pessoa
    protected String telefone; // Telefone da pessoa
    protected String endereco; // Endereço da pessoa
    protected String cpf; // CPF da pessoa

    /**
     * Construtor da classe Pessoa.
     * 
     * @param nome Nome da pessoa.
     * @param telefone Telefone da pessoa.
     * @param endereco Endereço da pessoa.
     * @param cpf CPF da pessoa.
     */
    public Pessoa(String nome, String telefone, String endereco, String cpf) {
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
        this.cpf = cpf;
    }

    /**
     * Retorna o nome da pessoa.
     * 
     * @return Nome da pessoa.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Retorna o telefone da pessoa.
     * 
     * @return Telefone da pessoa.
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     * Retorna o endereço da pessoa.
     * 
     * @return Endereço da pessoa.
     */
    public String getEndereco() {
        return endereco;
    }

    /**
     * Retorna o CPF da pessoa.
     * 
     * @return CPF da pessoa.
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * Método abstrato para retornar detalhes da pessoa.
     * Subclasses devem implementar.
     * 
     * @return String com detalhes específicos da pessoa.
     */
    public abstract String toString();
}
