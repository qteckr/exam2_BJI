package com.example.myapplication;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Vector;

public class objListItem {
    public static class Item {
    }

    private Vector<Item> mList = new Vector<Item>();

    public Vector<Item> getList() {
        return mList;
    }

    public void clearList() {
        mList.clear();
    }
}
