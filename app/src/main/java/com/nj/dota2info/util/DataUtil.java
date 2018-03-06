package com.nj.dota2info.util;

import com.google.gson.Gson;
import com.nj.dota2info.gson.Hero;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018-03-06.
 */

public class DataUtil {
    public static List<Hero> handleHeroListResponse(String response) {
        List<Hero> heroList = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(response);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Hero hero = new Gson().fromJson(jsonObject.toString(), Hero.class);
                heroList.add(hero);
            }
            return heroList;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
