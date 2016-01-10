package xyz.davidng.dongaexchange;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

/**
 * Created by admin on 1/10/2016.
 */
public class FetchData extends AsyncTask<String, Void, String> {
    Context context;
    int customLayoutID;
    ListView lvMain;
    ArrayList<Exchange> arrayTemp;

    // constructor.
    public FetchData(Context context, int customLayoutID, ListView lvMain) {
        this.context = context;
        this.customLayoutID = customLayoutID;
        this.lvMain = lvMain;
    }

    @Override
    protected String doInBackground(String... params) {
        arrayTemp = new ArrayList<Exchange>();
        if(params[0] != null){
            String content = readFromURL(params[0]);
            try {
                JSONObject root = new JSONObject(content);
                JSONArray array = root.getJSONArray("items");
                for(int i=0; i<array.length(); i++){
                    JSONObject item = array.getJSONObject(i);
                    String type = item.optString("type");
                    String imageurl = item.optString("imageurl");
                    String muatienmat = item.optString("muatienmat");
                    String bantienmat = item.optString("bantienmat");
                    arrayTemp.add(new Exchange(type, imageurl, muatienmat, bantienmat));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(String value) {
        CustomAdapter adapter = new CustomAdapter(context, customLayoutID, arrayTemp);
        lvMain.setAdapter(adapter);
    }

    public Bitmap getBitmapFromURL(String src) {
        try {
            java.net.URL url = new java.net.URL(src);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    private static String readFromURL(String url) {
        StringBuilder content = new StringBuilder();
        try {
            URL u = new URL(url);
            URLConnection urlConnection = u.openConnection();
            urlConnection.setRequestProperty("Accept",
                    "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(urlConnection.getInputStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line + "\n");
            }
            bufferedReader.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // this is a hack =))
        return content.toString().substring(1, content.length()-2);
    }
}
