
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

// Airline Travel Scheduler - Planner
// Bongki Moon (bkmoon@snu.ac.kr)

public class Planner {
    //airportFlightMap entry contains flights departing from key airport
    private HashMap<Airport, LinkedList<Flight>> airportFlightMap;
    /* flightLinkMap entry contains flights that can be transferred from key flight
     * this will be used for dynamic programming
     */
    private HashMap<Flight, HashSet<Flight>> flightLinkMap;

    // constructor; initialize graph
    public Planner(LinkedList<Airport> portList, LinkedList<Flight> fltList) {
        airportFlightMap = new HashMap<>();
        flightLinkMap = new HashMap<>();
        for (Airport port : portList) {
            airportFlightMap.put(port, new LinkedList<>());
        }
        //initialize airportFlightMap
        for (Flight flt : fltList) {
            Airport port = flt.getSrc();
            airportFlightMap.get(port).add(flt);
        }
        //initialize flightLinkMap

        ////takes too long
//        for (Flight flt : fltList) {
//            int arrival = flt.getArrivalMin();
//            Airport departAirport = flt.getDest();
//            arrival += departAirport.getConnectTime();
//            HashMap<Airport, Flight> flightMap = new HashMap<>();
//
//            for (Flight canGoTo : airportFlightMap.get(departAirport)) {
//                if (canGoTo.getDepartureMin() > arrival) {
//                    flightMap.merge(canGoTo.getDest(), canGoTo, (flt1, flt2) ->
//                            flt1.getArrivalMin() < flt2.getArrivalMin() ? flt1 : flt2
//                    );
//                }
//            }
//            flightLinkMap.put(flt, new HashSet<>(flightMap.values()));
//        }
    }

    public Itinerary Schedule(String start, String end, String departure) {
        LinkedList<Flight> flights = new LinkedList<>();
        boolean found = false;
        int departMin = getMinute(departure);
        //TODO

        return new Itinerary(flights, found);
    }

    static int getMinute(String time) {
        int minutes;
        minutes = Integer.parseInt(time.substring(2, 3));
        minutes += Integer.parseInt(time.substring(0, 1)) * 60;
        if (minutes > 24 * 60)
            minutes -= 24 * 60;
        return minutes;
    }

    static int getInterval(int start, int end) {
        return start < end ? end - start : end + 24 * 60 - start;
    }

}
