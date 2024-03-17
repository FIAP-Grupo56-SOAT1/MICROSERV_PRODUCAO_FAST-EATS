# language: pt
Funcionalidade: Gerenciamento de Cozinha Pedido

  Cenário: Consultar Cozinha Pedido
    Dado que existe um Cozinha Pedido com ID "65b1762af79a235f75630fa6"
    Quando o usuário consulta o Cozinha Pedido por ID "65b1762af79a235f75630fa6"
    Então é retornado o Cozinha Pedido com ID "65b1762af79a235f75630fa6"

  Cenário: Consultar Cozinha Pedido por ID de Pedido
    Dado que existe um Pedido com ID 1 na cozinha
    E existe na Cozinha  um Pedido associado ao Pedido com ID 1
    Quando o usuário consulta o Cozinha Pedido por ID de Pedido 1
    Então é retornado o Cozinha Pedido associado ao Pedido com ID 1

  Cenário: Lista todos os pedidos na cozinha
    Dado que existe pedidos na cozinha
    Quando o usuário consulta a lista de pedidos
    Então é retornado uma lista de  Cozinha Pedido
