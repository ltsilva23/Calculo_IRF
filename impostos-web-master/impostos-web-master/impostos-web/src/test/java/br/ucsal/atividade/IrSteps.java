package br.ucsal.atividade;

	import org.jbehave.core.annotations.Given;
	import org.jbehave.core.annotations.Then;
	import org.jbehave.core.annotations.When;
	import org.jbehave.core.steps.Steps;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

	public class IrSteps extends ImpostoSelenium {
		
	    @Given("estou na funcionalidade de cálculo de imposto de renda")
	    public void instanceImpostoRenda() {
	    	openBrowser();
	    	
	    }

	    @When("informo como rendimentos tributáveis $valorRendimento")
	    public void informarRendimento(Double valorRendimento) {
	    	rendimento(valorRendimento);

	    }

	    @When("habilito a opção de informar deduções")
	    public void habilitarDeducoes() {
	    	habilitar();
	    }

	    @When("informo como previdência oficial $valorPrevidencia")
	    public void informaPrevidencia(Double valorPrevidencia){
	    	previdencia(valorPrevidencia);
	    }

	    @When("informo  $quantDependents dependents")
	    public void informarDependentes(int quantDependents) {
	    	dependentes(quantDependents);
	    }

	    @When("solicito que o cálculo seja realizado")
	    public void solicitarCalculo() {
	    	calcular();
	    	
	    }

	    @Then("o valor do imposto a pagar é de $valorImposto")
	    public void impostoEsperado(Double valorImposto) {
	    	resultadoImposto(valorImposto);
	    }

	    @Then("a alíquota efetiva é de $aliquotaEsperada%")
	    public void aliquotaEsperada(Double aliquotaEsperada) {
	    	resultadoAliquota(aliquotaEsperada);
	    	closeBrowser();
	    }

	}