package to.msn.wings.othello;

// 盤面を生成するクラス
public class Board {
    int numberOfLinesInBoard; // 盤面の行数。正方形。
    StringBuilder sb = new StringBuilder();
    StringBuilder sbBoard = new StringBuilder();
    // 盤面を文字列で生成。
    Board(int numberOfLinesInBoard) {
        if (numberOfLinesInBoard % 2 == 0) {
            this.numberOfLinesInBoard = numberOfLinesInBoard;
            // 盤面を*で表す。
            /*
             ****
             *白黒*
             *黒白*
             ****
             */
            for (int i = 0; i < numberOfLinesInBoard; i++) {
                sb.append("*");
            }
            //行数分だけ****を追加する処理。
            for (int i = 0; i < numberOfLinesInBoard; i++) {
                sbBoard.append(sb);
            }
            sb = null; // もう使わない。ガベージコレクションの動作を期待して。
            // 初期配置に石をセットする
            int center = (numberOfLinesInBoard * numberOfLinesInBoard / 2) - (numberOfLinesInBoard / 2) - 1;
            sbBoard.setCharAt(center, '白');
            sbBoard.setCharAt(center + 1, '黒');
            sbBoard.setCharAt(center + numberOfLinesInBoard, '黒');
            sbBoard.setCharAt(center + numberOfLinesInBoard + 1, '白');
        } else {
            System.out.println("盤面の行数は偶数を指定してください");
        }
    }
    // (x,y)座標を文字列順に変換して返す
    // x + (y　×　盤面１行の列数)
    int coordinateTransformation(int x, int y, Board b) {
        int result = (x - 1) + ((y - 1) * b.numberOfLinesInBoard);
        return result;
    }
    // 指定座標に石をセットする
    public void setStoneInBoard(Stone s, Board b) {
        int result = b.coordinateTransformation(s.x, s.y, b);
        sbBoard.setCharAt(result, s.color);
		/*for(int i = 0; i < numberOfLinesInBoard; i++) {
			System.out.println(sbBoard.substring(i*numberOfLinesInBoard, i*numberOfLinesInBoard+numberOfLinesInBoard));
		}*/
    }
    // 実行部分は後で別クラスに移す
    public static void main(String[] args) {
        // TODO 自動生成されたメソッド・スタブ
    }
}