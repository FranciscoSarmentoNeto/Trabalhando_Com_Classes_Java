import javax.swing.*;
import java.awt.*;

public class App extends JFrame {

    static Pessoa[] pessoas = new Pessoa[10];
    static int numPessoasCadastradas = 0;

    public App() {
        setTitle("Gerenciamento de Cadastros de Pessoas");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setBackground(Color.WHITE);

        JLabel titleLabel = new JLabel("Gerenciamento de Cadastros de Pessoas");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.add(titleLabel);
        titlePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        titlePanel.setBackground(Color.LIGHT_GRAY);

        JButton cadastrarBtn = createColoredButton("Cadastrar uma pessoa", Color.LIGHT_GRAY);
        JButton consultarBtn = createColoredButton("Consultar pessoas", Color.LIGHT_GRAY);
        JButton editarBtn = createColoredButton("Editar informações de uma pessoa", Color.LIGHT_GRAY);
        JButton excluirBtn = createColoredButton("Excluir uma pessoa", Color.LIGHT_GRAY);
        JButton imprimirBtn = createColoredButton("Imprimir todas as pessoas cadastradas", Color.LIGHT_GRAY);
        JButton sairBtn = createColoredButton("Sair", Color.DARK_GRAY);

        cadastrarBtn.addActionListener(e -> cadastrarPessoa());
        consultarBtn.addActionListener(e -> consultarPessoa());
        editarBtn.addActionListener(e -> editarPessoa());
        excluirBtn.addActionListener(e -> excluirPessoa());
        imprimirBtn.addActionListener(e -> imprimirTodasPessoas());
        sairBtn.addActionListener(e -> dispose());

        panel.add(cadastrarBtn);
        panel.add(consultarBtn);
        panel.add(editarBtn);
        panel.add(excluirBtn);
        panel.add(imprimirBtn);
        panel.add(sairBtn);

        setLayout(new BorderLayout());
        add(titlePanel, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);
    }

