# language: pt
Funcionalidade: Receber pedido na cozinha

  Cenario: Receber pedido na cozinha
    Dado que o pedido e criado e enviado para cozinha
    Quando o pedido e recebido e processado na cozinha
    Entao o pedido e definido como status recebido

  Cenario: Receber pedido na cozinha
    Dado que o pedido que não existe é enviado para cozinha
    Quando o pedido e nao esta com status pago
    Entao o pedido nao e preparado e retorna mensagem de erro