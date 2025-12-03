package dicionario;

import java.util.ArrayList;

public class Testes {
	public static void main(String[] args) throws Exception {
		Dicionario dicionario = new Dicionario("InGlEs");
		
		ArrayList<String> idiomas = dicionario.getIdiomas();
		
		for (String idioma : idiomas) {
			System.out.println("- " + idioma);
		}
		
		System.out.println(dicionario.traduzirParaPortugues("software"));
		System.out.println(dicionario.traduzirParaIdioma("programa"));
		System.out.println(dicionario.localizarPalavraPortugues("t"));
		System.out.println(dicionario.localizarPalavraIdioma("s"));


	}
}

