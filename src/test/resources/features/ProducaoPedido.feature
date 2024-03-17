# language: pt
Funcionalidade: Gerenciamento da Produção dos Pedidos

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
