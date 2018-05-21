
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

    public Itinerary Schedule(String startStr, String endStr, String departure) {
        //airportMinMap stores optimal flight to key airport
        HashMap<Airport, Itinerary> airportMinMap = new HashMap<>();

        //flightQueue is a min-heap for getting fastest flight every Dijkstra algorithm
        Queue<Itinerary> flightQueue = new PriorityQueue<>(Comparator.comparing(Flight::getElapseTime));

        Airport start = Airport.portMap.get(startStr);
        Airport dest = Airport.portMap.get(endStr);
        int departMin = getMinute(departure);

        for(Flight nextflt : start.allNextFlight(departMin)){
            Itinerary it = new Itinerary(nextflt);
            flightQueue.add(it);
            airportMinMap.put(nextflt.getDest(), it);
        }

        Flight flt = start.nextFlight(departMin, dest);

        //contains itinerary that already has been calculated
        if (flt != null) return new Itinerary(flt);

        return Dijkstra(dest, flightQueue, airportMinMap);
    }

    private Itinerary Dijkstra(Airport dest
            , Queue<Itinerary> flightQueue
            , HashMap<Airport
            , Itinerary> airportMinMap) {

        while (true) {
            Itinerary fastest = flightQueue.poll();//min value; airport with least elapseTime
            Airport fltDest = fastest.getDest();
            if (fltDest.equals(dest))
                return fastest;

            HashSet<Flight> nextSet = fltDest.allNextFlight(fastest.arrivalMin);

            for (Flight flt : nextSet) {
                Airport loopDest = flt.dest;
                int etime = addElapseTime(fastest, flt);
                if (etime < airportMinMap.get(loopDest).getElapseTime()
                        || !airportMinMap.containsKey(loopDest)) {
                    /* make new itinerary that is better than one stored in map*/
                    Itinerary newIter = fastest.appendedNew(flt);
                    airportMinMap.put(loopDest, newIter);
                    flightQueue.add(newIter);
                }
            }
        }
    }

    private static int addElapseTime(Itinerary it, Flight flt) {
        int elapse = it.getElapseTime();
        elapse += getInterval(it.getArrivalMin(), flt.getDepartureMin());
        return elapse;
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
