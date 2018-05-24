
// Airline Travel Scheduler - Airport
// Bongki Moon (bkmoon@snu.ac.kr)

import java.util.*;

public class Airport {

    public static HashMap<String, Airport> portMap;

    private HashSet<Airport> destList;

    //entry contains flight heading to key airport
    private HashMap<Airport, HashSet<Flight>> flightSet;

    private final String portName;
    private final int connectionTime;

    public Airport(String port, String connectTime) {
        destList = new HashSet<>();
        flightSet = new HashMap<>();
        if (portMap == null)
            portMap = new HashMap<>();
        portName = port;
        connectionTime = Planner.getMinute(connectTime);
        portMap.put(port, this);
    } // constructor

    /*TODO: make sure this works well, and also consider late starting, early arriving cases*/
    /*TODO: also consider changing data structure of flights*/
    public void addFlight(Flight flt) {
        destList.add(flt.getDest());
        if (flightSet.keySet().contains(flt.getDest())) {
            HashSet<Flight> flights = flightSet.get(flt.getDest());
            Flight remove = null;
            for (Flight setflt : flights) {//flight list should have no two flights departing at same time
                if (setflt.getDepartureMin() == flt.getDepartureMin()) {
                    remove = setflt;
                    break;
                }
            }
            if (remove != null) {//check if flights departing at same time exists
                if (Planner.getInterval(flt.getArrivalMin(), remove.getArrivalMin()) < 12 * 60) {
                    //check if new flight arrives faster
                    flights.remove(remove);
                    flights.add(flt);
                }
            } else// no flight at same departing time
                flights.add(flt);
        } else {
            flightSet.put(flt.getDest(), new HashSet<>());
            flightSet.get(flt.getDest()).add(flt);
        }
    }

    public Flight nextFlight(int time, Airport dest, boolean connect) {
        if (!flightSet.keySet().contains(dest))
            return null;
        Flight fastestFlight = null;
        int minInterval = Planner.DAY_MIN;
        for (Flight flt : flightSet.get(dest)) {
            int interval = Planner.getInterval(time, flt.getArrivalMin());
            if (interval < minInterval
                    && (!connect || Planner.getInterval(time, flt.getDepartureMin()) > connectionTime)) {
                minInterval = interval;
                fastestFlight = flt;
            }
        }//System.out.println(time + fastestFlight.departureMin);
        return fastestFlight;
    }

    public HashSet<Flight> allNextFlight(int time, boolean connect) {
        HashSet<Flight> nextSet = new HashSet<>();
        for (Airport dest : destList) {
            Flight next = nextFlight(time, dest, connect);
            if (next != null)
                nextSet.add(nextFlight(time, dest, connect));
        }
        return nextSet;
    }

    public void print() {
        System.out.print(portName);
    }

    public boolean canGoTo(Airport dest) {
        return destList.contains(dest);
    }

    @Override
    public int hashCode() {
        return portName.hashCode();
    }
}
