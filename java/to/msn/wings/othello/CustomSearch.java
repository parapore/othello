package to.msn.wings.othello;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;



public class CustomSearch extends AsyncTask<String, Bitmap, Bitmap> {
    String link = null;
    Bitmap bmp = null;
    List<String> linkList = new ArrayList<>();
    private Listener listener;


    @Override
    protected Bitmap doInBackground(String... qry) {
        //URLを取得
        getLink(qry[0]);

        //10秒に1回画像を切り替える（5回まで）
        for (int i = 0; i < 4; i++) {
            try {
                publishProgress(downloadImage(qry[0]));
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return downloadImage(qry[0]);
    }

    // 途中経過をメインスレッドに返す
    @Override
    protected void onProgressUpdate(Bitmap... bmp) {
        if (listener != null) {
            this.bmp = bmp[0];
            listener.onSuccess(bmp[0]);
        }
    }

    // 非同期処理が終了後、結果をメインスレッドに返す
    @Override
    protected void onPostExecute(Bitmap bmp) {
        if (listener != null) {
            this.bmp = bmp;
            listener.onSuccess(bmp);
        }
    }

    //レスポンスはjson
    //jsonからURLを探し出す
    List<String> getLink(String qry) {
        try {
            URL url = new URL(
                    "https://www.googleapis.com/customsearch/v1?key=" + "APIkey" + "&cx=" + "ID" + "&q=" + qry + "&searchType=image");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {

                if (output.contains("\"link\": \"")) {

                    //substringが文字列の抜きだし。
                    //indexOfが指定文字の先頭位置を返す。
                    // "link\":の.length()で"link\":以降の文字を抜き出す。
                    // 構造はこう↓
                    //substring(indexOf("開始文字"), indexOf("終了文字"))
                    // さらに詳しくはこう↓
                    //substring(indexOf("開始文字") + ("開始文字の長さ").length(), indexOf("終了文字"))
                    link = output.substring(output.indexOf("\"link\": \"") + ("\"link\": \"").length(), output.indexOf("\","));
                    System.out.println(link);       //Will print the google search links
                    linkList.add(link);
                }
            }
            conn.disconnect();
            return linkList;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    private Bitmap downloadImage(String address) {
        HttpURLConnection urlConnection = null;
        Random random = new Random();

        try {
            // linkListの中からランダムでURLを選択する
            URL url = new URL(linkList.get(random.nextInt(10)));

            // HttpURLConnection インスタンス生成
            urlConnection = (HttpURLConnection) url.openConnection();

            // タイムアウト設定
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(20000);

            // リクエストメソッド
            urlConnection.setRequestMethod("GET");

            // リダイレクトを自動で許可しない設定
            urlConnection.setInstanceFollowRedirects(false);

            // ヘッダーの設定(複数設定可能)
            urlConnection.setRequestProperty("Accept-Language", "jp");

            // 接続
            urlConnection.connect();

            int resp = urlConnection.getResponseCode();

            switch (resp) {
                case HttpURLConnection.HTTP_OK:
                    try (InputStream is = urlConnection.getInputStream()) {
                        bmp = BitmapFactory.decodeStream(is);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case HttpURLConnection.HTTP_UNAUTHORIZED:
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            Log.d("debug", "downloadImage error");
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return bmp;
    }

    void setListener(Listener listener) {
        this.listener = listener;
    }

    interface Listener {
        void onSuccess(Bitmap bmp);
    }

    Bitmap getBmp() {
        return bmp;
    }
}

