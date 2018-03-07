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
    public static void handleHeroListResponse(String response, List<Hero> heroList) {
        //这个不能new，否则每次解析之后就不是一个List集合了，recyclerview的Adapter调用notifyDataSetChanged，也就不起作用了
        //List<Hero> heroList = new ArrayList<>();
        //解析之前需要把集合清一下，否则看着也不没有改变似的
        heroList.clear();
        try {
            JSONArray jsonArray = new JSONArray(response);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Hero hero = new Gson().fromJson(jsonObject.toString(), Hero.class);
                heroList.add(hero);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
