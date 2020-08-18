package to.msn.wings.othello;
import java.util.HashMap;
import java.util.Map;


public class Stone {
    char color;
    int position;

    /*
   オセロ盤を表すHashMap
   キーが座標。
   値が石の色
   値には「黒」「白」「＊」が入る。
   「＊」は石が置かれていない空白マスを表す。
     */

    Map<Integer, Character> stoneMap = new HashMap<>(); //
    // 石の色と位置をセット
    void setStone(char color, int position) {
        this.position = position;
        this.color = color;
    }
}