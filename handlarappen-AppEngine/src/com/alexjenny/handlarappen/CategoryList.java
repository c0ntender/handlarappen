

package com.alexjenny.handlarappen;

import java.io.IOException;
import java.util.List;

import javax.inject.Named;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;

@Api(name = "shoppinglist", version = "v1" // ,
//clientIds = {Ids.WEB_CLIENT_ID, Ids.ANDROID_CLIENT_ID},
//audiences = {Ids.ANDROID_AUDIENCE}
)
public class CategoryList {

	
	/** 
	 * List available categories 
	 * 
	 * @param limit
	 * @param order
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	@ApiMethod(name = "categorylist.list",  path = "categorylist/list")
	public List<Category> list()
			throws IOException {
		
		
		PersistenceManager pm = getPersistenceManager();
		Query query = pm.newQuery(Category.class);
		query.setOrdering("name desc");

		try  {
		return (List<Category>) pm.newQuery(query).execute();
		} finally {pm.close();}


	}

	/**
	 * Adds a new type of Category
	 * 
	 * @param category
	 * @return
	 * @throws IOException
	 */
	@ApiMethod(name = "categorylist.insert",  path = "categorylist/insert")
	public Category insert(Category category) throws IOException {
		
		PersistenceManager pm = getPersistenceManager();
		pm.makePersistent(category);
		pm.close();
		
		new MessageEndpoint().sendMessage("Category inserted, " + category.getName());
		

		return category;

	}
	
	/**
	 * Remove a type of category
	 * 
	 * @param category
	 * @return
	 * @throws IOException
	 */
	@ApiMethod(name = "categorylist.remove",  path = "categorylist/remove")
	public Category remove(@Named("id") String id) throws IOException {
		
		
		
		PersistenceManager pm = getPersistenceManager();
		Category category = null;
		pm.deletePersistent(pm.getObjectById(Category.class, id));
		
//		new MessageEndpoint().sendMessage("Category removed, " + category.getName());
		pm.close();
		return category;

	}
	
	private static PersistenceManager getPersistenceManager() {
		return PMF.get().getPersistenceManager();
	}

}
