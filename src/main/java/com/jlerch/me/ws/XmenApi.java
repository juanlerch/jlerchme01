package com.jlerch.me.ws;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.jlerch.me.Mutante;
import com.jlerch.me.backend.DB;
import com.jlerch.me.model.DNA;

@WebServlet(
    name = "XmenApi",
    urlPatterns = {"/mutant"}
)
public class XmenApi extends HttpServlet {

 DB db;
	
 private String readJson(HttpServletRequest request) {

	  StringBuffer jb = new StringBuffer();
	  String line = null;
	  try {
	    BufferedReader reader = request.getReader();
	    while ((line = reader.readLine()) != null)
	      jb.append(line);
	  } catch (Exception e) { /*report an error*/ }

	  return jb.toString();
	 
 }	
	
  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) 
      throws IOException {

	  String json  = readJson(request);		  
	  
      Gson gson = new Gson();
      DNA dna = gson.fromJson(json,DNA.class);
     
      Mutante mutante = new Mutante();
      boolean esMutante = mutante.isMutant(dna.dna);
	  
      if (db==null) db = new DB();
      db.save(dna,esMutante);
      
      if (esMutante) {
    	  response.setStatus(HttpServletResponse.SC_OK);
      }else {
    	  response.setStatus(HttpServletResponse.SC_FORBIDDEN);
      }

  }


	public void setDb(DB db) {
		this.db = db;
	}
  
  
}