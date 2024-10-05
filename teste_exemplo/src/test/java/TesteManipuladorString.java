

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import com.example.ManipuladorString;

/**
 * TesteManipuladorString
 */
public class TesteManipuladorString {

    
    ManipuladorString manipulador = new ManipuladorString();

    @Test
   public void testInvertString() {
        assertEquals("dlroW olleH", manipulador.invertString("Hello World"));
        assertEquals("avaJ", manipulador.invertString("Java"));
        assertEquals("", manipulador.invertString("")); // Teste com string vazia
        assertNull(manipulador.invertString(null)); // Teste com string nula
    }

    @Test
   public void testContarVogais() {
        assertEquals(3, manipulador.contarVogais("Hello World"));
        assertEquals(2, manipulador.contarVogais("Java"));
        assertEquals(0, manipulador.contarVogais("bcdfg")); // Sem vogais
        assertEquals(0, manipulador.contarVogais("")); // Teste com string vazia
        assertEquals(0, manipulador.contarVogais(null)); // Teste com string nula
    }

    @Test
   public void testContarPalavras() {
        assertEquals(2, manipulador.contarPalavras("Hello World"));
        assertEquals(1, manipulador.contarPalavras("Java"));
        assertEquals(3, manipulador.contarPalavras("  Teste de palavras   "));
        assertEquals(0, manipulador.contarPalavras("")); // Teste com string vazia
        assertEquals(0, manipulador.contarPalavras(null)); // Teste com string nula
    }
}