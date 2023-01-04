// -----------------------------------------------------
// Part: 2
// Written by: Jeremie Garzon 40062316
// -----------------------------------------------------

public class TVShow implements Watchable{

    private String showID;
    private String showName;
    private double startTime;
    private double endTime;

    public TVShow(String showID, String showName, double startTime, double endTime) {
        this.showID = showID;
        this.showName = showName;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public TVShow(TVShow copy){
        this.showID = copy.showID;
        this.showName = copy.showName;
        this.startTime = copy.startTime;
        this.endTime = copy.endTime;
    }

    public TVShow clone(String newShowID){
        return new TVShow(newShowID, this.showName, this.startTime, this.endTime);
    }

    public String toString() {
        return " The TVShow " + showName + " has an ID of " + showID +
                ", starting at " + startTime + " and ending at " + endTime;
    }

    public boolean equals(Object o){

        if( o == null || o.getClass() != getClass())
            return false;

        TVShow tv = (TVShow) o;

        if( tv.showName.equals(this.showName) &&
            tv.startTime == this.startTime &&
            tv.endTime == this.endTime)
            return true;

        return false;
    }

    public String isOnSameTime(TVShow s){

        if(this.startTime == s.startTime && this.endTime == s.endTime)
            return "Same time";

        if(this.startTime > s.endTime || this.endTime < s.startTime)
            return "Different time";

        return "Some Overlap";
    }

    public String getShowID(){
        return showID;
    }
}
