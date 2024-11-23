package aluno_unisenai.gestao_imobiliaria;

/**
 * Classe Locatario, que representa o locatário responsável pelo aluguel do imóvel.
 * Herda atributos e métodos da classe abstrata Pessoa.
 * 
 * @author instrutor
 */
public class Locatario extends Pessoa {
    private final String email; // Email do locatário
    private final double salario; // Salário do locatário

    /**
     * Construtor da classe Locatario.
     * 
     * @param nome Nome do locatário.
     * @param telefone Telefone do locatário.
     * @param endereco Endereço do locatário.
     * @param cpf CPF do locatário.
     * @param email Email do locatário.
     * @param salario Salário do locatário.
     */
    public Locatario(String nome, String telefone, String endereco, String cpf, String email, double salario) {
        super(nome, telefone, endereco, cpf); // Chama o construtor da classe Pessoa
        this.email = email;
        this.salario = salario;
    }

    /**
     * Retorna o email do locatário.
     * 
     * @return Email do locatário.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Retorna o salário do locatário.
     * 
     * @return Salário do locatário.
     */
    public double getSalario() {
        return salario;
    }

    /**
     * Retorna o nome do locatário (herdado da classe Pessoa).
     * 
     * @return Nome do locatário.
     */
    @Override
    public String getNome() {
        return super.getNome(); // Método herdado da classe Pessoa
    }

    /**
     * Retorna o telefone do locatário (herdado da classe Pessoa).
     * 
     * @return Telefone do locatário.
     */
    @Override
    public String getTelefone() {
        return super.getTelefone(); // Método herdado da classe Pessoa
    }

    /**
     * Representação em String dos detalhes do locatário.
     * 
     * @return String com os detalhes do locatário.
     */
    @Override
    public String toString() {
        return "Locatário: " + getNome() + "\n" +
               "Telefone: " + getTelefone() + "\n" +
               "Endereço: " + getEndereco() + "\n" +
               "CPF: " + getCpf() + "\n" +
               "Email: " + email + "\n" +
               "Salário: R$" + salario;
    }
}
