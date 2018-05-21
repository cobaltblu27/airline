
import java.util.*;
import java.util.stream.Collectors;

// Airline Travel Scheduler - Planner
// Bongki Moon (bkmoon@snu.ac.kr)

public class Planner {
    public static final int DAY_MIN = 24 * 60;
    private LinkedList<Airport> portList;
    private LinkedList<Flight> fltList;

    // constructor; initialize graph
    public Planner(LinkedList<Airport> portList, LinkedList<Flight> fltList) {
        for (Flight flt : fltList) {
            flt.getSrc().addFlight(flt);
        }
        this.portList = portList;
        this.fltList = fltList;
    }

    public Itinerary Schedule(String start, String end, String departure) {
        LinkedList<Flight> flights = new LinkedList<>();

        //airportMinMap stores optimal flight to key airport
        HashMap<Airport, Flight> airportMinMap = new HashMap<>();

        //flightQueue is a min-heap for getting fastest flight every Dijkstra algorithm
        Queue<Flight> flightQueue
                = new PriorityQueue<>(Comparator.comparing(Flight::getElapseTime));
        //marks visited airport, may be unnecessary though
        HashSet<Airport> visited = new HashSet<>();

        Airport startAirport = Airport.portMap.get(start);
        Airport destAirport = Airport.portMap.get(end);
        int departMin = getMinute(departure);

        flightQueue.addAll(startAirport.allNextFlight(departMin));

        Flight flt = startAirport.nextFlight(departMin, destAirport);

        //contains itinerary that already has been calculated
        if (flt != null) return (Itinerary) flt;

        Dijkstra(departMin, startAirport, destAirport, flightQueue, visited, airportMinMap);

        return new Itinerary(flights, true);
    }

    private void Dijkstra(int time, Airport st
            , Airport dest
            , Queue<Flight> flightQueue
            , HashSet<Airport> visited
            , HashMap<Airport, Flight> airportMinMap) {

        while (true) {
            Flight fastest = flightQueue.poll();//min value; airport with least elapseTime
            Airport fltDest = fastest.getDest();
            if (fltDest.equals(dest)) {
                //TODO
            }
            HashSet<Flight> nextSet = fltDest.allNextFlight(fastest.arrivalMin);
            for (Flight flt : nextSet) {
                airportMinMap.get(flt.getDest()).getElapseTime() 
            }
            if (visited.isEmpty())
                break;
        }
    }

    static int getMinute(String time) {
        int minutes;
        minutes = Integer.parseInt(time.substring(2, 3));
        minutes += Integer.parseInt(time.substring(0, 1)) * 60;
        if (minutes > DAY_MIN)
            minutes -= DAY_MIN;
        return minutes;
    }

    static int getInterval(int start, int end) {
        return start < end ? end - start : end + DAY_MIN - start;
    }


}
