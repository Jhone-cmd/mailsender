# Mail Sender

Aplicação Spring Boot desenvolvida em Java para cadastro de alunos e envio de e-mail de boas-vindas. O projeto foi feito assistindo às aulas da plataforma RocketSeat, seguindo uma proposta prática de aprendizado com foco em persistência de dados, validação de entrada, envio de e-mail transacional e renderização de template HTML.

## Visão Geral

Este projeto expõe uma API REST simples para receber os dados de um aluno, validar as informações enviadas, verificar se o e-mail já existe na base e, caso esteja tudo correto, salvar o registro no banco e disparar um e-mail de boas-vindas.

A aplicação foi pensada para demonstrar, de forma clara e organizada, a integração entre:

- Spring Web MVC para criar a API REST.
- Spring Data JPA para persistência.
- Banco H2 em memória para desenvolvimento e testes rápidos.
- Spring Mail para envio de e-mails.
- Thymeleaf para montar o conteúdo HTML do e-mail.
- Bean Validation para validações de entrada.
- Lombok para reduzir código repetitivo.

## Funcionalidades

### Cadastro de aluno

O endpoint principal recebe um aluno com os campos nome, e-mail, data de nascimento e senha. Antes de persistir, a aplicação valida os dados e impede e-mails duplicados.

### Validação de dados

O projeto utiliza anotações de validação para garantir que:

- o nome não esteja em branco;
- o e-mail tenha formato válido;
- a data de nascimento seja informada;
- a senha respeite o tamanho mínimo e máximo definidos no DTO.

Quando alguma regra falha, a API responde com os erros de campo em formato estruturado.

### Verificação de e-mail duplicado

Antes de salvar o aluno, a aplicação consulta o banco para conferir se já existe um registro com o mesmo e-mail. Se existir, a resposta é `409 Conflict` com a mensagem de erro apropriada.

### Envio de e-mail de boas-vindas

Após a validação e o cadastro, a aplicação envia um e-mail HTML com:

- template renderizado com Thymeleaf;
- nome, e-mail e data de nascimento do aluno;
- imagem embutida no corpo do e-mail;
- anexo disponibilizado a partir dos recursos da aplicação.

### Persistência com H2

O banco utilizado no projeto é o H2 em memória, o que facilita a execução local sem depender de um banco externo.

### Console H2

O console web do H2 está habilitado para facilitar inspeção dos dados durante o desenvolvimento.

## Endpoint da API

### `POST /students`

Cria um novo aluno e dispara o e-mail de boas-vindas.

#### Exemplo de requisição

```json
{
  "name": "Maria Silva",
  "email": "maria.silva@email.com",
  "birthday": "1995-05-15",
  "password": "minhaSenhaSegura"
}
```

#### Respostas esperadas

- `201 Created` quando o cadastro é concluído com sucesso.
- `400 Bad Request` quando há erro de validação ou outra falha de entrada.
- `409 Conflict` quando o e-mail já estiver cadastrado.

## Estrutura de Pastas

```text
src/main/java/com/jhonecmd/mailsender/
├── MailsenderApplication.java
├── controller/
│   └── StudentController.java
├── dto/
│   ├── ErrorMessageDTO.java
│   └── StudentDTO.java
├── exception/
│   ├── ExceptionHandlerController.java
│   └── StudentAlreadyExists.java
├── mail/
│   ├── MailComponent.java
│   ├── MailMessage.java
│   └── StudentComponent.java
├── model/
│   └── StudentEntity.java
├── repository/
│   └── StudentRepository.java
├── service/
│   └── StudentService.java
└── start/
    └── StartApp.java

src/main/resources/
├── application.properties
├── static/
│   ├── docs/
│   └── images/
└── templates/
    └── welcome-template.html
```

### O que faz cada camada

- `controller`: recebe as requisições HTTP e devolve as respostas da API.
- `dto`: define os objetos de transferência usados na entrada e saída de dados.
- `exception`: centraliza o tratamento de erros e as exceções personalizadas.
- `mail`: contém a lógica de montagem e envio dos e-mails.
- `model`: representa as entidades persistidas no banco.
- `repository`: faz o acesso aos dados via Spring Data JPA.
- `service`: concentra a regra de negócio principal.
- `start`: executa ações simples na inicialização da aplicação.
- `resources/templates`: guarda o template HTML do e-mail.
- `resources/static`: armazena arquivos estáticos usados como imagem e anexo.

## Bibliotecas e Tecnologias

O projeto utiliza principalmente:

- Java 21
- Spring Boot 4.1.0
- Spring Web MVC
- Spring Data JPA
- Spring Mail
- Spring Validation
- Spring Thymeleaf
- H2 Database
- Lombok
- Jakarta Persistence
- Jakarta Validation

## Como a Aplicação Funciona

1. O cliente envia uma requisição `POST /students` com os dados do aluno.
2. O Spring valida o corpo da requisição usando `StudentDTO`.
3. O `StudentController` repassa os dados para a camada de serviço.
4. O `StudentService` verifica se já existe um aluno com o mesmo e-mail.
5. Se o e-mail for novo, o `StudentComponent` monta e envia o e-mail de boas-vindas.
6. O aluno é salvo na tabela `students`.
7. A API responde com o identificador do aluno criado.

## Detalhes do E-mail

O e-mail enviado no cadastro é mais elaborado do que uma mensagem simples de texto. Ele usa um template HTML localizado em `src/main/resources/templates/welcome-template.html`, com destaque visual, dados do aluno, data de envio, imagem embutida e anexo.

Isso permite praticar um cenário realista de comunicação com o usuário, bem próximo do que seria usado em uma aplicação de produção.

## Configuração

O arquivo `application.properties` concentra as configurações principais do projeto, como:

- nome da aplicação;
- banco H2 em memória;
- console do H2;
- configuração de JPA;
- configuração do servidor SMTP.

Para uso local, revise as credenciais de e-mail antes de executar a aplicação. O ideal é manter dados sensíveis fora do repositório e configurar variáveis de ambiente quando for necessário publicar o projeto.

## Como Executar

### Requisitos

- Java 21
- Maven

### Execução com Maven Wrapper

```bash
./mvnw spring-boot:run
```

### Execução com Maven instalado

```bash
mvn spring-boot:run
```

### Acesso ao console H2

Depois de iniciar a aplicação, acesse:

```text
http://localhost:8080/h2-console
```

Use a URL configurada no `application.properties` para conectar ao banco em memória.

## Observações Importantes

- O projeto foi desenvolvido como exercício prático seguindo as aulas da RocketSeat.
- O fluxo principal é o cadastro de aluno com envio de e-mail de boas-vindas.
- O banco H2 é reiniciado junto com a aplicação, já que está configurado em memória.
- A estrutura já está preparada para evolução futura, como novos endpoints, autenticação ou integração com outros serviços.

## Licença

Projeto educacional criado para fins de estudo e prática.