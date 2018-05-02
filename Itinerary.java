// Airline Travel Scheduler - Itinerary
// Bongki Moon (bkmoon@snu.ac.kr)

import java.util.ArrayList;

public class Itinerary {

    private ArrayList<Flight> flights;
    private boolean found;

    // constructor
    Itinerary(ArrayList<Flight> flights, boolean found) {
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
