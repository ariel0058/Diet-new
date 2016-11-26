package com.parse.starter;

import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

/**
 * Created by PeiLei on 11/11/16.
 * This class is used for keeping tracking how much food (calories) the user ate
 */

public class FoodEatenUtil {
	private static String userId = ParseUser.getCurrentUser().getObjectId();
	public  static Map<String, Map<String, Integer>> foodEaten_MealType;


	/**
	 * this method is used to add an entry of food eaten (store in database)
	 * @param mealType the meal type (breakfast, lunch or dinner)
	 * @param foodType the type of the food stored in db (egg)
	 * @param year the year
	 * @param month the month
	 * @param day  the day
	 * @param quantity the quantity
	 */
	public static void addEntry(final String mealType, final String foodType, final int year,
								final int month, final int day, final int quantity) {
		ParseQuery<ParseObject> query = ParseQuery.getQuery("Food");

		query.whereEqualTo("name", foodType);

		query.findInBackground(new FindCallback<ParseObject>() {
			@Override
			public void done(List<ParseObject> objects, ParseException e) {

				if (e == null && objects != null) {

					for (ParseObject object : objects) {
						// foodTypeID = object.getObjectId().toString();
						ParseObject FoodEaten = new ParseObject("FoodEaten");
						FoodEaten.put("userId", userId);
						FoodEaten.put("mealType", mealType);
						FoodEaten.put("foodTypeID", object.getObjectId());
						FoodEaten.put("year", year);
						FoodEaten.put("month", month);
						FoodEaten.put("day", day);
						FoodEaten.put("quantity", quantity);
						FoodEaten.saveInBackground(new SaveCallback() {
							@Override
							public void done(ParseException e) {

								if (e == null) {

									Log.i("SaveInBackground", "Successful");

								} else {


									Log.i("SaveInBackground", "Failed. Error: " + e.toString());

								}

							}
						});
					}

				}

			}
		});

	}

	/**
	 * get the calorie for the user in a day
	 * @param year the year
	 * @param month  the month
	 * @param day  the day
	 * @return  the calorie calculated for this user in a day
	 */
	public static int getDailyCalorie(final int year, final int month, final int day) {
		//*******************
		ParseQuery<ParseObject> eatenQuery = ParseQuery.getQuery("FoodEaten");
		eatenQuery.whereEqualTo("userId", userId);
		eatenQuery.whereEqualTo("year", year);
		eatenQuery.whereEqualTo("month", month);
		eatenQuery.whereEqualTo("day", day);
		// ParseQuery<ParseObject> foodQuery = ParseQuery.getQuery("Food");
		int total_calories = 0;
		try {
			List<ParseObject> objects = eatenQuery.find();
			for(final ParseObject object : objects) {
				ParseQuery<ParseObject> foodQuery = ParseQuery.getQuery("Food");   // ok
				foodQuery.whereEqualTo("objectId", object.get("foodTypeID").toString()); // ok
				try {
					List<ParseObject> foodObjs = foodQuery.find();
					for(ParseObject food : foodObjs) {
						total_calories += (int)food.get("calorie") * (int)object.get("quantity");
					}
				} catch (Exception ee) {
					ee.printStackTrace();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return total_calories;

		/*
		eatenQuery.findInBackground(new FindCallback<ParseObject>() {
			public int total_calories = 0;
			@Override
			public void done(List<ParseObject> objects, ParseException e) {

				for(final ParseObject object : objects) {
					ParseQuery<ParseObject> foodQuery = ParseQuery.getQuery("Food");   // ok
					foodQuery.whereEqualTo("objectId", object.get("foodTypeID").toString()); // ok
					try {
						List<ParseObject> foodObjs = foodQuery.find();
						for(ParseObject food : foodObjs) {
							total_calories += (int)food.get("calorie");
						}
					} catch (Exception ee) {
						ee.printStackTrace();
					}
				}
				Log.i("for testing", Integer.toString(total_calories)); // ok 500
			}
		});
		//*********************************
        */
	}


	/**
	 * return the meal type eaten
	 * @param mealType
	 * @return  mealType eaten (today)
	 */
	public static Map<String, Map<String, Integer>> getFoodEaten_MealType(String mealType) {
		foodEaten_MealType = new HashMap<String, Map<String, Integer>>();
		Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
		ParseQuery<ParseObject> foodQuery = ParseQuery.getQuery("FoodEaten");
		foodQuery.whereEqualTo("mealType", mealType);
		foodQuery.whereEqualTo("userId", userId);
		foodQuery.whereEqualTo("year", calendar.get(Calendar.YEAR));
		foodQuery.whereEqualTo("month", calendar.get(Calendar.MONTH) + 1);
		foodQuery.whereEqualTo("day", calendar.get(Calendar.DAY_OF_MONTH));

		ParseQuery<ParseObject> query = ParseQuery.getQuery("Food");


		Map<String, Integer> foodQuantityEaten = new HashMap<String, Integer>();
		try {
			List<ParseObject> foodObjs = foodQuery.find();
			for(ParseObject object : foodObjs) {
				int quantity = (int)object.get("quantity");
				query.whereEqualTo("objectId", object.get("foodTypeID").toString());
				try {
					List<ParseObject> foods = query.find();
					for(ParseObject food : foods) {
						foodQuantityEaten.put(food.get("name").toString(), quantity);
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		foodEaten_MealType.put(mealType, foodQuantityEaten);
		return foodEaten_MealType;
	}

	/**
	 * this is a test method showing you how to add an entry and get Calendar obj
	 */
	public static void test() {
		Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		FoodEatenUtil.addEntry("breakfast", "milk", year, month, day, 2);
	}
}
