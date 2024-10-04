package com.example;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CalculadoraAvancada extends JPanel {
  
    JTextField displayAvancada;
   
    String[] botoes = { "^", "sqrt", "log","!",
                        "7", "8", "9", "/",
                        "4", "5", "6", "*",
                        "1", "2", "3", "-",
                        "0", ".", "=", "+"};
    //construtor
    public CalculadoraAvancada() {
        super(new BorderLayout());
        displayAvancada = new JTextField();
        displayAvancada.setHorizontalAlignment(JTextField.RIGHT);
        displayAvancada.setEditable(false);
        this.add(displayAvancada,BorderLayout.NORTH);

    //criar um painel para os botões
    JPanel painelBotoes = new JPanel(new GridLayout(5,4,3,3));
    for (String textoBotoes : botoes) {
        JButton botao = new JButton(textoBotoes);
       botao.addActionListener(null);
       //adicionar ação dos botões
       painelBotoes.add(botao);    
        
    }
    this.add(painelBotoes,BorderLayout.CENTER);

    }                    
}
    

