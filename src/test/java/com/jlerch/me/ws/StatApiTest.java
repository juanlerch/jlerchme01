package com.jlerch.me.ws;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;
import com.jlerch.me.MockHttpServletResponse;
import com.jlerch.me.backend.DB;
import com.jlerch.me.backend.MockDatastoreService;
import com.jlerch.me.model.DNA;
import com.jlerch.me.model.Stat;

public class StatApiTest {

	 
	 @Test
	  public void testStat() throws IOException {
		 
		 StatApi api = new StatApi();
		 
		 
		 MockHttpServletResponse response = new MockHttpServletResponse();
		
		 DB db = new DB(new MockDatastoreService());
		 
		 String[] dnaa = {"ATGCGA","CAGTGC","TTATTT","AGACGG","GCGTCA","TCACTG"};
		 
		 DNA dna  = new DNA();
		  
		 dna.dna=dnaa;
		    
		 db.save(dna, true);
		 
		 
		 api.setDb(db);
		 
		 api.doGet(null, response); 
		 
		 Gson gson = new Gson();
		 Stat stat = gson.fromJson(response.getWriterContent().toString(), Stat.class );
		 
		 Assert.assertEquals("application/json", response.getContentType());
		 
		 
		 Assert.assertEquals(1,stat.count_human_dna);
		 Assert.assertEquals(1,stat.count_mutant_dna);
		 Assert.assertEquals(1.0,stat.ratio,0.01);
		 
	 }
	
	
}
