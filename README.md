# Vote API

## Proposta
No cooperativismo, cada associado possui um voto e as decisões são tomadas em assembleias, por votação. 
Esta API Backend foi criada para gerenciar essas sessões de votação.

## Funcionalidades 
* Cadastrar e Listar pautas;
* Abrir uma sessão de votação em uma pauta (a sessão de votação deve ficar aberta por um
tempo determinado na chamada de abertura ou 1 minuto por default)
* Listar Sessões de voto   
* Receber votos dos associados em pautas (os votos são apenas 'SIM'/'NAO'. Cada associado
é identificado por um id único e pode votar apenas uma vez por pauta)
* Contabilizar os votos e dar o resultado da sessão de votação na pauta

## Pré-requisitos
* Java (openjdk 15)
* Intellij Community / Eclipse
* SpringBoot
* Maven
* Docker

## API REST APP ENGINE GOOGLE CLOUD
Para ver a documentação e usar a api, acesse [POLLVOTE-API](https://session-vote.rj.r.appspot.com/swagger-ui.html).

## Mensageria
Por falta de tempo não consegui implementar a parte da mmensageria, onde eu usaria o kafka com a fila na hora em que se encerra uma sessão
