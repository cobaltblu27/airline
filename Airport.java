
// Airline Travel Scheduler - Airport
// Bongki Moon (bkmoon@snu.ac.kr)

import java.util.*;

public class Airport {

    public static HashMap<String, Airport> portMap;

    private HashSet<Airport> destList;

    //entry contains flight heading to key airport
    private HashMap<Airport, HashSet<Flight>> flightSet;

    private String portName;
    private int connectionTime;

    public Airport(String port, String connectTime) {
        flightSet = new HashMap<>();
        if (portMap == null)
            portMap = new HashMap<>();
        portName = port;
        connectionTime = Planner.getMinute(connectTime);
        portMap.put(port, this);
    } // constructor

    public void print() {

    }

    public boolean canGoTo(Airport dest){
        return destList.contains(dest);
    }

    public Flight nextFlight(int time, Airport dest) {
        if (!flightSet.keySet().contains(dest))
            return null;
        Flight fastestFlight = null;
        int minInterval = 24 * 60;
        for (Flight flt : flightSet.get(dest)) {
            if (flt.getDepartureMin() > time) {
                int interval = Planner.getInterval(time, flt.getDepartureMin());
                if (interval < minInterval && interval >= connectionTime) {
                    minInterval = interval;
                    fastestFlight = flt;
                }
            }
        }
        return fastestFlight;
    }

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

    @Override
    public int hashCode() {
        return portName.hashCode();
    }

    public int getConnectTime() {
        return connectionTime;
    }

}
