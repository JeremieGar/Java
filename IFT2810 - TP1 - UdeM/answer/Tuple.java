package answer;

public class Tuple implements Comparable<Tuple>{

    public final int x;
    public final int y;

    public Tuple(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "answer.Tuple{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    public int compareTo(Tuple o) {

        if(o.x == this.x && o.y == this.y){
            return 0; // true
        }

        return 1;
    }
}