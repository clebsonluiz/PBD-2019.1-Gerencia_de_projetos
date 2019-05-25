package br.com.pbd2019_1.dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import br.com.pbd2019_1.exception.DAOException;

public class DAOResSQL {

	public static String LerArquivo(String Caminho) throws DAOException { // Caminho do arquivo

		String conteudo = "";
		try 
		{
			FileReader arq = new FileReader(Caminho);
			BufferedReader lerArq = new BufferedReader(arq);
			String linha = "";
			linha = lerArq.readLine();
			while (linha != null) {
				conteudo += linha + "\r\n";
				linha = lerArq.readLine();
			}
			arq.close();

			return conteudo;
		}
		catch (FileNotFoundException ex)
		{
			throw new DAOException("Arquivo não encontrado");
		}
		catch (IOException ex)
		{
			throw new DAOException("Erro ao ler arquivo");
		}
	}

	public static boolean EscreverArquivo(String Caminho, String Texto) throws DAOException {

		try {
			if(!Caminho.contains(".sql") || !Caminho.contains(".SQL"))
				Caminho += ".sql";
			// FileWriter arq = new FileWriter(Caminho, true); // o parametro true significa
			// que eu não quero sobrescrever o arquivo
			FileWriter arq = new FileWriter(Caminho);
			PrintWriter gravarArq = new PrintWriter(arq);

			// BufferedWriter gravarArq = new BufferedWriter(arq);

			gravarArq.println(Texto); // tem a mesma função de um gravarArq.write(texto); + gravarArq.newLine();
										// juntos
			// gravarArq.newLine();
			// gravarArq.write(string, off, len); // caso em outros sistemas
			gravarArq.close();
			return true;
		} catch (IOException e) {
			throw new DAOException("Não foi possível salvar o arquivo");
		}

	}

}
