# Requests disponíveis para teste e seus objetivos

## Teste

Para poder estar realizando os testes pode ser feito o download do repósitorio em questão e estar subindo localmente através do Intellij.

Para questões de banco de dados foi utilizando Postgres.
Nesse repositório se encontra o arquivo script.sql que contém os scripts para criação das bases e também das tabelas.

---
## Informações API

### /api/pedido

#### POST
Criar um registro na tabela pedido sem informações relevantes.

#### GET
Retornar todos os dados do pedido, itens e também as informações dos produtos vinculados aos itens do pedido

#### Exemplo usando SOAPUI
* POST
  * Method: Post
  * EndPoint: http://localhost:8080
  * Resource: /api/pedido
  * Parameters:
  
* GET:
  * Method: Get
  * EndPoint: http://localhost:8080
  * Resource: /api/produto
  * Parameters: ?id=4
---

### /api/produto

#### GET
Retorna todos os produtos cadastrados na base de dados

#### POST
Cria um novo produto no banco com base na descrição e preço repassado

#### Exemplo
* POST
    * Method: Post
    * EndPoint: http://localhost:8080
    * Resource: /api/produto
    * Parameters: ?descricao=batata frita&preco=6

* GET:
    * Method: Get
    * EndPoint: http://localhost:8080
    * Resource: /api/produto
    * Parameters:

---
### /api/itemPedido

#### POST
Gera um novo registro de item do pedido vinculando o Pedido passado, o código do produto com a quantidade também repassada por parâmetro

#### Delete
Com base na quantidade passada por parâmetro, caso o produto tenha sido adicionado ao pedido passado por parâmetro será verificado, se a quantidade repassada for menor que a quantidade atual, porém maior que ZERO será realizado uma atualização no registro e a nova quantidade será considerada a passada por parâmetro. Caso a quantidade seja ZERO ou MENOR então o item será apagado do pedido

#### Exemplo
* POST:
  * Method: Get
  * EndPoint: http://localhost:8080
  * Resource: /api/itemPedido
  * Parameters: ?idPedido=4&idProduto=1&qtProduto=8

* DELETE:
  * Method: Get
  * EndPoint: http://localhost:8080
  * Resource: /api/itemPedido
  * Parameters: ?idPedido=4&idProduto=1&qtProduto=2

---

### /api/fechamento

#### PUT
Será verificado o PEDIDO passado por parâmetro, percorrido todos os itens do pedido e será calculado o preço total do pedido(ITEM_PEDIDO.QT_PRODUTO * PRODUTO.PREÇO)

#### GET
Com base no PEDIDO passado por parâmetro e na lista de produtos passado será calculado um “pré” valor de fechamento com base apenas na lista dos itens repassado.

#### Exemplo
* PUT
  * Method: Put
  * EndPoint: http://localhost:8080
  * Resource: /api/fechamento
  * Parameters: ?idPedido=4
* GET
  * Method: Get
  * EndPoint: http://localhost:8080
  * Resource: /api/fechamento
  * Parameters: ?idPedido=4&listaItens={1,2;1147,3}

