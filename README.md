# message-schedule

Projeto de agendamento para envio de comunicação. Desenvolvido na Linguagem Java.

## Tecnologias 

- [Spring Boot](https://spring.io/projects/spring-boot) - Framework de Desenvolvimento para a Linguagem Java.

- [Lombok](https://projectlombok.org/) - Biblioteca Java focada em produtividade e redução de código boilerplate que, por meio de anotações adicionadas ao nosso código, ensinamos o compilador (maven ou gradle) durante o processo de compilação a criar código Java.

- [JUnit5](https://junit.org/junit5/) - Framework facilita a criação e manutenção do código para a automação de testes com apresentação dos resultados.

- [Mockito](https://site.mockito.org/) - Estrutura de teste de código aberto para Java liberada sob a licença MIT. A estrutura permite a criação de objetos duplos de teste em testes de unidade automatizados com o objetivo de desenvolvimento orientado a teste ou desenvolvimento orientado a comportamento.

- [PostgreSQL](https://www.postgresql.org/download/) - Banco de dados.

- [Hibernate](https://hibernate.org/) - Framework para persistência de dados. (ORM)

- [JPA](https://hibernate.org/orm/) - Especificação do Java que dita como os Frameworks ORM devem ser implementados.

- [Docker](https://www.docker.com/) - Plataforma open source que facilita a criação e administração de ambientes isolados. Ele possibilita o empacotamento de uma aplicação ou ambiente dentro de um container, se tornando portátil para qualquer outro host que contenha o Docker instalado.

- [Swagger](https://swagger.io/) - Essencialmente uma linguagem de descrição de interface para descrever APIs RESTful expressas usando JSON.


## Para compilar e executar o projeto você precisa ter instalado

 - [Maven](https://maven.apache.org/) - Ferramenta de automação de compilação utilizada primariamente em projetos Java.
 - [Docker](https://docs.docker.com/get-docker/) - Execução de aplicativos de containers.
 
## Instalação

 - Clone o projeto: `$git clone https://github.com/paluan-batista/message-schedule.git`
 - Após concluir o download do projeto, acesse o diretório: `$cd message-schedule/`
 - Na raiz do projeto chamado da seguinte maneira: `$./docker-run.sh`
 
## As APIs

Você pode visualizar as APIs utilizando o Swagger através desta URL: http://localhost:8080/swagger-ui.html#/

### Descrição das APIs
- Enviar uma solicitação de agendamento - Utilizando o verbo POST - http://localhost:8080/api/schedule/ - Com o body abaixo:

`{
	"send_date": "2022-02-09 23:59:59",
	"recipient": {
		"recipient": "paluan.desenvolvimento@gmail.com"
	},
	"message": "Mensagem de teste",
	"type": "EMAIL"
}`
`OBS: os TYPES disponíveis são SMS, EMAIL, PUSH e WHATSAPP`

Existem validações, ou seja, se o campo `recipient` for um e-mail independente do que for informado no campo `type` a aplicação vai salvar o `type` como `EMAIL` e caso o `type` diferente de um `EMAIL` ou `PHONE CELULAR` retornamos uma exceção informando que o `type` é inválido. A data informada também não pode ser no dia anterior, nem com um time anterior ao time atual.

Exemplo de uma resposta após salvar uma solicitação de agendamento:
`Header - Location: http://localhost:8080/api/schedule/PENDING
Content-type: application/json
Date: Wed, 09 Feb 2022 23:59:59 GMT`

E no body: `"23ba1ab7-6384-48c6-b6e4-4947ce5f749b"`

- Buscar solicitações de agendamento por status - Utilizando o verbo GET - http://localhost:8080/api/schedule/{status} - Status disponíveis para consulta são `PENDING, DELETED, SENT` . A busca por status está paginada com o [Pageable do próprio Spring](https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/domain/Pageable.html).

- Deletar uma solicitação de agendamento por `UUID` - Utilizando o verbo DELETE - http://localhost:8080/api/schedule/{uuid} - O `UUID` é devolvido após realizar a solicitação de agendamento.
