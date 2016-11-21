package com.parse.starter;

import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PeiLei on 11/19/16.
 */

public class FoodUtil {
	private static ArrayList<String> allFood;

	/**
	 * get all the food names.
	 * @return the ArrayList containing all the food names
	 */
	public static ArrayList<String> getAllFoodNames() {
		allFood = new ArrayList<String>();
		ParseQuery<ParseObject> query = ParseQuery.getQuery("Food");
		try {
			List<ParseObject> objects = query.find();
			for(ParseObject object : objects)
				allFood.add(object.get("name").toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return allFood;
	}
}
