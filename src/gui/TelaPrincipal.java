package gui;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import negocio.Fachada;

public class TelaPrincipal extends JFrame {
	
	private Fachada fachada;
	private Scanner console;
	
//	private JLabel label1, label2, label3, label4 = new JLabel("Rótulo 4", JLabel.CENTER);
	
	public TelaPrincipal() {
		
		/*--------------------------------------------------------------------------------
		
		Container pane = this.getContentPane();
		pane.setLayout(new GridLayout(4, 1));
		
		JLabel label1 = new JLabel("Testando rótulos com JAVA");
		JLabel label2 = new JLabel("Texto Centralizado", JLabel.CENTER);
		ImageIcon img = new ImageIcon("resources/Crystal_java.png");
		JLabel label3 = new JLabel("JAVA - Interface Gráfica", img, JLabel.RIGHT);
		
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
		
		/*--------------------------------------------------------------------------------*/
		
		Container pane = this.getContentPane();
		pane.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		JTextArea area1 = new JTextArea();
		JTextArea area2 = new JTextArea();
		
		/*--------------------------------------------------------------------------------*/
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(360,150);
		this.setVisible(true);
		
//		this.fachada = Fachada.getInstance();
//		console = new Scanner(System.in);
//		
//		System.out.println("Indexar?");
//		
//		String go = console.next();
//		
//		if(go != null)
//		{
//			fachada.indexarDocumentos();
//		}
		
	}
	
}
