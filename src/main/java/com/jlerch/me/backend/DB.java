package com.jlerch.me.backend;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.Key;
import com.google.cloud.datastore.Query;
import com.google.cloud.datastore.QueryResults;
import com.google.gson.Gson;
import com.jlerch.me.model.DNA;
import com.jlerch.me.model.Stat;

public class DB {
	
	public static  String HUMAN = "HUMAN";
	public static  String MUTANT= "MUTANT";

	Datastore datastore; 
	
	public DB() {
		this.datastore = DatastoreOptions.getDefaultInstance().getService();
	}
	
	public DB(Datastore datastore) {
		this.datastore = datastore;
	}
	
	private String dnaAsKey(String[] dna) {
  
		  StringBuffer sb = new StringBuffer();
	      for(int i = 0; i < dna.length; i++) {
	         sb.append(dna[i]);
	      }
	      
	      return sb.toString();
	      
	}
	
	public void save(DNA dna,boolean esMutante) {
		
		 
		Gson gson = new Gson();
		String json = gson.toJson(dna); 
				
		String kind = HUMAN;
		if(esMutante) {
			kind = MUTANT;
		}
		
		//KeyFactory keyFactory = datastore.newKeyFactory().setKind(kind);
	    //IncompleteKey key = keyFactory.setKind(kind).newKey(dnaAsKey(dna.dna));
	    
	    // Record a visit to the datastore, storing the IP and timestamp.
	    //FullEntity<IncompleteKey> entity =
	      //  FullEntity.newBuilder(key).set("dna", dna.dna.toString()).build();
	    
	    
	    Key key = datastore.newKeyFactory()
	    	    .setKind(kind)
	    	    .newKey(dnaAsKey(dna.dna));
	    
	    Entity find = datastore.get(key);
	    
	    if(find==null) {
		   	Entity entity = Entity.newBuilder(key)
		    	    .set("dna", json)
		    	    .build();
		    
		    datastore.add(entity);
	    }
	

	}

	private long  count(String king) {
		//cuando hay estadisticas de disponibles
	    Query<Entity> query =Query.newGqlQueryBuilder(Query.ResultType.ENTITY, "SELECT * FROM __Stat_" + HUMAN +"__").build();
	    QueryResults<Entity> results = datastore.run(query);

	    Stat stat = new Stat();
	    
	    if (results.hasNext()) {
	    	Entity e = results.next();
	    	System.out.println("count..stat..");
	    	return e.getLong("count");
	    }
	    return count2(king); 
	    
	}
	
	private long  count2(String king) {
		    // cuando no hay estadisticas de disponibles
			Query<Entity> query =
		        Query.newEntityQueryBuilder()
		            .setKind(king)
		            .build();
		    QueryResults<Entity> results = datastore.run(query);
		    long count=0l;
		    while(results.hasNext()){
		    	results.next();
		    	count++;
		    }
		    System.out.println("count..loop..");
	    return count;	    
	}
	
	
	public Stat stat() {
		Stat stat = new Stat();
	    stat.count_human_dna = count(HUMAN);
	    stat.count_mutant_dna= count(MUTANT);
		stat.ratio = 1.0d * stat.count_mutant_dna / stat.count_human_dna;
		return stat;
	}

	
	
}
