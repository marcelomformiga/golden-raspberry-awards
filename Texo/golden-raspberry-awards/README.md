# Golden Raspberry Awards

## Descrição
Essa é uma API, desenvolvida como exercício proposto pela empresa TEXO, que lê um arquivo CSV ao inicializar a aplicação e popula uma base de dados em memória. Possui um endpoint que recupera uma lista de ganhadores por período.

## Tecnologias
- Java 17
- Maven 3.9.4
- Spring Boot 3.2.4
- Banco de Dados H2

### Detalhes Técnicos
Essa é uma API REST, criada com o auxílio do Maven para gerenciamento de suas dependências, sendo desenvolvida com Java 17 e Spring Boot 3.2.3.
Para leitura do arquivo CSV, fou utilizada a biblioteca "commons-csv" da Apache. O banco de dados H2 foi o escolhido para uso em memória ao inicializar a aplicação.
Foi utilizada a biblioteca auxiliar "ModelMapper" para realizar de forma mais amigável a conversão de objetos semelhantes.
Com o intuito de deixar o código mais limpo, optei pela utilização da biblioteca "Lombok" que já é muito utilizada no mercado. Essa biblioteca facilita a criação de objetos e de manipulação de entidades.
Os testes unitários foram realizados com o auxílio da biblioteca "Mockito" e os testes de integração para testar endpoint foram realizados com o auxílio do "MockMvc".
A biblioteca "Guava" do Google, foi utilizada para facilitar a manipulação de coleções do tipo "HashMap", já que ela possui maiores funcionalidade em relação a biblioteca padrão, como permitir o uso de chaves repetidas.

## Arquitetura
Foi desenvolvida uma arquitetura REST simples para o exercício proposto. O arquivo CSV "movies-list" encontra-se na raíz da pasta "resources" e as configurações de inicialização estão no arquivo de propriedades "application.properties" da mesma pasta.

### Características
A arquitetura é semelhante as já padrão de mercado para esse tipo de API.
Há a camada REST que possui as classes controladoras com os recursos de entrada e saída da aplicação. Há a camada de classes de Serviços que realizam a comunicação dos recursos com as demais camadas.
Na camada de domínio estão as entidades mapeadas do banco de dados e os repositórios de acesso a essa base de dados. Há uma camada também de classes de negócios e classes utilitárias.
Segue abaixo a estrutura de pacotes:

golden_raspberry_awards
1) rest
1.1) controller
1.2) dto
2) domain
2.1) model
2.2) repository
3) service
4) business
5) util

- REST: Possui os recursos necessários da API que são as portas de entrada e saída da mesma. Dividido em 2 outros pacotes.
- CONTROLLER: Recursos de classes controladoras onde estão os endpoints do sistema.
- DTO: Dentro estão os objetos de entrada e saída da aplicação.
- DOMAIN: Camada de domínio das classes de modelo mapeadas das entidades do Banco de Dados.
- REPOSITORY: Camada que acessa a base de dados.
- SERVICE: Camada de comunicação entre as classes controladoras e o repositório.
- BUSINESS: Essa camada foi construída para conter as regras de negócio e separá-las dos Serviços. Essa camada, em outras aplicações, também é conhecida como "UC" ou "Use Cases".
- UTIL: Pacote com as classes utilitárias comuns a todas as outras camadas.

O fluxo de uma requisição entre os pacotes foi pensado da seguinte forma:

CONTROLLER --> BUSINESS --> SERVICE --> REPOSITORY

As classes utilitárias são comuns a toda aplicação.

Dentro do pacote contendo as classes de testes, há um pacote chamado "mock" que contém classes úteis de instâncias de objetos utilizados na execução dos testes.
Dessa forma, essas instâncias podem ser utilizadas em qualquer teste que se faça necessário, ganhando assim em reaproveitamento e otimização.

## Repositório GITHUB

O link de acesso do repositório GITHUB para baixar o projeto é o seguinte:

https://github.com/marcelomformiga/golden-raspberry-awards

OBS.: Baixar a branch "master".

## Base de Dados
Para conferir a base de dados H2 (precisa executar a API antes), utilizar a seguinte configuração abaixo:

JDBC URL: jdbc:h2:mem:golden_raspberry_awards
USUÁRIO: sa
SENHA: sa

Executar o seguinte comando abaixo para conferir os registros salvos do arquivo:

SELECT * FROM FILM;

## Executar a API
Ao inicializar sem erros a API, executando a classe "GoldenRaspberryAwardsApplication" que está no pacote raiz, subirá uma base de dados H2 em memória (ver seção acima "Base de Dados") e o sistema já automaticamente executará o método "readFIle" (com a anotação "@PostConstruct") da classe "FileUtil", lendo assim o arquivo CSV para popular a base de dados referida.
A API foi configurada para rodar no endereço "localhost" na porta "8080".

### Endpoint para recuperar os vencedores do prêmio por período
Para executar, pode fazer uso de um programa como o "Postman" ou até mesmo digitar o endpoint diretamente em seu navegador.

http://localhost:8080/awards/winners-range

MÉTODO HTTP: GET

curl -X GET http://localhost:8080/filmes

## Pontos de melhoria para implementação futura
Alguns pontos de melhoria e sugestões para continuar o desenvolvimento da aplicação:

- Aprimorar e aplicar um melhor manipulamento para tratamento de exceções.
- Aplicar conceitos do SOLID na arquitetura, principalmente no pacote "BUSINESS" e classes utilitárias.
- Cobrir classes com mais testes unitários, principalmente explorando os fluxos de exceção.
- Aplicar logs para serem salvos em arquivos textos.
- Lógica do código que percorre os vencedores para verificar o intervalo de prêmios pode ser otimizado ainda. 






