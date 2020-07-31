package to.msn.wings.othello;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.io.IOException;
import java.util.Random;

/* 概要。
HasMap内でオセロ盤を表すmapを生成する。
HashMap<Integer, Character>
   キーがオセロ盤内の座標。
   値が石の色。
        値には「黒」「白」「＊」が入る。
        「＊」は石が置かれていない空白マスを表す。
裏返し判定はHasMapを利用する。

CPUの画像をGoogleの画像検索からダウンロードできる。
好きなアイドルや俳優との対戦気分を味わえるｗ
google custom search APIを利用。
 */


public class MainActivity extends AppCompatActivity {
    Stone s = new Stone();
    Reverse r = new Reverse();
    private Handler handler = new Handler();
    char color; // 石の色を表す。
    int blackCount = 0; // 黒石の数
    int whiteCount = 0; // 白石の数
    ImageView img = null;
    TextView characterTxt;
    Animation expansionEnd;
    Animation alphaEnd;
    Animation anim;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 盤面をHashMapで作る。
        // 最初は全マスに「＊」を置く。
        // ＊は空白マスを表す。
        for (int i = 0; i < 64; i++) {
            s.stoneMap.put(i, '*');
        }
    }

    CustomSearch cs;
    private ImageView characterImageTop;

    //対戦相手の画像取得をする
    public void getImgOnClick(View view) throws IOException {
        // Viewの取得
        characterImageTop = findViewById(R.id.imageView0);
        EditText editText = findViewById(R.id.editText);

        characterImageTop.setImageResource(R.drawable.download);

        // 検索クエリの取得
        String[] qry = new String[1];
        qry[0] = editText.getText().toString();

        // ボタンをタップして非同期処理を開始
        cs = new CustomSearch();
        // Listenerを設定
        cs.setListener(createListener());
        cs.execute(qry[0]);
    }

    @Override
    protected void onDestroy() {
        cs.setListener(null);
        super.onDestroy();
    }

    private CustomSearch.Listener createListener() {
        return new CustomSearch.Listener() {
            @Override
            public void onSuccess(Bitmap bmp) {
                characterImageTop.setImageBitmap(bmp);
            }
        };
    }

    //Start + RESETボタンを押された時の処理
    public void startOnClick(View view) {
        color = '黒'; //先手は黒
        TextView txt = findViewById(R.id.text);
        txt.setText(null);
        Toast.makeText(this, "あなたは先手（黒）です。", Toast.LENGTH_LONG).show();

        //各アニメーションの準備。アニメーション定義ファイルをロード。
        Animation expansionStart = AnimationUtils.loadAnimation(this, R.anim.start);
        expansionEnd = AnimationUtils.loadAnimation(this, R.anim.end);
        alphaEnd = AnimationUtils.loadAnimation(this, R.anim.alphaend);

        //画像をポップアップする処理。
        final ImageView start = findViewById(R.id.popUpImage);
        final ImageView popUpcharacterImage = findViewById(R.id.popUpcharacterImage);
        Bitmap bmp = null;
        try {
            bmp = cs.getBmp();
        } catch (NullPointerException e) {
            System.out.println("画像未取得です。");
        }
        start.setImageResource(R.drawable.start);
        popUpcharacterImage.setImageBitmap(bmp);
        start.startAnimation(expansionStart);

        //1秒後にポッポアップ画像を自動消去。
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                start.setImageDrawable(null);
                popUpcharacterImage.setImageDrawable(null);
            }
        }, 1000);

        // 盤面を表すHashMapの初期化。
        // ＊は空白マスを表す。
        // 全マスに＊をセットする。
        for (int i = 0; i < 64; i++) {
            s.stoneMap.put(i, '*');
        }
        //盤面に初期配置の石を4つ置く
        // HashMapの盤面中央に
        // 白黒
        // 黒白
        // をセット
        s.stoneMap.put(27, '白');
        s.stoneMap.put(28, '黒');
        s.stoneMap.put(35, '黒');
        s.stoneMap.put(36, '白');

        //ここからビューへの反映処理。長い。
        ImageView iv2 = findViewById(R.id.imageView2);
        ImageView iv4 = findViewById(R.id.imageView4);
        ImageView iv6 = findViewById(R.id.imageView6);
        ImageView iv8 = findViewById(R.id.imageView8);
        ImageView iv10 = findViewById(R.id.imageView10);
        ImageView iv12 = findViewById(R.id.imageView12);
        ImageView iv14 = findViewById(R.id.imageView14);
        ImageView iv16 = findViewById(R.id.imageView16);
        ImageView iv18 = findViewById(R.id.imageView18);
        ImageView iv20 = findViewById(R.id.imageView20);
        ImageView iv22 = findViewById(R.id.imageView22);
        ImageView iv24 = findViewById(R.id.imageView24);
        ImageView iv26 = findViewById(R.id.imageView26);
        ImageView iv28 = findViewById(R.id.imageView28);
        ImageView iv30 = findViewById(R.id.imageView30);
        ImageView iv32 = findViewById(R.id.imageView32);
        ImageView iv34 = findViewById(R.id.imageView34);
        ImageView iv36 = findViewById(R.id.imageView36);
        ImageView iv38 = findViewById(R.id.imageView38);
        ImageView iv40 = findViewById(R.id.imageView40);
        ImageView iv42 = findViewById(R.id.imageView42);
        ImageView iv44 = findViewById(R.id.imageView44);
        ImageView iv46 = findViewById(R.id.imageView46);
        ImageView iv48 = findViewById(R.id.imageView48);
        ImageView iv50 = findViewById(R.id.imageView50);
        ImageView iv52 = findViewById(R.id.imageView52);
        ImageView iv54 = findViewById(R.id.imageView54);
        ImageView iv56 = findViewById(R.id.imageView56);
        final ImageView iv58 = findViewById(R.id.imageView58);
        ImageView iv60 = findViewById(R.id.imageView60);
        ImageView iv62 = findViewById(R.id.imageView62);
        ImageView iv64 = findViewById(R.id.imageView64);
        ImageView iv66 = findViewById(R.id.imageView66);
        ImageView iv68 = findViewById(R.id.imageView68);
        ImageView iv70 = findViewById(R.id.imageView70);
        final ImageView iv72 = findViewById(R.id.imageView72);
        final ImageView iv74 = findViewById(R.id.imageView74);
        ImageView iv76 = findViewById(R.id.imageView76);
        ImageView iv78 = findViewById(R.id.imageView78);
        ImageView iv80 = findViewById(R.id.imageView80);
        ImageView iv82 = findViewById(R.id.imageView82);
        ImageView iv84 = findViewById(R.id.imageView84);
        ImageView iv86 = findViewById(R.id.imageView86);
        ImageView iv88 = findViewById(R.id.imageView88);
        ImageView iv90 = findViewById(R.id.imageView90);
        ImageView iv92 = findViewById(R.id.imageView92);
        ImageView iv94 = findViewById(R.id.imageView94);
        ImageView iv96 = findViewById(R.id.imageView96);
        ImageView iv98 = findViewById(R.id.imageView98);
        ImageView iv100 = findViewById(R.id.imageView100);
        ImageView iv102 = findViewById(R.id.imageView102);
        ImageView iv104 = findViewById(R.id.imageView104);
        ImageView iv106 = findViewById(R.id.imageView106);
        ImageView iv108 = findViewById(R.id.imageView108);
        ImageView iv110 = findViewById(R.id.imageView110);
        ImageView iv112 = findViewById(R.id.imageView112);
        ImageView iv114 = findViewById(R.id.imageView114);
        ImageView iv116 = findViewById(R.id.imageView116);
        ImageView iv118 = findViewById(R.id.imageView118);
        ImageView iv120 = findViewById(R.id.imageView120);
        ImageView iv122 = findViewById(R.id.imageView122);
        ImageView iv124 = findViewById(R.id.imageView124);
        ImageView iv126 = findViewById(R.id.imageView126);
        ImageView iv128 = findViewById(R.id.imageView128);
        iv2.setImageDrawable(null);
        iv4.setImageDrawable(null);
        iv6.setImageDrawable(null);
        iv8.setImageDrawable(null);
        iv10.setImageDrawable(null);
        iv12.setImageDrawable(null);
        iv14.setImageDrawable(null);
        iv16.setImageDrawable(null);
        iv18.setImageDrawable(null);
        iv20.setImageDrawable(null);
        iv22.setImageDrawable(null);
        iv24.setImageDrawable(null);
        iv26.setImageDrawable(null);
        iv28.setImageDrawable(null);
        iv30.setImageDrawable(null);
        iv32.setImageDrawable(null);
        iv34.setImageDrawable(null);
        iv36.setImageDrawable(null);
        iv38.setImageDrawable(null);
        iv40.setImageDrawable(null);
        iv42.setImageDrawable(null);
        iv44.setImageDrawable(null);
        iv46.setImageDrawable(null);
        iv48.setImageDrawable(null);
        iv50.setImageDrawable(null);
        iv52.setImageDrawable(null);
        iv54.setImageDrawable(null);
        iv56.setImageDrawable(null);
        iv58.setImageDrawable(null);
        iv60.setImageDrawable(null);
        iv62.setImageDrawable(null);
        iv64.setImageDrawable(null);
        iv66.setImageDrawable(null);
        iv68.setImageDrawable(null);
        iv70.setImageDrawable(null);
        iv72.setImageDrawable(null);
        iv74.setImageDrawable(null);
        iv76.setImageDrawable(null);
        iv78.setImageDrawable(null);
        iv80.setImageDrawable(null);
        iv82.setImageDrawable(null);
        iv84.setImageDrawable(null);
        iv86.setImageDrawable(null);
        iv88.setImageDrawable(null);
        iv90.setImageDrawable(null);
        iv92.setImageDrawable(null);
        iv94.setImageDrawable(null);
        iv96.setImageDrawable(null);
        iv98.setImageDrawable(null);
        iv100.setImageDrawable(null);
        iv102.setImageDrawable(null);
        iv104.setImageDrawable(null);
        iv106.setImageDrawable(null);
        iv108.setImageDrawable(null);
        iv110.setImageDrawable(null);
        iv112.setImageDrawable(null);
        iv114.setImageDrawable(null);
        iv116.setImageDrawable(null);
        iv118.setImageDrawable(null);
        iv120.setImageDrawable(null);
        iv122.setImageDrawable(null);
        iv124.setImageDrawable(null);
        iv126.setImageDrawable(null);
        iv128.setImageDrawable(null);

        //0.1秒の間を空けて1個ずつ石をセット
        iv56.setImageResource(R.drawable.w);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                iv58.setImageResource(R.drawable.k);
            }
        }, 100);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                iv72.setImageResource(R.drawable.k);
            }
        }, 200);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                iv74.setImageResource(R.drawable.w);
            }
        }, 300);
    }


    //盤面がタップされた時の処理
    Random random = new Random();//CPUの指し手用

    public void btnCurrent_onClick(View view) throws InterruptedException {
        /*
        ・処理の流れ
            どのマス目がタップされたか判定をする
            そこに石が置けるか判定。
            true→
                HashMapの盤面で石置き＆ひっくり返しをする
                ArrayListに石置き＆ひっくり返す座標を格納
                ArrayListを元に画面上の石を反映
             false→
                そこに石を置けませんメッセージを表示する。
         */

        //オセロ盤のImageViewをあらかじめ取得しておく
        final ImageView iv1 = findViewById(R.id.imageView1);
        final ImageView iv3 = findViewById(R.id.imageView3);
        final ImageView iv5 = findViewById(R.id.imageView5);
        final ImageView iv7 = findViewById(R.id.imageView7);
        final ImageView iv9 = findViewById(R.id.imageView9);
        final ImageView iv11 = findViewById(R.id.imageView11);
        final ImageView iv13 = findViewById(R.id.imageView13);
        final ImageView iv15 = findViewById(R.id.imageView15);
        final ImageView iv17 = findViewById(R.id.imageView17);
        final ImageView iv19 = findViewById(R.id.imageView19);
        final ImageView iv21 = findViewById(R.id.imageView21);
        final ImageView iv23 = findViewById(R.id.imageView23);
        final ImageView iv25 = findViewById(R.id.imageView25);
        final ImageView iv27 = findViewById(R.id.imageView27);
        final ImageView iv29 = findViewById(R.id.imageView29);
        final ImageView iv31 = findViewById(R.id.imageView31);
        final ImageView iv33 = findViewById(R.id.imageView33);
        final ImageView iv35 = findViewById(R.id.imageView35);
        final ImageView iv37 = findViewById(R.id.imageView37);
        final ImageView iv39 = findViewById(R.id.imageView39);
        final ImageView iv41 = findViewById(R.id.imageView41);
        final ImageView iv43 = findViewById(R.id.imageView43);
        final ImageView iv45 = findViewById(R.id.imageView45);
        final ImageView iv47 = findViewById(R.id.imageView47);
        final ImageView iv49 = findViewById(R.id.imageView49);
        final ImageView iv51 = findViewById(R.id.imageView51);
        final ImageView iv53 = findViewById(R.id.imageView53);
        final ImageView iv55 = findViewById(R.id.imageView55);
        final ImageView iv57 = findViewById(R.id.imageView57);
        final ImageView iv59 = findViewById(R.id.imageView59);
        final ImageView iv61 = findViewById(R.id.imageView61);
        final ImageView iv63 = findViewById(R.id.imageView63);
        final ImageView iv65 = findViewById(R.id.imageView65);
        final ImageView iv67 = findViewById(R.id.imageView67);
        final ImageView iv69 = findViewById(R.id.imageView69);
        final ImageView iv71 = findViewById(R.id.imageView71);
        final ImageView iv73 = findViewById(R.id.imageView73);
        final ImageView iv75 = findViewById(R.id.imageView75);
        final ImageView iv77 = findViewById(R.id.imageView77);
        final ImageView iv79 = findViewById(R.id.imageView79);
        final ImageView iv81 = findViewById(R.id.imageView81);
        final ImageView iv83 = findViewById(R.id.imageView83);
        final ImageView iv85 = findViewById(R.id.imageView85);
        final ImageView iv87 = findViewById(R.id.imageView87);
        final ImageView iv89 = findViewById(R.id.imageView89);
        final ImageView iv91 = findViewById(R.id.imageView91);
        final ImageView iv93 = findViewById(R.id.imageView93);
        final ImageView iv95 = findViewById(R.id.imageView95);
        final ImageView iv97 = findViewById(R.id.imageView97);
        final ImageView iv99 = findViewById(R.id.imageView99);
        final ImageView iv101 = findViewById(R.id.imageView101);
        final ImageView iv103 = findViewById(R.id.imageView103);
        final ImageView iv105 = findViewById(R.id.imageView105);
        final ImageView iv107 = findViewById(R.id.imageView107);
        final ImageView iv109 = findViewById(R.id.imageView109);
        final ImageView iv111 = findViewById(R.id.imageView111);
        final ImageView iv113 = findViewById(R.id.imageView113);
        final ImageView iv115 = findViewById(R.id.imageView115);
        final ImageView iv117 = findViewById(R.id.imageView117);
        final ImageView iv119 = findViewById(R.id.imageView119);
        final ImageView iv121 = findViewById(R.id.imageView121);
        final ImageView iv123 = findViewById(R.id.imageView123);
        final ImageView iv125 = findViewById(R.id.imageView125);
        final ImageView iv127 = findViewById(R.id.imageView127);


        //アニメーション定義ファイルをロード
        anim = AnimationUtils.loadAnimation(this, R.anim.anim);

        // どのマス目がタップされたかを判定し、Stoneクラスにマス目座標と石の色を渡す。
        switch (view.getId()) {
            case R.id.imageView1:
                s.setStone(color, 0);
                break;
            case R.id.imageView3:
                s.setStone(color, 1);
                break;
            case R.id.imageView5:
                s.setStone(color, 2);
                break;
            case R.id.imageView7:
                s.setStone(color, 3);
                break;
            case R.id.imageView9:
                s.setStone(color, 4);
                break;
            case R.id.imageView11:
                s.setStone(color, 5);
                break;
            case R.id.imageView13:
                s.setStone(color, 6);
                break;
            case R.id.imageView15:
                s.setStone(color, 7);
                break;
            case R.id.imageView17:
                s.setStone(color, 8);
                break;
            case R.id.imageView19:
                s.setStone(color, 9);
                break;
            case R.id.imageView21:
                s.setStone(color, 10);
                break;
            case R.id.imageView23:
                s.setStone(color, 11);
                break;
            case R.id.imageView25:
                s.setStone(color, 12);
                break;
            case R.id.imageView27:
                s.setStone(color, 13);
                break;
            case R.id.imageView29:
                s.setStone(color, 14);
                break;
            case R.id.imageView31:
                s.setStone(color, 15);
                break;
            case R.id.imageView33:
                s.setStone(color, 16);
                break;
            case R.id.imageView35:
                s.setStone(color, 17);
                break;
            case R.id.imageView37:
                s.setStone(color, 18);
                break;
            case R.id.imageView39:
                s.setStone(color, 19);
                break;
            case R.id.imageView41:
                s.setStone(color, 20);
                break;
            case R.id.imageView43:
                s.setStone(color, 21);
                break;
            case R.id.imageView45:
                s.setStone(color, 22);
                break;
            case R.id.imageView47:
                s.setStone(color, 23);
                break;
            case R.id.imageView49:
                s.setStone(color, 24);
                break;
            case R.id.imageView51:
                s.setStone(color, 25);
                break;
            case R.id.imageView53:
                s.setStone(color, 26);
                break;
            case R.id.imageView55:
                s.setStone(color, 27);
                break;
            case R.id.imageView57:
                s.setStone(color, 28);
                break;
            case R.id.imageView59:
                s.setStone(color, 29);
                break;
            case R.id.imageView61:
                s.setStone(color, 30);
                break;
            case R.id.imageView63:
                s.setStone(color, 31);
                break;
            case R.id.imageView65:
                s.setStone(color, 32);
                break;
            case R.id.imageView67:
                s.setStone(color, 33);
                break;
            case R.id.imageView69:
                s.setStone(color, 34);
                break;
            case R.id.imageView71:
                s.setStone(color, 35);
                break;
            case R.id.imageView73:
                s.setStone(color, 36);
                break;
            case R.id.imageView75:
                s.setStone(color, 37);
                break;
            case R.id.imageView77:
                s.setStone(color, 38);
                break;
            case R.id.imageView79:
                s.setStone(color, 39);
                break;
            case R.id.imageView81:
                s.setStone(color, 40);
                break;
            case R.id.imageView83:
                s.setStone(color, 41);
                break;
            case R.id.imageView85:
                s.setStone(color, 42);
                break;
            case R.id.imageView87:
                s.setStone(color, 43);
                break;
            case R.id.imageView89:
                s.setStone(color, 44);
                break;
            case R.id.imageView91:
                s.setStone(color, 45);
                break;
            case R.id.imageView93:
                s.setStone(color, 46);
                break;
            case R.id.imageView95:
                s.setStone(color, 47);
                break;
            case R.id.imageView97:
                s.setStone(color, 48);
                break;
            case R.id.imageView99:
                s.setStone(color, 49);
                break;
            case R.id.imageView101:
                s.setStone(color, 50);
                break;
            case R.id.imageView103:
                s.setStone(color, 51);
                break;
            case R.id.imageView105:
                s.setStone(color, 52);
                break;
            case R.id.imageView107:
                s.setStone(color, 53);
                break;
            case R.id.imageView109:
                s.setStone(color, 54);
                break;
            case R.id.imageView111:
                s.setStone(color, 55);
                break;
            case R.id.imageView113:
                s.setStone(color, 56);
                break;
            case R.id.imageView115:
                s.setStone(color, 57);
                break;
            case R.id.imageView117:
                s.setStone(color, 58);
                break;
            case R.id.imageView119:
                s.setStone(color, 59);
                break;
            case R.id.imageView121:
                s.setStone(color, 60);
                break;
            case R.id.imageView123:
                s.setStone(color, 61);
                break;
            case R.id.imageView125:
                s.setStone(color, 62);
                break;
            case R.id.imageView127:
                s.setStone(color, 63);
                break;
        }

        // タップした所に石を置けるかの判定をする。
        if (r.isPutOK(s) && r.isAllReverse(s)) {
            r.reverseStone(s); // 裏返し実行メソッド

            // 石を置く位置をマップとリストに格納
            s.stoneMap.put(s.position, color);
            r.reverseList.add(s.position);


            // 石のひっくり返しを画面上に反映させる処理。
            // ArrayList内に格納されている、ひっくり返す位置を全て取り出す。
            for (int i : r.reverseList) {
                switch (i) {
                    case 0:
                        img = findViewById(R.id.imageView2);
                        break;
                    case 1:
                        img = findViewById(R.id.imageView4);
                        break;
                    case 2:
                        img = findViewById(R.id.imageView6);
                        break;
                    case 3:
                        img = findViewById(R.id.imageView8);
                        break;
                    case 4:
                        img = findViewById(R.id.imageView10);
                        break;
                    case 5:
                        img = findViewById(R.id.imageView12);
                        break;
                    case 6:
                        img = findViewById(R.id.imageView14);
                        break;
                    case 7:
                        img = findViewById(R.id.imageView16);
                        break;
                    case 8:
                        img = findViewById(R.id.imageView18);
                        break;
                    case 9:
                        img = findViewById(R.id.imageView20);
                        break;
                    case 10:
                        img = findViewById(R.id.imageView22);
                        break;
                    case 11:
                        img = findViewById(R.id.imageView24);
                        break;
                    case 12:
                        img = findViewById(R.id.imageView26);
                        break;
                    case 13:
                        img = findViewById(R.id.imageView28);
                        break;
                    case 14:
                        img = findViewById(R.id.imageView30);
                        break;
                    case 15:
                        img = findViewById(R.id.imageView32);
                        break;
                    case 16:
                        img = findViewById(R.id.imageView34);
                        break;
                    case 17:
                        img = findViewById(R.id.imageView36);
                        break;
                    case 18:
                        img = findViewById(R.id.imageView38);
                        break;
                    case 19:
                        img = findViewById(R.id.imageView40);
                        break;
                    case 20:
                        img = findViewById(R.id.imageView42);
                        break;
                    case 21:
                        img = findViewById(R.id.imageView44);
                        break;
                    case 22:
                        img = findViewById(R.id.imageView46);
                        break;
                    case 23:
                        img = findViewById(R.id.imageView48);
                        break;
                    case 24:
                        img = findViewById(R.id.imageView50);
                        break;
                    case 25:
                        img = findViewById(R.id.imageView52);
                        break;
                    case 26:
                        img = findViewById(R.id.imageView54);
                        break;
                    case 27:
                        img = findViewById(R.id.imageView56);
                        break;
                    case 28:
                        img = findViewById(R.id.imageView58);
                        break;
                    case 29:
                        img = findViewById(R.id.imageView60);
                        break;
                    case 30:
                        img = findViewById(R.id.imageView62);
                        break;
                    case 31:
                        img = findViewById(R.id.imageView64);
                        break;
                    case 32:
                        img = findViewById(R.id.imageView66);
                        break;
                    case 33:
                        img = findViewById(R.id.imageView68);
                        break;
                    case 34:
                        img = findViewById(R.id.imageView70);
                        break;
                    case 35:
                        img = findViewById(R.id.imageView72);
                        break;
                    case 36:
                        img = findViewById(R.id.imageView74);
                        break;
                    case 37:
                        img = findViewById(R.id.imageView76);
                        break;
                    case 38:
                        img = findViewById(R.id.imageView78);
                        break;
                    case 39:
                        img = findViewById(R.id.imageView80);
                        break;
                    case 40:
                        img = findViewById(R.id.imageView82);
                        break;
                    case 41:
                        img = findViewById(R.id.imageView84);
                        break;
                    case 42:
                        img = findViewById(R.id.imageView86);
                        break;
                    case 43:
                        img = findViewById(R.id.imageView88);
                        break;
                    case 44:
                        img = findViewById(R.id.imageView90);
                        break;
                    case 45:
                        img = findViewById(R.id.imageView92);
                        break;
                    case 46:
                        img = findViewById(R.id.imageView94);
                        break;
                    case 47:
                        img = findViewById(R.id.imageView96);
                        break;
                    case 48:
                        img = findViewById(R.id.imageView98);
                        break;
                    case 49:
                        img = findViewById(R.id.imageView100);
                        break;
                    case 50:
                        img = findViewById(R.id.imageView102);
                        break;
                    case 51:
                        img = findViewById(R.id.imageView104);
                        break;
                    case 52:
                        img = findViewById(R.id.imageView106);
                        break;
                    case 53:
                        img = findViewById(R.id.imageView108);
                        break;
                    case 54:
                        img = findViewById(R.id.imageView110);
                        break;
                    case 55:
                        img = findViewById(R.id.imageView112);
                        break;
                    case 56:
                        img = findViewById(R.id.imageView114);
                        break;
                    case 57:
                        img = findViewById(R.id.imageView116);
                        break;
                    case 58:
                        img = findViewById(R.id.imageView118);
                        break;
                    case 59:
                        img = findViewById(R.id.imageView120);
                        break;
                    case 60:
                        img = findViewById(R.id.imageView122);
                        break;
                    case 61:
                        img = findViewById(R.id.imageView124);
                        break;
                    case 62:
                        img = findViewById(R.id.imageView126);
                        break;
                    case 63:
                        img = findViewById(R.id.imageView128);
                        break;
                }
                img.setImageResource(R.drawable.k);
                img.startAnimation(anim);//石を置くときのアニメーション
            }


            //キャラクターのセリフ
            characterTxt = findViewById(R.id.characterText);
            characterTxt.setText("私の番だね！");
            if (r.reverseList.size() >= 6) {
                characterTxt.setText("すごい！大量ゲット！");
            }

            r.reverseList.clear();//裏返し完了したので裏返すリストを全消去

            //石の枚数を数える処理
            blackCount = 0;
            whiteCount = 0;
            for (Character c : s.stoneMap.values()) {
                if (c == '黒') {
                    blackCount++;
                } else if (c == '白') {
                    whiteCount++;
                }
            }
            // 現在の石の枚数をTextViewにセット
            String stoneCount = "黒×" + blackCount + "　白×" + whiteCount;
            TextView txt = findViewById(R.id.text);
            txt.setText(stoneCount);

            //勝敗判定
            //全マス埋まったor置ける場所がない
            if (blackCount + whiteCount == 64 | r.isCheckEnd(s, '白')) {
                ImageView end = findViewById(R.id.popUpImage);
                ImageView popUpcharacterImage = findViewById(R.id.popUpcharacterImage);
                final TextView endText = findViewById(R.id.endText);
                Bitmap bmp = null;
                try {
                    bmp = cs.getBmp();
                } catch (NullPointerException e) {
                    System.out.println("画像未取得です。");
                }
                end.setImageResource(R.drawable.syoubu);
                popUpcharacterImage.setImageBitmap(bmp);
                end.startAnimation(expansionEnd);
                popUpcharacterImage.startAnimation(alphaEnd);
                endText.setText(stoneCount);

                if (blackCount > whiteCount) {
                    characterTxt.setText("負けちゃった。強いんだね！");
                    Toast.makeText(this, "あなたの勝ちです！", Toast.LENGTH_LONG).show();
                } else if (whiteCount > blackCount) {
                    characterTxt.setText("勝ったー！遊んでくれてありがとね。");
                    Toast.makeText(this, "あなたの負けです。", Toast.LENGTH_LONG).show();
                } else {
                    characterTxt.setText("引き分けだね！！もうひと勝負しよう！");
                    Toast.makeText(this, "引き分けです。", Toast.LENGTH_LONG).show();
                }
                return;//処理を終了する
            }
            color = '白';//白番に変更

        } else {
            Toast.makeText(this, "そこには置けません(> <)", Toast.LENGTH_SHORT).show();
            return;//処理を終了する
        }

        //CPUが指し終わるまでオセロ盤をタップ不可にする。
        //途中でタップするとバグってしまうため。
        iv1.setEnabled(false);
        iv3.setEnabled(false);
        iv5.setEnabled(false);
        iv7.setEnabled(false);
        iv9.setEnabled(false);
        iv11.setEnabled(false);
        iv13.setEnabled(false);
        iv15.setEnabled(false);
        iv17.setEnabled(false);
        iv19.setEnabled(false);
        iv21.setEnabled(false);
        iv23.setEnabled(false);
        iv25.setEnabled(false);
        iv27.setEnabled(false);
        iv29.setEnabled(false);
        iv31.setEnabled(false);
        iv33.setEnabled(false);
        iv35.setEnabled(false);
        iv37.setEnabled(false);
        iv39.setEnabled(false);
        iv41.setEnabled(false);
        iv43.setEnabled(false);
        iv45.setEnabled(false);
        iv47.setEnabled(false);
        iv49.setEnabled(false);
        iv51.setEnabled(false);
        iv53.setEnabled(false);
        iv55.setEnabled(false);
        iv57.setEnabled(false);
        iv59.setEnabled(false);
        iv61.setEnabled(false);
        iv63.setEnabled(false);
        iv65.setEnabled(false);
        iv67.setEnabled(false);
        iv69.setEnabled(false);
        iv71.setEnabled(false);
        iv73.setEnabled(false);
        iv75.setEnabled(false);
        iv77.setEnabled(false);
        iv79.setEnabled(false);
        iv81.setEnabled(false);
        iv83.setEnabled(false);
        iv85.setEnabled(false);
        iv87.setEnabled(false);
        iv89.setEnabled(false);
        iv91.setEnabled(false);
        iv93.setEnabled(false);
        iv95.setEnabled(false);
        iv97.setEnabled(false);
        iv99.setEnabled(false);
        iv101.setEnabled(false);
        iv103.setEnabled(false);
        iv105.setEnabled(false);
        iv107.setEnabled(false);
        iv109.setEnabled(false);
        iv111.setEnabled(false);
        iv113.setEnabled(false);
        iv115.setEnabled(false);
        iv117.setEnabled(false);
        iv119.setEnabled(false);
        iv121.setEnabled(false);
        iv123.setEnabled(false);
        iv125.setEnabled(false);
        iv127.setEnabled(false);


        //ここからCpu
        //時間差でビューに反映したいのでHandlerを利用。
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                while (color == '白') {
                    //ランダムで盤面に石を置く
                    //置ける場所に当たるまで繰り返す
                    s.setStone(color, random.nextInt(64));

                    if (r.isPutOK(s) && r.isAllReverse(s)) { // そこに石を置けるかの判定をする。
                        r.reverseStone(s); // 裏返し実行メソッド

                        // 石を置く位置をマップとリストに格納
                        s.stoneMap.put(s.position, color);
                        r.reverseList.add(s.position);

                        // 石のひっくり返しを画面上に反映させる処理。
                        // ArrayList内に格納されている、ひっくり返す位置を全て取り出す。
                        for (int i : r.reverseList) {
                            switch (i) {
                                case 0:
                                    img = findViewById(R.id.imageView2);
                                    break;
                                case 1:
                                    img = findViewById(R.id.imageView4);
                                    break;
                                case 2:
                                    img = findViewById(R.id.imageView6);
                                    break;
                                case 3:
                                    img = findViewById(R.id.imageView8);
                                    break;
                                case 4:
                                    img = findViewById(R.id.imageView10);
                                    break;
                                case 5:
                                    img = findViewById(R.id.imageView12);
                                    break;
                                case 6:
                                    img = findViewById(R.id.imageView14);
                                    break;
                                case 7:
                                    img = findViewById(R.id.imageView16);
                                    break;
                                case 8:
                                    img = findViewById(R.id.imageView18);
                                    break;
                                case 9:
                                    img = findViewById(R.id.imageView20);
                                    break;
                                case 10:
                                    img = findViewById(R.id.imageView22);
                                    break;
                                case 11:
                                    img = findViewById(R.id.imageView24);
                                    break;
                                case 12:
                                    img = findViewById(R.id.imageView26);
                                    break;
                                case 13:
                                    img = findViewById(R.id.imageView28);
                                    break;
                                case 14:
                                    img = findViewById(R.id.imageView30);
                                    break;
                                case 15:
                                    img = findViewById(R.id.imageView32);
                                    break;
                                case 16:
                                    img = findViewById(R.id.imageView34);
                                    break;
                                case 17:
                                    img = findViewById(R.id.imageView36);
                                    break;
                                case 18:
                                    img = findViewById(R.id.imageView38);
                                    break;
                                case 19:
                                    img = findViewById(R.id.imageView40);
                                    break;
                                case 20:
                                    img = findViewById(R.id.imageView42);
                                    break;
                                case 21:
                                    img = findViewById(R.id.imageView44);
                                    break;
                                case 22:
                                    img = findViewById(R.id.imageView46);
                                    break;
                                case 23:
                                    img = findViewById(R.id.imageView48);
                                    break;
                                case 24:
                                    img = findViewById(R.id.imageView50);
                                    break;
                                case 25:
                                    img = findViewById(R.id.imageView52);
                                    break;
                                case 26:
                                    img = findViewById(R.id.imageView54);
                                    break;
                                case 27:
                                    img = findViewById(R.id.imageView56);
                                    break;
                                case 28:
                                    img = findViewById(R.id.imageView58);
                                    break;
                                case 29:
                                    img = findViewById(R.id.imageView60);
                                    break;
                                case 30:
                                    img = findViewById(R.id.imageView62);
                                    break;
                                case 31:
                                    img = findViewById(R.id.imageView64);
                                    break;
                                case 32:
                                    img = findViewById(R.id.imageView66);
                                    break;
                                case 33:
                                    img = findViewById(R.id.imageView68);
                                    break;
                                case 34:
                                    img = findViewById(R.id.imageView70);
                                    break;
                                case 35:
                                    img = findViewById(R.id.imageView72);
                                    break;
                                case 36:
                                    img = findViewById(R.id.imageView74);
                                    break;
                                case 37:
                                    img = findViewById(R.id.imageView76);
                                    break;
                                case 38:
                                    img = findViewById(R.id.imageView78);
                                    break;
                                case 39:
                                    img = findViewById(R.id.imageView80);
                                    break;
                                case 40:
                                    img = findViewById(R.id.imageView82);
                                    break;
                                case 41:
                                    img = findViewById(R.id.imageView84);
                                    break;
                                case 42:
                                    img = findViewById(R.id.imageView86);
                                    break;
                                case 43:
                                    img = findViewById(R.id.imageView88);
                                    break;
                                case 44:
                                    img = findViewById(R.id.imageView90);
                                    break;
                                case 45:
                                    img = findViewById(R.id.imageView92);
                                    break;
                                case 46:
                                    img = findViewById(R.id.imageView94);
                                    break;
                                case 47:
                                    img = findViewById(R.id.imageView96);
                                    break;
                                case 48:
                                    img = findViewById(R.id.imageView98);
                                    break;
                                case 49:
                                    img = findViewById(R.id.imageView100);
                                    break;
                                case 50:
                                    img = findViewById(R.id.imageView102);
                                    break;
                                case 51:
                                    img = findViewById(R.id.imageView104);
                                    break;
                                case 52:
                                    img = findViewById(R.id.imageView106);
                                    break;
                                case 53:
                                    img = findViewById(R.id.imageView108);
                                    break;
                                case 54:
                                    img = findViewById(R.id.imageView110);
                                    break;
                                case 55:
                                    img = findViewById(R.id.imageView112);
                                    break;
                                case 56:
                                    img = findViewById(R.id.imageView114);
                                    break;
                                case 57:
                                    img = findViewById(R.id.imageView116);
                                    break;
                                case 58:
                                    img = findViewById(R.id.imageView118);
                                    break;
                                case 59:
                                    img = findViewById(R.id.imageView120);
                                    break;
                                case 60:
                                    img = findViewById(R.id.imageView122);
                                    break;
                                case 61:
                                    img = findViewById(R.id.imageView124);
                                    break;
                                case 62:
                                    img = findViewById(R.id.imageView126);
                                    break;
                                case 63:
                                    img = findViewById(R.id.imageView128);
                                    break;
                            }
                            img.setImageResource(R.drawable.w);
                            img.startAnimation(anim);
                        }

                        //キャラクターのセリフ
                        characterTxt = findViewById(R.id.characterText);
                        String[] characterString = {"あなたの番です。", "いい勝負！", "いけそう！", "楽しいね♪"};
                        characterTxt.setText(characterString[random.nextInt(4)]);
                        if (r.reverseList.size() >= 6) {
                            characterTxt.setText("すごい！大量ゲット！");
                        }

                        r.reverseList.clear();

                        blackCount = 0;
                        whiteCount = 0;
                        for (Character c : s.stoneMap.values()) {
                            if (c == '黒') {
                                blackCount++;
                            } else if (c == '白') {
                                whiteCount++;
                            }
                        }
                        // 石の枚数をTextViewにセット
                        TextView txt = findViewById(R.id.text);
                        String stoneCount = "黒×" + blackCount + "　白×" + whiteCount;
                        txt.setText(stoneCount);

                        //勝敗判定。全マス埋まった場合
                        if (blackCount + whiteCount == 64 | r.isCheckEnd(s, '黒')) {
                            ImageView end = findViewById(R.id.popUpImage);
                            ImageView popUpcharacterImage = findViewById(R.id.popUpcharacterImage);
                            final TextView endText = findViewById(R.id.endText);
                            Bitmap bmp = null;
                            try {
                                bmp = cs.getBmp();
                            } catch (NullPointerException e) {
                                System.out.println("画像未取得です。");
                            }
                            end.setImageResource(R.drawable.syoubu);
                            popUpcharacterImage.setImageBitmap(bmp);
                            end.startAnimation(expansionEnd);
                            popUpcharacterImage.startAnimation(alphaEnd);
                            endText.setText(stoneCount);

                            if (blackCount > whiteCount) {
                                characterTxt.setText("負けちゃった。強いんだね！");
                                Toast.makeText(MainActivity.this, "あなたの勝ちです。", Toast.LENGTH_LONG).show();
                            } else if (whiteCount > blackCount) {
                                characterTxt.setText("勝ったー！遊んでくれてありがとね。");
                                Toast.makeText(MainActivity.this, "あなたの負けです。", Toast.LENGTH_LONG).show();
                            } else {
                                characterTxt.setText("引き分けだね！！もうひと勝負しよう！");
                                Toast.makeText(MainActivity.this, "引き分けです。", Toast.LENGTH_LONG).show();
                            }
                        }

                        color = '黒';//黒番に変更

                        iv1.setEnabled(true);
                        iv3.setEnabled(true);
                        iv5.setEnabled(true);
                        iv7.setEnabled(true);
                        iv9.setEnabled(true);
                        iv11.setEnabled(true);
                        iv13.setEnabled(true);
                        iv15.setEnabled(true);
                        iv17.setEnabled(true);
                        iv19.setEnabled(true);
                        iv21.setEnabled(true);
                        iv23.setEnabled(true);
                        iv25.setEnabled(true);
                        iv27.setEnabled(true);
                        iv29.setEnabled(true);
                        iv31.setEnabled(true);
                        iv33.setEnabled(true);
                        iv35.setEnabled(true);
                        iv37.setEnabled(true);
                        iv39.setEnabled(true);
                        iv41.setEnabled(true);
                        iv43.setEnabled(true);
                        iv45.setEnabled(true);
                        iv47.setEnabled(true);
                        iv49.setEnabled(true);
                        iv51.setEnabled(true);
                        iv53.setEnabled(true);
                        iv55.setEnabled(true);
                        iv57.setEnabled(true);
                        iv59.setEnabled(true);
                        iv61.setEnabled(true);
                        iv63.setEnabled(true);
                        iv65.setEnabled(true);
                        iv67.setEnabled(true);
                        iv69.setEnabled(true);
                        iv71.setEnabled(true);
                        iv73.setEnabled(true);
                        iv75.setEnabled(true);
                        iv77.setEnabled(true);
                        iv79.setEnabled(true);
                        iv81.setEnabled(true);
                        iv83.setEnabled(true);
                        iv85.setEnabled(true);
                        iv87.setEnabled(true);
                        iv89.setEnabled(true);
                        iv91.setEnabled(true);
                        iv93.setEnabled(true);
                        iv95.setEnabled(true);
                        iv97.setEnabled(true);
                        iv99.setEnabled(true);
                        iv101.setEnabled(true);
                        iv103.setEnabled(true);
                        iv105.setEnabled(true);
                        iv107.setEnabled(true);
                        iv109.setEnabled(true);
                        iv111.setEnabled(true);
                        iv113.setEnabled(true);
                        iv115.setEnabled(true);
                        iv117.setEnabled(true);
                        iv119.setEnabled(true);
                        iv121.setEnabled(true);
                        iv123.setEnabled(true);
                        iv125.setEnabled(true);
                        iv127.setEnabled(true);
                    }
                }
            }
        }, 2000);
    }


    //ポップアップ画像をクリックすると画像消去
    public void frame_onClick(View view) {
        ImageView startEnd = findViewById(R.id.popUpImage);
        ImageView popUpcharacterImage = findViewById(R.id.popUpcharacterImage);
        TextView endText = findViewById(R.id.endText);
        startEnd.setImageDrawable(null);
        popUpcharacterImage.setImageDrawable(null);
        endText.setText(null);
    }
}