# AJuDE: AquiJUntosDoandoEsperança
#### Universidade Federal de Campina Grande

![AjudeProject](https://i.imgur.com/TR683cb.png)

### Projeto de Software 2019.2

#### API REST
* **Campanha API**
  * **POST /api/v1/campanha** - Deve cadastrar uma Campanha. A campanha deve ser passada na requisição HTTP por um JSON no corpo da requisição. O servidor registra os dados da Campanha e de quem à criou, e retorna um JSON contendo esses dados, porém com informações resumidas do Usuário.
  * **GET /campanha/{urlCampanha}** - Deve retornar um json com a informações resumidas do Dono, além da Camapanha, e o código 200.
  * **PUT campanha/{urlCampanha}/status** - Deve setar Status para Encerrado, sem precisar de arquivo JSON na requisição. Retorna um JSON da Campanha.
  * **POST campanha/{urlCampanha}/comentario** - Deve cadastrar um novo Comentário em uma Campanha específica e retorna um arquivo JSON de Comentário.
  * **DELETE campanha/{urlCampanha}/apagarComentario/{idComentario}** - Deve alterar o boolean 'apagado' para true em Comentario, fazendo com que o retorno do 'textoComentario' retorne vazio, a requisição retorna um JSON do Comentário.
  * **POST campanha/{urlCampanha}/comentario/{idComentario}** - Deve cadastrar uma nova Resposta em um Comentário específico de uma Campanha, também, específica. Retornando um arquivo JSON de Comentário.
  * **DELETE campanha/{urlCampanha}/comentario/{idComentario}/apagarResposta/{idResposta}** - Deve alterar o boolean 'apagada' para true em Resposta (presente em Comentário), fazendo com que o retorno do 'textoResposta' retorne vazio, a requisição retorna um JSON do Comentário.
  * **POST campanha/{urlCampanha}/like** - A primeira requisição registra um objeto Like (Contendo o Usuário) em Campanha, setando o 'contLikes' para +1. A partir da segunda requisição em diante, o servidor identifica o Usuário e alterna o 'contLikes' para +1 e -1.
  
* **Usuário API**
  * **Item 2a** -
  * **Item 2b** -







* [Backend](https://github.com/ManoMax/backend_AJuDE).
* [Frontend](https://github.com/EuclidesRamos/frontend_AJuDE).
* Orientação por: [@daltonserey](https://github.com/daltonserey), [@raquelvl](https://github.com/raquelvl) e [@juliafealves](https://github.com/juliafealves).
* [Especificações](https://docs.google.com/document/d/1h5WhnOhvyRmIbj_obhWK5XmoJgK35lVWPM2UwwMOT_Y/preview#heading=h.hfzc6dzi4lux) .

<p><i>
  
Author: [@ManoMax](https://github.com/ManoMax)
<br>Co-author: [@EuclidesRamos](https://github.com/EuclidesRamos)

</i></p>
