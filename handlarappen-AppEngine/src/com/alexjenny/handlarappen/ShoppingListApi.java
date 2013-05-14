package com.alexjenny.handlarappen;

import java.io.IOException;
import java.util.List;

import javax.annotation.Nullable;
import javax.inject.Named;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.appengine.api.oauth.OAuthRequestException;
import com.google.appengine.api.users.User;

@Api(name = "shoppinglist", version = "v1" // ,
// clientIds = {Ids.WEB_CLIENT_ID, Ids.ANDROID_CLIENT_ID},
// audiences = {Ids.ANDROID_AUDIENCE}
)
public class ShoppingListApi {

	@ApiMethod(name = "shoppinglist.list", path = "shoplistlist")
	public List<ShoppableItem> list(@Nullable @Named("limit") String limit,
			@Nullable @Named("order") String order, User user)
			throws IOException {

		ShoppingList list = getShoppingList();

		return list.getItems();

	}

	@ApiMethod(name = "shoppinglist.insert", path = "shoplistinsert")
	public ShoppableItem insert(ShoppableItem item, @Named("amount") int amount)
			throws IOException {

		ShoppingList shoppingList = getShoppingList();

		shoppingList.addItem(item);

		// TODO fix
		// new MessageEndpoint().sendMessage("Item inserted into shopping list"
		// + item.getName());

		return item;

	}

	@ApiMethod(name = "shoppinglist.remove")
	public ShoppableItem removeFromShoppingList(ShoppableItem item)
			throws OAuthRequestException, IOException {

		ShoppingList shoppingList = getShoppingList();
		shoppingList.removeItem(item);

		// TODO fix
		// new MessageEndpoint().sendMessage("Item removed from shopping list"
		// + item.getName());

		return item;
	}

	private static PersistenceManager getPersistenceManager() {
		return PMF.get().getPersistenceManager();
	}
	
	private ShoppingList getShoppingList() {
		PersistenceManager pm = getPersistenceManager();
		Query query = pm.newQuery(ShoppingList.class);
		@SuppressWarnings("unchecked")
		List<ShoppingList> list = (List<ShoppingList>) query.execute();

		// If no shopping list create a new one
		if (list == null || list.size() == 0) {
			ShoppingList shoppingList = new ShoppingList();
			pm.makePersistent(shoppingList);
			return shoppingList;
		}

		return list.get(0);
	}	
}