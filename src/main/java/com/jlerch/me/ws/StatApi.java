package com.jlerch.me.ws;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.jlerch.me.Mutante;
import com.jlerch.me.backend.DB;
import com.jlerch.me.model.DNA;
import com.jlerch.me.model.Stat;

@WebServlet(
    name = "StatApi",
    urlPatterns = {"/stat"}
)
public class StatApi extends HttpServlet {

	
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
  public void doGet(HttpServletRequest request, HttpServletResponse response) 
      throws IOException {

	  DB db = new DB();
	  Gson gson = new Gson();
	  
	  Stat stat = db.stat();
			  
	  String json = gson.toJson(stat); 		 
	  
	  PrintWriter out = response.getWriter();
	  response.setContentType("application/json");
      response.setCharacterEncoding("UTF-8");
      out.print(json);
      out.flush();   
      
  }
}