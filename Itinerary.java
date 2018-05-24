// Airline Travel Scheduler - Itinerary
// Bongki Moon (bkmoon@snu.ac.kr)


import java.util.*;

public class Itinerary{

    private LinkedList<Flight> flights;
    private int elapseTime;
    private boolean found;
    private Airport dest;
    private Airport src;

    // constructor

    Itinerary(LinkedList<Flight> flights, boolean found) {
        this.flights = flights;
        this.found = found;
        src = flights.getFirst().getSrc();
        dest = flights.getLast().getDest();
        setETime();
    }

    Itinerary(Flight flt){
        flights = new LinkedList<>();
        flights.add(flt);
        found = true;
    }

    public Itinerary appendedNew(Flight flt) {
//        if (arrivalMin + getDest().getConnectTime() > flt.getDepartureMin()) {
//            //invalid, make exception
//        }
        LinkedList<Flight> newFltList = new LinkedList<>(flights);
        newFltList.add(flt);
        return new Itinerary(newFltList, true);
    }

    public void append(Flight flt){
        flights.add(flt);
        this.dest = flt.getDest();
        setETime();
    }

    public boolean isFound() {
        return found;
    }

    public void print() {
        for (Flight f : flights) f.print();
        System.out.println();
    }

    private void setETime() {//TODO wrong
        elapseTime = 0;
        int arrivalTime = Planner.DAY_MIN;
        for (Flight flt : flights) {
            if (flt.departureMin < arrivalTime)
                elapseTime += Planner.DAY_MIN;
            if (flt.getDepartureMin() > flt.getArrivalMin())
                elapseTime += Planner.DAY_MIN;
            arrivalTime = flt.getArrivalMin();
        }
        elapseTime = elapseTime + arrivalMin() - departureMin();
        print();System.out.println(elapseTime);
    }

    public Airport dest(){
        return flights.getLast().getDest();
    }
    public Airport src(){
        return flights.getFirst().getSrc();
    }

    public int arrivalMin(){
        return flights.getLast().getArrivalMin();
    }

    public int departureMin(){
        return flights.getFirst().getDepartureMin();
    }

    public int getElapseTime(int start){
        return elapseTime + Planner.getInterval(start, flights.getFirst().getDepartureMin());
    }
}
