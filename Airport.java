
// Airline Travel Scheduler - Airport
// Bongki Moon (bkmoon@snu.ac.kr)

import java.io.*;
import java.util.*;

public class Airport {

    public static HashMap<String, Airport> portMap;

    private String portName;
    private int minutes;

    public Airport(String port, String connectTime) {
        if(portMap == null)
            portMap = new HashMap<>();
        portName = port;
        minutes = Planner.getMinute(connectTime);
        portMap.put(port, this);
    } // constructor

    public void print() {

    }

    @Override
    public int hashCode(){
        return portName.hashCode();
    }

    public int getConnectTime(){
        return minutes;
    }

}
