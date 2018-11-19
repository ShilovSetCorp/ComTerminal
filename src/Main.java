import jssc.SerialPort;
import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

import java.util.Scanner;


public class Main {
    private static SerialPort serialPort;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        //Передаём в конструктор имя порта
        serialPort = new SerialPort("COM2");
        try {

//            //Включаем аппаратное управление потоком

//
//            serialPort.writeString("Get data");
           String[] strArr = new String [4];
            strArr[0] = "Test string";
            strArr[1] = "Recieve";
            strArr[2] = "Enable";
            strArr[3] = "Disable";
            String s = "";

            serialPort.openPort();
            serialPort.setParams(SerialPort.BAUDRATE_9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
            serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN |
                    SerialPort.FLOWCONTROL_RTSCTS_OUT);
            //Устанавливаем ивент лисенер и маску
            serialPort.addEventListener(new PortReader(), SerialPort.MASK_RXCHAR);
            //Отправляем запрос устройству
            while (!s.equals("stop")) {
                s = in.nextLine();
                serialPort.writeBytes(s.getBytes());
            }
            serialPort.closePort();
        } catch (SerialPortException ex) {
            System.out.println(ex);
        }
    }

    private static class PortReader implements SerialPortEventListener {

        public void serialEvent(SerialPortEvent event) {
            if (event.getEventValue() > 0) {
                try {
                    //Получаем ответ от устройства, обрабатываем данные и т.д.
                    String data = serialPort.readString(event.getEventValue());
                    System.out.println(data);
                    //И снова отправляем запрос
                   // serialPort.writeString("Get data");
                } catch (SerialPortException ex) {
                    System.out.println(ex);
                }
            }
        }
    }

}
