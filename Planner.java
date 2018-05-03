
import java.util.LinkedList;

// Airline Travel Scheduler - Planner
// Bongki Moon (bkmoon@snu.ac.kr)

public class Planner {


    // constructor; initialize graph
    public Planner(LinkedList<Airport> portList, LinkedList<Flight> fltList) {
        for(Flight flt: fltList){
            flt.getSrc().addFlight(flt);
        }

    }

    public Itinerary Schedule(String start, String end, String departure) {
        LinkedList<Flight> flights = new LinkedList<>();
        boolean found = false;
        int departMin = getMinute(departure);


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
