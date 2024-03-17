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


  Cenario: Tentar alterar o status do pedido para Recebidio
    Dado que existe um pedido status diferente de pago com id 1
    Quando o usuário solicita a alteração para o status do Recebido para Pedido 1
    Entao é exibida uma excessão informando que o pedido 1 não pode ser recebido se não estiver com status pago

  Cenario: Tentar alterar o status do pedido para EmPreparo
    Dado que existe um pedido status diferente de recebido com id 1
    Quando o usuário solicita a alteração para o status do Pedido 1 para status EmPreparo
    Entao é exibida uma excessão informando que o pedido 1 não pode mudar para status EmPreparo porque não está com status Recebido

  Cenario: Tentar alterar o status do pedido para Pronto
    Dado que existe um pedido com id 1 status diferente de EmPreparo
    Quando o usuário solicita a alteração do pedido 1 para status Pronto
    Entao é exibida uma excessão informando que o pedido 1 não pode mudar para status Pronto porque está com status diferente de EmPreparo


  Cenario: Tentar alterar o status do pedido para Finalizado
    Dado que existe um pedido com id 1 status diferente de Pronto
    Quando o usuário solicita a alteração do pedido 1 para status Finalizado
    Entao é exibida uma excessão informando que o pedido 1 não pode mudar para status Finalizado porque está com status diferente de Pronto