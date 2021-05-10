package com.jlerch.me.backend;

import java.io.IOException;
import org.junit.Assert;
import org.junit.Test;

import com.jlerch.me.model.DNA;
import com.jlerch.me.model.Stat;

 
public class DBTest {


  @Test
  public void testDB() throws IOException {
	    MockDatastoreService datastore = new MockDatastoreService(); 
	   
	    DB db = new DB(datastore);
	    
	    String[] dnaa = {"ATGCGA","CAGTGC","TTATTT","AGACGG","GCGTCA","TCACTG"};
	    
	    
	    DNA dna  = new DNA();
	    dna.dna=dnaa;
	    
	    db.save(dna, true);
	    
        Stat stat =  db.stat();
	    
	    
	    Assert.assertEquals(stat.count_human_dna, 1);
	    Assert.assertEquals(stat.count_mutant_dna, 1);
	    
	  }

  
  /*
  @Test
  public void testStatApi() throws IOException {
    MockHttpServletResponse response = new MockHttpServletResponse();
    new HelloAppEngine().doGet(null, response);
    Assert.assertEquals("text/plain", response.getContentType());
    Assert.assertEquals("UTF-8", response.getCharacterEncoding());
    Assert.assertEquals("Hello App Engine!\r\n", response.getWriterContent().toString());
  }
  
  @Test
  public void testXmenApi() throws IOException {
    MockHttpServletResponse response = new MockHttpServletResponse();
    new HelloAppEngine().doGet(null, response);
    Assert.assertEquals("text/plain", response.getContentType());
    Assert.assertEquals("UTF-8", response.getCharacterEncoding());
    Assert.assertEquals("Hello App Engine!\r\n", response.getWriterContent().toString());
  }
  
  public void testDB() throws IOException {
	    MockHttpServletResponse response = new MockHttpServletResponse();
	    new HelloAppEngine().doGet(null, response);
	    Assert.assertEquals("text/plain", response.getContentType());
	    Assert.assertEquals("UTF-8", response.getCharacterEncoding());
	    Assert.assertEquals("Hello App Engine!\r\n", response.getWriterContent().toString());
	  }
 
  */
}
