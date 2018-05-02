// Airline Travel Scheduler - Flight
// Bongki Moon (bkmoon@snu.ac.kr)

public class Flight {

    public String src, dest;
    public String stime, dtime;
    public int smin, dmin;
    public int interval;

    // constructor
    public Flight(String src, String dest, String stime, String dtime) {
        this.src = src;
        this.dest = dest;
        this.stime = stime;
        this.dtime = dtime;
        smin = Planner.getMinute(stime);
        dmin = Planner.getMinute(dtime);
        interval = Planner.getInterval(smin, dmin);
    }

    public void print() {
        System.out.print("[" + src + "->" + dest + ":" + stime + "->" + dtime + "]");
    }

}
