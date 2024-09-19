package br.com.marcelo;

public class ExemploConceitoBasico {
    // atributos
    int idade = 5;
    double valor = 99.99;
    char letra = 'J';
    boolean teste = false;

    public static void main(String[] args) {
        int a = 30, b = 40;
        double c = 3.5;
        boolean resultado = (a > b) && (c < 5);
        System.out.println("Soma a+b =" + (a + b));
        System.out.println("Comparação: " + (a > b)); // Falso
        System.out.println("Resultado lógico: " + resultado); // Falso



    }
    public  void declaracaoDeUmMetodo (int a , int b) {
        System.out.println(a+b);


        
    }
    public int metodoComReturn(int a , int b){
        int c = a+b;
        return c;
    }
}
