# AJuDE: AquiJUntosDoandoEsperança
#### Universidade Federal de Campina Grande

![AjudeProject](https://i.imgur.com/TR683cb.png)

### Projeto de Software 2019.2

#### API REST
* **Campanha API**
  * **POST /api/v1/campanha** - Passando a campanha na requisição HTTP por um JSON (contendo nomeCurto, descricao, deadLine e url como String e meta como inteiro) no corpo da requisição, além do idToken Login no header. O servidor registra os dados, do Usuário dono e, da Campanha. Retornando um JSON, que contem esses dados, porém com as informações do Usuário dono filtradas (sem a senha e número de Cartão).
  * **GET /api/v1//campanha/{urlCampanha}** - Contendo um idToken Login no header. Retorna um JSON com a informações resumidas do Dono, além da Campanha, e o código 200.
  * **PUT /api/v1/campanha/{urlCampanha}/status** - Seta os Status para Encerrado em Campanha, sem precisar de arquivo JSON na requisição, apenas pela identificação da urlCampanha. Retorna um JSON da mesma Campanha.
  * **POST /api/v1/campanha/{urlCampanha}/comentario** - Enviando um arquivo JSON com o 'textoComentario' tipo String, além do idToken Login no header. Cadastra um novo Comentário em uma Campanha específica, identificada pela urlCampanha, e retorna um arquivo JSON de Comentário.
  * **DELETE /api/v1/campanha/{urlCampanha}/apagarComentario/{idComentario}** - Identificado pela urlCampanha e idComentario, além do idToken Login no header. Deve alterar o boolean 'apagado' para true em Comentario, além de setas suas respectivas respostas também para 'apagada's. Fazendo com que o retorno do 'textoComentario' retorne vazio (caso requerido no front, retorno 'textoRespostas' também vem vazio). A requisição retorna um JSON do Comentário.
  * **POST /api/v1/campanha/{urlCampanha}/comentario/{idComentario}** - Identificado pela urlCampanha e idComentario, além do idToken Login no header. Enviando um JSON com 'textoResposta' tipo String. Cadastra uma nova Resposta, em um Comentário específico de uma Campanha, também, específica. Retornando um arquivo JSON desse Comentário e suas Respostas.
  * **DELETE /api/v1/campanha/{urlCampanha}/comentario/{idComentario}/apagarResposta/{idResposta}** - Identificado pela urlCampanha, idComentario e idResposta, além do idToken Login no header. A requisição altera o boolean 'apagada' para true em Resposta (presente em Comentário), fazendo com que o retorno do 'textoResposta' retorne vazio, a requisição retorna um JSON do Comentário.
  * **POST /api/v1/campanha/{urlCampanha}/like** - Enviando o idToken Login no header. A primeira requisição registra um objeto Like (Contendo o Usuário) em Campanha, setando o 'contLikes' para +1. A partir da segunda requisição em diante, o servidor identifica o Usuário e alterna o 'contLikes' para -1 e +1.
  * **GET /api/v1/campanha/busca/{substring}** - Faz uma verificação nas Campanhas cadastradas. Retornando uma lista de JSON Campanha, com todas que tiverem, em seu título, uma Sub-String igual a enviada na requisição.
  * **POST /api/v1/campanha/{urlCampanha}/doacao** - Além do idToken Login no header, enviando um arquivo JSON, contendo a data da requisição tipo String e a quantia tipo float. Indentificada pela urlCampanha, a requisição registra uma nova Doação em Campanha, atualizando o valor de doacoes, e retornando um JSON Campanha.
  * **GET /api/v1/campanha/campanhasByLike** - Retorna um arquivo JSON contendo uma Lista de Campanhas ordenadas pela quantidade de Likes.
  * **GET /api/v1/campanha/campanhasByData** - Retorna um arquivo JSON contendo uma Lista de Campanhas ordenadas pelo deadLine mais próximo.
  * **GET /api/v1/campanha/campanhasByQuantia** - Retorna um arquivo JSON contendo uma Lista de Campanhas ordenadas por quais estão mais próximas de serem alcançadas.
  
* **Usuário API**
  * **POST /api/v1/usuarios** - Enviando um arquivo JSON (contendo urlUser, primeiroNome, ultimoNome, email, numCartao e senha, todos tipo String), a requisição cadastra um novo Usuario no servidor e retorna um arquivo JSON com as informações do Usuário filtradas (sem a senha e número de Cartão). Além de enviar um email de Boas-vindas ao email cadastrado.
  * **GET /api/v1/usuarios/{urlUser}** - Identificado pela urlUser, a requisição retorna um arquivo JSON com as informações do Usuário filtradas (sem a senha e número de Cartão), além da lista de suas Campanhas criadas e lista de Campanhas em que fez Doações.
  * **GET /api/v1/usuarios/list** - A requisição retorna uma lista com as informações filtradas (sem a senha e número de Cartão) de todos os Usuários cadastrados no servidor.
  * **DELETE /api/v1/usuarios/delete** - A requisição, tendo autorização do Token Login, remove a conta do Usuário do servidor. Retornando um JSON da conta Usuário filtrada (sem a senha e o número do cartão).
  
* **Login API**
  * **POST /api/v1/login** - Enviando um JSON (contendo um email e senha) do Usuário, a requisição retorna um JSON (contendo "idToken": "token") do Login.



* [Backend](https://github.com/ManoMax/backend_AJuDE).
* [Frontend](https://github.com/EuclidesRamos/frontend_AJuDE).
* Orientação por: 
  * [@daltonserey](https://github.com/daltonserey);
  * [@raquelvl](https://github.com/raquelvl);
  * [@juliafealves](https://github.com/juliafealves).
* [Especificações](https://docs.google.com/document/d/1h5WhnOhvyRmIbj_obhWK5XmoJgK35lVWPM2UwwMOT_Y/preview#heading=h.hfzc6dzi4lux) .

<p><i>
  
Author: [@ManoMax](https://github.com/ManoMax)
<br>Co-author: [@EuclidesRamos](https://github.com/EuclidesRamos)

</i></p>
