package br.com.aplicando.archunit;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.springframework.stereotype.Controller;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(
        packages = "br.com.aplicando.archunit",
        importOptions = ImportOption.DoNotIncludeTests.class
)
public class ArchitectureTest {

    @ArchTest
    static ArchRule ClassesQuePossuemAnotacaoController_DeveraoFinalizarComNomeController =
            classes()
                    .that().resideInAPackage("..controller..")
                    .or().areAnnotatedWith(Controller.class)
                    .should().haveSimpleNameEndingWith("Controller");
}