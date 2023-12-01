# API de Lista de Tarefas com Spring Boot 📝

Bem-vindo à API de Lista de Tarefas desenvolvida em Java com o framework Spring Boot. Esta API fornece operações CRUD completas para gerenciar suas tarefas, comentários e categorias de maneira eficiente.

## Recursos Principais

### Tarefas (Tasks)

- **Listar Tarefas:** Obtenha uma lista de todas as tarefas registradas.
- **Detalhes da Tarefa:** Consulte informações detalhadas sobre uma tarefa específica.
- **Adicionar Tarefa:** Registre uma nova tarefa com sua descrição, data de criação e categoria associada.
- **Atualizar Tarefa:** Modifique os detalhes de uma tarefa existente, incluindo sua descrição e categoria.
- **Excluir Tarefa:** Remova uma tarefa do sistema.

### Comentários (Comments)

- **Listar Comentários:** Visualize todos os comentários relacionados às tarefas.
- **Detalhes do Comentário:** Obtenha informações específicas sobre um comentário em particular.
- **Adicionar Comentário:** Adicione um novo comentário a uma tarefa existente.
- **Atualizar Comentário:** Faça alterações em um comentário já existente.
- **Excluir Comentário:** Remova um comentário associado a uma tarefa.

### Categorias

- **Listar Categorias:** Veja todas as categorias disponíveis.
- **Detalhes da Categoria:** Consulte informações específicas sobre uma categoria.
- **Adicionar Categoria:** Registre uma nova categoria que pode ser associada a várias tarefas.
- **Atualizar Categoria:** Modifique o nome ou outros detalhes de uma categoria existente.
- **Excluir Categoria:** Remova uma categoria do sistema.

## Relacionamentos

- **Tarefas e Categorias:** Uma tarefa pode ser associada a uma categoria, permitindo uma organização eficiente.
- **Tarefas e Comentários:** Cada tarefa pode conter vários comentários, proporcionando uma discussão detalhada sobre as atividades.

## Como Usar

1. **Clonar o Repositório:**
   ```bash
   git clone https://github.com/ibaG20/To-Do-List.git
   
2. **Configuração do Banco de Dados::**
   ```bash
   Configure suas credenciais de banco de dados no arquivo application.properties.

3. **Endpoints Disponíveis:**
   ```bash
   A documentação do Swagger está em desenvolvimento, mas todos os edpoints dos 3 CRUDs estão disponíveis

