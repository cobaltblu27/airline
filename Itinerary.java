// Airline Travel Scheduler - Itinerary
// Bongki Moon (bkmoon@snu.ac.kr)


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Itinerary extends Flight {

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
        setETime();
    }

    public Itinerary appendedNew(Flight flt) {
//        if (arrivalMin + getDest().getConnectTime() > flt.getDepartureMin()) {
//            //invalid, make exception
//        }
        LinkedList<Flight> newFltList = new LinkedList<>(flights);
        newFltList.add(flt);
        return new Itinerary(newFltList, true);
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

    private void setETime() {
        elapseTime = 0;
        int arrivaltime = Planner.DAY_MIN;
        for (Flight flt : flights) {
            if (flt.departureMin < arrivaltime)
                elapseTime += Planner.DAY_MIN;
            if (flt.getDepartureMin() > flt.getArrivalMin())
                elapseTime += Planner.DAY_MIN;
            arrivaltime = flt.getArrivalMin();
        }
        elapseTime = elapseTime + arrivalMin - departureMin;
    }
}
