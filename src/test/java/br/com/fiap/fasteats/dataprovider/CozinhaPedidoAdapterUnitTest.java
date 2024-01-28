package br.com.fiap.fasteats.dataprovider;

import br.com.fiap.fasteats.core.domain.model.CozinhaPedido;
import br.com.fiap.fasteats.dataprovider.repository.CozinhaRepository;
import br.com.fiap.fasteats.dataprovider.repository.entity.CozinhaPedidoEntity;
import br.com.fiap.fasteats.dataprovider.repository.mapper.CozinhaPedidoEntityMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static br.com.fiap.fasteats.core.constants.ProcessoCozinhaConstants.STATUS_COZINHA_RECEBIDO;
import static br.com.fiap.fasteats.core.constants.StatusPedidoConstants.STATUS_PEDIDO_RECEBIDO;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CozinhaPedidoAdapterUnitTest {
    @Mock
    private CozinhaRepository cozinhaRepository;
    @Mock
    private CozinhaPedidoEntityMapper cozinhaPedidoEntityMapper;
    @InjectMocks
    private CozinhaPedidoAdapter cozinhaPedidoAdapter;
    AutoCloseable openMocks;

    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    void salvar_DeveSalvarCozinhaPedido_QuandoChamado() {
        // Arrange
        CozinhaPedidoEntity cozinhaPedidoEntity = getCozinhaPedidoEntity();
        CozinhaPedido cozinhaPedido = toCozinhaPedido(cozinhaPedidoEntity);
        when(cozinhaPedidoEntityMapper.toCozinhaPedidoEntity(cozinhaPedido)).thenReturn(cozinhaPedidoEntity);
        when(cozinhaRepository.save(cozinhaPedidoEntity)).thenReturn(cozinhaPedidoEntity);
        when(cozinhaPedidoEntityMapper.toCozinhaPedido(cozinhaPedidoEntity)).thenReturn(cozinhaPedido);

        // Act
        CozinhaPedido result = cozinhaPedidoAdapter.salvar(cozinhaPedido);

        // Assert
        assertNotNull(result);
        assertEquals(cozinhaPedido, result);
        assertEquals(cozinhaPedido.hashCode(), result.hashCode());
        assertEquals(cozinhaPedidoEntity, toCozinhaPedidoEntity(result));
        assertEquals(cozinhaPedidoEntity.hashCode(), toCozinhaPedidoEntity(result).hashCode());
        assertNotEquals(cozinhaPedidoEntity, getCozinhaPedidoEntityFromBuilder());
        assertNotEquals(cozinhaPedidoEntity.toString(), getCozinhaPedidoEntityFromBuilder().toString());
        assertNotEquals(cozinhaPedido, new CozinhaPedido());
        verify(cozinhaRepository, times(1)).save(cozinhaPedidoEntity);
    }

    @Test
    void listar_DeveRetornarListaDeCozinhasPedidos_QuandoChamado() {
        // Arrange
        List<CozinhaPedidoEntity> cozinhaPedidoEntities = List.of(new CozinhaPedidoEntity());
        List<CozinhaPedido> cozinhaPedidos = List.of(new CozinhaPedido());
        when(cozinhaRepository.findAll()).thenReturn(cozinhaPedidoEntities);
        when(cozinhaPedidoEntityMapper.toCozinhaPedido(any())).thenReturn(new CozinhaPedido());

        // Act
        List<CozinhaPedido> result = cozinhaPedidoAdapter.listar();

        // Assert
        assertNotNull(result);
        assertEquals(cozinhaPedidos.size(), result.size());
        verify(cozinhaRepository, times(1)).findAll();
        verify(cozinhaPedidoEntityMapper, times(cozinhaPedidoEntities.size())).toCozinhaPedido(any());
    }

    @Test
    void consultar_DeveRetornarCozinhaPedido_QuandoCozinhaExiste() {
        // Arrange
        String cozinhaId = "1";
        CozinhaPedidoEntity cozinhaPedidoEntity = new CozinhaPedidoEntity();
        cozinhaPedidoEntity.setCozinhaId(cozinhaId);
        cozinhaPedidoEntity.setIdPedido(1L);
        cozinhaPedidoEntity.setStatusPedido(STATUS_PEDIDO_RECEBIDO);
        cozinhaPedidoEntity.setProcessoAtual(STATUS_COZINHA_RECEBIDO);
        cozinhaPedidoEntity.setDataRecebimentoDoPedido(LocalDateTime.now());
        cozinhaPedidoEntity.setDataInicioPreparo(LocalDateTime.now());
        cozinhaPedidoEntity.setDataFinalizacaoPreparo(LocalDateTime.now());
        cozinhaPedidoEntity.setDataEntregaPedido(LocalDateTime.now());
        CozinhaPedido cozinhaPedido = toCozinhaPedido(cozinhaPedidoEntity);

        when(cozinhaRepository.findById(cozinhaId)).thenReturn(Optional.of(cozinhaPedidoEntity));
        when(cozinhaPedidoEntityMapper.toCozinhaPedido(cozinhaPedidoEntity)).thenReturn(cozinhaPedido);

        // Act
        Optional<CozinhaPedido> result = cozinhaPedidoAdapter.consultar(cozinhaId);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(cozinhaPedido, result.get());
        verify(cozinhaRepository, times(1)).findById(cozinhaId);
        verify(cozinhaPedidoEntityMapper, times(1)).toCozinhaPedido(cozinhaPedidoEntity);
    }

    @Test
    void consultar_DeveRetornarOptionalVazio_QuandoCozinhaNaoExiste() {
        // Arrange
        String cozinhaId = "1";
        when(cozinhaRepository.findById(cozinhaId)).thenReturn(Optional.empty());

        // Act
        Optional<CozinhaPedido> result = cozinhaPedidoAdapter.consultar(cozinhaId);

        // Assert
        assertTrue(result.isEmpty());
        verify(cozinhaRepository, times(1)).findById(cozinhaId);
        verifyNoInteractions(cozinhaPedidoEntityMapper);
    }

    @Test
    void consultarPorIdPedido_DeveRetornarCozinhaPedido_QuandoPedidoExiste() {
        // Arrange
        Long idPedido = 1L;
        CozinhaPedidoEntity cozinhaPedidoEntity = new CozinhaPedidoEntity();
        CozinhaPedido cozinhaPedido = new CozinhaPedido();
        when(cozinhaRepository.findByIdPedido(idPedido)).thenReturn(Optional.of(cozinhaPedidoEntity));
        when(cozinhaPedidoEntityMapper.toCozinhaPedido(cozinhaPedidoEntity)).thenReturn(cozinhaPedido);

        // Act
        Optional<CozinhaPedido> result = cozinhaPedidoAdapter.consultarPorIdPedido(idPedido);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(cozinhaPedido, result.get());
        verify(cozinhaRepository, times(1)).findByIdPedido(idPedido);
        verify(cozinhaPedidoEntityMapper, times(1)).toCozinhaPedido(cozinhaPedidoEntity);
    }

    @Test
    void consultarPorIdPedido_DeveRetornarOptionalVazio_QuandoPedidoNaoExiste() {
        // Arrange
        Long idPedido = 1L;
        when(cozinhaRepository.findByIdPedido(idPedido)).thenReturn(Optional.empty());

        // Act
        Optional<CozinhaPedido> result = cozinhaPedidoAdapter.consultarPorIdPedido(idPedido);

        // Assert
        assertTrue(result.isEmpty());
        verify(cozinhaRepository, times(1)).findByIdPedido(idPedido);
        verifyNoInteractions(cozinhaPedidoEntityMapper);
    }

    private CozinhaPedidoEntity getCozinhaPedidoEntity() {
        LocalDateTime dataHoraTeste = LocalDateTime.now();
        return new CozinhaPedidoEntity("1234556", dataHoraTeste, dataHoraTeste,
                dataHoraTeste, dataHoraTeste, 1L,
                STATUS_PEDIDO_RECEBIDO, STATUS_COZINHA_RECEBIDO);
    }

    private CozinhaPedidoEntity getCozinhaPedidoEntityFromBuilder() {
        LocalDateTime dataHoraTeste = LocalDateTime.now();
        return  CozinhaPedidoEntity.builder()
                .cozinhaId("12345567")
                .dataRecebimentoDoPedido(dataHoraTeste)
                .dataInicioPreparo(dataHoraTeste)
                .dataFinalizacaoPreparo(dataHoraTeste)
                .dataEntregaPedido(dataHoraTeste)
                .idPedido(1L)
                .statusPedido(STATUS_PEDIDO_RECEBIDO)
                .processoAtual(STATUS_COZINHA_RECEBIDO)
                .build();
    }

    private CozinhaPedido toCozinhaPedido(CozinhaPedidoEntity cozinhaEntity) {
        CozinhaPedido cozinhaPedido = new CozinhaPedido();
        cozinhaPedido.setCozinhaId(cozinhaEntity.getCozinhaId());
        cozinhaPedido.setDataRecebimentoDoPedido(cozinhaEntity.getDataRecebimentoDoPedido());
        cozinhaPedido.setDataInicioPreparo(cozinhaEntity.getDataInicioPreparo());
        cozinhaPedido.setDataFinalizacaoPreparo(cozinhaEntity.getDataFinalizacaoPreparo());
        cozinhaPedido.setDataEntregaPedido(cozinhaEntity.getDataEntregaPedido());
        cozinhaPedido.setIdPedido(cozinhaEntity.getIdPedido());
        cozinhaPedido.setStatusPedido(cozinhaEntity.getStatusPedido());
        cozinhaPedido.setProcessoAtual(cozinhaEntity.getProcessoAtual());
        return cozinhaPedido;
    }

    private CozinhaPedidoEntity toCozinhaPedidoEntity(CozinhaPedido cozinha) {
        CozinhaPedidoEntity.CozinhaPedidoEntityBuilder cozinhaPedidoEntity = CozinhaPedidoEntity.builder();
        cozinhaPedidoEntity.cozinhaId(cozinha.getCozinhaId());
        cozinhaPedidoEntity.dataRecebimentoDoPedido(cozinha.getDataRecebimentoDoPedido());
        cozinhaPedidoEntity.dataInicioPreparo(cozinha.getDataInicioPreparo());
        cozinhaPedidoEntity.dataFinalizacaoPreparo(cozinha.getDataFinalizacaoPreparo());
        cozinhaPedidoEntity.dataEntregaPedido(cozinha.getDataEntregaPedido());
        cozinhaPedidoEntity.idPedido(cozinha.getIdPedido());
        cozinhaPedidoEntity.statusPedido(cozinha.getStatusPedido());
        cozinhaPedidoEntity.processoAtual(cozinha.getProcessoAtual());
        return cozinhaPedidoEntity.build();
    }
}
