package br.com.pbd2019_1.utils;
public class DocumentoUtil {

	public static boolean isCPF(String CPF) {
		
		CPF = removerCaracteresEspeciais(CPF);
		
		for(int i = 0; i < 10; i++) {
			String cpf = ""+i+""+i+""+i+""+i+""+i+""+i+""+i+""+i+""+i+""+i+""+i;
			if(CPF.equals(cpf) || CPF.length() != 11) return false;
		}
		
		char dig10, dig11;
		int soma, resto, numero, peso;
		
		//Calcula o 1º Digito verificador
		
		soma = 0;
		peso = 10;
		
		for (int i = 0; i < 9; i++) {
			numero = (int) (CPF.charAt(i) - 48);
			soma = soma + (numero * peso);
			peso --;
		}
		
		resto = 11 - (soma % 11);
		
		if(resto > 9)
			dig10 = '0';
		else
			dig10 = (char) (resto + 48);
		
		//Calcula o 2º Digito verificador
		soma = 0;
		peso = 11;
		
		for(int i = 0; i < 10; i++) {
			numero = (int) (CPF.charAt(i) - 48);
			soma = soma + (numero * peso);
			peso --;
		}
		
		
		resto = 11 - (soma % 11);
		
		if(resto > 9)
			dig11 = '0';
		else
			dig11 = (char) (resto + 48);
		
		return ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10)));
	}

	private static String removerCaracteresEspeciais(String cpf) {
		if (cpf.contains(".")) 
			cpf = cpf.replace(".", "");
		if (cpf.contains("-")) 
			cpf = cpf.replace("-", "");
		return cpf;
	}
	
	public static String retornarCaracteresEspeciais(String cpf) {
		cpf = removerCaracteresEspeciais(cpf);
		return (cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "." + cpf.substring(6, 9) + "-" + cpf.substring(9, 11));
	}
	
}
