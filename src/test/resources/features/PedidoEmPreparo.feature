# language: pt
Funcionalidade: Receber pedido na cozinha

  Cenario: Pedido em preparo na cozinha
    Dado que o pedido está com status recebido
    Quando o pedido e preparado na cozinha
    Entao o pedido e definido como status em preparo


  Cenario: Enviar um pedido que nao esta pago para cozinha
    Dado que o pedido enviado nao esta pago
    Quando o pedido esta com staus diferente de pago
    Entao o pedido nao pode ser recebido na cozinha