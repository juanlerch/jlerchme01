package com.jlerch.me;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;
import com.jlerch.me.backend.MockDatastoreService;
import com.jlerch.me.model.DNA;
import com.jlerch.me.model.Stat;


 
public class MutanteTest {

  @Test
  public void testMutante() throws IOException {
    MockHttpServletResponse response = new MockHttpServletResponse();
    
    Mutante mutante = new Mutante();
	String[] dna =  {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
    boolean result = mutante.isMutant(dna) ;
    Assert.assertEquals(true, result);
  }
  
   
  @Test
  public void testHumano() throws IOException {
    MockHttpServletResponse response = new MockHttpServletResponse();
    
    Mutante mutante = new Mutante();
    String[] dna = {"ATGCGA","CAGTGC","TTATTT","AGACGG","GCGTCA","TCACTG"};
    boolean result = mutante.isMutant(dna) ;
    Assert.assertEquals(false, result);
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
