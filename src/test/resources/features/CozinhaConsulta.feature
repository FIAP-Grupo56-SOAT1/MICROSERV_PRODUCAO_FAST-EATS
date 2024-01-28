# language: pt
Funcionalidade:Obter informação da cozinha

  Cenario: A cozinha recebe o indentificador da cozinha para retorna informações
    Dado que o a cozinha recebe o cozinhaId
    Quando o pedido ja foi iniciado na cozinha
    Entao os dados do pedido fica disponivel na cozinha

