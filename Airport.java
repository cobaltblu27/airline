
// Airline Travel Scheduler - Airport
// Bongki Moon (bkmoon@snu.ac.kr)

import java.io.*;
import java.util.*;

public class Airport {

    private String portName;
    private int hour;
    private int minute;

    public Airport(String port, String connectTime) {
        portName = port;
        hour = Integer.parseInt(connectTime.substring(0, 1));
        minute = Integer.parseInt(connectTime.substring(2, 3));
    } // constructor

    public void print() {

    }

}
