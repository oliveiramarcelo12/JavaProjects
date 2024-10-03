package com.example;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ProdutoController {

    public void exibirProdutos() {
        Connection connection = DBConnect.connect();
        
        if (connection != null) {
            try {
                // Recuperar produtos
                String query = "SELECT * FROM produtos";
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                
                List<Produto> produtos = new ArrayList<>();
                while (rs.next()) {
                    Produto produto = new Produto(rs.getInt("id"), rs.getString("nome"), rs.getDouble("preco"));
                    produtos.add(produto);
                }
                
                // Exibir nome e preço de cada produto
                System.out.println("Lista de produtos:");
                for (Produto p : produtos) {
                    System.out.println(p.getNome() + " - R$ " + p.getPreco());
                }

                // Encontrar o produto mais caro e o mais barato
                Produto maisCaro = produtos.stream().max((p1, p2) -> Double.compare(p1.getPreco(), p2.getPreco())).orElse(null);
                Produto maisBarato = produtos.stream().min((p1, p2) -> Double.compare(p1.getPreco(), p2.getPreco())).orElse(null);

                System.out.println("\nProduto mais caro: " + maisCaro.getNome() + " - R$ " + maisCaro.getPreco());
                System.out.println("Produto mais barato: " + maisBarato.getNome() + " - R$ " + maisBarato.getPreco());

                // Calcular a média de preços
                double media = produtos.stream().mapToDouble(Produto::getPreco).average().orElse(0.0);
                System.out.println("Média de preços: R$ " + media);

                stmt.close();
                connection.close();
            } catch (Exception e) {
                System.out.println("Erro ao executar consulta: " + e.getMessage());
            }
        }
    }

}
