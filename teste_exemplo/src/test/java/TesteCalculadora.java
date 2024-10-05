


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.example.Calculadora;

public class TesteCalculadora {
 Calculadora calc = new Calculadora();
 
//Criar os testes
 @Test
 public void testSoma() {
   double resultado = calc.soma(3, 8);
   assertEquals(11, resultado, 0);
 }

 @Test
 public void testSubtracao() {
   double resultado = calc.subtracao(8, 3);
   assertEquals(5, resultado, 0);
 }
 
 @Test
 public void testMultiplicacao() {
   double resultado = calc.multiplicacao(3, 8);
   assertEquals(24, resultado, 0);
 }

 @Test
 public void testDivisao() {
   double resultado = calc.divisao(8, 2);
   assertEquals(4, resultado, 0);
 }
 
 @Test
 public void testDivisaoPorZero() {
 assertThrows(IllegalArgumentException.class,()-> calc.divisao(10, 0));
 }
}
