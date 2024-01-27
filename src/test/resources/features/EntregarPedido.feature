# language: pt
Funcionalidade: Receber pedido na cozinha

  Cenario: Receber pedido na cozinha
    Dado que o pedido e criado e enviado para cozinha
    Quando o pedido e recebido e processado na cozinha
    Entao o pedido e definido como status recebido