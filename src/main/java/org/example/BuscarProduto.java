package org.example;

import javax.swing.*;

public class BuscarProduto extends JFrame {

    public BuscarProduto() {
        setTitle("Buscar Produto");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        JLabel label = new JLabel("Buscar Produto");
        JTextField textField = new JTextField(20);
        JButton button = new JButton("Buscar");

        panel.add(label);
        panel.add(textField);
        panel.add(button);

        add(panel);
    }
}
