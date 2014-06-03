package buscadores;

import java.io.File;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.SimpleFSDirectory;
import org.apache.lucene.util.Version;

public class Buscador {
	
	private String diretorioDoIndice = "indice-lucene";
	private Analyzer myAnalyzer;

	public Buscador(Analyzer analyzer) {
		this.myAnalyzer = analyzer;
	}
	
	public void setAnalyzer(Analyzer new_analyzer) {
		this.myAnalyzer = new_analyzer;
	}
	
	public void buscaComParser(String consulta) {
		try {
			Directory diretorio = new SimpleFSDirectory(new File(diretorioDoIndice));
			
			IndexReader leitor = DirectoryReader.open(diretorio);
			
			IndexSearcher buscador = new IndexSearcher(leitor);
			
			QueryParser parser = new QueryParser(Version.LUCENE_48, "Texto", this.myAnalyzer);
			Query query = parser.parse(consulta);
			
			long inicio = System.currentTimeMillis();
			
			TopDocs resultado = buscador.search(query, 10);
			
			long fim = System.currentTimeMillis();
			System.out.println("Tempo total para busca: " + (fim - inicio) + "ms\n");
			
			int totalDeOcorrencias = resultado.totalHits;
			System.out.println("Total de documentos encontrados:" + totalDeOcorrencias+"\n");
			
			for (ScoreDoc sd : resultado.scoreDocs) {
				Document documento = buscador.doc(sd.doc);
				System.out.println("Caminho:" + documento.get("Caminho"));
				System.out.println("Ultima modificacao:"+ documento.get("UltimaModificacao"));
				System.out.println("Score:" + sd.score);
				System.out.println("--------");
			}
			
			leitor.close();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
