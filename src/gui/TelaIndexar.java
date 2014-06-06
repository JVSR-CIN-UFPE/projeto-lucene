package gui;

import java.util.Scanner;

import negocio.Fachada;

public class TelaIndexar {
	
	private Fachada fachada;
	private Scanner console;
	
	public TelaIndexar() {
		this.fachada = Fachada.getInstance();
		this.console = new Scanner(System.in);
		
		System.out.println("|-------------------- TELA DE INDEXACAO DE DOCUMENTOS --------------------|");
		
		/* SETAR STOPWORD */
		
		System.out.println("Indexar com stopwords? y/n");
		char choice_stopwprds = this.console.next().charAt(0);
		boolean stopwords = choice_stopwprds=='y';
		
		/* SETAR STEMMING */
		
		System.out.println("Indexar com stemming? y/n");
		char choice_stemming = this.console.next().charAt(0);
		boolean stemming = choice_stemming=='y';
		
		
		/* SETAR DIRETORIO */
		
		System.out.println("Indique o diretorio para indexacaoo:");
		String diretorio = this.console.next();
		
		/* Configurar as propriedades de stopwords e stemming 
		 * do Analyzer a ser utilizado (sempre sendo o BrazilianAnalyzer) */
		
		fachada.configureAnalyzer(stopwords,stemming);
		
		/* EFETURAR A INDEXACAO DO DIRETORIO */
		fachada.indexarDocumentos(diretorio);
		
	}
	
	public static void console() {
		Fachada fachada = Fachada.getInstance();
		Scanner console = new Scanner(System.in);
		
		System.out.println("|-------------------- TELA DE INDEXACAO DE DOCUMENTOS --------------------|");
		
		/* SETAR STOPWORD */
		
		System.out.println("Indexar com stopwords? y/n");
		char choice_stopwprds = console.next().charAt(0);
		boolean stopwords = choice_stopwprds=='y';
		
		/* SETAR STEMMING */
		
		System.out.println("Indexar com stemming? y/n");
		char choice_stemming = console.next().charAt(0);
		boolean stemming = choice_stemming=='y';
		
		
		/* SETAR DIRETORIO */
		
		System.out.println("Indique o diretorio para indexacao:");
		String diretorio = console.next();
		
		/* Configurar as propriedades de stopwords e stemming 
		 * do Analyzer a ser utilizado (sempre sendo o BrazilianAnalyzer) */
		
		fachada.configureAnalyzer(stopwords,stemming);
		
		/* EFETURAR A INDEXACAO DO DIRETORIO */
		fachada.indexarDocumentos(diretorio);
		
	}
	
}
