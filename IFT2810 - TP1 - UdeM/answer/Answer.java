package answer;

import java.util.LinkedList;

public class Answer implements Comparable<Answer>{

    public boolean b;
    public LinkedList<Tuple> path = new LinkedList<>();

    public Answer(boolean b){
        this.b = b;
    }

    @Override
    public int compareTo(Answer o) {
        if( o.b != this.b){
            return -1;
        }

        if(o.path.size() != this.path.size()){
            return 1;
        }

        for(int i=0; i < o.path.size();i++){
            if(this.path.get(i).compareTo(o.path.get(i)) != 0){
                return 2;
            }

        }

        return 0;
    }
}
