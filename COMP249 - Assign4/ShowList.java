// -----------------------------------------------------
// Part: 2
// Written by: Jeremie Garzon 40062316
// -----------------------------------------------------

import java.util.NoSuchElementException;

public class ShowList {

    class ShowNode{

        private TVShow show;
        private ShowNode link;

        public ShowNode(){
            show = null;
            link = null;
        }

        public ShowNode(TVShow show, ShowNode link){
            this.show = show;
            this.link = link;
        }

        public ShowNode(ShowNode copy){
            this.show = copy.show;
            this.link = copy.link;
        }

        public ShowNode clone(){
            return new ShowNode(this.show, this.link);
        }

        public TVShow getShow() {
            return show;
        }

        public void setShow(TVShow show) {
            this.show = show;
        }

        public ShowNode getLink() {
            return link;
        }

        public void setLink(ShowNode link) {
            this.link = link;
        }
    }

    private ShowNode head;
    private int size;

    public ShowList(){
        head = null;
        size = 0;
    }

    public ShowList(ShowList copy){
        this.head = copy.head;
        this.size = copy.size;
    }

    public void addToStart(TVShow show){
        head = new ShowNode(show, head);
        size++;
    }

    public void addToEnd(TVShow show){

        if(head == null)
            head = new ShowNode(show,null);
        else{
            ShowNode target = head;

            while(target.link != null)
                target = target.link;

            target.link = new ShowNode(show, null);
        }

    }

    public void insertAtIndex(TVShow newShow, int index){

        if (index > (this.size - 1))
            throw (new NoSuchElementException());

        ShowNode target, previous;
        ShowNode newNode = new ShowNode(newShow, null);

        previous = getNodeAtIndex(0,index-1, head);
        target = getNodeAtIndex(0, index, head);

        if (index == 0)
            head = target;

        previous.setLink(newNode);
        newNode.setLink(target);

        size += 1;

    }

    protected ShowNode getNodeAtIndex(int current, int selected, ShowNode node){

        while(current != selected){
            node = node.getLink();
            current += 1;
        }

        return node;
    }

    public void deleteFromIndex(int index){

        if (index > (this.size - 1))
            throw (new NoSuchElementException());

        ShowNode previous, next;
        ShowNode target = getNodeAtIndex(0, index, head);

        previous = getNodeAtIndex(0, index-1, head);
        next = target.link;

        previous.setLink(next);
        size--;
    }

    public void deleteFromStart(){

        head = head.link;
        size--;
    }

    public void replaceAtIndex(TVShow newShow, int index){

        if (index < (this.size - 1)){
            ShowNode target = getNodeAtIndex(0, index, head);
            target.setShow(newShow);
        }
    }

    public ShowNode find(String targetShowID){

        int count = 0;
        ShowNode target = head;

        do{

            if(target.show.getShowID() == targetShowID)
                break;
            else
                target = target.link;

            count++;

        }while(!(target == null));

        System.out.println("The find method iterates " + count + " times.");
        return target;
    }

    public boolean contains(String targetShowID){

        boolean contain = false;
        ShowNode target = head;

        do{

            if(target.show.getShowID() == targetShowID)
                contain = true;

            target = target.link;

        }while(target.link != null);

        return contain;
    }

    public boolean equals(ShowList newList){

        ShowNode thisListNode = head;

        do{

            String currentShowID = thisListNode.show.getShowID();
            if(!newList.contains(currentShowID))
                return false;

            thisListNode = thisListNode.link;

        }while(thisListNode.link != null);

        return true;
    }
}
