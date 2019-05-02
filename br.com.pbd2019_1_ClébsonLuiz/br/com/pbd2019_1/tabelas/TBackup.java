package br.com.pbd2019_1.tabelas;

import java.text.SimpleDateFormat;

import br.com.pbd2019_1.entidade.Backup;

public class TBackup extends TGenerica<Backup>{
	private static final long serialVersionUID = 1L;

	public TBackup() {
		super(new String[] {"Data","Status","Autor","Localização"});
	}

	@Override
	public Object getValueAt(int linha, int coluna) {
		switch (coluna) {
		case 0:
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(getList().get(linha).getData_backup());
		case 1:
			return getList().get(linha).getStatus_backup();
		case 2:
			return getList().get(linha).getAutor_backup();
		case 3:
			return getList().get(linha).getLocal_backup();
		}
		return null;
	}

}
