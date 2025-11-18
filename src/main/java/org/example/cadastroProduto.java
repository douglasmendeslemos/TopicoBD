package org.example;

import DAO.*;
import org.example.database.ConnectionFactory;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class cadastroProduto extends JFrame{
    private JTextField descricao;
    private JTextField preco;
    private JButton cadastrarBotao;
    private JPanel cadastroProd;
    private JButton Limpar;
    private JButton buscarProdutoButton;

    // INSTÂNCIA DO DAO (A View conversa diretamente com o DAO neste modelo simples)
    public final ProdutoDAO produtoDAO = new ProdutoDAO();

    // Construtor
    public cadastroProduto() {
        // Se a classe View é um JFrame, use:
        setContentPane(getMainPanel());
        setSize(300, 200);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a janela
        adicionarListeners();
    }

    // Opcional: Getter para o painel se a classe for JPanel
    public JPanel getMainPanel() {
        return cadastroProd;
    }
    private void adicionarListeners() {
        cadastrarBotao.addActionListener(e -> handlerCadastrar());
        Limpar.addActionListener(e -> limparCampos());
        buscarProdutoButton.addActionListener(e -> consultar());
    }

    private void handlerCadastrar() {
        System.out.println("Entrou");
        // 1. Coleta dados da tela
        int valor = Integer.parseInt(preco.getText().trim());
        String desc = descricao.getText().trim();
        //String precoText = campoPreco.getText().trim().replace(",", "."); // Aceita vírgula ou ponto
        System.out.println("Entrou");
        // 2. Validação simples de entrada
        if ( desc.isEmpty()) {//verificar porque não esta entrando aqui.
            JOptionPane.showMessageDialog(this,
                    "Todos os campos devem ser preenchidos.", "Erro de Validação",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            /* Tenta converter o preço para double
            //double preco = Double.parseDouble(precoText);
            // 3. Cria o objeto Model
            Produto novoProduto = new Produto(desc, valor);

            // 4. Chama o DAO para persistir (INSERT)
            //produtoDAO.salvar(novoProduto);

            // 5. Feedback de sucesso
            JOptionPane.showMessageDialog(this,
                    "Produto cadastrado com sucesso!", "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);
            */
            String sql = "INSERT INTO produtos (descricao, preco) VALUES (?, ?)";

            try (Connection conn = ConnectionFactory.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setString(1, desc);
                stmt.setInt(2, valor);
                stmt.executeUpdate();
                System.out.println("✅ Paciente salvo com sucesso!");
                JOptionPane.showMessageDialog(this,
                        "✅ Paciente salvo com sucesso!", "INSERIDO NO BANCO",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException e) {
                System.out.println("❌❌❌❌ Erro ao salvar paciente: " + e.getMessage());
            }
            limparCampos();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "O campo Preço deve ser um número válido.", "Erro de Formato",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void consultar() {
        String sql = "SELECT * FROM produtos";
        List<Produto> produtos = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             java.sql.ResultSet rst = stmt.executeQuery()) {

            while (rst.next()) {
                Produto p = new Produto(
                        rst.getString("descricao"),
                        rst.getInt("preco")
                );
                produtos.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private void limparCampos() {
        descricao.setText("");
        preco.setText("");
        //campoPreco.setText("");
        descricao.requestFocus();
    }

}
