
import java.util.*;

// Airline Travel Scheduler - Planner
// Bongki Moon (bkmoon@snu.ac.kr)

public class Planner {

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
        HashMap<Airport, Integer> intervalMap = new HashMap<>();
        LinkedList<Flight> flights = new LinkedList<>();
        Queue<Airport> flightQueue = new PriorityQueue<>();
        HashSet<Airport> visited = new HashSet<>();

        Airport startAirport = Airport.portMap.get(start);
        Airport destAirport = Airport.portMap.get(end);
        int departMin = getMinute(departure);
        boolean found = false;

        Flight flt = startAirport.nextFlight(departMin, destAirport);
        if (flt != null) {
            return (Itinerary) flt;
        }

        startAirport.initElapseTime();
        startAirport.elapseTime = 0;
        startAirport.currentTime = departMin;
        flightQueue.add(startAirport);
        while (true) {
            Airport fastest = flightQueue.poll();//min value; airport with least elapseTime
            for(Airport dest: fastest.getDestList()){
                flt = fastest.nextFlight(fastest.currentTime, dest);
                //TODO
            }
            if (visited.isEmpty())
                break;
        }

        return new Itinerary(flights, true);
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
