package to.msn.wings.othello;

public class Stone {
    char color;
    int x;
    int y;
    // 駒の(x,y)座標と駒の色をset
    Stone(char color, int x, int y, Board b) {
        this.color = color;
        this.x = x;
        this.y = y;
    }
    public static void main(String[] args) {
        Board b = new Board(8);
        Stone s = new Stone('黒',3,6,b);
        Reverse r = new Reverse();
        if(r.isPutOK(s, b) && r.isAllReverse(s,b)) {
            b.setStoneInBoard(s, b);
            r.reverseStone(s, b);
        }
    }
}