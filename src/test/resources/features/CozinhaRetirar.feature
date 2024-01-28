# language: pt
Funcionalidade: Receber pedido na cozinha

  Cenario: O pedido está com status finalizado e a cozinha disponibiliza para retirada
    Dado que o a pedido está pronto
    Quando a cozinha atualizar o status do pedido
    Entao o pedido fica disponivel para retirada