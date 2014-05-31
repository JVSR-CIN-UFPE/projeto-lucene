package basico;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

public class LuceneTeste {
	
	String pathLucene = "/tmp/lucene-index";
	
	public List<Noticia> gerarNoticiasExemplo() {
		
		List<Noticia> lista = new Vector<Noticia>();
		
		return lista;
	}
	
	public void indexarDocumento(Noticia noticia, IndexWriter indexWriter){
		
	}
	
	public void iniciarLucene() {
		
		try {
			File indexPath = new File(pathLucene);

			// Cria o diretorio, caso inexistente, para o index do Lucene
			if(!indexPath.exists())
				indexPath.mkdir();
			
			if(indexPath.exists()) {
				Directory directoryIndex = FSDirectory.open(indexPath);
				
				// Criacao inicial do index
				IndexWriter indexWriter = new IndexWriter(directoryIndex, 
						new IndexWriterConfig(Version.LUCENE_48, new StandardAnalyzer(Version.LUCENE_48)));
				
				indexWriter.close();
			}
			
		} catch(Throwable t) {
			String m = "Erro ao iniciar Lucene";
			System.out.println(m);
			t.printStackTrace();
		}
	}
	
	public void indexarNoticiasAoLucene() {
		System.out.println("Indexando noticias ao lucene...");
		
		IndexWriter indexWriter = null;
		
		try {
			File pathIndexFile = new File(pathLucene);
			
			if(!pathIndexFile.exists())
				throw new RuntimeException("Lucene não foi iniciado corretamente.");
			
			// Abre o index do Lucene
			Directory directoryIndex = FSDirectory.open(pathIndexFile);
			
			// Cria um IndexWriter para indexar o conteudo
			indexWriter = new IndexWriter(directoryIndex, 
					new IndexWriterConfig(Version.LUCENE_48, new StandardAnalyzer(Version.LUCENE_48)));
			
			// Algumas noticias de exemplo para indexar
			List<Noticia> noticias = gerarNoticiasExemplo();
			
			for(Noticia noticia : noticias)
				indexarDocumento(noticia, indexWriter);
			
//			indexWriter.optimize();
			
		} catch (Throwable t) {
			String m = "Erro ao indexar conteudo ao Lucene";
			System.out.println(m);
			t.printStackTrace();
		} finally {
			if(indexWriter != null) {
				try {
					//necessario fechar o indexWriter apos termino
					indexWriter.close();
				} catch (Throwable t) {
					t.printStackTrace();
				}
			}
		}
		
		System.out.println("Noticias indexado ao lucene...");

	}

	public void buscarNoticiasLucene() {
		
		IndexSearcher searcher = null;
		
		try {
			File pathIndexFile = new File(pathLucene);
			
			// Abre o index do Lucene
			Directory directoryIndex = FSDirectory.open(pathIndexFile);
			
			// cria o indexSearch para efetuar a busca
//			searcher = new IndexSearcher();
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}








