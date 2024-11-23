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

        // Painel de Registro de Dados com Abas
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Corretor", createCorretorPanel());
        tabbedPane.addTab("Proprietário", createProprietarioPanel());
        tabbedPane.addTab("Locatário", createLocatarioPanel());
        tabbedPane.addTab("Imóvel", createImovelPanel());

        JPanel dataRegistrationPanel = new JPanel(new BorderLayout());
        dataRegistrationPanel.add(tabbedPane, BorderLayout.CENTER);

        // Painel de Operações
        JPanel operationsPanel = createOperationsPanel();

        // Divisor Principal entre Registro de Dados e Operações
        JSplitPane mainSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, dataRegistrationPanel, operationsPanel);
        mainSplitPane.setDividerLocation(400);
        mainSplitPane.setResizeWeight(0.5);

        // Configuração Principal do Frame
        frame.setLayout(new BorderLayout());
        frame.add(mainSplitPane, BorderLayout.CENTER);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private JPanel createOperationsPanel() {
        JPanel operationsPanel = new JPanel(new BorderLayout());

        // Painel de Busca e Filtros
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

        // Adiciona o painel de busca ao topo do painel de operações
        operationsPanel.add(searchPanel, BorderLayout.NORTH);

        // Painel de Botões de Operação
        JPanel operationButtonsPanel = new JPanel();
        operationButtonsPanel.setLayout(new GridBagLayout());
        operationButtonsPanel.setBorder(BorderFactory.createTitledBorder("Operações"));

        GridBagConstraints gbcOp = new GridBagConstraints();
        gbcOp.insets = new Insets(5, 5, 5, 5);
        gbcOp.fill = GridBagConstraints.HORIZONTAL;
        gbcOp.gridx = 0;
        gbcOp.gridy = 0;

        // Campo de Valor
        operationButtonsPanel.add(new JLabel("Valor:"), gbcOp);
        gbcOp.gridx = 1;
        JTextField valorField = new JTextField(10);
        operationButtonsPanel.add(valorField, gbcOp);

        // Botões
        gbcOp.gridx = 0;
        gbcOp.gridy = 1;
        JButton receberButton = new JButton("Registrar Recebimento");
        receberButton.addActionListener(e -> {
            try {
                double valor = Double.parseDouble(valorField.getText());
                corretor.receber(valor);
                atualizarRelatorio();
                JOptionPane.showMessageDialog(operationsPanel, "Recebimento registrado com sucesso!");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(operationsPanel, "Insira um valor válido!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
        operationButtonsPanel.add(receberButton, gbcOp);

        gbcOp.gridx = 1;
        JButton sacarButton = new JButton("Sacar Comissão");
        sacarButton.addActionListener(e -> {
            try {
                double valor = Double.parseDouble(valorField.getText());
                corretor.sacarComissoes(valor);
                atualizarRelatorio();
                JOptionPane.showMessageDialog(operationsPanel, "Saque realizado com sucesso!");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(operationsPanel, "Insira um valor válido!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
        operationButtonsPanel.add(sacarButton, gbcOp);

        // Adiciona o painel de botões de operação ao centro do painel de operações
        operationsPanel.add(operationButtonsPanel, BorderLayout.CENTER);

        // Painel de Relatório
        JPanel relatorioPanel = new JPanel(new BorderLayout());
        relatorioPanel.setBorder(BorderFactory.createTitledBorder("Relatório"));
        relatorioTextArea = new JTextArea();
        relatorioTextArea.setEditable(false); // Somente leitura
        relatorioTextArea.setLineWrap(true);
        relatorioTextArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(relatorioTextArea);
        relatorioPanel.add(scrollPane, BorderLayout.CENTER);

        // Adiciona o painel de relatório ao sul do painel de operações
        operationsPanel.add(relatorioPanel, BorderLayout.SOUTH);

        return operationsPanel;
    }

    private JPanel createCorretorPanel() {
        JPanel panel = createFormPanel("Informações do Corretor",
                new String[]{"Nome", "Telefone", "Endereço", "CPF", "Registro", "Comissão (%)"});

        // Removemos os botões e campos de operação daqui

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
