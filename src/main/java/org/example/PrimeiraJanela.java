package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PrimeiraJanela extends JFrame{
    static void main(String[] args) {
        /* Cria uma janela (JFrame)
        JFrame frame = new JFrame();
        frame.setSize(300, 200);
        frame.setLayout(new FlowLayout());// Layout simples
        frame.setLocationRelativeTo(null);// Centraliza a janela na tela

        // Componentes
        JLabel labelNome = new JLabel("Nome:");
        JTextField campoNome = new JTextField(15);
        JButton botao = new JButton("OK");


        // Evento do botão
        botao.addActionListener(new ActionListener() { //Ação de chamar metodo novo.
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);//fecha a tela anterior.
                String texto = campoNome.getText();
                JOptionPane.showMessageDialog(frame, "Você digitou: " + texto);
                frame.setVisible(true);//abre a tela anterior novamente.
            }
        });


        // Adiciona componentes à janela
        frame.add(labelNome);
        frame.add(campoNome);
        frame.add(botao);

        frame.setVisible(true);
        */
        // Garante que a interface seja criada na EDT (Event Dispatch Thread)
        // 1. Instancia a sua tela
        cadastroProduto tela = new cadastroProduto();
        // 2. Torna a tela visível
        tela.setVisible(true);
    }
}

