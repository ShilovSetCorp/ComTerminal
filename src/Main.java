
import comport.ComPort;
import ui.*;
import jssc.SerialPortException;




public class Main {

    public static ComPort comPort;
    public static UI ui;

    public static void main(String[] args) throws SerialPortException {
        comPort = new ComPort("COM14");
        comPort.initComPort();
        ui = new UI(comPort);
    }
}
