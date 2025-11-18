package DAO;

import org.example.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static javax.swing.JOptionPane.showMessageDialog;

public class ProdutoDAO {
    String driver = "org.postgresql.Driver";
    String usuario = "douglas";
    String senha = "123456";
    String url = "jdbc:postgresql://192.168.189.128/exemplo";
    java.sql.Connection con = null;
    // conecxao AQUI PARA TESTE

    /* Método para inserir um novo Produto no banco de dados
    public void salvar(Produto produto) throws SQLException {
        // SQL Injection-safe
        String sql = "INSERT INTO produtos(nome, descricao) VALUES ('+produto.getNome()+', '+produto.getDescricao()')";

        // try-with-resources: garante que a Connection e o PreparedStatement sejam fechados
        try (Connection conn = (java.sql.Connection) java.sql.DriverManager.getConnection(url, usuario, senha);
             java.sql.Statement ps = con.createStatement();
             ps.executeUpdate(sql);
             showMessageDialog(null,"Produto inserido com sucesso!");
             ps.close();//AQUI EU paREI
             con.close();
             //PreparedStatement ps = conn.prepareStatement(sql)) {

            // 1. Atribui os valores do objeto Produto aos placeholders (?)
            //ps.setString(1, produto.getNome());
            //ps.setString(2, produto.getDescricao());
            //ps.setDouble(3, produto.getPreco());

            // 2. Executa o comando SQL
            //ps.executeUpdate();

        } catch (SQLException e) {
            // Re-lança a exceção para que a View possa capturar e mostrar a mensagem
            System.err.println("Erro ao salvar produto: " + e.getMessage());
            throw e;
        }
        */
    }
