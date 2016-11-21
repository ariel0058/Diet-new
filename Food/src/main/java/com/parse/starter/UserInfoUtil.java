package com.parse.starter;

import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by PeiLei on 11/19/16.
 */

public class UserInfoUtil {
	// create an map to store all the user info
	private Map<String, String> userInfo;

	/**
	 * the ctor.
	 */
	public UserInfoUtil() {
		// instantiate the field
		userInfo = new HashMap<String, String>();
		String name = ParseUser.getCurrentUser().getUsername();
		userInfo.put("userName", name);
		ParseQuery<ParseObject> userQuery = ParseQuery.getQuery("UserInfo");
		userQuery.whereEqualTo("user_id", ParseUser.getCurrentUser().getObjectId());
		try {
			List<ParseObject> Objs = userQuery.find();
			for(ParseObject info : Objs) {
				userInfo.put("ideal_weight", info.get("ideal_weight").toString());
				userInfo.put("height", info.get("height").toString());
				userInfo.put("recommended_calories", info.get("recommended_calories").toString());
				userInfo.put("bmi", info.get("bmi").toString());
				userInfo.put("current_weight", info.get("current_weight").toString());
				userInfo.put("age", info.get("age").toString());
			}
		} catch (Exception ee) {
			ee.printStackTrace();
		}

	}


	/**
	 * get the userInfoMap
	 */
	public Map<String, String> getUserInfoMap() {
		return userInfo;
	}
}
