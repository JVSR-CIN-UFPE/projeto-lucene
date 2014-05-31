package indexadores;

import java.io.Reader;

import org.apache.lucene.analysis.br.*;
import org.apache.lucene.util.Version;

public class MyAnalaiser {
	
	BrazilianAnalyzer bra;
	
	public MyAnalaiser() {
		// TODO Auto-generated constructor stub
		bra = new BrazilianAnalyzer(Version.LUCENE_48);
		
		System.out.println(bra.getDefaultStopSet());
		
	}
	
	
	public static void main(String[] args) {
		new MyAnalaiser();
	}
}