    private JButton createColoredButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setBackground(color);
        button.setForeground(Color.BLACK);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setMaximumSize(new Dimension(Integer.MAX_VALUE, button.getMinimumSize().height));
        return button;
    }

    private void cadastrarPessoa() {
        JDialog cadastroDialog = new JDialog(this, "Cadastrar Pessoa", true);
        cadastroDialog.setSize(350, 250);
        cadastroDialog.setLayout(new GridLayout(5, 2, 10, 10));

        JLabel nomeLabel = new JLabel("Nome:");
        nomeLabel.setFont(new Font("Arial", Font.BOLD, 14));
        JTextField nomeField = new JTextField();

        JLabel idadeLabel = new JLabel("Idade:");
        idadeLabel.setFont(new Font("Arial", Font.BOLD, 14));
        JTextField idadeField = new JTextField();

        JLabel sexoLabel = new JLabel("Sexo:");
        sexoLabel.setFont(new Font("Arial", Font.BOLD, 14));
        JComboBox<String> sexoBox = new JComboBox<>(new String[] { "Masculino", "Feminino" });

        JLabel telefoneLabel = new JLabel("Telefone:");
        telefoneLabel.setFont(new Font("Arial", Font.BOLD, 14));
        JTextField telefoneField = new JTextField();

        JButton cadastrarBtn = new JButton("Cadastrar");

        cadastroDialog.add(nomeLabel);
        cadastroDialog.add(nomeField);
        cadastroDialog.add(idadeLabel);
        cadastroDialog.add(idadeField);
        cadastroDialog.add(sexoLabel);
        cadastroDialog.add(sexoBox);
        cadastroDialog.add(telefoneLabel);
        cadastroDialog.add(telefoneField);
        cadastroDialog.add(new JLabel());
        cadastroDialog.add(cadastrarBtn);

        cadastrarBtn.addActionListener(e -> {
            String nome = nomeField.getText();
            int idade = Integer.parseInt(idadeField.getText());
            String sexo = (String) sexoBox.getSelectedItem();
            String telefone = telefoneField.getText();

            if (numPessoasCadastradas < pessoas.length) {
                pessoas[numPessoasCadastradas++] = new Pessoa(nome, idade, sexo, telefone);
                JOptionPane.showMessageDialog(cadastroDialog, "Pessoa cadastrada com sucesso!");
                cadastroDialog.dispose();
            } else {
                JOptionPane.showMessageDialog(cadastroDialog, "Capacidade máxima atingida!", "Erro",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        cadastroDialog.setVisible(true);
    }

    private void consultarPessoa() {
        String nome = JOptionPane.showInputDialog(this, "Insira o nome para consulta:");

        if (nome != null && !nome.isEmpty()) {
            for (int i = 0; i < numPessoasCadastradas; i++) {
                if (pessoas[i].nome.equalsIgnoreCase(nome)) {
                    JOptionPane.showMessageDialog(this,
                            "Nome: " + pessoas[i].nome + "\nIdade: " + pessoas[i].idade + "\nSexo: "
                                    + pessoas[i].sexoDaPessoa + "\nTelefone: " + pessoas[i].telefoneContato);
                    return;
                }
            }
            JOptionPane.showMessageDialog(this, "Pessoa não encontrada.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void editarPessoa() {
        String nome = JOptionPane.showInputDialog(this, "Insira o nome para editar:");

        if (nome != null && !nome.isEmpty()) {
            for (int i = 0; i < numPessoasCadastradas; i++) {
                if (pessoas[i].nome.equalsIgnoreCase(nome)) {
                    JDialog editDialog = new JDialog(this, "Editar Pessoa", true);
                    editDialog.setSize(350, 250);
                    editDialog.setLayout(new GridLayout(5, 2, 10, 10));

                    JTextField nomeField = new JTextField(pessoas[i].nome);
                    JTextField idadeField = new JTextField(String.valueOf(pessoas[i].idade));
                    JComboBox<String> sexoBox = new JComboBox<>(new String[] { "Masculino", "Feminino" });
                    sexoBox.setSelectedItem(pessoas[i].sexoDaPessoa);
                    JTextField telefoneField = new JTextField(pessoas[i].telefoneContato);
                    JButton salvarBtn = new JButton("Salvar");

                    JLabel nomeLabel = new JLabel("Nome:");
                    nomeLabel.setFont(new Font("Arial", Font.BOLD, 14));
                    JLabel idadeLabel = new JLabel("Idade:");
                    idadeLabel.setFont(new Font("Arial", Font.BOLD, 14));
                    JLabel sexoLabel = new JLabel("Sexo:");
                    sexoLabel.setFont(new Font("Arial", Font.BOLD, 14));
                    JLabel telefoneLabel = new JLabel("Telefone:");
                    telefoneLabel.setFont(new Font("Arial", Font.BOLD, 14));

                    editDialog.add(nomeLabel);
                    editDialog.add(nomeField);
                    editDialog.add(idadeLabel);
                    editDialog.add(idadeField);
                    editDialog.add(sexoLabel);
                    editDialog.add(sexoBox);
                    editDialog.add(telefoneLabel);
                    editDialog.add(telefoneField);
                    editDialog.add(new JLabel());
                    editDialog.add(salvarBtn);

                    int[] indice = { i };

                    salvarBtn.addActionListener(e -> {
                        pessoas[indice[0]].setNome(nomeField.getText());
                        pessoas[indice[0]].setIdade(Integer.parseInt(idadeField.getText()));
                        pessoas[indice[0]].setSexoDaPessoa((String) sexoBox.getSelectedItem());
                        pessoas[indice[0]].setTelefoneContato(telefoneField.getText());
                        JOptionPane.showMessageDialog(editDialog, "Pessoa editada com sucesso!");
                        editDialog.dispose();
                    });

                    editDialog.setVisible(true);
                    return;
                }
            }
            JOptionPane.showMessageDialog(this, "Pessoa não encontrada.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void excluirPessoa() {
        String nome = JOptionPane.showInputDialog(this, "Insira o nome para excluir:");

        if (nome != null && !nome.isEmpty()) {
            for (int i = 0; i < numPessoasCadastradas; i++) {
                if (pessoas[i].nome.equalsIgnoreCase(nome)) {
                    String pessoaInfo = "Nome: " + pessoas[i].nome + "\nIdade: " + pessoas[i].idade +
                            "\nSexo: " + pessoas[i].sexoDaPessoa + "\nTelefone: " + pessoas[i].telefoneContato;

                    int response = JOptionPane.showConfirmDialog(this,
                            "Tem certeza que deseja excluir a seguinte pessoa?\n\n" + pessoaInfo,
                            "Confirmar Exclusão",
                            JOptionPane.YES_NO_OPTION);

                    if (response == JOptionPane.YES_OPTION) {
                        pessoas[i] = pessoas[numPessoasCadastradas - 1];
                        pessoas[numPessoasCadastradas - 1] = null;
                        numPessoasCadastradas--;
                        JOptionPane.showMessageDialog(this, "Pessoa excluída com sucesso!");
                    }
                    return;
                }
            }
            JOptionPane.showMessageDialog(this, "Pessoa não encontrada.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void imprimirTodasPessoas() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numPessoasCadastradas; i++) {
            sb.append("Nome: ").append(pessoas[i].nome).append("\n");
            sb.append("Idade: ").append(pessoas[i].idade).append("\n");
            sb.append("Sexo: ").append(pessoas[i].sexoDaPessoa).append("\n");
            sb.append("Telefone: ").append(pessoas[i].telefoneContato).append("\n\n");
        }
        JTextArea textArea = new JTextArea(sb.toString());
        textArea.setEditable(false);
        textArea.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(400, 300));
        JOptionPane.showMessageDialog(this, scrollPane, "Pessoas Cadastradas", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            App app = new App();
            app.setVisible(true);
        });
    }
}
