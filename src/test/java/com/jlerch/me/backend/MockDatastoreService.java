package com.jlerch.me.backend;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

import com.google.cloud.datastore.Batch;
import com.google.cloud.datastore.Cursor;
import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.FullEntity;
import com.google.cloud.datastore.IncompleteKey;
import com.google.cloud.datastore.Key;
import com.google.cloud.datastore.KeyFactory;
import com.google.cloud.datastore.Query;
import com.google.cloud.datastore.QueryResults;
import com.google.cloud.datastore.ReadOption;
import com.google.cloud.datastore.Transaction;
import com.google.cloud.datastore.Value;
import com.google.datastore.v1.TransactionOptions;
import com.google.datastore.v1.QueryResultBatch.MoreResultsType;

/**
 * This mock class is created to enable basic unit testing of the
 * {@link HelloAppEngine} class. Only methods used in the unit test
 * have a non-trivial implementation.
 * 
 * Feel free to change this class or replace it using other ways for testing
 * {@link HttpServlet}s, e.g. Spring MVC Test or Mockito to suit your needs.
 */
public class MockDatastoreService implements Datastore {

	List<Entity> entities = new ArrayList<Entity>();
	
	@Override
	public DatastoreOptions getOptions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Entity get(Key key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<Entity> get(Key... keys) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Entity> fetch(Key... keys) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> QueryResults<T> run(Query<T> query) {
			QueryResults<T> results = new QueryResults<T>() {
				
				boolean hasnext=true;
				@Override
				public T next() {
					FullEntity f = MockDatastoreService.this.entities.get(0);
					return (T)f;
				}
				
				@Override
				public boolean hasNext() {
					if(hasnext) {
						hasnext=false;
						return true;
					}
					
					return hasnext;
					
				}
				
				@Override
				public int getSkippedResults() {
					// TODO Auto-generated method stub
					return 0;
				}
				
				@Override
				public Class<?> getResultClass() {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public MoreResultsType getMoreResults() {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public Cursor getCursorAfter() {
					// TODO Auto-generated method stub
					return null;
				}
			};  
		return results;
	}

	@Override
	public Transaction newTransaction(TransactionOptions options) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Transaction newTransaction() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T runInTransaction(TransactionCallable<T> callable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T runInTransaction(TransactionCallable<T> callable, TransactionOptions options) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Batch newBatch() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Key allocateId(IncompleteKey key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Key> allocateId(IncompleteKey... keys) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Key> reserveIds(Key... keys) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Entity add(FullEntity<?> entity1) {
		
		   Key key = this.newKeyFactory()
		    	    .setKind("kind")
		    	    .newKey("key");
		Entity entity = Entity.newBuilder(key)
	    	    .set("count", 1)
	    	    .build(); 
		
		this.entities.add((Entity) entity);
		return (Entity) entity;
	}

	@Override
	public List<Entity> add(FullEntity<?>... entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Entity... entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Entity put(FullEntity<?> entity) {
		this.entities.add((Entity)entity);
		return null;
	}

	@Override
	public List<Entity> put(FullEntity<?>... entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Key... keys) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public KeyFactory newKeyFactory() {
		KeyFactory f = new KeyFactory("jlerchme01");
		return f;
	}

	@Override
	public Entity get(Key key, ReadOption... options) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<Entity> get(Iterable<Key> keys, ReadOption... options) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Entity> fetch(Iterable<Key> keys, ReadOption... options) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> QueryResults<T> run(Query<T> query, ReadOption... options) {
		
		return null;
	}
	
	
}
