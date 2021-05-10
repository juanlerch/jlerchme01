package com.jlerch.me.ws;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;
import com.jlerch.me.MockHttpServletRequest;
import com.jlerch.me.MockHttpServletResponse;
import com.jlerch.me.backend.DB;
import com.jlerch.me.backend.MockDatastoreService;
import com.jlerch.me.model.DNA;
import com.jlerch.me.model.Stat;

public class XMenApiTest {

	 
	 @Test
	  public void testHuman() throws IOException {
		 
		 XmenApi api = new XmenApi();
		 
		 
		 MockHttpServletResponse response = new MockHttpServletResponse();
		
		 DB db = new DB(new MockDatastoreService());
		 
		 String[] dnaHumano= {"ATGCGA","CAGTGC","TTATTT","AGACGG","GCGTCA","TCACTG"};
		 
		 
		 DNA dna  = new DNA();
		  
		 dna.dna=dnaHumano;
		    
		 db.save(dna, true);
		 
		 Gson gson = new Gson();
		 String json = gson.toJson(dna); 
				 
		 api.setDb(db);
		 
		 BufferedReader reader = new BufferedReader(new StringReader(json));
		 MockHttpServletRequest request = new MockHttpServletRequest(reader);
		 		 
		 
		 api.doPost(request, response); 
		 
		 
		 Stat stat = gson.fromJson(response.getWriterContent().toString(), Stat.class );
		 
		 Assert.assertEquals(403, response.getStatus());
		 
		 
	 }
	
	
	 
	 @Test
	  public void testMutant() throws IOException {
		 
		 XmenApi api = new XmenApi();
		 
		 
		 MockHttpServletResponse response = new MockHttpServletResponse();
		
		 DB db = new DB(new MockDatastoreService());
		 
		 String[] dnaMutante =  {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
		 
		 
		 DNA dna  = new DNA();
		  
		 dna.dna=dnaMutante;
		    
		 db.save(dna, true);
		 
		 Gson gson = new Gson();
		 String json = gson.toJson(dna); 
				 
		 api.setDb(db);
		 
		 BufferedReader reader = new BufferedReader(new StringReader(json));
		 MockHttpServletRequest request = new MockHttpServletRequest(reader);
		 		 
		 
		 api.doPost(request, response); 
		 
		 
		 Stat stat = gson.fromJson(response.getWriterContent().toString(), Stat.class );
		 
		 Assert.assertEquals(200, response.getStatus());
		 
		 
	 }
	 
}
