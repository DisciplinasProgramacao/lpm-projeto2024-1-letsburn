# Comentários - Revisão de 12/06 (fim da Sprint 3)

Todos os comentários referem-se ao código do ramo "master" ou "main". É obrigação do grupo manter o código principal neste ramo. Problemas relatados podem ser:

  - ❕❕  - observações e melhorias
  - ⚠️ - médios. Erros de lógica, regras mal implementadas... Descontos de até 1 ponto.
  - 🚨 - sérios. Regras faltando, problemas de modularidade... Descontos de até 3 pontos.
  - 💣 - graves. Falta de classes, requisitos ignorados ... Descontos de 5 ou mais pontos.

## Revisão

  - ❕❕ a documentação parece ter sumido toda no novo modelo de API .... -> swagger + collection postman com testes de integração
  - ❕❕ o diagrama também parece não estar muito aderente. -> atualizar diagrama
  - 🚨 eu não sei se estou gostando muito da organização da lógica. Por exemplo, várias operações de comanda/model estão sendo feitas em comanda/service. O model deveria ser mais genérico que o service, a meu ver. Esta decisão de abraçar a API sem nenhum pensamento crítico também está criando diversos métodos "perigosos" ou conceitualmente incorretos (por exemplo, a requisião ter um "setComanda" em lugar de um método que adicione um item a uma comanda). Caso a gente precisasse fazer uma aplicação que não estivesse no contexto web, como estas regras seriam reaproveitadas/utilizadas?
  - 🚨 não consegui perceber no ramo master nada do requisito de "menu fechado"
  - 🚨 se a opção (válida) do grupo é apresentar o trabalho como API, o restaurante controller está fazendo, na minha visão, pouca coisa. Fiquei sem entender direito algumas opçoes do service, que fica no limite de violar o encapsulamento ao coletar dados de DTO, requisição, comanda.... 
  - 🚨 precisamos ter um 'orquestrador' para a apresentação do trabalho. O App/bean que eu consegui ver ainda está lá na Sprint1.
