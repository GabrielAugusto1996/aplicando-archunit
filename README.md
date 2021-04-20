# Introdução

![image](https://dev-to-uploads.s3.amazonaws.com/uploads/articles/ch5qxnnb24ajcarwjxza.png)

Muita das vezes quando iniciamos um projeto, a maior dificuldade que possuímos é no momento de fazer a organização entre os nossos pacotes, classes e definições de qual classe poderá ter acesso a determinada outra classe, após derrotarmos essa dificuldade, o nosso próximo desafio é mantermos essa organização e documentarmos para os futuros desenvolvedores ou até mesmo nós, como definimos a arquitetura do nosso projeto, isso é o que você irá ver a seguir!

# Por quê Testes Arquiteturais?

![image](https://dev-to-uploads.s3.amazonaws.com/uploads/articles/9oa5262hws7pnosoo3do.png)

Como foi dito anteriormente, além de organizarmos mais o nosso projeto e definirmos uma convenção, conseguimos ter uma documentação de uma forma bem amigável,conforme exemplo visto abaixo, vimos uma regra informando onde as classes anotadas com **Entity** deverão residir:

```java
@ArchTest
static final ArchRule entities_must_reside_in_a_domain_package =
        classes().that().areAnnotatedWith(Entity.class).should().resideInAPackage("..domain..")
        .as("Entidades deverão residir no pacote de domínios");
```
Mas que tal agora colocarmos a mão na massa? :)

# Quais tecnologias iremos utilizar?

![image](https://dev-to-uploads.s3.amazonaws.com/uploads/articles/ngn93gk3iqvmt26uvys9.png)

Para esse artigo em questão, iremos precisar ter as seguintes tecnologias no nosso projeto:

1. [Java 8+](https://www.java.com/pt-BR/download/help/whatis_java.html): Linguagem de Programação Java na versão 8+
2. [Maven](https://maven.apache.org/): Ferramenta para o gerenciamento de dependências no Java.
3. [ArchUnit](https://www.archunit.org/): Ferramenta que será utilizada para o desenvolvimento dos nossos testes arquiteturais
4. [Spring](https://spring.io/): Framework de programação

Não irei entrar muito adentro de todas as tecnologias utilizadas pois não é o nosso objetivo desse artigo.

## Configurando o nosso projeto:

![image](https://dev-to-uploads.s3.amazonaws.com/uploads/articles/vc5dbouy55a2xq6iyn5z.png)

Para que possamos utilizar o **archunit** no nosso projeto, será necessário adicionarmos a seguinte dependência:

```xml
<dependency>
    <groupId>com.tngtech.archunit</groupId>
    <artifactId>archunit-junit5</artifactId>
    <version>0.18.0</version>
    <scope>test</scope>
</dependency>
```
Após isso, iremos fazer uma criação de uma única classe chamada **ArchitectureTest** no nosso pacote de testes, no qual irá ter a seguinte implementação nesse primeiro momento:

```java

@AnalyzeClasses(
        packages = "SeuPacote",
        importOptions = ImportOption.DoNotIncludeTests.class
)
public class ArchitectureTest {
}
```
**Observação:** No exemplo acima, foi inserido uma opção para que não incluísse os testes na nossa validação, mas caso você queira definir um padrão no nome dos seus testes também, pode ficar à vontade. :)

# Agora sim, vamos ao que nos interessa! =D

![image](https://dev-to-uploads.s3.amazonaws.com/uploads/articles/r5ijbphlme995jvlam0b.png)

Juntos, iremos definir uma arquitetura simples na qual iremos ter classes de **Controller** que irão poder chamar apenas as classes **Service** e que poderão chamar somente classes **Repository**, conforme visto no desenho abaixo:

![image](https://dev-to-uploads.s3.amazonaws.com/uploads/articles/3waa6n7a3gn6hnfsbpw6.png)

Após isso, iremos definir como irá ficar o nome de cada classe e por último, qual será o pacote que a mesma irá residir, dito isso, vamos criar a nossa arquitetura? :)

# Definindo o nome das nossas classes:

![image](https://dev-to-uploads.s3.amazonaws.com/uploads/articles/o28rw5zmucv52lfvx23z.png)

No 1º passo iremos definir como deverá ser o nome das nossas classes, já que somos pessoas extremamente criativas, vamos utilizar o nome de cada anotação no final da nossa classe, porquê será que ninguém nunca pensou nisso antes? :D

```java
    @ArchTest
    static ArchRule ClassesQuePossuemAnotacaoController_DeveraoFinalizarComNomeController =
            classes()
                    .that().areAnnotatedWith(Controller.class)
                    .should().haveSimpleNameEndingWith("Controller")
            .as("Caro desenvolvedor, todas as nossas classes que estão anotadas como Controller, deverão ter o nome finalizado com Controller");
```

Como visto acima, o uso dos testes do **ArchUnit** são bem simples e bastante intuitivos, dentro do nosso teste estamos informando que toda classe que está anotada como **Controller**, deverá ter o nome finalizado como **Controller** e além disso deixamos uma dica para o próximo desenvolvedor que não fizer da forma que nós planejamos, utilizando o ".as(....)", isso não é legal? :)

Forcei um erro, mudando o nome da nossa classe para **UsuarioJoao**, olha que legal o que aconteceu:

![image](https://dev-to-uploads.s3.amazonaws.com/uploads/articles/lwmqwciz20ztka9e63t6.png)

Após isso, basta criarmos as regras para as nossas outras classes, mas isso agora eu deixo para você tentar :)

Caso não consiga não se preocupe, basta abrir o [GitHub](https://github.com/GabrielAugusto1996/aplicando-archunit) do projeto :)

# Definindo a residência das nossas classes:

![image](https://dev-to-uploads.s3.amazonaws.com/uploads/articles/miwqb0ul4jxdmhqdusrt.png)

No 2º passo iremos definir onde cada classe irá residir, o teste irá ser bastante similar ao anterior, então vamos lá? :)

```java
@ArchTest
    static ArchRule ClassesQuePossuemAnotacaoController_DeveraoResidirNoPacoteController =
            classes()
                    .that().areAnnotatedWith(Controller.class)
                    .should().resideInAPackage("..controller..")
                    .as("Caro desenvolvedor, todas as nossas classes que estão anotadas como Controller, deverão residir no pacote *.controller");
```

Com o exemplo acima, informamos no ".resideInAPackage(...)", qual será o local que a nossa classe de fato irá residir.

# Definindo qual classe irá chamar qual classe:

![image](https://dev-to-uploads.s3.amazonaws.com/uploads/articles/t3rc7mpw5stpdqie13cv.png)

Por último, para concluirmos o nosso desafio, precisamos definir qual classe irá chamar a outra, para que dessa forma, tenhamos o controle de qual classe poderá conhecer a outra, para isso iremos seguir o exemplo abaixo:

```java

@ArchTest
static final ArchRule ClassesQueResidemNoPacoteControllerNaoPodemConhecerRepository =
        noClasses().that().resideInAPackage("..controller..")
        .should().dependOnClassesThat().resideInAPackage("..repository..")
        .as("As classes Repository não podem ficar juntas das classes Controller :(");

```
No exemplo acima, definimos que nenhuma classe que resida no pacote **Controller** poderá depender de nenhuma classe que resida no pacote **Repository**, que história triste de amor, não? :D

# Obrigado pessoal =D

![image](https://dev-to-uploads.s3.amazonaws.com/uploads/articles/n7hbpeodptbcdpqoxds0.png)

Agradeço a todos vocês que leram o meu artigo e que me acompanham na criação dos meus conteúdos :)

O [ArchUnit](https://www.archunit.org/getting-started), possui uma ótima documentação e me ajuda muito nos projetos que executo no meu dia à dia, o que você acha de apresentar isso nos seus projetos? :)

- Linkedin: https://www.linkedin.com/in/gabriel-augusto-1b4914145/
- GitHub: https://github.com/GabrielAugusto1996/aplicando-archunit 