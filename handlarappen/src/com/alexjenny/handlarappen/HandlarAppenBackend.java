package com.alexjenny.handlarappen;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.shoppinglist.Shoppinglist;
import com.google.api.services.shoppinglist.Shoppinglist.Categorylist;
import com.google.api.services.shoppinglist.model.Category;
import com.google.api.services.shoppinglist.model.CategoryCollection;
import com.google.api.services.shoppinglist.model.ShoppableItem;

public class HandlarAppenBackend {

	public List<Category> getCategories() {
		try {

			Shoppinglist.Builder builder = new Shoppinglist.Builder(
					AndroidHttp.newCompatibleTransport(), new GsonFactory(),
					new HttpRequestInitializer() {
						public void initialize(HttpRequest httpRequest) {
						}
					});

			Shoppinglist list = CloudEndpointUtils.updateBuilder(builder)
					.build();

			CategoryCollection collection = list.categorylist().list()
					.execute();
			System.out.println("Listing categories:");
			for (Category basketItem : collection.getItems()) {
				System.out.println("item " + basketItem.getName());
			}
			System.out.println("Done listing categories");

			return collection.getItems();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return Collections.emptyList();
	}

	// TODO funkar inte
	public boolean removeCategory(Category category) {
		try {
			Shoppinglist.Builder builder = new Shoppinglist.Builder(
					AndroidHttp.newCompatibleTransport(), new GsonFactory(),
					new HttpRequestInitializer() {
						public void initialize(HttpRequest httpRequest) {
						}
					});

			Shoppinglist list = CloudEndpointUtils.updateBuilder(builder)
					.build();

			if (list.categorylist().remove(category.getName()).execute() != null) {
				System.out.println("Success removing Category "
						+ category.getName());
				return true;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("Failure removing Category " + category.getName());
		return false;
	}

	public boolean addCategory(Category category) {
		try {

			Shoppinglist.Builder builder = new Shoppinglist.Builder(
					AndroidHttp.newCompatibleTransport(), new GsonFactory(),
					new HttpRequestInitializer() {
						public void initialize(HttpRequest httpRequest) {
						}
					});
			Shoppinglist list = CloudEndpointUtils.updateBuilder(builder)
					.build();
			
			if (list.categorylist().insert(category).execute() != null) {
				System.out.println("Success adding Category "
						+ category.getName());
				return true;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("Failure adding Category " + category.getName());
		return false;
	}
	
	public boolean addItemToShoppingList(ShoppableItem item) {
		try {

			Shoppinglist.Builder builder = new Shoppinglist.Builder(
					AndroidHttp.newCompatibleTransport(), new GsonFactory(),
					new HttpRequestInitializer() {
						public void initialize(HttpRequest httpRequest) {
						}
					});
			Shoppinglist list = CloudEndpointUtils.updateBuilder(builder)
					.build();
			

			if (list.shoppinglist().insert(Integer.valueOf(1), item).execute() != null) {
				System.out.println("Success adding Item "
						+ item.getName() + " to shopping list");
				return true;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("Failure adding Item "
				+ item.getName() + " to shopping list");
		return false;
	}

}
