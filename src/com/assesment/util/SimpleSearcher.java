package com.assesment.util;

import java.io.File;

import org.apache.lucene.analysis.SimpleAnalyzer;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

public class SimpleSearcher {
	
	public int searchIndex(File indexDir, String queryStr, int maxHits) throws Exception {
		
		Directory directory = FSDirectory.open(indexDir);

		if(directory ==  null) {
			return -2;
		}
		IndexSearcher searcher = new IndexSearcher(directory);
		QueryParser parser = new QueryParser(Version.LUCENE_30, "contents", new SimpleAnalyzer());
		Query query = parser.parse(queryStr);
		
		TopDocs topDocs = searcher.search(query, maxHits);
		searcher.close();
		
		return topDocs.totalHits;
	}

}
