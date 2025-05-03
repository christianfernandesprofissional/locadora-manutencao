# Como contribuir

Faça um fork no repositório, crie uma branch com o nome da feature ou fix que voce vai trabalhar e use essa branch. 

Use os seguintes padrões de commit para cada tipo antes da mensagem:

***

**feat** - Commits do tipo feat indicam que seu trecho de código está incluindo um novo recurso (se relaciona com o MINOR do versionamento semântico).

**fix** - Commits do tipo fix indicam que seu trecho de código commitado está solucionando um problema (bug fix), (se relaciona com o PATCH do versionamento semântico).

**docs** - Commits do tipo docs indicam que houveram mudanças na documentação, como por exemplo no Readme do seu repositório. (Não inclui alterações em código).

**test** - Commits do tipo test são utilizados quando são realizadas alterações em testes, seja criando, alterando ou excluindo testes unitários. (Não inclui alterações em código)

**build** - Commits do tipo build são utilizados quando são realizadas modificações em arquivos de build e dependências.

**perf** - Commits do tipo perf servem para identificar quaisquer alterações de código que estejam relacionadas a performance.

**style** - Commits do tipo style indicam que houveram alterações referentes a formatações de código, semicolons, trailing spaces, lint... (Não inclui alterações em código).

**refactor** - Commits do tipo refactor referem-se a mudanças devido a refatorações que não alterem sua funcionalidade, como por exemplo, uma alteração no formato como é processada determinada parte da tela, mas que manteve a mesma funcionalidade, ou melhorias de performance devido a um code review.

**chore** - Commits do tipo chore indicam atualizações de tarefas de build, configurações de administrador, pacotes... como por exemplo adicionar um pacote no gitignore. (Não inclui alterações em código)

**ci** - Commits do tipo ci indicam mudanças relacionadas a integração contínua (continuous integration).

**raw** - Commits do tipo raw indicam mudanças relacionadas a arquivos de configurações, dados, features, parâmetros.

**cleanup** - Commits do tipo cleanup são utilizados para remover código comentado, trechos desnecessários ou qualquer outra forma de limpeza do código-fonte, visando aprimorar sua legibilidade e manutenibilidade.

**remove** - Commits do tipo remove indicam a exclusão de arquivos, diretórios ou funcionalidades obsoletas ou não utilizadas, reduzindo o tamanho e a complexidade do projeto e mantendo-o mais organizado.

***

Além disso, caso seu commit referencie uma issue utilize # + número da issue na sua mensagem de commit.

**Exemplos** -> “feat: nova funcionalidade #23”, “fix: refatoração de um método #77”.

Após esse processo, faça um Pull Request com a branch nova criada, assim que sua PR for aprovada, o merge na branch main será feito. Após o merge, você pode apagar a sua branch criada, atualizar a branch main, e criar uma nova branch, reiniciando o processo para uma nova tarefa.

