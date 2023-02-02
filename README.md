# Spring-AlgaWorks
## API REST desenvolvida durante o curso _Mergulho Spring Rest_ pela AlgaWorks

## Tecnologias/Ferramentas usadas

  * <img align="center" alt="Alan-Java" height="30" width="40" src="https://raw.githubusercontent.com/devicons/devicon/master/icons/java/java-original.svg"> Java<br>
  * <img align="center" alt="Alan-MySql" height="30" width="40" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/mysql/mysql-original.svg"> MySql<br>
  * <img align="center" alt="Alan-Spring" height="30" width="40" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/spring/spring-original.svg"> Spring<br>
  * <img align="center" alt="Alan-Maven" height="30" width="40" src="https://user-images.githubusercontent.com/117518719/216434196-b63f5ea3-057f-42e5-abfc-b35deade0635.png"> Maven<br>
* <img align="center" alt="Alan-Postman" height="30" width="40" src="https://user-images.githubusercontent.com/117518719/216434927-59ceed3f-b838-42b3-845e-1975e2cb08a0.svg"> Posman<br>
* <img align="center" alt="Alan-Intellij" height="30" width="40" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/intellij/intellij-original.svg"> Intellij<br>

## ⭐ Breve descrição

O sistema possui cinco entidades (Cliente, Destinatário, Entrega, Ocorrencia e StatusEntrega)<br>
_obs.:_ StatusEntrega é uma _enum_

O programa funciona como um sistema de entregas onde o cliente insere seu nome, e-mail e telefone. Assim, é possível solicitar uma entrega que possui um Status (Pendente, finalizada ou cancelada), uma data de pedido e outra de finalização, a taxa, destinatário, ocorrencia e o cliente que fez o pedido.

A Ocorrencia é útil para colocar uma descrição de um eventual problema ou observação durante a entrega e/ou cancelamento de uma entrega. Assim, a ocorrencia está vinculada a uma entrega, possuindo uma descrição e a data do registro.

Um ponto interessante dessa API é a criação de classes para validação de exceções personalizadas e classes de validação.<br>
Por exemplo: A classe Problema é responsável por indicar o Status HTTP de um erro de validação, retornar a data e hora, um título, uma lista com os campos que não passaram da validação. Campos é uma subclasse que retorna o nome do campo e a menssagem vinculada a seu erro de validação ("não pode ser nulo", "não pode estar em branco"...)

## ⚙️ Operações
* Cliente: Criar, Listar todos, Obter por id, Atualizar e deletar
* Entrega: Criar, Listar, Obter por id, Atualizar (seu status)
*Ocorrencia: Criar (registrar), listar ocorrencias 

## 🌫 Camadas (Pacotes)

| Camada | Função |
| ------------- | ------------- |
| Assembler | Conversão de objetos |
| ExceptionHandler | Tratar erros e exceções |
| Model | Inserção/recepção  de atributos |
| Config | Configurar o bean |
| Controller | Configurar verbos e status HTTP |
| Domain/Exception | Atribuições para a camada de exceptionHandler |
| Domain/Model | Inserir entidades com suas anotações, validações e tipos|
| Domain/Repository | Gerenciamento das entidades|
| Domain/Service |Regras de negócio do sistema e ações CRUD|


### 👀 Observações
O sistema possui um pacote de migrations, assim ele mesmo cria seu Banco de Dados, Schemas e Tabelas <br>
A interface ValidationGroups valida os atributos marcados com @NotNull
