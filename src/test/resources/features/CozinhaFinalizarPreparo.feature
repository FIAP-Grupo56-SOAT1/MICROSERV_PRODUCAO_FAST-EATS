# language: pt
Funcionalidade: Finalizar preparo da cozinha

  Cenario: O pedido está no status de em preparo e a cozinha finaliza o pedido
    Dado que o a cozinha finaliza o pedido
    Quando o status do pedido está em preparo
    Entao o pedido vai para o status de finalizado
