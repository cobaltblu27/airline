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
        setETime();
    }

    Itinerary() {//create itinerary for no flight
        found = false;
    }

    public Itinerary appendedNew(Flight flt) {
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

    private void setETime() {//calculate and set elapseTime
        elapseTime = 0;
        int departure = flights.getFirst().getDepartureMin();//available connection time
        for (Flight flt : flights) {
            elapseTime += Planner.getInterval(departure, flt.getDepartureMin());
            elapseTime += flt.getElapseTime();
            elapseTime += flt.getDest().getConnectionTime();

            departure = flt.getArrivalMin() + flt.getDest().getConnectionTime();
            departure %= Planner.DAY_MIN;
        }
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
