package com.alexjenny.handlarappen;

import java.io.IOException;
import java.util.List;

import javax.annotation.Nullable;
import javax.inject.Named;

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
	@ApiMethod(name = "itemslist.list", path = "itemslist/list")
	public List<ShoppableItem> list(@Nullable @Named("limit") String limit,
			@Nullable @Named("order") String order)
			throws IOException {

		// TODO implement

		return null;

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

		// TODO implement
		return null;

	}
	
	/**
	 * Remove a shoppable item
	 * 
	 * @param item
	 * @return
	 * @throws IOException
	 */
	@ApiMethod(name = "itemslist.remove",  path="itemslist/remove")
	public ShoppableItem remove(ShoppableItem item) throws IOException {

		// TODO implement
		return null;

	}

}
