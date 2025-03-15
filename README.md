# Sobre
Forum Hub é um projeto desenvolvido como conclusão do programa [ONE (Oracle Next Education)](https://www.oracle.com/br/education/oracle-next-education/), uma parceria Oracle Alura. O objetivo deste projeto é implementar uma API RESTful desenvolvida em Java utilizando o framework Spring Boot que replica a funcionalidade de um fórum, permitindo a criação, consulta, atualização e exclusão de tópicos. A API foi projetada para ser eficiente, segura e de fácil manutenção, seguindo as melhores práticas de desenvolvimento.

# Funcionalidades

- Criação de Tópicos
- Listagem de Tópicos
- Detalhamento de Tópico
- Atualização de Tópicos
- Exclusão de Tópicos

# Tecnologias Usadas
- Java 17 
- Spring Boot 3.4.1
- Spring Boot Security
- JWT (JSON Web Token)
- Lombok
- Hibernate
- MySQL
- Maven

# Endpoints

| Método          | Endpoint      | Descrição                            |
|-----------------|---------------|--------------------------------------|
| **[GET](#)**    | `/topicos`    | Lista todos os Tópicos               |
| **[GET](#)**    | `/topicos/id` | Lista um Tópico específico detalhado |
| **[POST](#)**   | `/topicos`    | Cadastra um Tópico                   |
| **[PUT](#)**    | `/topicos/id` | Atualiza um Tópico                   |
| **[DELETE](#)** | `/topicos/id` | Deleta um Tópico                     |

#
<p align="center">
  <a href="https://www.oracle.com/br/education/oracle-next-education/">
    <img height="50" src="https://i.imgur.com/RYYUpCK.png">
  </a>
</p>
