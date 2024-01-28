# language: pt
Funcionalidade: Receber pedido na cozinha

  Cenario: Pedido em preparo na cozinha
    Dado que o pedido está com status recebido
    Quando o pedido e preparado na cozinha
    Entao o pedido e definido como status em preparo