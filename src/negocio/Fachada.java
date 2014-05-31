package negocio;

import buscadores.Buscador;
import indexadores.Indexador;

// Controlar as telas?
public class Fachada {
	
	private static Fachada singleton;
	
	private Indexador indexador;
	private Buscador buscador;
	
	private Fachada() {}
	
	public static Fachada getInstance() {
		singleton = new Fachada();
		return singleton;
	}

	public void indexarDocumentos() {
		indexador.indexaArquivosDoDiretorio();
	}
	
	
}
