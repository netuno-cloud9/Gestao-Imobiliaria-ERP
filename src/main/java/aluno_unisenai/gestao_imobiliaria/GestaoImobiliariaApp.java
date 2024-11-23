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

    // Simulação de um corretor
    private final Corretor corretor = new Corretor("João da Silva", "(11) 98765-4321", "Rua das Flores, 123", "123.456.789-00", "1234-AB", new java.util.Date(), 0.05);

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
        searchPanel.setLayout(new GridBagLayout());
        searchPanel.setBorder(BorderFactory.createTitledBorder("Busca e Filtros"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
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
        splitPane.setDividerLocation(400);
        splitPane.setResizeWeight(0.5);

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
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private JPanel createCorretorPanel() {
        JPanel panel = createFormPanel("Informações do Corretor",
                new String[]{"Nome", "Telefone", "Endereço", "CPF", "Registro", "Comissão (%)"});

        // Adicionar botões e campos para interagir com métodos da classe Corretor
        JButton receberButton = new JButton("Registrar Recebimento");
        JButton sacarButton = new JButton("Sacar Comissão");
        JTextField valorField = new JTextField(10);

        receberButton.addActionListener(e -> {
            try {
                double valor = Double.parseDouble(valorField.getText());
                corretor.receber(valor);
                atualizarRelatorio();
                JOptionPane.showMessageDialog(panel, "Recebimento registrado com sucesso!");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(panel, "Insira um valor válido!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        sacarButton.addActionListener(e -> {
            try {
                double valor = Double.parseDouble(valorField.getText());
                corretor.sacarComissoes(valor);
                atualizarRelatorio();
                JOptionPane.showMessageDialog(panel, "Saque realizado com sucesso!");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(panel, "Insira um valor válido!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        panel.add(new JLabel("Valor:"), BorderLayout.CENTER);
        panel.add(valorField, BorderLayout.CENTER);
        panel.add(receberButton, BorderLayout.CENTER);
        panel.add(sacarButton, BorderLayout.CENTER);

        return panel;
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
        panel.setLayout(new GridLayout(0, 2));
        panel.setBorder(BorderFactory.createTitledBorder(title));

        for (String label : labels) {
            panel.add(new JLabel(label + ":"));
            panel.add(new JTextField(15));
        }

        return panel;
    }

    private void emitirRelatorio() {
        atualizarRelatorio();
    }

    private void atualizarRelatorio() {
        dadosRelatorio.clear();
        dadosRelatorio.add("=== RELATÓRIO DE GESTÃO IMOBILIÁRIA ===\n");
        dadosRelatorio.add(corretor.toString());
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
