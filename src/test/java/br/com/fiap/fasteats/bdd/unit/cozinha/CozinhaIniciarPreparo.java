package br.com.fiap.fasteats.bdd.unit.cozinha;

import br.com.fiap.fasteats.core.dataprovider.AlterarPedidoStatusOutputPort;
import br.com.fiap.fasteats.core.dataprovider.CozinhaPedidoOutputPort;
import br.com.fiap.fasteats.core.dataprovider.PedidoOutputPort;
import br.com.fiap.fasteats.core.domain.exception.PedidoNotFound;
import br.com.fiap.fasteats.core.domain.model.Pedido;
import br.com.fiap.fasteats.core.usecase.AlterarPedidoStatusInputPort;
import br.com.fiap.fasteats.core.usecase.impl.AlterarPedidoStatusUseCase;
import br.com.fiap.fasteats.core.usecase.impl.CozinhaPedidoUseCase;
import br.com.fiap.fasteats.core.validator.AlterarPedidoStatusValidator;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

import static br.com.fiap.fasteats.core.constants.StatusPedidoConstants.STATUS_PEDIDO_RECEBIDO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class CozinhaIniciarPreparo {
    AutoCloseable openMocks;
    LocalDateTime dateTimeNow = LocalDateTime.now();
    @Before
    public void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
    }

    @After
    public void tearDown() throws Exception {
        openMocks.close();
    }

    @Mock
    private CozinhaPedidoOutputPort cozinhaPedidoOutputPort;

    @Mock
    private PedidoOutputPort pedidoOutputPort;

    @Mock
    private AlterarPedidoStatusInputPort alterarPedidoStatusInputPort;

    @InjectMocks
    private CozinhaPedidoUseCase cozinhaPedidoUseCase;


    @Mock
    private AlterarPedidoStatusOutputPort alterarPedidoStatusOutputPort;

    @InjectMocks
    private AlterarPedidoStatusUseCase alterarPedidoStatusUseCase;


    @Mock
    private AlterarPedidoStatusValidator alterarPedidoStatusValidator;

    @Dado("que o a pedido foi pagao")
    public void que_o_a_pedido_foi_pagao() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Quando("a cozinha recebe o pedidoId")
    public void a_cozinha_recebe_o_pedido_id() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Entao("inicia o preparo na cozinha")
    public void inicia_o_preparo_na_cozinha() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

}
