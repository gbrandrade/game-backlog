# Game Backlog

Sistema de gerenciamento pessoal de backlog de jogos (estilo Letterboxd, mas para games), desenvolvido em **Java** com persistência em **PostgreSQL** via **JDBC**. Projeto pessoal em desenvolvimento, criado como forma de aplicar conceitos de POO e modelagem de banco de dados relacional em um domínio real, fora do escopo típico de exercício de curso.

## Sobre o projeto

O sistema permite que um usuário registre os jogos que possui, em quais plataformas, com qual status (quero jogar, jogando, zerado, platinado, dropado), além de nota, resenha e horas para zerar — atribuídos por ele mesmo, e não ao jogo em si.

## Modelagem

O ponto central do projeto é a entidade **RegistroJogo**, uma entidade associativa que representa a relação entre um Usuário, um Jogo e uma Plataforma — carregando atributos próprios dessa relação (status, nota, resenha, horas para zerar). Essa decisão de modelagem nasceu da constatação de que esses campos não pertencem ao Jogo (que é compartilhado entre usuários), mas sim à experiência individual de cada usuário com aquele jogo, naquela plataforma específica.

### Entidades

- **Usuario** — id, nome, email (único), senha
- **Empresa** — id, nome (desenvolvedora/publicadora, campo único simplificado)
- **Jogo** — id, nome, gênero (enum), ano de lançamento, empresa (referência)
- **Plataforma** — id, nome
- **RegistroJogo** — id, usuário, jogo, plataforma, status (enum), nota, resenha, horas para zerar

### Diagrama de entidade-relacionamento

```
USUARIO ||--o{ REGISTRO_JOGO : possui
JOGO ||--o{ REGISTRO_JOGO : referenciado_em
PLATAFORMA ||--o{ REGISTRO_JOGO : referenciado_em
EMPRESA ||--o{ JOGO : publica
```

## Arquitetura

O projeto segue uma separação em camadas:

```
model/       → entidades de domínio (classes "burras", sem lógica de negócio)
repository/  → acesso a dados via JDBC (SQL puro, sem framework ORM)
service/     → regras de negócio (validações, orquestração)
config/      → configuração de infraestrutura (conexão com banco)
```

Essa arquitetura foi construída manualmente, sem uso de frameworks como Spring ou Hibernate, como forma de entender profundamente o que essas ferramentas abstraem — desde a abertura/fechamento de conexões (`try-with-resources`), passando pela prevenção de SQL Injection via `PreparedStatement`, até o mapeamento manual entre linhas de banco relacional e objetos Java (incluindo `JOIN`s de múltiplas tabelas para reconstruir grafos de objetos relacionados).

## Tecnologias

- **Java 21** (LTS)
- **PostgreSQL**
- **JDBC** (driver `org.postgresql:postgresql`)
- **Maven** (gerenciamento de dependências e build)

## Estrutura do banco de dados

5 tabelas relacionais: `usuario`, `empresa`, `plataforma`, `jogo`, `registrojogo`. A tabela `registrojogo` referencia as outras três (`usuario`, `jogo`, `plataforma`) simultaneamente, e `jogo` referencia `empresa` — totalizando uma cadeia de até 4 tabelas unidas em uma única consulta.

## Status atual

- [x] Modelagem de domínio e banco de dados
- [x] Camada `model` completa (5 entidades + 2 enums)
- [x] Camada `repository` completa (CRUD via JDBC para as 5 entidades, incluindo `JOIN`s)
- [ ] Camada `service` (regras de negócio)
- [ ] Interface de interação (console)

## Roadmap futuro

Funcionalidades adiadas conscientemente para manter o escopo do MVP enxuto:

- Perfil de usuário expandido (foto, banner, bio, jogos favoritos)
- Customização visual de perfil
- Migração da camada de acesso a dados para um framework ORM
- Interface gráfica (atualmente o projeto roda via console/testes)
- API REST

## Decisões de design notáveis

- **Empresa como entidade própria** ao invés de enum ou texto livre, por ser um conjunto de valores grande e em constante crescimento — diferente de Gênero e Status, que são conjuntos pequenos e estáveis, modelados como enum.
- **Enums persistidos como texto** (`VARCHAR`) no banco, com conversão via `Enum.valueOf()` na leitura — opção mais simples que o tipo `ENUM` nativo do Postgres, com a validação de valores permitidos delegada à camada de aplicação.
- **Senha nunca exposta em `toString()`** — mesmo antes da introdução de hashing, por princípio de não expor dados sensíveis em logs.
- **Plataforma dentro do registro, não em tabela associativa própria** — um mesmo jogo zerado em duas plataformas diferentes gera dois registros distintos, cada um com sua própria nota/resenha/horas.
