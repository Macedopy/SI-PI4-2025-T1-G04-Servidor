# Clarifica Obra Servidor
Projeto designado a disponibilizar um Servidor robusto para receber dados vindo da API ou de qualquer outra requisição e criar ações para os dados recebidos, voltando eles corretamente. Esse servidor tem a intenção de criar um autenticador para o Id de usuário, aumentando a segurança.
Este projeto é focado para auxiliar usuários finais a terem o acesso real ao progresso e o gerenciamento de suas construções e projetos, com ele, pode-se ter acesso a toda construção que está sendo gerenciada pela construtora, facilitando o dia a dia do cliente economizando seu tempo para evitar visitas desnecessárias na construção.

## Scripts para início
./mvnw start

Faça a requisição para a API para chegar no servidor.
Exemplo de requisição: curl --request POST
--url http://localhost:8080/users/customer
--header 'Content-Type: application/json'
--header 'User-Agent: insomnia/12.1.0'
--data '{ "id": "12345", "personalInformation": { "firstName": "Joao", "lastName": "Silva", "email": "ricardo.silva@example.com", "phone": "55-11-98765-4321", "cpf": "123.456.789-20" }, "role": "CLIENT" }'

Técnico
O projeto está separado em um projeto TCP/IP multithread, cliente/servidor para receber requisições feita por outros componentes e desenvolver esses dados recebidos em dados válidos, ele gera um ID válido que é validado pela API após receber a formatação correta, isso desenvolve uma segurança na aplicação por se tratar de um ID único e exclusivo de cada cliente, evitando quaisquer acessos restritos.

Contribuição
É obrigatório a criação de uma branch local em sua máquina alternativa da main, após as alterações, será feito uma reunião para confirmar as mudanças e realizar testes com os integrantes, após isso está liberado o commit na main.
