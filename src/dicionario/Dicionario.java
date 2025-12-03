package dicionario;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Dicionario {

	public String idiomaCorrente;
	public String diretorioDados = "src/dados";
	public String caminhoArquivo;

	public Dicionario(String idiomaCorrente) throws Exception {
		setIdioma(idiomaCorrente.replaceAll("\\s+", "").toLowerCase());
	}

	/*
	 * Define o parâmetro "idiomaCorrente" como sendo o idioma corrente e lança uma
	 * exceção caso o idioma que o usuario selecionar não exista
	 */
	public void setIdioma(String idiomaCorrente) throws Exception {
		if (!idiomaExiste(idiomaCorrente)) {
			throw new Exception("O idioma '" + idiomaCorrente + "' não está disponivel.");
		}
		this.idiomaCorrente = idiomaCorrente;
	}

	// Verifica se o idioma escolhido existe na base de dados
	public boolean idiomaExiste(String idiomaDigitado) {
		ArrayList<String> idiomas = getIdiomas();
		for (String idioma : idiomas) {
			if (idioma.equals(idiomaDigitado)) {
				return true;
			}
		}
		return false;
	}

	/*
	 * Retorna uma ArrayList dos idiomas permitidos no sistema. Verifica o nome dos
	 * arquivos .csv dentro do package "dados" e os adiciona na ArrayList idiomas
	 */
	public ArrayList<String> getIdiomas() {
		ArrayList<String> idiomas = new ArrayList<>();
		File pastaDados = new File(diretorioDados);

		if (pastaDados.exists() && pastaDados.isDirectory()) {
			File[] arquivos = pastaDados.listFiles((dir, nome) -> nome.endsWith(".csv"));

			if (arquivos != null) {
				for (File arquivo : arquivos) {
					String nomeArquivo = arquivo.getName();
					String nomeSemExtensao = nomeArquivo.substring(0, nomeArquivo.lastIndexOf("."));
					idiomas.add(nomeSemExtensao);
				}
			}
		}

		return idiomas;
	}

	// Transfere as linhas do arquivo csv correspondente ao idioma corrente para uma
	// ArrayList
	public ArrayList<String> converterCsv() throws Exception {
		ArrayList<String> linhasArquivoCsv = new ArrayList<>();
		String caminhoArquivo = diretorioDados + "/" + idiomaCorrente + ".csv";

		// Carrega todas as linhas do arquivo em um ArrayList
		try (Scanner scanner = new Scanner(new File(caminhoArquivo))) {
			while (scanner.hasNextLine()) {
				linhasArquivoCsv.add(scanner.nextLine());
			}
		} catch (Exception e) {
			throw new Exception("Erro ao ler o arquivo: " + e.getMessage());
		}
		return linhasArquivoCsv;
	}

	// Verifica se a palavra buscada é válida (sem acentos, cedilha ou símbolos
	// especiais)
	private void validarPalavra(String palavra) throws Exception {
		if (!palavra.matches("^[a-zA-Z0-9 ]*$")) {
			throw new Exception("A palavra buscada não deve conter acentos, cedilha ou símbolos especiais: " + palavra);
		}
	}

	// Busca a palavra digitada no arquivo CSV e retorna a versao dela no idioma
	// corrente
	public ArrayList<String> traduzirParaIdioma(String palavraBuscada) throws Exception {
		validarPalavra(palavraBuscada);
		ArrayList<String> linhasArquivo = converterCsv();
		ArrayList<String> traducoes = new ArrayList<>();

		for (String linha : linhasArquivo) {
			ArrayList<String> partes = new ArrayList<>(Arrays.asList(linha.split(";")));
			String palavraEmPortugues = partes.get(1);

			if (partes.size() > 1 && palavraEmPortugues.trim().equalsIgnoreCase(palavraBuscada)) {
				traducoes.add(partes.get(0).trim());
			}
		}
		return traducoes;
	}

	// Busca a palavra digitada no arquivo CSV e retorna a versao dela em portugues
	public ArrayList<String> traduzirParaPortugues(String palavraBuscada) throws Exception {
		validarPalavra(palavraBuscada);
		ArrayList<String> linhasArquivo = converterCsv();
		ArrayList<String> traducoes = new ArrayList<>();

		for (String linha : linhasArquivo) {
			ArrayList<String> partes = new ArrayList<>(Arrays.asList(linha.split(";")));
			String palavraNoIdiomaCorrente = partes.get(0);

			if (partes.size() > 1 && palavraNoIdiomaCorrente.trim().equalsIgnoreCase(palavraBuscada)) {
				traducoes.add(partes.get(1).trim());
			}
		}
		return traducoes;
	}

	/*
	 * Busca parcial do termo nas palavras em português e retorna lista das palavras
	 * encontradas (ou lista vazia)
	 */
	public ArrayList<String> localizarPalavraPortugues(String termoBuscado) throws Exception {
		validarPalavra(termoBuscado);
		ArrayList<String> linhasArquivo = converterCsv();
		ArrayList<String> palavrasEncontradas = new ArrayList<>();

		termoBuscado = termoBuscado.toLowerCase().trim();

		for (String linha : linhasArquivo) {
			ArrayList<String> partes = new ArrayList<>(Arrays.asList(linha.split(";")));
			String palavraEmPortugues = partes.get(1).trim().toLowerCase();

			if (palavraEmPortugues.startsWith(termoBuscado) && !palavrasEncontradas.contains(palavraEmPortugues)) {
				palavrasEncontradas.add(palavraEmPortugues);
			}
		}

		return palavrasEncontradas;
	}

	/*
	 * Busca parcial do termo nas palavras do idioma corrente e retorna lista das
	 * palavras encontradas (ou lista vazia)
	 */
	public ArrayList<String> localizarPalavraIdioma(String termoBuscado) throws Exception {
		validarPalavra(termoBuscado);
		ArrayList<String> linhasArquivo = converterCsv();
		ArrayList<String> palavrasEncontradas = new ArrayList<>();

		termoBuscado = termoBuscado.toLowerCase().trim();

		for (String linha : linhasArquivo) {
			ArrayList<String> partes = new ArrayList<>(Arrays.asList(linha.split(";")));
			String palavraEmIdiomaCorrente = partes.get(0).trim().toLowerCase();

			if (palavraEmIdiomaCorrente.startsWith(termoBuscado) && !palavrasEncontradas.contains(palavraEmIdiomaCorrente)) {
				palavrasEncontradas.add(palavraEmIdiomaCorrente);
			}
		}

		return palavrasEncontradas;
	}

}
