package aluno_unisenai.gestao_imobiliaria;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GestaoImobiliariaApp {

    private JTextArea relatorioTextArea;
    private JTextField searchField;
    private JComboBox<String> filterComboBox;

    private final List<String> dadosRelatorio = new ArrayList<>();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new GestaoImobiliariaApp().createAndShowUI();
        });
    }

    private void createAndShowUI() {
    JFrame frame = new JFrame("Gestão Imobiliária");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(800, 600);

    // Painel de Inputs com Abas
    JTabbedPane tabbedPane = new JTabbedPane();
    tabbedPane.addTab("Corretor", createCorretorPanel());
    tabbedPane.addTab("Proprietário", createProprietarioPanel());
    tabbedPane.addTab("Locatário", createLocatarioPanel());
    tabbedPane.addTab("Imóvel", createImovelPanel());

    // Painel de Relatório
    JPanel relatorioPanel = new JPanel(new BorderLayout());
    relatorioPanel.setBorder(BorderFactory.createTitledBorder("Relatório"));
    relatorioTextArea = new JTextArea();
    relatorioTextArea.setEditable(false); // Somente leitura
    relatorioTextArea.setLineWrap(true);
    relatorioTextArea.setWrapStyleWord(true);
    JScrollPane scrollPane = new JScrollPane(relatorioTextArea);
    relatorioPanel.add(scrollPane, BorderLayout.CENTER);

    // Painel de Pesquisa e Filtros
    JPanel searchPanel = new JPanel();
    searchPanel.setLayout(new GridBagLayout()); // Usar GridBagLayout para melhor controle
    searchPanel.setBorder(BorderFactory.createTitledBorder("Busca e Filtros"));
    searchPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Adicionar margens internas

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(5, 5, 5, 5); // Espaçamento interno entre os elementos
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.gridx = 0;
    gbc.gridy = 0;

    // Campo de busca
    searchPanel.add(new JLabel("Buscar:"), gbc);
    gbc.gridx = 1;
    searchField = new JTextField(20);
    searchPanel.add(searchField, gbc);

    // Combobox de filtro
    gbc.gridx = 0;
    gbc.gridy = 1;
    searchPanel.add(new JLabel("Filtro:"), gbc);
    gbc.gridx = 1;
    filterComboBox = new JComboBox<>(new String[]{"Todos", "Corretor", "Proprietário", "Locatário", "Imóvel"});
    searchPanel.add(filterComboBox, gbc);

    // Botão de pesquisa
    gbc.gridx = 1;
    gbc.gridy = 2;
    JButton searchButton = new JButton("Pesquisar");
    searchButton.addActionListener(e -> filtrarRelatorio());
    searchPanel.add(searchButton, gbc);

    relatorioPanel.add(searchPanel, BorderLayout.NORTH);

    // Divisor entre Inputs e Relatório
    JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, tabbedPane, relatorioPanel);
    splitPane.setDividerLocation(400); // Metade da tela
    splitPane.setResizeWeight(0.5); // Equilibra o redimensionamento

    // Botão no Rodapé
    JPanel buttonPanel = new JPanel();
    buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    JButton emitirRelatorioButton = new JButton("Emitir Relatório");
    emitirRelatorioButton.setPreferredSize(new Dimension(200, 30));
    emitirRelatorioButton.addActionListener(e -> emitirRelatorio());
    buttonPanel.add(emitirRelatorioButton);

    // Configuração Principal do Frame
    frame.setLayout(new BorderLayout());
    frame.add(splitPane, BorderLayout.CENTER);
    frame.add(buttonPanel, BorderLayout.SOUTH);
    frame.setLocationRelativeTo(null); // Centralizar a janela
    frame.setVisible(true);
}


    private JPanel createCorretorPanel() {
        return createFormPanel("Informações do Corretor",
                new String[]{"Nome", "Telefone", "Endereço", "CPF", "Registro", "Comissão (%)"});
    }

    private JPanel createProprietarioPanel() {
        return createFormPanel("Informações do Proprietário",
                new String[]{"Nome", "Telefone", "Endereço", "CPF", "Conta", "Agência"});
    }

    private JPanel createLocatarioPanel() {
        return createFormPanel("Informações do Locatário",
                new String[]{"Nome", "Telefone", "Endereço", "CPF", "Email", "Salário"});
    }

    private JPanel createImovelPanel() {
        return createFormPanel("Informações do Imóvel",
                new String[]{"Código", "Endereço", "Valor de Locação", "Vagas na Garagem", "Quartos", "Banheiros"});
    }

    private JPanel createFormPanel(String title, String[] labels) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), title));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;

        for (String label : labels) {
            gbc.gridx = 0; // Label na coluna 0
            panel.add(new JLabel(label + ":"), gbc);

            gbc.gridx = 1; // Campo de texto na coluna 1
            JTextField textField = new JTextField(15);
            textField.setPreferredSize(new Dimension(200, 20));
            panel.add(textField, gbc);

            gbc.gridy++; // Avança para a próxima linha
        }

        return panel;
    }

    private void emitirRelatorio() {
        // Gerar o conteúdo do relatório
        dadosRelatorio.clear();
        dadosRelatorio.add("=== RELATÓRIO DE GESTÃO IMOBILIÁRIA ===\n");
        dadosRelatorio.add("Corretor: João da Silva, Telefone: (11) 98765-4321, Endereço: Rua das Flores, 123\n");
        dadosRelatorio.add("Proprietário: Maria Oliveira, Telefone: (11) 91234-5678, Endereço: Av. Paulista, 456\n");
        dadosRelatorio.add("Locatário: Pedro Santos, Telefone: (11) 92345-6789, Endereço: Rua Vergueiro, 789\n");
        dadosRelatorio.add("Imóvel: Apartamento, Código 101, Endereço: Rua Augusta, 234, Valor de Locação: R$ 3.500,00\n");

        relatorioTextArea.setText(String.join("\n", dadosRelatorio));
    }

    private void filtrarRelatorio() {
        String searchTerm = searchField.getText().toLowerCase();
        String filter = (String) filterComboBox.getSelectedItem();

        List<String> resultados = dadosRelatorio.stream()
                .filter(linha -> filter.equals("Todos") || linha.toLowerCase().contains(filter.toLowerCase()))
                .filter(linha -> linha.toLowerCase().contains(searchTerm))
                .collect(Collectors.toList());

        relatorioTextArea.setText(String.join("\n", resultados));
    }
}
