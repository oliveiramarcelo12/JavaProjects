package com.example;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class SimplesActionListener implements ActionListener{
    private double valorAtual;
    JPanel calcSimples = new CalculadoraSimples();
    @Override
    public void actionPerformed(ActionEvent e) {
      String comando = e.getActionCommand();
      String operador;
      if (comando.matches("\\d")) {
        calcSimples.setDisplay(calcSimples.getDisplay() + comando);

        
      }else if(comando.matches("[+\\-*/]")){
        //seleção da operação
        valorAtual= Double.parseDouble((calcSimples.getDisplay));
        operador = comando;
        displaySimples.setText("");
      }
    }

    
}
