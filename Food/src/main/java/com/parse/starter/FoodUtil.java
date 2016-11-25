package com.parse.starter;

import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by PeiLei on 11/19/16.
 */

public class FoodUtil {
	private static ArrayList<String> allFood;
	private static Map<String, String> allUnit;
	private static Map<String, String> allCalorie;

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


	/**
	 * get all the food units
	 * @return the map containing all the food units
	 */
	public static Map<String, String> getAllUnit() {
		allUnit = new HashMap<String, String>();
		ParseQuery<ParseObject> query = ParseQuery.getQuery("Food");
		try {
			List<ParseObject> objects = query.find();
			for(ParseObject object : objects) {
				allUnit.put(object.get("name").toString(), object.get("unit").toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return allUnit;
	}

	/**
	 * get all the food Calories
	 * @return the map containing all the food units
	 */
	public static Map<String, String> getAllCalorie() {
		allCalorie = new HashMap<String, String>();
		ParseQuery<ParseObject> query = ParseQuery.getQuery("Food");
		try {
			List<ParseObject> objects = query.find();
			for(ParseObject object : objects) {
				allUnit.put(object.get("name").toString(), object.get("calorie").toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return allCalorie;
	}
}
