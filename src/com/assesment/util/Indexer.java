/**
 * 
 */
package com.assesment.util;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.apache.lucene.analysis.SimpleAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.store.FSDirectory;

/**
 * This class calls the lucene utility method to index the files
 * @author nanjum
 *
 */
public class Indexer {
		
	
	/**
	 * This Method calls the native lucene methods to create indices on the file located.
	 * @param indexDir directory where indices needs to be stored
	 * @param dataDir directory where UTF8 files are present
	 * @param suffix File types to be searched, here it is UTF8.
	 * @return Total files indexed
	 * @throws Exception exception
	 */
	public int index(File indexDir, File dataDir, String suffix) throws Exception {
		
		IndexWriter indexWriter = new IndexWriter(
				FSDirectory.open(indexDir), 
				new SimpleAnalyzer(),
				true,
				IndexWriter.MaxFieldLength.LIMITED);
		indexWriter.setUseCompoundFile(false);
		
		indexDirectory(indexWriter, dataDir, suffix);
		
		int numIndexed = indexWriter.maxDoc();
		indexWriter.optimize();
		indexWriter.close();
		
		return numIndexed;
		
	}
	
	/**
	 * This methods recursively digs into the directory, if any. file location specified. 
	 * @param indexDir directory where indices needs to be stored
	 * @param dataDir directory where UTF8 files are present
	 * @param suffix File types to be searched, here it is UTF8.
	 * @throws IOException exception to be thrown
	 */
	private void indexDirectory(IndexWriter indexWriter, File dataDir, String suffix) throws IOException {
		File[] files = dataDir.listFiles();
		for (int i = 0; i < files.length; i++) {
			File f = files[i];
			if (f.isDirectory()) {
				indexDirectory(indexWriter, f, suffix);
			}
			else {
				indexFileWithIndexWriter(indexWriter, f, suffix);
			}
		}
	}
	
	/**
	 * This method operates a file with specific extension
	 * @param indexWriter indexWritter
	 * @param f file to be indexed
	 * @param suffix file extension
	 * @throws IOException exception to be thrown
	 */
	private void indexFileWithIndexWriter(IndexWriter indexWriter, File f, String suffix) throws IOException {
		if (f.isHidden() || f.isDirectory() || !f.canRead() || !f.exists()) {
			return;
		}
		if (suffix!=null && !f.getName().endsWith(suffix)) {
			return;
		}
		Document doc = new Document();
		doc.add(new Field("contents", new FileReader(f)));		
		doc.add(new Field("filename", f.getCanonicalPath(), Field.Store.YES, Field.Index.ANALYZED));
		
		indexWriter.addDocument(doc);
	}

}

