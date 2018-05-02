
// Airline Travel Scheduler - Airport
// Bongki Moon (bkmoon@snu.ac.kr)

import java.io.*;
import java.util.*;

public class Airport {

    private String portName;
    private int minutes;

    public Airport(String port, String connectTime) {
        portName = port;
        minutes = Planner.getMinute(connectTime);
    } // constructor

    public void print() {

    }

    @Override
    public int hashCode(){
        return portName.hashCode();
    }

}
