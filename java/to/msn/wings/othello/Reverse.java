package to.msn.wings.othello;

// ひっくり返す判定をするクラス
public class Reverse {
    // 何枚ひっくり返せるかを表すint
    int numberOfRightReverse;
    int numberOfLeftReverse;
    int numberOfUpperReverse;
    int numberOfLowerReverse;
    int numberOfUpperRightReverse;
    int numberOfUpperLeftReverse;
    int numberOfLowerRightReverse;
    int numberOfLowerLeftReverse;
    boolean isPutOK(Stone s, Board b) {
        // StringBuilder文字列の中で先頭から何番目かを表す
        int numberFromBeginning = b.coordinateTransformation(s.x, s.y, b);
		/*int upper = b.coordinateTransformation(s.x, s.y - 1, b);
		int lower = b.coordinateTransformation(s.x, s.y + 1, b);
		int left = b.coordinateTransformation(s.x - 1, s.y, b);
		int right = b.coordinateTransformation(s.x + 1, s.y, b);*/
		/*char numberFromBeginningColor = s.color;
		char upperColor = b.sbBoard.charAt(upper);
		char lowerColor = b.sbBoard.charAt(lower);
		char leftColor = b.sbBoard.charAt(left);
		char rightColor = b.sbBoard.charAt(right);
		*/
        // 盤面の外はfalse
        // 置きたい場所にすでに駒があったらfalse
        if (s.x < 1 | s.x > b.numberOfLinesInBoard) {
            System.out.println("そこは盤面の外です");
            return false;
        } else if (s.y < 1 | s.y > b.numberOfLinesInBoard) {
            System.out.println("そこは盤面の外です");
            return false;
        } else if (b.sbBoard.charAt(numberFromBeginning) != '*') {
            System.out.println("そこにはすでに駒があります");
            return false;
        }
        System.out.println("盤面外でなく石もなし！");
        return true;
    }
    boolean isAllReverse(Stone s, Board b) {
        if (isRightReverse(s, b) | isLeftReverse(s, b) | isUpperReverse(s, b) | isLowerReverse(s, b) |
                isUpperRightReverse(s, b) | isUpperLeftReverse(s, b) | isLowerRightReverse(s, b)
                | isLowerLeftReverse(s, b)) {
            System.out.println("置けるで！");
            return true;
        }
        System.out.println("置けん(´;ω;｀)");
        return false;
    }
    // ひっくり返すメソッド
    void reverseStone(Stone s, Board b) {
        int result = b.coordinateTransformation(s.x, s.y, b);
        if(isRightReverse(s, b)) {
            for (int i = 1; i <= numberOfRightReverse; i++) {
                b.sbBoard.setCharAt(result+i, s.color);
            }
        }else if(isLeftReverse(s, b)) {
            for (int i = 1; i <= numberOfLeftReverse; i++) {
                b.sbBoard.setCharAt(result-i, s.color);
            }
        }else if(isUpperReverse(s, b)) {
            for (int i = 1; i <= numberOfUpperReverse; i++) {
                b.sbBoard.setCharAt(result-(i*b.numberOfLinesInBoard), s.color);
            }
        }else if(isLowerReverse(s, b)) {
            for (int i = 1; i <= numberOfLowerReverse; i++) {
                b.sbBoard.setCharAt(result+(i*b.numberOfLinesInBoard), s.color);
            }
        }else if(isUpperRightReverse(s, b)) {
            for (int i = 1; i <= numberOfUpperRightReverse; i++) {
                b.sbBoard.setCharAt(result+i-(i*b.numberOfLinesInBoard), s.color);
            }
        }else if(isUpperLeftReverse(s, b)) {
            for (int i = 1; i <= numberOfUpperLeftReverse; i++) {
                b.sbBoard.setCharAt(result-i-(i*b.numberOfLinesInBoard), s.color);
            }
        }else if(isLowerRightReverse(s, b)) {
            for (int i = 1; i <= numberOfLowerRightReverse; i++) {
                b.sbBoard.setCharAt(result+i+(i*b.numberOfLinesInBoard), s.color);
            }
        }else if(isLowerLeftReverse(s, b)) {
            for (int i = 1; i <= numberOfLowerLeftReverse; i++) {
                b.sbBoard.setCharAt(result-i+(i*b.numberOfLinesInBoard), s.color);
            }
        }
        for(int i = 0; i < b.numberOfLinesInBoard; i++) {
            System.out.println(b.sbBoard.substring(i*b.numberOfLinesInBoard, i*b.numberOfLinesInBoard+b.numberOfLinesInBoard));
        }
    }
    // 置いた石の右にひっくり返せる石があるかの判定
    boolean isRightReverse(Stone s, Board b) {
        int numberFromBeginning = b.coordinateTransformation(s.x, s.y, b);
        if (s.x == b.numberOfLinesInBoard) {
            System.out.println("右画面端ぃ！");
            return false;
        }else if (b.sbBoard.charAt(numberFromBeginning + 1) == '*') { // 隣が空白であれば処理を終了
            System.out.println("右隣が空白");
            return false;
        } else if (b.sbBoard.charAt(numberFromBeginning + 1) != s.color) { // 隣が自分と同じ色ではない
            for (int i = 1; s.x + i <= b.numberOfLinesInBoard; i++) { // なおかつさらに隣かさらにその先が自分と同じ色である
                if (b.sbBoard.charAt(numberFromBeginning + i) == s.color) {
                    System.out.println("右にとれる石あり");
                    numberOfRightReverse = i-1;
                    return true;
                }
            }
            System.out.println("右にとれる石なし");
            return false;
        }
        System.out.println("右が自分と同じ色");
        return false;
    }
    // 左にひっくり返せる石があるかの判定
    boolean isLeftReverse(Stone s, Board b) {
        int numberFromBeginning = b.coordinateTransformation(s.x, s.y, b);
        if (s.x == 1) {
            System.out.println("左画面端ぃ！");
            return false;
        }else if (b.sbBoard.charAt(numberFromBeginning - 1) == '*') { // 隣が空白であれば処理を終了
            System.out.println("左隣が空白");
            return false;
        } else if (b.sbBoard.charAt(numberFromBeginning - 1) != s.color) { // 隣が自分と同じ色ではない
            // なおかつさらに隣かさらにその先が自分と同じ色である
            for (int i = 1; s.x - i >= 1; i++) {
                if (b.sbBoard.charAt(numberFromBeginning - i) == s.color) {
                    System.out.println("左にとれる石あり");
                    numberOfLeftReverse = i-1;
                    return true;
                }
            }
            System.out.println("左にとれる石なし");
            return false;
        }
        System.out.println("左と同じ色");
        return false;
    }
    // 上にひっくり返せる石があるかの判定
    boolean isUpperReverse(Stone s, Board b) {
        int numberFromBeginning = b.coordinateTransformation(s.x, s.y, b);
        if (s.y == 1) {
            System.out.println("上画面端ぃ！");
            return false;
        }else if (b.sbBoard.charAt(numberFromBeginning - b.numberOfLinesInBoard) == '*') { // 隣が空白であれば処理を終了
            System.out.println("上隣が空白");
            return false;
        } else if (b.sbBoard.charAt(numberFromBeginning - b.numberOfLinesInBoard) != s.color) { // 隣が自分と同じ色ではない
            // なおかつさらに隣かさらにその先が自分と同じ色である
            for (int i = 1; s.y - i >= 1; i++) {
                if (b.sbBoard.charAt(numberFromBeginning - i * b.numberOfLinesInBoard) == s.color) {
                    System.out.println("上にとれる石あり");
                    numberOfUpperReverse = i-1;
                    return true;
                }
            }
            System.out.println("上にとれる石なし");
            return false;
        }
        System.out.println("上が自分と同じ色");
        return false;
    }
    // 下にひっくり返せる石があるかの判定
    boolean isLowerReverse(Stone s, Board b) {
        int numberFromBeginning = b.coordinateTransformation(s.x, s.y, b);
        if (s.y == b.numberOfLinesInBoard) {
            System.out.println("下画面端ぃ！");
            return false;
        }else if (b.sbBoard.charAt(numberFromBeginning + b.numberOfLinesInBoard) == '*') { // 隣が空白であれば処理を終了
            System.out.println("下隣が空白");
            return false;
        } else if (b.sbBoard.charAt(numberFromBeginning + b.numberOfLinesInBoard) != s.color) { // 隣が自分と同じ色ではない
            // なおかつさらに隣かさらにその先が自分と同じ色である
            for (int i = 1; s.y + i <= b.numberOfLinesInBoard; i++) {
                if (b.sbBoard.charAt(numberFromBeginning + i * b.numberOfLinesInBoard) == s.color) {
                    System.out.println("下にとれる石あり");
                    numberOfLowerReverse = i-1;
                    return true;
                }
            }
            System.out.println("下にとれる石なし");
            return false;
        }
        System.out.println("下が自分と同じ色");
        return false;
    }
    boolean isUpperRightReverse(Stone s, Board b) {
        int numberFromBeginning = b.coordinateTransformation(s.x, s.y, b);
        if (s.x == b.numberOfLinesInBoard | s.y == 1) {
            System.out.println("右上画面端ぃ！");
            return false;
        }else if (b.sbBoard.charAt(numberFromBeginning - b.numberOfLinesInBoard + 1) == '*') { // 隣が空白であれば処理を終了
            System.out.println("右上が空白");
            return false;
        } else if (b.sbBoard.charAt(numberFromBeginning - b.numberOfLinesInBoard + 1) != s.color) { // 隣が自分と同じ色ではない
            // なおかつさらに隣かさらにその先が自分と同じ色である
            for (int i = 1; s.x + i <= b.numberOfLinesInBoard && s.y - i >= 1; i++) {
                if (b.sbBoard.charAt(numberFromBeginning + i - (i * b.numberOfLinesInBoard)) == s.color) {
                    System.out.println("右上にとれる石あり");
                    numberOfUpperRightReverse = i-1;
                    return true;
                }
            }
            System.out.println("右上にとれる石なし");
            return false;
        }
        System.out.println("右上が自分と同じ色");
        return false;
    }
    boolean isUpperLeftReverse(Stone s, Board b) {
        int numberFromBeginning = b.coordinateTransformation(s.x, s.y, b);
        if (s.x == 1 | s.y == 1) {
            System.out.println("左上画面端ぃ！");
            return false;
        }else if (b.sbBoard.charAt(numberFromBeginning - b.numberOfLinesInBoard - 1) == '*') { // 隣が空白であれば処理を終了
            System.out.println("左上が空白");
            return false;
        } else if (b.sbBoard.charAt(numberFromBeginning - b.numberOfLinesInBoard - 1) != s.color) { // 隣が自分と同じ色ではない
            // なおかつさらに隣かさらにその先が自分と同じ色である
            for (int i = 1; s.x + i >= 1 && s.y - i >= 1; i++) {
                if (b.sbBoard.charAt(numberFromBeginning - i - (i * b.numberOfLinesInBoard)) == s.color) {
                    System.out.println("左上にとれる石あり");
                    numberOfUpperLeftReverse = i-1;
                    return true;
                }
            }
            System.out.println("左上にとれる石なし");
            return false;
        }
        System.out.println("左上が自分と同じ色");
        return false;
    }
    boolean isLowerRightReverse(Stone s, Board b) {
        int numberFromBeginning = b.coordinateTransformation(s.x, s.y, b);
        if (s.x == b.numberOfLinesInBoard | s.y == b.numberOfLinesInBoard) {
            System.out.println("右下画面端ぃ！");
            return false;
        }else if (b.sbBoard.charAt(numberFromBeginning + b.numberOfLinesInBoard + 1) == '*') { // 隣が空白であれば処理を終了
            System.out.println("右下が空白");
            return false;
        } else if (b.sbBoard.charAt(numberFromBeginning + b.numberOfLinesInBoard + 1) != s.color) { // 隣が自分と同じ色ではない
            // なおかつさらに隣かさらにその先が自分と同じ色である
            for (int i = 1; s.x + i <= b.numberOfLinesInBoard && s.y + i <= b.numberOfLinesInBoard; i++) {
                if (b.sbBoard.charAt(numberFromBeginning + i + (i * b.numberOfLinesInBoard)) == s.color) {
                    System.out.println("右下にとれる石あり");
                    numberOfLowerRightReverse = i-1;
                    return true;
                }
            }
            System.out.println("右下にとれる石なし");
            return false;
        }
        System.out.println("右下が自分と同じ色");
        return false;
    }
    boolean isLowerLeftReverse(Stone s, Board b) {
        int numberFromBeginning = b.coordinateTransformation(s.x, s.y, b);
        if (s.x == 1 | s.y == b.numberOfLinesInBoard) {
            System.out.println("左下画面端ぃ！");
            return false;
        }else if (b.sbBoard.charAt(numberFromBeginning + b.numberOfLinesInBoard - 1) == '*') { // 隣が空白であれば処理を終了
            System.out.println("左下が空白");
            return false;
        } else if (b.sbBoard.charAt(numberFromBeginning + b.numberOfLinesInBoard - 1) != s.color) { // 隣が自分と同じ色ではない
            // なおかつさらに隣かさらにその先が自分と同じ色である
            for (int i = 1; s.x - i >= 1 && s.y + i <= b.numberOfLinesInBoard; i++) {
                if (b.sbBoard.charAt(numberFromBeginning - i + (i * b.numberOfLinesInBoard)) == s.color) {
                    System.out.println("左下にとれる石あり");
                    numberOfLowerLeftReverse = i-1;
                    return true;
                }
            }
            System.out.println("左下にとれる石なし");
            return false;
        }
        System.out.println("左下が自分と同じ色");
        return false;
    }
    public static void main(String[] args) {
        // TODO 自動生成されたメソッド・スタブ
    }
}