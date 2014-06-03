package indexadores;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.text.SimpleDateFormat;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.SimpleFSDirectory;
import org.apache.lucene.util.Version;
import org.apache.tika.Tika;

public class Indexador {

	private final String diretorioDosIndices = "indice-lucene";

	private IndexWriter writer;

	private Analyzer myAnalyzer;
	
	public Indexador(Analyzer analyzer) {
		this.myAnalyzer = analyzer;
	}
	
	public void setAnalyzer(Analyzer new_analyzer) {
		this.myAnalyzer = new_analyzer;
	}
	
	private void apagaIndices(File diretorio) {
		if (diretorio.exists()) {
			File arquivos[] = diretorio.listFiles();
			for (File arquivo : arquivos) {
				arquivo.delete();
			}
		}
	}

	// Faz a indexacao de todos os arquivos e subdiretorios de um diretorio
	public void indexaArquivosDoDiretorio(String diretorio) {
		try {
			File diretorio_indices = new File(diretorioDosIndices);
			apagaIndices(diretorio_indices);

			Directory d = new SimpleFSDirectory(diretorio_indices);
			System.out.println("Diretorio do indice: " + diretorioDosIndices);

			IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_48, this.myAnalyzer);

			writer = new IndexWriter(d, config);

			long inicio = System.currentTimeMillis();

			indexaArquivosDoDiretorio(new File(diretorio));

			// Concluir a indexacao deixando os documentos e a base de indices prontos para consulta.
			writer.commit();
			writer.close();
			
			long fim = System.currentTimeMillis();
			System.out.println("Tempo para indexar: " + ((fim - inicio) / 1000) + "s");

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public void indexaArquivosDoDiretorio(File raiz) {

		/* Filtro para selscionar os arquivos que deve ser indexados */
		
		// Nao utilizado
		
//		FilenameFilter filtro = new FilenameFilter() {
//			public boolean accept(File arquivo, String nome) {
//				if (nome.toLowerCase().endsWith(".pdf")
//						|| nome.toLowerCase().endsWith(".odt")
//						|| nome.toLowerCase().endsWith(".doc")
//						|| nome.toLowerCase().endsWith(".docx")
//						|| nome.toLowerCase().endsWith(".ppt")
//						|| nome.toLowerCase().endsWith(".pptx")
//						|| nome.toLowerCase().endsWith(".xls")
//						|| nome.toLowerCase().endsWith(".txt")
//						|| nome.toLowerCase().endsWith(".rtf")
//						|| nome.toLowerCase().endsWith(".html")
//						|| arquivo.isDirectory()) {
//					return true;
//				}
//				return false;
//			}
//		};
		
		FilenameFilter filtro = null;

		for (File arquivo : raiz.listFiles(filtro)) {
			if (arquivo.isFile()) {
				
				String msg = "Indexando o arquivo " + arquivo.getAbsoluteFile() + ", " + (arquivo.length() / 1000) + "kb";
				System.out.println(msg);

				try {
					// Extrai o conteudo do arquivo com o Tika
					Tika tika = new Tika();
					String textoExtraido = tika.parseToString(arquivo);
					indexaArquivo(arquivo, textoExtraido);

				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			} else {
				indexaArquivosDoDiretorio(arquivo);
			}
		}
	}

	private void indexaArquivo(File arquivo, String textoExtraido) {

		SimpleDateFormat formatador = new SimpleDateFormat("yyyyMMdd");
		String ultimaModificacao = formatador.format(arquivo.lastModified());

		/*	
			Monta um Document para indexacao
			Field.Store.YES: armazena uma copia do texto no indice, aumentando muito o seu tamanho;
			Field.Index.ANALYZED: utilizado quando o campo eh de texto livre;
			Field.Index.NOT_ANALYZED: utilizado quando o campo eh um ID, data ou numerico.
		 */

		Document documento = new Document();
		documento.add(new TextField("UltimaModificacao", ultimaModificacao,	Store.YES));
		documento.add(new TextField("Caminho", arquivo.getAbsolutePath(), Store.YES));
		documento.add(new TextField("Texto", textoExtraido, Store.YES));
		
		try {
			// Adiciona o Document no indice
			this.writer.addDocument(documento);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

}
