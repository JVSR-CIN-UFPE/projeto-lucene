package gui;

import java.util.Scanner;

import javax.swing.JOptionPane;

import negocio.Fachada;

public class TelaBusca {
	
	private Fachada fachada;
	
	public TelaBusca() {
		this.fachada = Fachada.getInstance();
		
		// Utilizar telas!
		String parametro = JOptionPane.showInputDialog("Fa�a sua consulta!!!");
		
		fachada.buscar(parametro);
		
	}
	
	public static void console() {
		Fachada fachada = Fachada.getInstance();
		Scanner console = new Scanner(System.in);
		
		System.out.println("Faca sua consulta:");
		String parametro = console.nextLine();
		System.out.println();
		
		fachada.buscar(parametro);
	}
	
}
