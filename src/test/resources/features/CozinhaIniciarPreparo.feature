# language: pt
Funcionalidade: Receber pedido na cozinha e iniciar o preparo

  Cenario: O pedido está no status de em pago e a cozinha inicia o processo de preparo
    Dado que o a pedido foi pagao
    Quando a cozinha recebe o pedidoId
    Entao inicia o preparo na cozinha


