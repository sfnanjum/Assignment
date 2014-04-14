Assignment
==========
IndexerServlet.java
---------------------

This servlet indexes all the files present in WebContent/WEB-INF/classes/resources.corpus and stores all the indices in WebContent/WEB-INF/classes/resources.indices


SearchServlet.java
--------------------

This servlet searches for the query string value in the indexes and returns the count.

Indexer.java
-------------------

This class basically calls the native lucene methods to create indices on the files present in resources.corpus.


SimpleSearcher.java
-------------------

This class basically calls the native lucene maethods to search the given word in indices created.



How to deploy - 

Download and install Tomcat 4.0. and deploy Dynamic Web project present in the https://github.com/sfnanjum/Assignment. 

1. Run the indexer first, to index all the files present in the corpus.
2. Run the seracher with appropriate query string to search a specific key word


About lucene-  

Apache LuceneTM is a high-performance, full-featured text search engine library written entirely in Java. It is a technology suitable for nearly any application that requires full-text search, especially cross-platform.

http://lucene.apache.org/core/



