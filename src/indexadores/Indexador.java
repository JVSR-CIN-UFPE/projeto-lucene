package indexadores;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.text.SimpleDateFormat;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
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

	private String diretorioDosIndices = "indice-lucene";
	//    private String diretorioDosIndices = System.getProperty("user.home") + "/indice-lucene";

	//    private String diretorioParaIndexar = "filesToIndex";
	private String diretorioParaIndexar = System.getProperty("user.home") + "/Dropbox/Public/Algebra/";
	//    private String diretorioParaIndexar = System.getProperty("user.home") + "/Downloads";

	// {3}
	private IndexWriter writer;

	// {4}
	private Tika tika;

	public static void main(String[] args) {
		Indexador indexador = new Indexador();
		indexador.indexaArquivosDoDiretorio();
	}

	public void indexaArquivosDoDiretorio() {
		try {
			File diretorio = new File(diretorioDosIndices);
			apagaIndices(diretorio);

			// {5}
			Directory d = new SimpleFSDirectory(diretorio);
			System.out.println("Diretorio do indice: " + diretorioDosIndices);

			// {6}
			Analyzer analyzer = new BrazilianAnalyzer(Version.LUCENE_48);

			// {7}
			IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_48, analyzer);

			// {8}
			writer = new IndexWriter(d, config);

			long inicio = System.currentTimeMillis();

			indexaArquivosDoDiretorio(new File(diretorioParaIndexar));

			// {12}
			writer.commit();
			writer.close();
			long fim = System.currentTimeMillis();
			System.out.println("Tempo para indexar: " + ((fim - inicio) / 1000) + "s");

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	private void apagaIndices(File diretorio) {
		if (diretorio.exists()) {
			File arquivos[] = diretorio.listFiles();
			for (File arquivo : arquivos) {
				arquivo.delete();
			}
		}
	}

	public void indexaArquivosDoDiretorio(File raiz) {

		FilenameFilter filtro = new FilenameFilter() {
			public boolean accept(File arquivo, String nome) {
				if (nome.toLowerCase().endsWith(".pdf")
						|| nome.toLowerCase().endsWith(".odt")
						|| nome.toLowerCase().endsWith(".doc")
						|| nome.toLowerCase().endsWith(".docx")
						|| nome.toLowerCase().endsWith(".ppt")
						|| nome.toLowerCase().endsWith(".pptx")
						|| nome.toLowerCase().endsWith(".xls")
						|| nome.toLowerCase().endsWith(".txt")
						|| nome.toLowerCase().endsWith(".rtf")
						|| arquivo.isDirectory()) {
					return true;
				}
				return false;
			}
		};

		for (File arquivo : raiz.listFiles(filtro)) {
			if (arquivo.isFile()) {
				StringBuffer msg = new StringBuffer();
				msg.append("Indexando o arquivo ");
				msg.append(arquivo.getAbsoluteFile());
				msg.append(", ");
				msg.append(arquivo.length() / 1000);
				msg.append("kb");
				System.out.println(msg);

				try {
					// Extrai o conteúdo do arquivo com o Tika
					String textoExtraido = getTika().parseToString(arquivo);
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
			Monta um Document para indexação
			Field.Store.YES: armazena uma cópia do texto no índice, aumentando muito o seu tamanho;
			Field.Index.ANALYZED: utilizado quando o campo é de texto livre;
			Field.Index.NOT_ANALYZED: utilizado quando o campo é um ID, data ou númerico.
		 */

		Document documento = new Document();
		documento.add(new TextField("UltimaModificacao", ultimaModificacao,
				Store.YES));
		documento.add(new TextField("Caminho", arquivo.getAbsolutePath(),
				Store.YES));
		documento.add(new TextField("Texto", textoExtraido, Store.YES));
		try {
			// Adiciona o Document no índice, mas este só estará disponível para consulta após o commit.
			getWriter().addDocument(documento);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public Tika getTika() {
		if (tika == null) {
			tika = new Tika();
		}
		return tika;
	}

	public IndexWriter getWriter() {
		return writer;
	}
}
