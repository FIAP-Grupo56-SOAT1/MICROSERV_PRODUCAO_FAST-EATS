# language: pt
Funcionalidade: Receber pedido na cozinha

  Cenario: Pedido está pronto na cozinha
    Dado que o pedido está pronto na cozinha
    Quando o pedido e definido na cozinha como status pronto
    Entao a atualiza o pedido


  Cenario: Pedido está pronto na cozinha
    Dado que o pedido existe no sistema
    Quando o pedido esta com status difente de em preparo
    Entao o estatus do pedido nao pode ser atualizado para pronto