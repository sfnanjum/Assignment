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

import com.assesment.util.SimpleSearcher;

/**
 * This servlet searches for the query string
 * @author nanjum
 *
 */
public class SearchServlet extends HttpServlet {

	/** default serial version UID */
	private static final long serialVersionUID = 1L;
	
	/** Location where indices are present */
	private static final String INDEX_FOLDER = "/resources.indices";
	
	/**
	 * This is the main method of the servlet
	 * @param request httpServletRequest
	 * @param response httpServletResponse
	 * @throws IOException exception to be thrown
	 */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException{
		PrintWriter out = response.getWriter();
		SimpleSearcher searcher = new SimpleSearcher();
		URL indexLocation = SearchServlet.class.getResource(INDEX_FOLDER);
		File indexDir = null;
		indexDir = new File(indexLocation.getFile());
		String query = request.getQueryString();
		//Null query validation 
		if(query!=null) {
			query = query.split("=")[1];
		}else{
			out.print("No Query string specified, please append the following -  ?query=<search string>");
			return;
		}
		
		int max_hits = 100;
		int hits = 0;
		
		try {
			hits = searcher.searchIndex(indexDir, query, max_hits);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        if(hits==-2) {
        	out.println("Please run indexer once");
        }else{	
        	out.println("{count:"+hits+"}");
        }
        out.close();
	}
		
}
