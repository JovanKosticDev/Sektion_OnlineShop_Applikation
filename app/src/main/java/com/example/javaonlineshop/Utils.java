package com.example.javaonlineshop;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Utils {

    private static int ID = 0;
    private static final String ALL_ITEMS_KEY = "all_items";
    private static final String DB_NAME = "fake_database";

    private static Gson gson = new Gson();
    private static Type groceryListType = new TypeToken<ArrayList<GroceryItem>>(){}.getType();

    public static void initSharedPreferences(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(DB_NAME, Context.MODE_PRIVATE);
        ArrayList<GroceryItem> allItems = gson.fromJson(sharedPreferences.getString(ALL_ITEMS_KEY, null), groceryListType);
        if(null == allItems){
            initAllItems(context);
        }
    }

    public static void clearSharedPreferences(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(DB_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
    }

    private static void initAllItems(Context context){
        ArrayList<GroceryItem> allItems = new ArrayList<>();
        GroceryItem milk = new GroceryItem("Milch",
                "Besonders lecker cremig schmeckt Bärenmarke Die haltbare Milch in einer heißen Tasse Latte Macchiato, Cappuccino und Milchkaffee. Der Fettgehalt von 3,8 % verfeinert den sahnigen Kaffee-Genuss und rundet den Geschmack ab.",
                "https://cdn02.plentymarkets.com/f3fwwhiwe7th/item/images/71811280/full/Baerenmarke-Die-haltbare-Milch-3-8--Fett--Vollmilch-mit-Drehverschluss-1l--Milch-71811280.jpg",
                "Trinken", 2.3, 10);
        allItems.add(milk);

        GroceryItem iceCream = new GroceryItem("Ben & Jerry",
                "Vanille-Doppelrahmglace mit Cookieteig-Stücken (mit Schokoladenstückchen) (16%) und kakaohaltigen Fettglasurstücken (1%).",
                "https://i5.walmartimages.com/asr/6089ec90-dfea-4280-9dd2-a4e2972bf52e.9736d7870d96bd279b602f4f22a4f323.jpeg",
                "Essen", 5.9, 15);
        iceCream.setPopularityPoint(10);
        iceCream.setUserPoint(7);
        allItems.add(iceCream);

        GroceryItem soda = new GroceryItem("Gerolsteiner",
                "Sprudel, 0,5l",
                "https://bestino.de/media/image/d7/1e/64/gerolsteiner-medium-0-75l-2439-001_600x600.jpg",
                "Trinken", 1.18, 25);
        soda.setPopularityPoint(6);
        soda.setUserPoint(10);
        allItems.add(soda);

        SharedPreferences sharedPreferences = context.getSharedPreferences(DB_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(ALL_ITEMS_KEY, gson.toJson(allItems));
        editor.commit();
    }

    public static ArrayList<GroceryItem> getAllItems(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(DB_NAME, Context.MODE_PRIVATE);
        ArrayList<GroceryItem> allItems = gson.fromJson(sharedPreferences.getString(ALL_ITEMS_KEY, null), groceryListType);
        return allItems;
    }

    public static void changeRate(Context context, int itemId, int newRate){
        SharedPreferences sharedPreferences = context.getSharedPreferences(DB_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        ArrayList<GroceryItem> allItems = gson.fromJson(sharedPreferences.getString(ALL_ITEMS_KEY, null), groceryListType);
        if(null != allItems){
            ArrayList<GroceryItem> newItems = new ArrayList<>();
            for(GroceryItem i: allItems){
                if(i.getId() == itemId){
                    i.setRate(newRate);
                    newItems.add(i);
                }else{
                    newItems.add(i);
                }
            }
            editor.remove(ALL_ITEMS_KEY);
            editor.putString(ALL_ITEMS_KEY, gson.toJson(newItems));
            editor.commit();
        }

    }

    public static int getID() {
        ID++;
        return ID;
    }
}