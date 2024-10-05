
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.example.BancoService;
import com.example.ContaBancaria;
import com.example.ContaBancariaRepository;

public class TestBancoService {
    
    private ContaBancariaRepository repositoryMock;
    private BancoService bancoService;
    private ContaBancaria contaMock;

    @BeforeEach
    public void setUp() {
        // Criar mock do repositório
        repositoryMock = Mockito.mock(ContaBancariaRepository.class);

        // Criar instância do serviço com o mock injetado
        bancoService = new BancoService(repositoryMock);

        // Criar mock de uma conta bancária para usar nos testes
        contaMock = new ContaBancaria("12345", 1000.0);
    }
    @Test
   public void testDepositarComSucesso() {
        // Simular comportamento do repositório
        when(repositoryMock.encontrarPorNumero("12345")).thenReturn(contaMock);

        // Executar o método de depósito
        bancoService.depositar("12345", 500.0);

        // Verificar se o saldo foi atualizado corretamente
        assertEquals(1500.0, contaMock.getSaldo(),0);

        // Verificar se o método de atualizar foi chamado no repositório
        verify(repositoryMock).atualizar(contaMock);
    }

    @Test
   public void testSacarComSucesso() {
        // Simular comportamento do repositório
        when(repositoryMock.encontrarPorNumero("12345")).thenReturn(contaMock);

        // Executar o método de saque
        bancoService.sacar("12345", 300.0);

        // Verificar se o saldo foi atualizado corretamente
        assertEquals(700.0, contaMock.getSaldo(),0);

        // Verificar se o método de atualizar foi chamado no repositório
        verify(repositoryMock).atualizar(contaMock);
    }



}
