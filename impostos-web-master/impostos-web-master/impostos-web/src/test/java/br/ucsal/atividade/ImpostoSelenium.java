package br.ucsal.atividade;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ImpostoSelenium {

	public WebDriver driver;
	
	public void pausar() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	@BeforeEach
	public void openBrowser() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		String endereco = String.valueOf(getClass().getResource("/webapp/imposto.html"));
		driver.get(endereco);
		
	}
	

	@AfterEach
	public void closeBrowser() {
		driver.quit();
	}

	@Test
	public void rendimento(Double valor) {
		WebElement digitar = driver.findElement(By.id("rendimentosTributaveis"));
		digitar.clear();
		digitar.sendKeys(valor.toString());
		 pausar();
	}

	@Test
	public void habilitar() {
		WebElement habilitar = driver.findElement(By.id("isInformarDeducoes"));
		habilitar.click();
		pausar();
	}

	@Test
	public void previdencia(Double valorPrev) {

		WebElement valorPrevidencia = driver.findElement(By.id("previdenciaOficial"));
		valorPrevidencia.clear();
		valorPrevidencia.sendKeys(valorPrev.toString());
		pausar();
	}

	@Test
	public void dependentes(int quantDep) {
		WebElement digitar = driver.findElement(By.id("quantidadeDependentes"));
		digitar.clear();
		digitar.sendKeys(String.valueOf(quantDep));
		pausar();
	}

	@Test
	public void calcular() {
		WebElement calcular = driver.findElement(By.id("calculoBtn"));
		calcular.click();
		pausar();
	}
	
	@Test
	public void resultadoImposto(Double PagarImpostoEsperado) {
		String impostoPagar = driver.findElement(By.id("impostoPagar")).getAttribute("value");
		Double imposto = Double.parseDouble(impostoPagar);
		Assertions.assertEquals(PagarImpostoEsperado, imposto);
		
	}
	@Test
	public void resultadoAliquota(Double AliquotaEsperado) {
		String AliquotaEfetiva = driver.findElement(By.id("aliquotaEfetiva")).getAttribute("value");
		Double aliquota = Double.parseDouble(AliquotaEfetiva.replace("%", ""));
		Assertions.assertEquals(AliquotaEsperado, aliquota);
	}
}
