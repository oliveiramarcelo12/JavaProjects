package com.example;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
public class CalculadoraSimples extends JPanel {
    JTextField displaySimples;
   
    String[] botoes = { "7", "8", "9", "/",
                        "4", "5", "6", "*",
                        "1", "2", "3", "-",
                        "0", ".", "=", "+"};
    //construtor
    public CalculadoraSimples() {
        super(new BorderLayout());
        displaySimples = new JTextField();
        displaySimples.setHorizontalAlignment(JTextField.RIGHT);
        displaySimples.setEditable(false);
        this.add(displaySimples,BorderLayout.NORTH);

    //criar um painel para os botões
    JPanel painelBotoes = new JPanel(new GridLayout(4,4,3,3));
    for (String textoBotoes : botoes) {
        JButton botao = new JButton(textoBotoes);
       botao.addActionListener(new Handler1);
       //adicionar ação dos botões
       painelBotoes.add(botao);    
        
    }
    this.add(painelBotoes,BorderLayout.CENTER);
    

    }  
    
    public void setDisplaySimples(String texto) {
        this.displaySimples.setText(texto);
    }

    public String getDisplaySimples() {
        return displaySimples.getText();
    }
    public class Handler1 implements ActionListener{
        
    }
}