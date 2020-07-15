package to.msn.wings.othello;

        import java.util.HashMap;
        import java.util.Map;

public class Stone {
    char color;
    int position;
    Map<Integer, Character> stoneMap = new HashMap<>(); // 石の場所と色を格納するmap

    // 石の色と位置をセット
    void setStone(int numberOfTurns, int position) {
        if(numberOfTurns == -1) {
            this.color = '*'; // 初期配置用
        } else if(numberOfTurns % 2 == 0) {
            this.color = '白';
        } else {
            this.color = '黒';
        }
        this.position = position;
        stoneMap.put(position, color);
    }
}