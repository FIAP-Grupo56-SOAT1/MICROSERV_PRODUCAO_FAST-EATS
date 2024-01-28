# language: pt
Funcionalidade: Receber pedido na cozinha

  Cenario: Pedido está pronto na cozinha
    Dado que o pedido está finalizado
    Quando o pedido e definido na cozinha como status finalizado
    Entao o pedido é atualiza o pedido

  Cenario: Tentar retirar o pedido da cozinha
    Dado que o pedido nao esta com status finalizado
    Quando o pedido esta com status difernete de pronto
    Entao o pedido nao e finalizado na cozinha
