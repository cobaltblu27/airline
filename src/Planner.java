

import java.util.*;
import java.util.function.Function;
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
        int departMin = getMinute(departure);

        //airportMinMap stores optimal flight to key airport
        HashMap<Airport, Itinerary> airportMinMap = new HashMap<>();

        //flightQueue is a min-heap for getting fastest flight every Dijkstra algorithm
        Function<Itinerary, Integer> compareF = itinerary -> itinerary.getElapseTime(departMin);
        Queue<Itinerary> flightQueue = new PriorityQueue<>(Comparator.comparing(compareF));

        Airport start = Airport.portMap.get(startStr);
        Airport dest = Airport.portMap.get(endStr);

        if(start == null || dest == null)
            return new Itinerary();

        for (Flight nextflt : start.allNextFlight(departMin, false)) {
            Itinerary it = new Itinerary(nextflt);
            flightQueue.add(it);
            airportMinMap.put(nextflt.getDest(), it);
        }

        return Dijkstra(departMin, dest, flightQueue, airportMinMap);
    }

    private Itinerary Dijkstra(int start, Airport dest
            , Queue<Itinerary> flightQueue
            , HashMap<Airport, Itinerary> airportMinMap) {

        HashSet<Airport> visitset = new HashSet<>();
        while (true) {
            if (flightQueue.isEmpty())//return not found itinerary
                return new Itinerary();
            Itinerary fastest = flightQueue.poll();//min value; airport with least elapseTime
            Airport fltDest = fastest.dest();

            if(visitset.contains(fltDest))
                continue;
            else
                visitset.add(fltDest);

            if (fltDest.equals(dest))
                return fastest;

            HashSet<Flight> nextSet = fltDest.allNextFlight(fastest.arrivalMin(), true);

            for (Flight flt : nextSet) {
                Airport loopDest = flt.getDest();
                int etime = fastest.getElapseTime(start)
                        + getInterval(fastest.arrivalMin(), flt.getDepartureMin());

                if (!airportMinMap.containsKey(loopDest)
                        || etime < airportMinMap.get(loopDest).getElapseTime(start)) {
                    /* make new itinerary that is better than one stored in map*/
                    Itinerary newIter = fastest.appendedNew(flt);
                    airportMinMap.put(loopDest, newIter);
                    flightQueue.add(newIter);
                }
            }
        }
    }

    static int getMinute(String time) {
        int minutes;
        minutes = Integer.parseInt(time.substring(2, 4));
        minutes += Integer.parseInt(time.substring(0, 2)) * 60;
        if (minutes > DAY_MIN)
            minutes -= DAY_MIN;
        return minutes;
    }

    static int getInterval(int start, int end) {
        return start <= end ? end - start : end + DAY_MIN - start;
    }


}
