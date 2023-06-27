package br.com.alura.service;

import br.com.alura.client.ClientHttpConfiguration;
import br.com.alura.dominio.Abrigo;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.http.HttpResponse;

import static org.mockito.Mockito.*;

public class AbrigoServiceTest {

    private ClientHttpConfiguration client = mock(ClientHttpConfiguration.class);
    private AbrigoService abrigoService = new AbrigoService(client);
    private HttpResponse<String> response = mock(HttpResponse.class);
    private Abrigo abrigo = new Abrigo("Teste", "61981880390", "abrigo_alura@gmail.com");

    @Test
    public void deveVerificarSeDispararRequisicaoGetSeraChamado() throws IOException, InterruptedException {
        when(response.body()).thenReturn("[{"+abrigo.toString()+"}]");
        when(client.dispararRequisicaoGet(anyString())).thenReturn(response);
        abrigoService.listarAbrigos();

        verify(client.dispararRequisicaoGet(anyString()), atLeast(1));
    }
}
