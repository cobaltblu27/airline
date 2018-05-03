// Airline Travel Scheduler - Flight
// Bongki Moon (bkmoon@snu.ac.kr)

import java.util.Comparator;

public class Flight {

    private Airport src, dest;
    private String departureTime, arrivalTime;
    private int departureMin, arrivalMin;
    private int interval;

    // constructor
    public Flight(String src, String dest, String stime, String dtime) {
        this.src = Airport.portMap.get(src);
        this.dest = Airport.portMap.get(dest);
        this.departureTime = stime;
        this.arrivalTime = dtime;
        departureMin = Planner.getMinute(stime);
        arrivalMin = Planner.getMinute(dtime);
        interval = Planner.getInterval(departureMin, arrivalMin);
        this.src.addFlight(this);
    }

    public void print() {
        System.out.print("[" + src + "->" + dest + ":" + departureTime + "->" + arrivalTime + "]");
    }

    public int getArrivalMin() {
        return arrivalMin;
    }

    private int getInterval() {
        return interval;
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

}
