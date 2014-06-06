package negocio;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.br.*;
import org.apache.lucene.analysis.pt.PortugueseAnalyzer;
import org.apache.lucene.analysis.snowball.SnowballAnalyzer;
import org.apache.lucene.analysis.util.CharArraySet;
import org.apache.lucene.util.Version;

public class AnalyzerFactory {
	
	private AnalyzerFactory() {}
	
	public static BrazilianAnalyzer getAnalyzer(boolean stopwords, boolean stemming) {
		if(stemming) {
			if(stopwords) {
				return new BrazilianAnalyzer(Version.LUCENE_48);
			}
			else {
				Analyzer a = new SnowballAnalyzer(Version.LUCENE_48, "Portuguese");
				
				if(a instanceof BrazilianAnalyzer) {
					System.out.println("IsTrue");
				}
				else if(a instanceof PortugueseAnalyzer) {
					System.out.println("IsTrue2");
				}
				else {
					System.out.println("Fudeu");
				}
				
				return new BrazilianAnalyzer(Version.LUCENE_48, CharArraySet.EMPTY_SET);
			}
		}
		else {
			if(stopwords) {
				return new BrazilianAnalyzer(Version.LUCENE_48, BrazilianAnalyzer.getDefaultStopSet(), CharArraySet.EMPTY_SET);
			}
			else {
				return new BrazilianAnalyzer(Version.LUCENE_48, CharArraySet.EMPTY_SET, CharArraySet.EMPTY_SET);
			}
		}
	}
}
