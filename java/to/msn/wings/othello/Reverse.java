package to.msn.wings.othello;

import java.util.ArrayList;
import java.util.List;

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

    //どの方向に裏返せるかを表す
    boolean isRight;
    boolean isLeft;
    boolean isUpper;
    boolean isLower;
    boolean isUpperRight;
    boolean isUpperLeft;
    boolean isLowerRight;
    boolean isLowerLeft;
    // ひっくり返す場所を格納するリスト
    List<Integer> reverseList = new ArrayList<>();

    // 置きたい場所にすでに駒があったらfalse
    boolean isPutOK(Stone s) {
        if (s.stoneMap.get(s.position) == '*') {
            return true;
        }
        System.out.println("そこにはすでに駒があります");
        return false;
    }

    // 裏返せるかの判定
    // 1か所でも裏返せる場所があればtrue
    boolean isAllReverse(Stone s) {
        if (isRightReverse(s) | isLeftReverse(s) | isUpperReverse(s) |
                isLowerReverse(s) | isUpperRightReverse(s) | isUpperLeftReverse(s) | isLowerRightReverse(s)
                | isLowerLeftReverse(s)) {
            System.out.println("置けるで！");
            return true;
        }
        System.out.println("置けん(´;ω;｀)");
        return false;
    }

    //対戦終了判定。どこも置けるところがなければtrue.
    boolean isCheckEnd(Stone s, char color) {
        for(int i = 0; i < 64; i++) {
            s.setStone(color, i);
            if(isPutOK(s) && isAllReverse(s)) {
                isRight = false;
                isLeft = false;
                isUpper = false;
                isLower = false;
                isUpperRight = false;
                isUpperLeft = false;
                isLowerRight = false;
                isLowerLeft = false;
                return false;
            }
        }
        isRight = false;
        isLeft = false;
        isUpper = false;
        isLower = false;
        isUpperRight = false;
        isUpperLeft = false;
        isLowerRight = false;
        isLowerLeft = false;
        return true;
    }

    // ひっくり返す場所をリストに格納する
    void reverseStone(Stone s) {
        while (isRight | isLeft | isUpper | isLower | isUpperRight | isUpperLeft | isLowerRight | isLowerLeft) {

            if (isRight) {
                for (int i = 1; i <= numberOfRightReverse; i++) {
                    s.stoneMap.put(s.position + i, s.color);// map内に石を置く & ひっくり返す
                    reverseList.add(s.position + i);//ひっくり返す位置をリストに格納。View変更用。
                }
                isRight = false;
                numberOfRightReverse = 0;

            } else if (isLeft) {
                for (int i = 1; i <= numberOfLeftReverse; i++) {
                    s.stoneMap.put(s.position - i, s.color);
                    reverseList.add(s.position - i);
                }
                isLeft = false;
                numberOfLeftReverse = 0;

            } else if (isUpper) {
                for (int i = 1; i <= numberOfUpperReverse; i++) {
                    s.stoneMap.put(s.position - (i * 8), s.color);
                    reverseList.add(s.position - (i * 8));
                }
                isUpper = false;
                numberOfUpperReverse = 0;

            } else if (isLower) {
                for (int i = 1; i <= numberOfLowerReverse; i++) {
                    s.stoneMap.put(s.position + (i * 8), s.color);
                    reverseList.add(s.position + (i * 8));
                }
                isLower = false;
                numberOfLowerReverse = 0;

            } else if (isUpperRight) {
                for (int i = 1; i <= numberOfUpperRightReverse; i++) {
                    s.stoneMap.put(s.position + i - (i * 8), s.color);
                    reverseList.add(s.position + i - (i * 8));
                }
                isUpperRight = false;
                numberOfUpperRightReverse = 0;

            } else if (isUpperLeft) {
                for (int i = 1; i <= numberOfUpperLeftReverse; i++) {
                    s.stoneMap.put(s.position - i - (i * 8), s.color);
                    reverseList.add(s.position - i - (i * 8));
                }
                isUpperLeft = false;
                numberOfUpperLeftReverse = 0;

            } else if (isLowerRight) {
                for (int i = 1; i <= numberOfLowerRightReverse; i++) {
                    s.stoneMap.put(s.position + i + (i * 8), s.color);
                    reverseList.add(s.position + i + (i * 8));
                }
                isLowerRight = false;
                numberOfLowerRightReverse = 0;

            } else if (isLowerLeft) {
                for (int i = 1; i <= numberOfLowerLeftReverse; i++) {
                    s.stoneMap.put(s.position - i + (i * 8), s.color);
                    reverseList.add(s.position - i + (i * 8));
                }
                isLowerLeft = false;
                numberOfLowerLeftReverse = 0;
            }
        }
    }

    // 置いた石の右にひっくり返せる石があるかの判定
    boolean isRightReverse(Stone s) {
        if (s.position % 8 == 7) {
            System.out.println("右画面端ぃ！");
            return false;
        } else if (s.stoneMap.get(s.position + 1) == '*') { // 隣が空白であれば処理を終了
            System.out.println("右隣が空白");
            return false;
        } else if (s.color == s.stoneMap.get(s.position + 1)) { // 隣が自分と同じ色なら処理を終了
            System.out.println("右が自分と同じ色");
            return false;
        }

        // 隣より先に自分と同じ色の石があるかを判定
        for (int i = 2; (s.position + i) % 8 != 0; i++) {
            if (s.stoneMap.get(s.position + i) == '*') { // 空白マスが途中にあったら終了
                System.out.println("右に途中空白マス");
                return false;
            } else if(s.color == s.stoneMap.get(s.position + i)) {// 自分と同じ色があったら取れる。
                System.out.println("右にとれる石あり");
                numberOfRightReverse = i - 1;
                isRight = true;
                return true;
            }
        }
        System.out.println("右隣より先に同色の石なし");
        return false;
    }

    // 左にひっくり返せる石があるかの判定
    boolean isLeftReverse(Stone s) {
        if (s.position % 8 == 0) {
            System.out.println("左画面端ぃ！");
            return false;
        } else if (s.stoneMap.get(s.position - 1) == '*') {
            System.out.println("左隣が空白");
            return false;
        } else if (s.color == s.stoneMap.get(s.position - 1)) { // 隣が自分と同じ色ではない
            // なおかつさらに隣かさらにその先が自分と同じ色である
            System.out.println("左と同じ色");
            return false;
        }

        for (int i = 2; s.position - i >= 0 && (s.position - i) % 8 != 7; i++) {
            if (s.stoneMap.get(s.position - i) == '*') {
                System.out.println("左に途中空白マス");
                return false;
            } else if(s.color == s.stoneMap.get(s.position - i)) {
                System.out.println("左にとれる石あり");
                numberOfLeftReverse = i - 1;
                isLeft = true;
                return true;
            }
        }
        System.out.println("左隣より先に同色の石なし");
        return false;
    }

    // 上にひっくり返せる石があるかの判定
    boolean isUpperReverse(Stone s) {
        if (s.position <= 7) {
            System.out.println("上画面端ぃ！");
            return false;
        } else if (s.stoneMap.get(s.position - 8) == '*') {
            System.out.println("上隣が空白");
            return false;
        } else if (s.color == s.stoneMap.get(s.position - 8)) { // 隣が自分と同じ色ではない
            // なおかつさらに隣かさらにその先が自分と同じ色である

            System.out.println("上と同じ色");
            return false;
        }

        for (int i = 2; s.position - (i * 8) >= 0; i++) {
            if (s.stoneMap.get(s.position - (i * 8)) == '*') {
                System.out.println("上に途中空白マス");
                return false;
            } else if(s.color == s.stoneMap.get(s.position - (i * 8))) {
                System.out.println("上にとれる石あり");
                numberOfUpperReverse = i - 1;
                isUpper = true;
                return true;
            }
        }
        System.out.println("上隣より先に同色の石なし");
        return false;
    }

    // 下にひっくり返せる石があるかの判定
    boolean isLowerReverse(Stone s) {
        if (s.position >= 56) {
            System.out.println("下画面端ぃ！");
            return false;
        } else if (s.stoneMap.get(s.position + 8) == '*') {
            System.out.println("下隣が空白");
            return false;
        } else if (s.color == s.stoneMap.get(s.position + 8)) { // 隣が自分と同じ色ではない
            // なおかつさらに隣かさらにその先が自分と同じ色である
            System.out.println("下と同じ色");
            return false;
        }

        for (int i = 2; (s.position + (i * 8)) < 64; i++) {
            if (s.stoneMap.get(s.position + (i * 8))== '*') {
                System.out.println("下に途中空白マス");
                return false;
            } else if(s.color == s.stoneMap.get(s.position + (i * 8))) {
                System.out.println("下にとれる石あり");
                numberOfLowerReverse = i - 1;
                isLower = true;
                return true;
            }
        }
        System.out.println("下隣より先に同色の石なし");
        return false;
    }

    boolean isUpperRightReverse(Stone s) {
        if (s.position <= 7 | s.position % 8 == 7) {
            System.out.println("右上画面端ぃ！");
            return false;
        } else if (s.stoneMap.get(s.position + 1 - 8) == '*') {
            System.out.println("右上隣が空白");
            return false;
        } else if (s.color == s.stoneMap.get(s.position + 1 - 8)) { // 隣が自分と同じ色ではない
            System.out.println("右上と同じ色");
            return false;
        }

        for (int i = 2; s.position - (i * 8) + i >= 0 && (s.position + i - (i * 8)) % 8 != 0; i++) {
            if (s.stoneMap.get(s.position + i - (i * 8)) == '*') {
                System.out.println("右上に途中空白マス");
                return false;
            } else if(s.color == s.stoneMap.get(s.position + i - (i * 8))) {
                System.out.println("右上にとれる石あり");
                numberOfUpperRightReverse = i - 1;
                isUpperRight = true;
                return true;
            }
        }
        System.out.println("右上隣より先に同色の石なし");
        return false;
    }

    boolean isUpperLeftReverse(Stone s) {
        if (s.position % 8 == 0 | s.position <= 7) {
            System.out.println("左上画面端ぃ！");
            return false;
        } else if (s.stoneMap.get(s.position - 1 - 8) == '*') { // 隣が空白であれば処理を終了
            System.out.println("左上が空白");
            return false;
        } else if (s.color == s.stoneMap.get(s.position - 1 - 8)) { // 隣が自分と同じ色ではない
            // なおかつさらに隣かさらにその先が自分と同じ色である

            System.out.println("左上が自分と同じ色");
            return false;
        }

        for (int i = 2; s.position - i >= 0 && (s.position - i) % 8 != 7 && s.position - (i * 8) >= 0; i++) {
            if (s.stoneMap.get(s.position - i - (i * 8)) == '*') {
                System.out.println("左上に途中空白マス");
                return false;
            } else if(s.color == s.stoneMap.get(s.position - i - (i * 8))) {
                System.out.println("左上にとれる石あり");
                numberOfUpperLeftReverse = i - 1;
                isUpperLeft = true;
                return true;
            }
        }
        System.out.println("左上隣より先に同色の石なし");
        return false;
    }

    boolean isLowerRightReverse(Stone s) {
        if (s.position % 8 == 7 | s.position >= 56) {
            System.out.println("右下画面端ぃ！");
            return false;
        } else if (s.stoneMap.get(s.position + 1 + 8) == '*') { // 隣が空白であれば処理を終了
            System.out.println("右下が空白");
            return false;
        } else if (s.color == s.stoneMap.get(s.position + 1 + 8)) { // 隣が自分と同じ色ではない
            System.out.println("右下が自分と同じ色");
            return false;
        }

        for (int i = 2; (s.position + i) % 8 != 0 && s.position + (i * 8) <= 63; i++) {
            if (s.stoneMap.get(s.position + i + (i * 8)) == '*') {
                System.out.println("右下に途中空白マス");
                return false;
            } else if(s.color == s.stoneMap.get(s.position + i + (i * 8))) {
                System.out.println("右下にとれる石あり");
                numberOfLowerRightReverse = i - 1;
                isLowerRight = true;
                return true;
            }
        }
        System.out.println("右下隣より先に同色の石なし");
        return false;
    }

    boolean isLowerLeftReverse(Stone s) {
        if (s.position % 8 == 0 | s.position >= 56) {
            System.out.println("左下画面端ぃ！");
            return false;
        } else if (s.stoneMap.get(s.position - 1 + 8) == '*') { // 隣が空白であれば処理を終了
            System.out.println("左下が空白");
            return false;
        } else if (s.color == s.stoneMap.get(s.position - 1 + 8)) { // 隣が自分と同じ色ではない
            System.out.println("左下が自分と同じ色");
            return false;
        }

        for (int i = 2; s.position - i >= 0 && (s.position - i) % 8 != 7 && s.position + (i * 8) <= 63; i++) {
            if (s.stoneMap.get(s.position - i + (i * 8)) == '*') {
                System.out.println("左下に途中空白マス");
                return false;
            } else if(s.color == s.stoneMap.get(s.position - i + (i * 8))) {
                System.out.println("左下にとれる石あり");
                numberOfLowerLeftReverse = i - 1;
                isLowerLeft = true;
                return true;
            }
        }
        System.out.println("左下隣より先に同色の石なし");
        return false;
    }

}