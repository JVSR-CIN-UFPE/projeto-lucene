package negocio;

import org.apache.lucene.analysis.Analyzer;

import buscadores.Buscador;
import indexadores.Indexador;

public class Fachada {
	
	private static Fachada singleton;
	
	private Configurador configurador;
	private Indexador indexador;
	private Buscador buscador;
	private Analyzer analyzer;
	
	private Fachada() {
		this.configurador = Configurador.getInstance();
		this.analyzer = AnalyzerFactory.getAnalyzer(Configurador.STOPWORDS, Configurador.STEMMING);
		this.indexador = new Indexador(analyzer);
		this.buscador = new Buscador(analyzer);
	}
	
	public static Fachada getInstance() {
		if(singleton == null)
			singleton = new Fachada();
		return singleton;
	}
	
	public void configureAnalyzer(boolean stopwords, boolean stemming) {
		Configurador.STOPWORDS = stopwords;
		Configurador.STEMMING  = stemming;
		Analyzer analyzer = AnalyzerFactory.getAnalyzer(stopwords, stemming);
		this.indexador.setAnalyzer(analyzer);
		this.buscador.setAnalyzer(analyzer);
	}

	public void indexarDocumentos(String diretorio) {
		indexador.indexaArquivosDoDiretorio(diretorio);
	}

	public void buscar(String parametro) {
		this.buscador.buscaComParser(parametro);
	}

	public void finalizar() {
		configurador.finalizar();
	}
}
