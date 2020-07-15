package to.msn.wings.othello;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Board b = new Board(8);
    Stone s = new Stone();
    Reverse r = new Reverse();
    int numberOfTurns = 1; // 何ターン目かを表す。
    int flag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startOnClick(View view) {
        for (int i = 0; i < 64; i++) {
            s.setStone(-1, i);
        }
        s.setStone(0, 27);
        s.setStone(1, 28);
        s.setStone(0, 35);
        s.setStone(1, 36);

        ImageView initialPlacement1 = findViewById(R.id.imageView56);
        ImageView initialPlacement2 = findViewById(R.id.imageView58);
        ImageView initialPlacement3 = findViewById(R.id.imageVie72);
        ImageView initialPlacement4 = findViewById(R.id.imageView74);
        initialPlacement1.setImageResource(R.drawable.w);
        initialPlacement2.setImageResource(R.drawable.k);
        initialPlacement3.setImageResource(R.drawable.k);
        initialPlacement4.setImageResource(R.drawable.w);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void btnCurrent_onClick(View view) {

        /* Todo
        ・色について
            カラーはどりあえず黒。
            先手黒、後手白。
            カウントして奇数なら黒とか。

        ・処理の流れ
            どのボタンが押されたか判定をする
            そこに石が置けるか判定。
                true→ひっくり返す座標を返すメソッドを呼び出す→int flag = 1;
                →switch(flag)
                case 1:
                    ここでようやく石をセットするメソッド起動。
                    ※問題点はひっくり返す座標はいくつも出るはずだが、それをどうやって保管するか。
                    →ArrayList<int>でよさそう。intに座標持たせる。
                */
        switch (view.getId()) {
            case R.id.imageView1:
                // ↓判定前にセットしちゃダメよ。
                s.setStone(numberOfTurns, 0);
                flag = 0;
                break;

            case R.id.imageView3:
                s.setStone(numberOfTurns, 1);
                flag = 3;
                break;

            case R.id.imageView5:
                s.setStone(numberOfTurns, 2);
                flag = 5;
                break;
            case R.id.imageView7:
                break;
            case R.id.imageView9:
                break;
            case R.id.imageView11:
                break;

            case R.id.imageView13:
                break;

            case R.id.imageView15:
                break;

            case R.id.imageView17:
                break;

            case R.id.imageView19:
                break;

            case R.id.imageView21:
                break;

            case R.id.imageView23:
                break;

            case R.id.imageView25:
                break;

            case R.id.imageView27:
                break;

            case R.id.imageView29:
                break;

            case R.id.imageView31:
                break;

            case R.id.imageView33:
                break;

            case R.id.imageView35:
                break;

            case R.id.imageView37:
                break;

            case R.id.imageView39:
                break;

            case R.id.imageView41:
                break;

            case R.id.imageView43:
                break;

            case R.id.imageView45:
                break;

            case R.id.imageView47:
                break;

            case R.id.imageView49:
                break;

            case R.id.imageView51:
                break;

            case R.id.imageView53:
                s.setStone(numberOfTurns, 26);



                flag = 26;
                break;

            case R.id.imageView55:
                break;

            case R.id.imageView57:
                break;

            case R.id.imageView59:
                break;

            case R.id.imageView61:
                break;

            case R.id.imageView63:
                break;

            case R.id.imageView65:
                break;

            case R.id.imageView67:
                break;

            case R.id.imageView69:
                break;

            case R.id.imageView71:
                break;

            case R.id.imageView73:
                break;

            case R.id.imageView75:
                break;

            case R.id.imageView77:
                break;

            case R.id.imageView79:
                break;

            case R.id.imageView81:
                break;

            case R.id.imageView83:
                break;

            case R.id.imageView85:
                break;

            case R.id.imageView87:
                break;

            case R.id.imageView89:
                break;

            case R.id.imageView91:
                break;

            case R.id.imageView93:
                break;

            case R.id.imageView95:
                break;

            case R.id.imageView97:
                break;

            case R.id.imageView99:
                break;

            case R.id.imageView101:
                break;

            case R.id.imageView103:
                break;

            case R.id.imageView105:
                break;

            case R.id.imageView107:
                break;

            case R.id.imageView109:
                break;

            case R.id.imageView111:
                break;

            case R.id.imageView113:
                break;

            case R.id.imageView115:
                break;

            case R.id.imageView117:
                break;

            case R.id.imageView119:
                break;

            case R.id.imageView121:
                break;

            case R.id.imageView123:
                break;
        }

        //↓この部分はどのcaseも共通だから外に出してよい？
        if (r.isPutOK(s, b) && r.isAllReverse(s, b)) {
            b.setStoneInBoard(s, b);
            r.reverseStone(s, b);
            numberOfTurns++;
        } else {
            s.stoneMap.replace(flag, '*');
            System.out.println("そこには置けません(´;ω;｀)");
        }

    // for文で
        for(int i: r.reverseList) {
            switch(i) {
                case 27:
                    ImageView iv56 = findViewById(R.id.imageView56);
                    if(s.color == '黒') {
                        iv56.setImageResource(R.drawable.k);
                    } else {
                        iv56.setImageResource(R.drawable.w);
                    }


            }
        }

    }


}