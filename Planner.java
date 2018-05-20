
import java.util.*;

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
        Queue<Flight> flightQueue
                = new PriorityQueue<>(Comparator.comparing(Flight::getElapseTime));
        HashSet<Airport> visited = new HashSet<>();

        Airport startAirport = Airport.portMap.get(start);
        Airport destAirport = Airport.portMap.get(end);
        int departMin = getMinute(departure);

        flightQueue.addAll(startAirport.allNextFlight(departMin));

        Flight flt = startAirport.nextFlight(departMin, destAirport);

        //contains itinerary that already has been calculated
        if (flt != null) return (Itinerary) flt;

        Dijkstra(departMin, startAirport, destAirport, flightQueue, visited);

        return new Itinerary(flights, true);
    }

    private void Dijkstra(int time, Airport st
            , Airport dest
            , Queue<Flight> flightQueue
            , HashSet<Airport> visited) {

        while (true) {
            Flight fastest = flightQueue.poll();//min value; airport with least elapseTime


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
