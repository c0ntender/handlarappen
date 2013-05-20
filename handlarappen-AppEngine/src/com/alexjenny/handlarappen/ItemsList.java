package com.alexjenny.handlarappen;

import java.io.IOException;
import java.util.List;

import javax.annotation.Nullable;
import javax.inject.Named;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;

@Api(name = "shoppinglist", version = "v1" // ,
//clientIds = {Ids.WEB_CLIENT_ID, Ids.ANDROID_CLIENT_ID},
//audiences = {Ids.ANDROID_AUDIENCE}
)
// TODO add authentication
public class ItemsList {

	/** 
	 * List shoppable items 
	 * 
	 * @param limit
	 * @param order
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	@ApiMethod(name = "itemslist.list", path = "itemslist/list")
	public List<ShoppableItem> list(@Nullable @Named("limit") String limit,
			@Nullable @Named("order") String order)
			throws IOException {
		PersistenceManager pm = getPersistenceManager();
		Query query = pm.newQuery(ShoppableItem.class);
		query.setOrdering("name desc");

		try {
		return (List<ShoppableItem>) pm.newQuery(query).execute();
		} finally {pm.close();}

	}

	/**
	 * Adds a new type of shoppable item to the database
	 * 
	 * @param item
	 * @return
	 * @throws IOException
	 */
	@ApiMethod(name = "itemslist.insert", path = "itemslist/insert")
	public ShoppableItem insert(ShoppableItem item) throws IOException {
		
		PersistenceManager pm = getPersistenceManager();
		pm.makePersistent(item);
		pm.close();
		
//		new MessageEndpoint().sendMessage("to be impl");
		

		return item;

	}
	
	/**
	 * Remove a shoppable item
	 * 
	 * @param item
	 * @return
	 * @throws IOException
	 */
	@ApiMethod(name = "itemslist.remove",  path="itemslist/remove")
	public ShoppableItem remove(@Named("id") String id) throws IOException {
		
		PersistenceManager pm = getPersistenceManager();
		// TODO verify
		pm.deletePersistent(pm.getObjectById(ShoppableItem.class, id));
		pm.close();

//		new MessageEndpoint().sendMessage("to be impl");
		
		return null;

	}
	
	private static PersistenceManager getPersistenceManager() {
		return PMF.get().getPersistenceManager();
	}

}
