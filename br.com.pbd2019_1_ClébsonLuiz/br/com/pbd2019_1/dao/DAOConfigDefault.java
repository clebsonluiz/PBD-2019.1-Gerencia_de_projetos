package br.com.pbd2019_1.dao;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;

import br.com.pbd2019_1.entidade.ConfigDefault;
import br.com.pbd2019_1.utils.DateUtil;

public class DAOConfigDefault {
	
	public static void setConfigPadrao() {
		ConfigDefault config = null;
		try 
		{
			config = loadConfig();
		} 
		catch (IOException e)
		{
			try 
			{
				config = new ConfigDefault();
				config.setHora_bakup(null);
				config.setImagemFundoDefault("1");
				saveConfig(config);
			} 
			catch (IOException e1) 
			{
				e1.printStackTrace();
			}
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
		
		
	}

	public static void saveConfig(ConfigDefault config) throws IOException {
		FileOutputStream fos = new FileOutputStream("config.txt");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(config);
		oos.close();
	}
	
	public static ConfigDefault loadConfig() throws IOException, ClassNotFoundException {
		FileInputStream fis = new FileInputStream("config.txt");
		ObjectInputStream ois = new ObjectInputStream(fis);
		ConfigDefault config = (ConfigDefault) ois.readObject();
		ois.close();
		return config;
	}
	
	public static void setImagemPadrao(String imagem) 
	{
		try 
		{
			ConfigDefault config = loadConfig();
			config.setImagemFundoDefault(imagem);
			saveConfig(config);
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
	}
	
	public static void setDataAgenda(Date data_backup) 
	{
		try 
		{
			ConfigDefault config = loadConfig();
			config.setHora_bakup(
					DateUtil.getDateString(
							"yyyy-MM-dd HH:MM:SS",
							DateUtil.getDateSQL(data_backup)));
			saveConfig(config);
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
	}
	
	public static void setHorarioAgenda(String hora_backup) 
	{
		try 
		{
			ConfigDefault config = loadConfig();
			config.setHora_bakup(hora_backup);
			saveConfig(config);
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
	}
	
}
