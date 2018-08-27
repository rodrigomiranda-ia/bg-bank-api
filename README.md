# Bank API

API REST com o objetivo de fornecer serviços para manter estoque de cédulas e saque de valores.

A API conta com os seguintes endpoints:

https://bank-example-api.herokuapp.com/bank/api/estoque/[valor]

Se consumida com o método HTTP GET, retornará todo o estoque de cédulas cadastradas na base de dados.
Se passado [valor] com o método HTTP GET , ele retornará o estoque da nota específica.
Esse endopoint com o método HTTP POST, cadastra ou atualiza o estoque de uma cédula existente. Com o seguinte exemplo de body:

{
    "valorNota":"50",
    "quantidade": "10"
}

Se passado [valor] com o método HTTP DELETE, ele excluirá a cédula da base de dados.

https://bank-example-api.herokuapp.com/bank/api/saque?valor=[valor]

Deve ser consumida com o método HTTP GET, onde retornará a combinação de cédulas necessárias para totalizar o valor do saque.

A aplicação está implantada no Heroku.

Para executar localmente basta seguir os seguintes passos:

- Fazer clone do projeto.
- Modificar parametros de banco de dados do arquivo application.properties.
- Executar dentro do PowerShell no diretório da API o comando: ./gradlew bootRun. Ou por meio de uma IDE.