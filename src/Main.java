import jssc.SerialPort;
import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

import java.util.Scanner;


public class Main {

    public static void main(String[] args) throws SerialPortException {
        ComPort comPort = new ComPort("COM2");
        comPort.initComPort();
        comPort.sendToCom("asd");
    }
}
