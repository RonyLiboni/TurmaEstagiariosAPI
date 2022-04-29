# Primeiros passos do programa

- Quando você iniciar o projeto o banco de dados será criado e populado com o Flyway. Serão populadas as tabelas das classes abaixo:
    - Usuario
    - Perfil
    - Turma
    - Estagiario
    
- Utilizei Lombok, para executar esse programa você precisa ter ele instalado em sua IDE.

- Há dois usuarios cadastrados.
    
   - Admin
        - usuario: admin@gmail.com
        - senha: 123456
        - Tem acesso a todos endpoints.
    
    - Usuario comum
        - usuario: usuario@gmail.com
        - senha: 123456
        - Tem acesso somente ao POST /auth, e a todos métodos GET


#  Endpoints que Admin e usuario comum tem acesso antes de se autenticarem

## Autenticação com token
    - POST http://localhost:8080/auth - Processo de autenticacao na API
    - Pode utilizar o JSON abaixo para autenticacao.  
  - {
  "usuario": "Admin@gmail.com",
  "senha": "123456"
  }
  

# Endpoints que Admin e Usuario comum tem acesso após autenticarem-se

## Todos métodos GET
    - GET http://localhost:8080/turma - Retorna as turmas cadastradas.
    - GET http://localhost:8080/turma/{id} - Retorna uma turma através do Id.
    
    - GET http://localhost:8080/estagiario - Retorna os estagiários cadastrados.
    - GET http://localhost:8080/estagiario/{id} - Retorna uma turma através do Id.
    
    - Está implementada paginação, entao pode-se ordenar também através de qualquer atributo, conforme abaixo.
    - GET http://localhost:8080/estagiario?sort=nome,asc    - Ordena estagiários pelo nome em ordem crescente
    - GET http://localhost:8080/turma?sort=tecnologia,desc  - Ordenada a turma pela tecnologia em ordem decrescente
    

# Endpoints que somente Admin tem acesso após se autenticar

## Turma
    - POST http://localhost:8080/turma - Cadastrar uma turma.
    - PUT http://localhost:8080/turma/{id} - Atualiza uma turma através do Id.
    - DELETE http://localhost:8080/turma/{id} - Deleta uma turma através do Id (Não deixa deletar se houverem estagiarios atrelados a ela).

   -Pode utilizar o JSON para POST ou PUT
  - {
  "nome": "Turma 1 - 2022",
  "tecnologia": "Java"
}

## Estagiario
    - POST http://localhost:8080/estagiario - Cadastrar um estagiario.
    - PUT http://localhost:8080/estagiario/{id} - Atualiza um estagiario através do Id.
    - DELETE http://localhost:8080/estagiario/{id} - Deleta um estagiario através do Id.
    - JSON para GET e PUT de estagiario 
  - { 
"turmaId": 1,
"cpf": "04901213067",
"email": "thanos@g.com",
"nome": "Thanos"
}

#  Validações

- Nenhum campo pode ser nulo ou vazio.
- Os CPFS informados devem ser válidos. (Exemplos de CPFs aceitos: 988.602.170-59 ou 04901213067)
- Os e-mails informados devem ter formato de email. Exemplo: rony@gmail.com
- O Token JWT tem duração de 1 dia.
