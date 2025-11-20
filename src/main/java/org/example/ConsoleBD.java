package org.example;

import org.example.database.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Scanner;
import java.util.Stack;

public class ConsoleBD {

    Scanner leia = new Scanner(System.in);
    Stack<String> pilhaDeLivros = new Stack<>();

    public int menuOpcoes() {
        IO.println("\n----------------------------------------------");
      /*System.out.println("1. Colocar livro ");
        System.out.println("2. Retirar livro");
        System.out.println("3. Ver título do livro do topo da pilha ");
        System.out.println("4. Quantidade de livros que estão empilhados ");
        System.out.println("5. Mostrar todos os títulos de livros que estão empilhados. ");
        System.out.print("Para sair digite 0: ");
        return leia.nextInt();*/
        IO.println("1. Inserir registro no banco de dados ");
        IO.println("2. Realizar consulta no banco de dados ");
        IO.println("3. Deletar no banco de dados ");
        IO.println("4. Atualizar registro no banco de dados ");
        System.out.print("Para sair digite 0: ");
        return leia.nextInt();
    }

    //tornando o metodo menuOpcoes() em um metodo mais versatio.

    public int principal() {
        int opcao;
        do {
            opcao = menuOpcoes();
            /*
             * Utilizar uma variavel opção para evitar pulos de entrada
             * chamar o menu apenas no inicio do loop.
             * */
            switch (opcao) {
                case 0:
                    System.out.println("Saindo do programa. Até mais!");
                    break;
                case 1:
                    Inserir();
                    break;
                case 2:
                    consultar();
                    break;
                case 3:
                    System.out.println("Informe o codigo que deseja deletar:");
                    int codigoDelete = leia.nextInt();
                    deletar(codigoDelete);
                    break;
                case 4:
                    System.out.println("Informe o codigo que deseja deletar:");
                    int codigoAtulizar = leia.nextInt();
                    leia.nextLine(); // limpa o buffer
                    System.out.println("Digite a descrição:");
                    String descricao = leia.nextLine();
                    System.out.print("Digite o valor: ");
                    Double preco = leia.nextDouble();
                    AtualizarProduto(descricao, preco, codigoAtulizar);
                    break;
                case 5:
                    //mostrarObjetos();
                    break;
                default:
                    System.out.println("!! ERRO: Opção inválida. Informe novamente!!");
                    break;
            }
        } while (opcao != 0);
        leia.close();//fechamento do scanner.
        return 0;
    }

    private void AtualizarProduto(String novaDescricao, Double novoPreco, int codigo) {
        String sql = "UPDATE produtos SET descricao = ?, preco = ? WHERE codigo = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, novaDescricao);
            stmt.setDouble(2, novoPreco);
            stmt.setInt(3, codigo);
            stmt.executeUpdate();
            System.out.println("✅ Produto Atualizado com sucesso!");
        } catch (SQLException e) {
            System.out.println("❌ Erro ao atualizar produto: " + e.getMessage());
        }
    }

    private void deletar(int codigo) {
        String sql = "delete from produtos WHERE codigo = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, codigo);
            stmt.executeUpdate();
            System.out.println("✅ Produto Deletado com sucesso!");
        } catch (SQLException e) {
            System.out.println("❌ Erro ao deletar produto: " + e.getMessage());
        }
    }

    /*
    public void colocarLivro() {
        leia.nextLine(); // limpa o buffer
        System.out.print("Digite o nome do livro: ");
        String nomeLivro = leia.nextLine();
        pilhaDeLivros.push(nomeLivro);
        System.out.print("-> Livro \"" + nomeLivro + "\" adicionado com sucesso!\n");
    }

    public void retirarLivro() {
        if (verificarVazia()) {
            System.out.println(">> A pilha está vazia.");
        } else {
            System.out.println("\"" + pilhaDeLivros.peek() + "\" saiu da pilha.");
            pilhaDeLivros.pop();
        }
    }

    public void verificarTopo(){
        if (verificarVazia()) {
            System.out.println(">> A pilha está vazia.");
        } else {
            System.out.println("O livro " + pilhaDeLivros.peek() + " está no TOPO da pilha.");
        }
    }

    public boolean verificarVazia(){
        return pilhaDeLivros.isEmpty();
    }

    public int verificarQTDeLivro(){
        if (verificarVazia()) {
            System.out.println(">> A pilha está vazia.");
        } else {
            System.out.println("-> A pilha possui "+  pilhaDeLivros.size() +" livro(s)." );
            return pilhaDeLivros.size();
        }
        return 0;
    }

    public void mostrarObjetos(){
        if (verificarVazia()) {
            System.out.println(">> A pilha está vazia.");
        } else {
            System.out.println("--- Livros Empilhados ---");
            for (String livro : pilhaDeLivros.reversed()) {//O reversed informa o primeiro que foi colocado até o ultimo. Reverso.
                System.out.println("- " + livro);
            }System.out.print("---------------FIM DA LISTA-------------------\n");
        }
    }
     *///metodos utilizados na classe de livros da Luila.
    public void Inserir() {
        leia.nextLine(); // limpa o buffer
        System.out.println("Digite a descrição:");
        String descricao = leia.nextLine();
        System.out.print("Digite o valor: ");
        int preco = leia.nextInt();

        String sql = "INSERT INTO produtos (descricao, preco) VALUES (?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, descricao);
            stmt.setInt(2, preco);
            stmt.executeUpdate();
            System.out.println("✅ Produto salvo com sucesso!");
        } catch (SQLException e) {
            System.out.println("❌ Erro ao salvar produto: " + e.getMessage());
        }
    }

    public void consultar() {
        String sql = "SELECT * FROM produtos";

        try (Connection conn = ConnectionFactory.getConnection();) {
            java.sql.Statement st  = conn.createStatement();
            ResultSet rst = st.executeQuery(sql);
            String descricao = null;
            int codigo;
            double preco;
            while (rst.next()) {
                codigo = rst.getInt("codigo");
                descricao = rst.getString("descricao");
                preco = rst.getDouble("preco");
                System.out.println(codigo+ " \t| "+ preco + " \t| " + descricao);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }
}
