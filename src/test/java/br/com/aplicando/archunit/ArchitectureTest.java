package br.com.aplicando.archunit;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(
        packages = "br.com.aplicando.archunit",
        importOptions = ImportOption.DoNotIncludeTests.class
)
public class ArchitectureTest {

    // Definindo Nomes das Classes

    @ArchTest
    static ArchRule ClassesQuePossuemAnotacaoController_DeveraoFinalizarComNomeController =
            classes()
                    .that().areAnnotatedWith(Controller.class)
                    .should().haveSimpleNameEndingWith("Controller")
            .as("Caro desenvolvedor, todas as nossas classes que estão anotadas como Controller, deverão ter o nome finalizado com Controller");

    @ArchTest
    static ArchRule ClassesQuePossuemAnotacaoRepository_DeveraoFinalizarComNomeController =
            classes()
                    .that().areAnnotatedWith(Repository.class)
                    .should().haveSimpleNameEndingWith("Repository")
                    .as("Caro desenvolvedor, todas as nossas classes que estão anotadas como Repository, deverão ter o nome finalizado com Repository");

    @ArchTest
    static ArchRule ClassesQuePossuemAnotacaoService_DeveraoFinalizarComNomeController =
            classes()
                    .that().areAnnotatedWith(Service.class)
                    .should().haveSimpleNameEndingWith("Service")
                    .as("Caro desenvolvedor, todas as nossas classes que estão anotadas como Service, deverão ter o nome finalizado com Service");

    // Definindo o nome dos pacotes
    @ArchTest
    static ArchRule ClassesQuePossuemAnotacaoController_DeveraoResidirNoPacoteController =
            classes()
                    .that().areAnnotatedWith(Controller.class)
                    .should().resideInAPackage("..controller..")
                    .as("Caro desenvolvedor, todas as nossas classes que estão anotadas como Controller, deverão residir no pacote *.controller");

    @ArchTest
    static ArchRule ClassesQuePossuemAnotacaoService_DeveraoResidirNoPacoteService =
            classes()
                    .that().areAnnotatedWith(Service.class)
                    .should().resideInAPackage("..service..")
                    .as("Caro desenvolvedor, todas as nossas classes que estão anotadas como Service, deverão residir no pacote *.service");

    @ArchTest
    static ArchRule ClassesQuePossuemAnotacaoRepository_DeveraoResidirNoPacoteRepository =
            classes()
                    .that().areAnnotatedWith(Repository.class)
                    .should().resideInAPackage("..repository..")
                    .as("Caro desenvolvedor, todas as nossas classes que estão anotadas como Repository, deverão residir no pacote *.repository");
}