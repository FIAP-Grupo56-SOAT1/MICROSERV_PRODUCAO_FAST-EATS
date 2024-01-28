# language: pt
Funcionalidade: Gerenciamento de Cozinha Pedido

  Cenário: Consultar Cozinha Pedido
    Dado que existe um Cozinha Pedido com ID "65b1762af79a235f75630fa6"
    Quando o usuário consulta o Cozinha Pedido por ID "65b1762af79a235f75630fa6"
    Então é retornado o Cozinha Pedido com ID "65b1762af79a235f75630fa6"

  Cenário: Consultar Cozinha Pedido por ID de Pedido
    Dado que existe um Pedido com ID 1
    E existe um Cozinha Pedido associado ao Pedido com ID 1
    Quando o usuário consulta o Cozinha Pedido por ID de Pedido 1
    Então é retornado o Cozinha Pedido associado ao Pedido com ID 1

  Cenário: Receber Pedido na Cozinha
    Dado que existe um Pedido com ID 1
    Quando o usuário solicita receber o Pedido na Cozinha com ID 1
    Então é retornado o Cozinha Pedido atualizado com o status "RECEBIDO"
    E o status do pedido foi atualizado para "RECEBIDO"

  Cenário: Iniciar Preparo na Cozinha
    Dado que existe um Pedido com ID 1
    E existe um Cozinha Pedido associado ao Pedido com ID 1
    Quando o usuário solicita iniciar o preparo na Cozinha com ID 1
    Então é retornado o Cozinha Pedido atualizado com o status "INICIO_PREPARO"
    E o status do pedido foi atualizado para "EM_PREPARO"

  Cenário: Finalizar Preparo na Cozinha
    Dado que existe um Pedido com ID 1
    E existe um Cozinha Pedido associado ao Pedido com ID 1
    Quando o usuário solicita finalizar o preparo na Cozinha com ID 1
    Então é retornado o Cozinha Pedido atualizado com o status "FINALIZANDO_PREPARO"
    E o status do pedido foi atualizado para "PRONTO"


  Cenário: Retirar Pedido na Cozinha
    Dado que existe um Pedido com ID 1
    E existe um Cozinha Pedido associado ao Pedido com ID 1
    Quando o usuário solicita retirar o Pedido na Cozinha com ID 1
    Então é retornado o Cozinha Pedido atualizado com o status "PEDIDO_ENTREGUE"
    E o status do pedido foi atualizado para "FINALIZADO"
