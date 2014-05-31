package gui;

import java.util.Scanner;

import negocio.Fachada;

public class TelaPrincipal {
	
	private Fachada fachada;
	private Scanner console;
	
	public TelaPrincipal() {
		this.fachada = Fachada.getInstance();
		console = new Scanner(System.in);
		
		System.out.println("Indexar?");
		
		String go = console.next();
		
		if(go != null)
		{
			fachada.indexarDocumentos();
		}
		
	}
	
}
