package br.com.pbd2019_1.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import br.com.pbd2019_1.exception.DAOException;
import br.com.pbd2019_1.utils.DateUtil;

public class DAOResBackup {

	private List<String> getCommandsBackup(String local_PgDump, String ip, String userName, File backupFile, String nomeFile){
		
		List<String> commands = new ArrayList<>();
		commands.add(local_PgDump);
        commands.add("-h"); //database server host
        commands.add(ip);
        commands.add("-p"); //database server port number
        commands.add("5432");
        commands.add("-U"); //connect as specified database user
        commands.add(userName);
        commands.add("-F"); //output file format (custom, directory, tar, plain text (default))
        commands.add("c");
        commands.add("-b"); //include large objects in dump
        commands.add("-v"); //verbose mode
        commands.add("-f"); //output file or directory name
        commands.add(backupFile + File.separator + nomeFile);
        commands.add("-d"); //database name
        commands.add("PBD_GESTAO");
        
        System.out.println(commands);
		
        return commands;
	}
	
	
	private List<String> getCommandsRestore(String local_PgRestore, String ip, String userName, File backupFile){
		
		List<String> commands = new ArrayList<>();
		commands.add(local_PgRestore);
        commands.add("-h");
        commands.add(ip);
        commands.add("-p");
        commands.add("5432");
        commands.add("-U");
        commands.add(userName);
        commands.add("-d");
        commands.add("PBD_GESTAO");
        commands.add("-v");
        commands.add(backupFile.toString());
        
        System.out.println(commands);
		
        return commands;
	}
	
	public void executarBackup(String path, String nomeBackup, String userName, String password, String type) throws DAOException{

		File backupFile = new File(path);
		System.out.println(backupFile.toString());

		if (!backupFile.exists()) 
		{
			File dir = backupFile;
			dir.mkdirs();
		}

		String dataAtual = DateUtil.getDateString("yyyy-MM-dd", DateUtil.getDataAtual());

		String nomeFile = "backup_"+nomeBackup+"_" + dataAtual + ".backup";

		List<String> commands = null;

		final String local_PgDump = "C:\\Program Files\\PostgreSQL\\9.5\\bin\\pg_dump.exe";
		final String local_PgRestore = "C:\\Program Files\\PostgreSQL\\9.5\\bin\\pg_restore.exe";

		switch (type) 
		{
		case "BACKUP":
			commands = getCommandsBackup(local_PgDump, "localhost", userName, backupFile, nomeFile);
			break;
		case "RESTORE":
			commands = getCommandsRestore(local_PgRestore, "localhost", userName, backupFile);
			break;
		}

		if ((commands != null) && (!commands.isEmpty())) 
		{
			try 
			{
				ProcessBuilder pb = new ProcessBuilder(commands);
				pb.environment().put("PGPASSWORD", password);

				Process process = pb.start();

				try (BufferedReader buf = new BufferedReader(
						new InputStreamReader(process.getErrorStream()))) {
					String line = buf.readLine();
					while (line != null) {
						line = buf.readLine();
					}
				}

				process.waitFor();
				process.destroy();

			} 
			catch (IOException | InterruptedException ex) 
			{
				ex.printStackTrace();
				throw new DAOException("ERRO AO REALIZAR OPERAÇÃO NO BANCO DE DADOS : TIPO "+type);
			}
		} 
		else 
			throw new DAOException("ERRO AO REALIZAR BACKUP : PARAMETROS VAZIOS OU SINTAXE INVALIDA");
	}
	
}
