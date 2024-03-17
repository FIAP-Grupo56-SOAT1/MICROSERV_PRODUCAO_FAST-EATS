package br.com.fiap.fasteats.core.usecase.impl.bdd.unit;

import br.com.fiap.fasteats.core.dataprovider.CozinhaPedidoOutputPort;
import br.com.fiap.fasteats.core.domain.model.CozinhaPedido;
import br.com.fiap.fasteats.core.domain.model.Pedido;
import br.com.fiap.fasteats.core.usecase.AlterarPedidoStatusInputPort;
import br.com.fiap.fasteats.core.usecase.impl.CozinhaPedidoUseCase;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class CozinhaPedidoSteps {

    @Mock
    private CozinhaPedidoOutputPort cozinhaPedidoOutputPort;
    @Mock
    private AlterarPedidoStatusInputPort alterarPedidoStatusInputPort;
    @InjectMocks
    private CozinhaPedidoUseCase cozinhaPedidoUseCase;

    private CozinhaPedido cozinhaPedido;
    private CozinhaPedido cozinhaPedidoRetornado;

    public CozinhaPedidoSteps() {
        MockitoAnnotations.openMocks(this);
    }

    @Dado("que existe um Cozinha Pedido com ID {string}")
    public void existeUmCozinhaPedidoComId(String cozinhaPedidoId) {
        cozinhaPedido = new CozinhaPedido();
        cozinhaPedido.setCozinhaId(cozinhaPedidoId);

        when(cozinhaPedidoOutputPort.consultar(cozinhaPedidoId)).thenReturn(Optional.of(cozinhaPedido));
    }


    @Quando("o usuário consulta o Cozinha Pedido por ID {string}")
    public void usuarioConsultaCozinhaPedidoPorId(String cozinhaPedidoId) {
        cozinhaPedidoRetornado = cozinhaPedidoUseCase.consultar(cozinhaPedidoId);
    }

    @Quando("o usuário consulta o Cozinha Pedido por ID de Pedido {long}")
    public void usuarioConsultaCozinhaPedidoPorIdPedido(Long pedidoId) {
        try {
            CozinhaPedido result = cozinhaPedidoUseCase.consultarPorIdPedido(pedidoId);
            assertNotNull(result);
            assertEquals(cozinhaPedido.getIdPedido(), result.getIdPedido());
        } catch (Exception e) {
            fail("Erro ao consultar Cozinha Pedido por ID de Pedido");
        }
    }



    @Entao("é retornado o Cozinha Pedido com ID {string}")
    public void retornadoCozinhaPedidoComId(String cozinhaPedidoId) {
        assertNotNull(cozinhaPedidoRetornado);
        assertEquals(cozinhaPedidoId, cozinhaPedido.getCozinhaId());
        assertEquals(cozinhaPedidoId, cozinhaPedidoRetornado.getCozinhaId());
    }

    @Entao("é retornado o Cozinha Pedido associado ao Pedido com ID {long}")
    public void retornadoCozinhaPedidoAssociadoAoPedidoComId(Long pedidoId) {
        assertNotNull(cozinhaPedido.getIdPedido());
        assertEquals(pedidoId, cozinhaPedido.getIdPedido());
    }




    private void configureMockForStatus(Long pedidoId, String status) {
        Pedido pedido = new Pedido();
        pedido.setId(pedidoId);
        pedido.setStatusPedido(status);
        pedido.setDataHoraCriado(LocalDateTime.now());
        pedido.setDataHoraRecebimento(LocalDateTime.now());
        pedido.setDataHoraFinalizado(LocalDateTime.now());

        cozinhaPedido = new CozinhaPedido();
        cozinhaPedido.setIdPedido(pedidoId);
        cozinhaPedido.setDataRecebimentoDoPedido(LocalDateTime.now());
        cozinhaPedido.setDataInicioPreparo(LocalDateTime.now());
        cozinhaPedido.setDataFinalizacaoPreparo(LocalDateTime.now());
        cozinhaPedido.setDataEntregaPedido(LocalDateTime.now());
    }
}
