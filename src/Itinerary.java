// Airline Travel Scheduler - Itinerary
// Bongki Moon (bkmoon@snu.ac.kr)


import java.util.*;

public class Itinerary {

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

    Itinerary(Flight flt) {
        flights = new LinkedList<>();
        flights.add(flt);
        found = true;
    }

    Itinerary() {//create itinerary for no flight
        found = false;
    }

    public Itinerary appendedNew(Flight flt) {
//        if (arrivalMin + getDest().getConnectTime() > flt.getDepartureMin()) {
//            //invalid, make exception
//        }
        LinkedList<Flight> newFltList = new LinkedList<>(flights);
        newFltList.add(flt);
        return new Itinerary(newFltList, true);
    }

    public void append(Flight flt) {
        flights.add(flt);
        this.dest = flt.getDest();
        setETime();
    }

    public boolean isFound() {
        return found;
    }

    public void print() {
        if (found) {
            for (Flight f : flights) f.print();
            System.out.println();
        } else System.out.println("No Flight Schedule Found.");
    }

    private void setETime() {
        elapseTime = 0;
        int arrivalTime = 0;
        for (Flight flt : flights) {
            if (arrivalTime > flt.getDepartureMin())
                elapseTime += Planner.DAY_MIN;

            if (flt.getDepartureMin() > flt.getArrivalMin())
                elapseTime += Planner.DAY_MIN;

            arrivalTime = flt.getArrivalMin();
        }
        elapseTime = elapseTime + arrivalMin() - departureMin();
    }

    public Airport dest() {
        return flights.getLast().getDest();
    }

    public Airport src() {
        return flights.getFirst().getSrc();
    }

    public int arrivalMin() {
        return flights.getLast().getArrivalMin();
    }

    public int departureMin() {
        return flights.getFirst().getDepartureMin();
    }

    public int getElapseTime(int start) {
        return elapseTime + Planner.getInterval(start, flights.getFirst().getDepartureMin());
    }
}
