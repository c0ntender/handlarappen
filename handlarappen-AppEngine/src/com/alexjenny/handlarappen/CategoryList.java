

package com.alexjenny.handlarappen;

import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;

@Api(name = "categorylist", version = "v1" // ,
// clientIds = {Ids.WEB_CLIENT_ID, Ids.ANDROID_CLIENT_ID},
// audiences = {Ids.ANDROID_AUDIENCE}
)
// TODO add authentication
// TODO same api as the rest? 
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
	@ApiMethod(name = "categorylist.list")
	public List<Category> list()
			throws IOException {
		
		
		PersistenceManager pm = getPersistenceManager();
		Query query = pm.newQuery(Category.class);
		query.setOrdering("name desc");

		return (List<Category>) pm.newQuery(query).execute();


	}

	/**
	 * Adds a new type of Category
	 * 
	 * @param category
	 * @return
	 * @throws IOException
	 */
	@ApiMethod(name = "categorylist.insert")
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
	@ApiMethod(name = "categorylist.remove")
	public Category remove(Category category) throws IOException {
		
		PersistenceManager pm = getPersistenceManager();
		// TODO verify
		pm.deletePersistent(category);

		new MessageEndpoint().sendMessage("Category removed, " + category.getName());
		
		return category;

	}
	
	private static PersistenceManager getPersistenceManager() {
		return PMF.get().getPersistenceManager();
	}

}
