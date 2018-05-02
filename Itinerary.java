// Airline Travel Scheduler - Itinerary
// Bongki Moon (bkmoon@snu.ac.kr)


import java.util.LinkedList;

public class Itinerary {

    private LinkedList<Flight> flights;
    private boolean found;

    // constructor
    Itinerary(LinkedList<Flight> flights, boolean found) {
        this.flights = flights;
        this.found = found;
    }

    public boolean isFound() {
        return found;
    }

    public void print() {
        for (Flight f : flights) {
            f.print();
        }
        System.out.println();
    }

}
