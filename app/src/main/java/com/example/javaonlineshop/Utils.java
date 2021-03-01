package com.example.javaonlineshop;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class Utils {

    private static int ID = 0;
    private static final String ALL_ITEMS_KEY = "all_items";
    private static final String DB_NAME = "fake_database";
    private static final String CART_ITEMS_KEY = "cart_items";

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
        allItems.add(iceCream);

        GroceryItem soda = new GroceryItem("Gerolsteiner",
                "Lecker, Sprudel! Die kleinen Blubberbläschen sind Kohlensäure, ein Gemisch aus Kohlendioxid und Wasser.",
                "https://bestino.de/media/image/d7/1e/64/gerolsteiner-medium-0-75l-2439-001_600x600.jpg",
                "Trinken", 1.18, 25);
        allItems.add(soda);

        GroceryItem nivea = new GroceryItem("Nivea",
                "Vertrauen Sie der blauen Dose für die ganze Familie. Seit über 100 Jahren pflegt und schützt die NIVEA Creme mit ihrer reichhaltigen Formel jeden Hauttypen, in jedem Alter und bei jeder Gelegenheit. Die cremige Textur mit dem beliebten Duft in der typischen Dose schützt und verwöhnt die Haut besonders unkompliziert - Denn NIVEA ist für alle da. Ein Effekt, den Sie fühlen und sehen können: NIVEA Creme Mini.",
                "https://images-na.ssl-images-amazon.com/images/I/81CGBGpbNqL._SL1500_.jpg",
                "Pflege", 3.5, 10);
        allItems.add(nivea);

        GroceryItem barilla = new GroceryItem("Barilla",
                "Ein schmackhafter Teller Pasta ist ein Genuss für jeden Tag des Jahres. Aus diesem Grund verwenden wir nur den besten Weizen, Wasser und liebevolle Sorgfalt. So wird Barilla Pasta seit 1877 hergestellt.",
                "https://images-na.ssl-images-amazon.com/images/I/41llTtim9SL.jpg",
                "Essen", 1.65, 40);
        allItems.add(barilla);

        GroceryItem nutella = new GroceryItem("Nutella",
                "Süß in den Tag starten \n" +
                        "Den Morgen mal süß beginnen? Mit der großen Auswahl an süßen Brotaufstrichen von REWE zaubern Sie ein Lächeln auf die Gesichter aller Schleckermäuler. Probieren Sie die Vielfalt der schokoladigen Aufstriche bis hin zur fruchtigen Konfitüre. ",
                "https://d3el976p2k4mvu.cloudfront.net/medias/sys_master/h22/hb9/8822395600926.png",
                "Essen", 2.99, 25);
        allItems.add(nutella);

        GroceryItem cola = new GroceryItem("Coca Cola",
                "Kalorienfreies koffeinhaltiges Erfrischungsgetränk mit Pflanzenextrakten mit Süßungsmitteln",
                "https://paralelaplus.rs/wp-content/uploads/2020/05/Coca-Cola-Zero-limenka-0.33l.png",
                "Trinken", 0.69, 5);
        allItems.add(cola);

        GroceryItem gillette = new GroceryItem("Gillette Rasierer",
                "Die Gillette Mach3 Rasierer für Männer verfügen über 3 DuraComfort Klingen, stärker als Stahl, die länger scharf bleiben (im Vergleich zu Sensor3). Mit schärferen Klingen (die ersten beiden Klingen im Vergleich zu Sensor3) ist er für 15 angenehme Rasuren entwickelt. Diese Gillette Rasierer haben einen Feuchtigkeitsstreifen für besseres Gleiten, und um Ihre Haut vor Hautreizungen zu schützen. Die mikrofeinen Hautschutz-Lamellen, spannen die Haut und bereiten das Haar auf die Rasur vor. Der Mach3 Rasierer verfügt über einen perfekt ausbalancierten ergonomischen Griff, für ein herausragendes Rasurerlebnis. Alle Mach3 Klingen können mit allen Mach3 Griffen verwendet werden.",
                "https://www.hiper.rs/pub/media/catalog/product/cache/f8158826193ba5faa8b862a9bd1eb9e9/6/6/6606-mach-3-turbo-1-up.jpg",
                "Pflege", 7.99, 10);
        allItems.add(gillette);

        GroceryItem axeDeo = new GroceryItem("Axe Deospray",
                "Für authentische Typen, die ihren Weg vom Anfang bis zum Ende gehen, hat AXE dieses konsequente Bodyspray kreiert. Es kombiniert den effizienten AXE-Schutz vor Körpergeruch mit einem männlichen Duft, entdeckt von einigen der besten Duftexperten auf der Welt. Vollkommen ohne Aluminiumsalze schützt AXE Alaska seinen Träger so effektiv, dass er sich voll und ganz auf seinen eigenen Plan konzentrieren kann. ",
                "https://m.media-amazon.com/images/I/61QBH6DZT2L._AC_SS450_.jpg",
                "Pflege", 2.75, 30);
        allItems.add(axeDeo);

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

    public static void addReview(Context context, Review review){
        SharedPreferences sharedPreferences = context.getSharedPreferences(DB_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        ArrayList<GroceryItem> allItems = getAllItems(context);
        if(null != allItems){
            ArrayList<GroceryItem> newItems = new ArrayList<>();
            for (GroceryItem i : allItems){
                if(i.getId() == review.getGroceryItemId()){
                    ArrayList<Review> reviews = i.getReviews();
                    reviews.add(review);
                    i.setReviews(reviews);
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

    public static ArrayList<Review> getReviewsByID(Context context, int itemId) {
        ArrayList<GroceryItem> allItems = getAllItems(context);
        if(null != allItems){
            for (GroceryItem i : allItems){
                if(i.getId() == itemId){
                    ArrayList<Review> reviews = i.getReviews();
                    return reviews;
                }
            }
        }
        return null;
    }

    public static void addItemToCart(Context context, GroceryItem item){
        SharedPreferences sharedPreferences = context.getSharedPreferences(DB_NAME, Context.MODE_PRIVATE);
        ArrayList<GroceryItem> cartItems = gson.fromJson(sharedPreferences.getString(CART_ITEMS_KEY, null), groceryListType);
        if(cartItems == null){
            cartItems = new ArrayList<>();
        }

        cartItems.add(item);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(CART_ITEMS_KEY);
        editor.putString(CART_ITEMS_KEY, gson.toJson(cartItems));
        editor.commit();
    }

    public static ArrayList<GroceryItem> getCartItems(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(DB_NAME, Context.MODE_PRIVATE);
        ArrayList<GroceryItem> cartItems = gson.fromJson(sharedPreferences.getString(CART_ITEMS_KEY, null), groceryListType);
        return cartItems;
    }

    public static ArrayList<GroceryItem> searchForItems(Context context, String text){
        ArrayList<GroceryItem> allItems = getAllItems(context);
        if(null != allItems){
            ArrayList<GroceryItem> items = new ArrayList<>();
            for(GroceryItem item: allItems){
                if(item.getName().equalsIgnoreCase(text)){
                    items.add(item);
                }

                String[] names = item.getName().split(" ");
                for(int i = 0; i < names.length; i++){
                    if(text.equalsIgnoreCase(names[i])){
                        boolean doesExist = false;

                        for(GroceryItem j : items){
                            if(j.getId() == item.getId()){
                                doesExist = true;
                            }
                        }
                        if(!doesExist){
                            items.add(item);
                        }
                    }
                }
            }
            return items;
        }
        return null;
    }

    public static ArrayList<String> getCategories(Context context){
        ArrayList<GroceryItem> allItems = getAllItems(context);
        if(null != allItems){
            ArrayList<String> categories = new ArrayList<>();
            for(GroceryItem item : allItems){
                boolean doesExist = false;
                for(String s : categories){
                    if(item.getCategory().equals(s)){
                        doesExist = true;
                    }
                }
                if(!doesExist){
                    categories.add(item.getCategory());
                }
            }
            return categories;
        }
        return null;
    }

    public static ArrayList<GroceryItem> getItemsByCategory(Context context, String category){
        ArrayList<GroceryItem> allItems = getAllItems(context);
        if(null != allItems){
            ArrayList<GroceryItem> items = new ArrayList<>();
            for(GroceryItem item : allItems){
                if(item.getCategory().equals(category)){
                    items.add(item);
                }
            }
            return items;
        }
        return null;
    }

    public static int getID() {
        ID++;
        return ID;
    }
}



















