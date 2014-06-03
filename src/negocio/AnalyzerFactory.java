package negocio;

import java.util.Vector;

import org.apache.lucene.analysis.br.*;
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
				return new BrazilianAnalyzer(Version.LUCENE_48, null);
			}
		}
		else {
			CharArraySet set = new CharArraySet(Version.LUCENE_48, new Vector<>(), true);
			
			if(stopwords) {
				return new BrazilianAnalyzer(Version.LUCENE_48, BrazilianAnalyzer.getDefaultStopSet(), set);
			}
			else {
				return new BrazilianAnalyzer(Version.LUCENE_48, null, set);
			}
		}
	}
}
