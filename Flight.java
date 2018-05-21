// Airline Travel Scheduler - Flight
// Bongki Moon (bkmoon@snu.ac.kr)

import java.util.HashSet;

public class Flight{
    int elapseTime;
    protected Airport src, dest;
    protected String departureTime, arrivalTime;
    int departureMin, arrivalMin;

    private HashSet<Flight> nextFlight;

    // constructor
    public Flight(String src, String dest, String stime, String dtime) {
        nextFlight = new HashSet<>();
        this.src = Airport.portMap.get(src);
        this.dest = Airport.portMap.get(dest);
        this.departureTime = stime;
        this.arrivalTime = dtime;
        departureMin = Planner.getMinute(stime);
        arrivalMin = Planner.getMinute(dtime);
        elapseTime = Planner.getInterval(departureMin, arrivalMin);
    }

    public Flight(Airport src, Airport dest, int smin, int dmin){
        this.src = src;
        this.dest = dest;
        departureMin = smin;
        arrivalMin = dmin;
    }

    public void print() {
        System.out.print("[");
        src.print();
        System.out.print("->");
        dest.print();
        System.out.print(":" + departureTime + "->" + arrivalTime + "]");
    }

    public int getArrivalMin() {
        return arrivalMin;
    }

    public int getDepartureMin() {
        return departureMin;
    }

    public Airport getSrc() {
        return src;
    }

    public Airport getDest() {
        return dest;
    }

    public int getElapseTime(){
        return elapseTime;
    }
}
