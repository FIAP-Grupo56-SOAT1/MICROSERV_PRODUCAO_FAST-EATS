# language: pt
Funcionalidade: Alterar Status do Pedido

  Cenario: Alterar status para Recebido
    Dado que existe um pedido com id 1
    Quando o usuário solicita a alteração do status do pedido 1 para Recebido
    Entao o status do pedido 1 é alterado para Recebido com sucesso

  Cenario: Alterar status para Em Preparo
    Dado que existe um pedido com id 1
    Quando o usuário solicita a alteração do status do pedido 1 para Em Preparo
    Entao o status do pedido 1 é alterado para Em Preparo com sucesso

  Cenario: Alterar status para Pronto
    Dado que existe um pedido com id 1
    Quando o usuário solicita a alteração do status do pedido 1 para Pronto
    Entao o status do pedido 1 é alterado para Pronto com sucesso

  Cenario: Alterar status para Finalizado
    Dado que existe um pedido com id 1
    Quando o usuário solicita a alteração do status do pedido 1 para Finalizado
    Entao o status do pedido 1 é alterado para Finalizado com sucesso

  Cenario: Tentar alterar status para Recebido de um pedido não encontrado
    Dado que não existe um pedido com id 2
    Quando o usuário solicita a alteração do status do pedido 2 para Recebido
    Entao é exibida uma excessão informando que o pedido 2 não foi encontrado

  Cenario: Tentar alterar status para Em Preparo de um pedido não encontrado
    Dado que não existe um pedido com id 2
    Quando o usuário solicita a alteração do status do pedido 2 para Em Preparo
    Entao é exibida uma excessão informando que o pedido 2 não foi encontrado

  Cenario: Tentar alterar status para Pronto de um pedido não encontrado
    Dado que não existe um pedido com id 2
    Quando o usuário solicita a alteração do status do pedido 2 para Pronto
    Entao é exibida uma excessão informando que o pedido 2 não foi encontrado

  Cenario: Tentar alterar status para Finalizado de um pedido não encontrado
    Dado que não existe um pedido com id 2
    Quando o usuário solicita a alteração do status do pedido 2 para Finalizado
    Entao é exibida uma excessão informando que o pedido 2 não foi encontrado
