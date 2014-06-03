package gui;

import java.util.Scanner;

import negocio.Fachada;

public class TelaBusca {
	
	private Fachada fachada;
	private Scanner console;
	
	public TelaBusca() {
		this.fachada = Fachada.getInstance();
		this.console = new Scanner(System.in);
		
		System.out.println("Faca sua consulta:");
		String parametro = this.console.nextLine();
		
		// Utilizar telas!
//		parametro = JOptionPane.showInputDialog("Faça sua consulta!!!");
		
		System.out.println();
		
		fachada.buscar(parametro);
		
	}
	
}
