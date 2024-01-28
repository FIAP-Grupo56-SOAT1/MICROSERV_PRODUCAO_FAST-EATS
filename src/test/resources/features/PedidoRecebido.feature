# language: pt
Funcionalidade: Receber pedido na cozinha

  Cenario: Pedido em preparo na cozinha
    Dado que o pedido está com status recebido
    Quando o pedido e preparado na cozinha
    Entao o pedido e definido como status em preparo


  Cenario: Enviar pedido para cozinha
    Dado que o pedido que não existe é enviado para cozinha
    Quando o pedido e nao existe
    Entao o pedido nao e preparado e retorna mensagem de erro