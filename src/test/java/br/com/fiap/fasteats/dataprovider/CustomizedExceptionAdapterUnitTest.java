package br.com.fiap.fasteats.dataprovider;

import br.com.fiap.fasteats.core.domain.exception.PedidoNotFound;
import br.com.fiap.fasteats.core.domain.exception.RegraNegocioException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CustomizedExceptionAdapterUnitTest {

    @Mock
    private WebRequest webRequest;

    @InjectMocks
    private CustomizedExceptionAdapter exceptionAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void handleAllExceptions_DeveRetornarInternalServerError() {
        // Arrange
        Exception exception = new Exception("Erro interno");

        when(webRequest.getDescription(false)).thenReturn("Descrição do erro");

        // Act
        ResponseEntity<Object> responseEntity = exceptionAdapter.handleAllExceptions(exception, webRequest);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        verify(webRequest, times(1)).getDescription(false);
    }

    @Test
    void handleUserNotFoundException_DeveRetornarNotFound() {
        // Arrange
        PedidoNotFound exception = new PedidoNotFound("Pedido não encontrado");

        when(webRequest.getDescription(false)).thenReturn("Descrição do erro");

        // Act
        ResponseEntity<Object> responseEntity = exceptionAdapter.handleUserNotFoundException(exception, webRequest);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        verify(webRequest, times(1)).getDescription(false);
    }

    @Test
    void handleRegraNegocioException_DeveRetornarBadRequest() {
        // Arrange
        RegraNegocioException exception = new RegraNegocioException("Violou uma regra de negócio");

        when(webRequest.getDescription(false)).thenReturn("Descrição do erro");

        // Act
        ResponseEntity<Object> responseEntity = exceptionAdapter.handleRegraNegocioException(exception, webRequest);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        verify(webRequest, times(1)).getDescription(false);
    }
}
