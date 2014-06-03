package gui;

import javax.swing.JOptionPane;

import buscadores.Buscador;

public class TelaBusca {
	
	public static void main(String[] args) {
		Buscador b = new Buscador();
		String parametro = JOptionPane.showInputDialog("Consulta");
		b.buscaComParser(parametro);
	}
	
}
