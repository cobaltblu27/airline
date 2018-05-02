// Airline Travel Scheduler - Flight
// Bongki Moon (bkmoon@snu.ac.kr)

public class Flight {

    private String src, dest;
    private String stime, dtime;

    // constructor
    public Flight(String src, String dest, String stime, String dtime) {
        this.src = src;
        this.dest = dest;
        this.stime = stime;
        this.dtime = dtime;
    }

    public void print() {
        System.out.print("[" + src + "->" + dest + ":" + stime + "->" + dtime + "]");
    }

}
