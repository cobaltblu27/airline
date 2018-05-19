// Airline Travel Scheduler - Itinerary
// Bongki Moon (bkmoon@snu.ac.kr)


import java.util.LinkedList;

public class Itinerary extends Flight{

    private LinkedList<Flight> flights;
    private boolean found;

    // constructor

    Itinerary(LinkedList<Flight> flights, boolean found) {
        super(flights.getFirst().getSrc()
                , flights.getLast().getDest()
                , flights.getFirst().getDepartureMin()
                , flights.getLast().getArrivalMin());
        this.flights = flights;
        this.found = found;
        flights.getFirst().getSrc().addFlight(this);
        elapseTime = Planner.getInterval(departureMin, arrivalMin);
    }

    public LinkedList<Flight> getFlights() {
        return flights;
    }

    public boolean isFound() {
        return found;
    }

    public void print() {
        for (Flight f : flights) f.print();
        System.out.println();
    }

}
