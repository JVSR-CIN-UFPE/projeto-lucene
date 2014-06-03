package gui;

import java.util.Scanner;

import javax.swing.JFrame;

public class TelaPrincipal extends JFrame {
	
	private static final long serialVersionUID = 1838317687279883502L;
	
	private Scanner console;
	
	public TelaPrincipal() {
		
		this.console = new Scanner(System.in);
		
		/*--------------------------------------------------------------------------------
		
		Container pane = this.getContentPane();
		pane.setLayout(new GridLayout(4, 1));
		
		ImageIcon img = new ImageIcon("resources/Crystal_java.png");
		
		JLabel label1 = new JLabel("Testando rótulos com JAVA");
		JLabel label2 = new JLabel("Texto Centralizado", JLabel.CENTER);
		JLabel label3 = new JLabel("JAVA - Interface Gráfica", img, JLabel.RIGHT);
		JLabel label4 = new JLabel("Rótulo 4", JLabel.CENTER);
		
		JPanel painel = new JPanel(new GridLayout(4,1));
		
		painel.add(label1);
		painel.add(label2);
		painel.add(label3);
		
		pane.add(painel);

		/*--------------------------------------------------------------------------------
		
		Container pane = this.getContentPane();
		pane.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		JButton button1 = new JButton("Abrir");
		JButton button2 = new JButton("Novo");
		JButton button3 = new JButton("Fechar");
		
		pane.add(button1);
		pane.add(button2);
		pane.add(button3);
		
		/*--------------------------------------------------------------------------------
		Container pane = this.getContentPane();
		pane.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		JTextField texto1 = new JTextField(10);
		JTextField texto2 = new JTextField(25);
		JTextField texto3 = new JTextField("ESCREVA SEU TEXTO AQUI!!", 30);
		
		pane.add(texto1);
		pane.add(texto2);
		pane.add(texto3);
		
		/*--------------------------------------------------------------------------------
		
		Container pane = this.getContentPane();
		pane.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		JTextArea area1 = new JTextArea();
		JTextArea area2 = new JTextArea();
		
		/*--------------------------------------------------------------------------------
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(360,150);
		this.setVisible(true);
		
		/*--------------------------------------------------------------------------------*/
		
		while(true) {
			System.out.println("Escolha a opcao:\n" + "1. Indexar\n" + "2. Buscar");
			
			int opcao = console.nextInt();
			
			// ACTION 1 => INDEXER
			if(opcao == 1) {
				new TelaIndexar();
			}
			// ACTION 2 => SEARCHER
			else if(opcao == 2) {
				new TelaBusca();
			}
			
			System.out.println();
		}
		
	}
	
}
