import sun.awt.image.ImageWatched;

import java.util.HashMap;
import java.util.LinkedList;

// Airline Travel Scheduler - Planner
// Bongki Moon (bkmoon@snu.ac.kr)

public class Planner {

    private HashMap<Airport, LinkedList<Flight>> flightMap;



    // constructor; initialize graph
    public Planner(LinkedList<Airport> portList, LinkedList<Flight> fltList) {
        for(Airport port: portList){
            flightMap.put(port, new LinkedList<>());
        }
        for(Flight flt: fltList){
            Airport port = Airport.portMap.get(flt.src);
            //TODO
        }
    }

    public Itinerary Schedule(String start, String end, String departure) {
        LinkedList<Flight> flights = new LinkedList<>();
        boolean found = false;
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
