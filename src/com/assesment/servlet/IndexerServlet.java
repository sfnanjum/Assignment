/**
 * 
 */
package com.assesment.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.assesment.util.Indexer;

/**
 * This Servlet direct the indexing request from the browser
 * @author nanjum
 *
 */
public class IndexerServlet extends HttpServlet {

	/** serial Version Id */
	private static final long serialVersionUID = 1L;
	
	/** Location where indices are stored */
	private static final String INDEX_FOLDER = "/resources.indices";
	
	/** Location where utf8 files are present */
	private static final String FILE_LOCATION = "/resources.corpus";
	
	/** suffix to be indexed and searched */
	private static final String SUFFIX = "utf8";
	
	/**
	 * This is the main method of the servlet
	 * @param request httpServletRequest
	 * @param response httpServletResponse
	 * @throws IOException exception to be thrown
	 */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException{
		Indexer indexer = new Indexer();
		PrintWriter out = response.getWriter();
		URL indexLocation = IndexerServlet.class.getResource(INDEX_FOLDER);
		File indexDir = null;
		indexDir = new File(indexLocation.getFile());
		
		URL corpusLocation = Indexer.class.getResource(FILE_LOCATION);
		File dataDir = null;
		dataDir = new File(corpusLocation.getFile());
				
		
		try {
			int numIndex = indexer.index(indexDir, dataDir, SUFFIX);
			out.println("<html>");
			out.println("<body>");
			out.println("<h1> Total files indexed = "+numIndex+"</h1>");
			out.println("</body>");
			out.println("</html>");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
