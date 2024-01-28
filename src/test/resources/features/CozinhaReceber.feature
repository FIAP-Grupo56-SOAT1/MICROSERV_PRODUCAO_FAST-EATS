# language: pt
Funcionalidade: Receber pedido na cozinha

  Cenario: O pedido está no status de em pago e a cozinha recebe o pedido
    Dado que o a pedido foi pagao
    Quando a cozinha inicia o pedido
    Entao a cozinha atualzia o status do pedido
