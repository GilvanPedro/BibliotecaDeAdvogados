# Biblioteca de Advocacia

## Descrição

Este projeto é um sistema de gerenciamento de biblioteca desenvolvido especificamente para escritórios de advocacia. Permite o controle de empréstimos, devoluções, histórico de leitura e gerenciamento de usuários e livros, facilitando a organização e o acesso aos recursos bibliográficos.

## Funcionalidades

- **Gerenciamento de Livros**: Cadastro, atualização e consulta de livros com categorias específicas.
- **Gerenciamento de Usuários**: Controle de usuários com diferentes funções (advogados, estagiários, etc.).
- **Empréstimos e Devoluções**: Sistema completo para empréstimo e devolução de livros, com validações automáticas.
- **Histórico de Leitura**: Rastreamento do histórico de leituras dos usuários.
- **Validações**: Verificações de e-mail, CPF e outras regras de negócio.
- **Relatórios e Utilitários**: Cálculo de multas, validações de quantidade de livros emprestados, etc.

## Tecnologias Utilizadas

- **Linguagem**: Java 17
- **Build Tool**: Maven
- **Banco de Dados**: MySQL
- **Conectividade**: JDBC (MySQL Connector/J)
- **Arquitetura**: MVC (Model-View-Controller) com camadas de Service, Repository e Controller

## Esquema do Banco de Dados

O projeto utiliza um banco de dados MySQL com as seguintes tabelas:

### Script de Criação

```sql
CREATE DATABASE bibliotecaadvocacia;

USE bibliotecaadvocacia;

CREATE TABLE usuarios(
	id INT PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(100) NOT NULL,
	email VARCHAR(100) NOT NULL,
	cpf VARCHAR(20) NOT NULL,
	funcao VARCHAR(50) NOT NULL,
	total_livros_alugados INT DEFAULT 0,
	multa_pendente BOOL DEFAULT FALSE,
	possui_livro BOOL DEFAULT FALSE,
	pontos INT DEFAULT 0
);

ALTER TABLE usuarios ADD valor_ulta INT DEFAULT 0;
ALTER TABLE usuarios ADD ativo BOOLEAN DEFAULT TRUE;

CREATE TABLE livros(
	id INT PRIMARY KEY AUTO_INCREMENT,
	titulo VARCHAR(200) NOT NULL,
	autor VARCHAR(100) NOT NULL,
	editora VARCHAR(100) NOT NULL,
	categoria VARCHAR(50) NOT NULL,
	quantidade_livros INT DEFAULT 1,
	quantidade_emprestimo INT DEFAULT 0
);

ALTER TABLE livros ADD ativo BOOLEAN DEFAULT TRUE;

CREATE TABLE emprestimo (
    id INT PRIMARY KEY AUTO_INCREMENT,
    usuario_id INT NOT NULL,
    livro_id INT NOT NULL,
    emprestimo_data DATE NOT NULL,
    devolucao_data DATE NOT NULL,
    livro_devolvido BOOLEAN DEFAULT FALSE,
    
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id),
    FOREIGN KEY (livro_id) REFERENCES livros(id)
);

CREATE TABLE historico_leitura(
	id INT PRIMARY KEY AUTO_INCREMENT,
	usuario_id INT NOT NULL,
	livro_id INT NOT NULL,
	emprestimo_id INT NOT NULL,
	tipo_leitura VARCHAR(50) NOT NULL,
	status_leitura VARCHAR(40) NOT NULL,
	
	FOREIGN KEY (usuario_id) REFERENCES usuarios(id),
	FOREIGN KEY (livro_id) REFERENCES livros(id),
	FOREIGN KEY (emprestimo_id) REFERENCES emprestimo(id)
);
```

### Descrição das Tabelas

- **usuarios**: Armazena informações dos usuários (advogados, estagiários, etc.), incluindo status de multas e livros emprestados.
- **livros**: Contém dados dos livros disponíveis, com controle de quantidade e empréstimos.
- **emprestimo**: Registra os empréstimos realizados, com datas de empréstimo e devolução.
- **historico_leitura**: Mantém o histórico de leituras dos usuários, vinculado aos empréstimos.

## Pré-requisitos

- Java 17 ou superior instalado
- Maven 3.6+ instalado
- MySQL Server instalado e configurado
- Uma base de dados MySQL criada para o projeto

## Instalação

1. Clone o repositório:
   ```bash
   git clone <url-do-repositorio>
   cd BibliotecaAdvocacia
   ```

2. Configure o banco de dados:
    - Crie uma base de dados no MySQL.
    - Atualize as credenciais de conexão no arquivo `src/main/resources/application.properties` ou diretamente na classe `ConexaoBanco.java`.

3. Compile o projeto:
   ```bash
   mvn clean compile
   ```

4. Execute os testes (opcional):
   ```bash
   mvn test
   ```

## Uso
``Por enquanto a aplicação não está completa, ainda planejo ir fazendo um pequeno frontend com o javaswing, mas você pode criar uma classe main e criar uma interação com o terminal para ir testando o software.``

## Estrutura do Projeto

```
src/
├── main/
│   ├── java/
│   │   └── br/com/
│   │       ├── controller/     # Controllers REST
│   │       ├── database/       # Conexão com banco
│   │       ├── model/          # DTOs, Entities, Enums
│   │       ├── repository/     # Acesso a dados
│   │       ├── service/        # Lógica de negócio
│   │       └── util/           # Utilitários
│   └── resources/
└── test/
    └── java/
```

## Contribuição

1. Fork o projeto
2. Crie uma branch para sua feature (`git checkout -b feature/nova-feature`)
3. Commit suas mudanças (`git commit -am 'Adiciona nova feature'`)
4. Push para a branch (`git push origin feature/nova-feature`)
5. Abra um Pull Request

## Licença

Este projeto está licenciado sob a [MIT License](LICENSE).

## Contato

Para dúvidas ou sugestões, entre em contato com [gilvanpedro2006@gmail.com].
