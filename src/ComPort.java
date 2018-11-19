import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

/**
 * Created by Пользователь on 19.11.2018.
 */
public class ComPort {
    private static SerialPort serialPort;

    public ComPort(String comPort) throws SerialPortException {
        this.serialPort = new SerialPort(comPort);
    }




    public void initComPort() {
        try {
            serialPort.openPort();
            serialPort.setParams(SerialPort.BAUDRATE_9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
            serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN |
                    SerialPort.FLOWCONTROL_RTSCTS_OUT);
            //Устанавливаем ивент лисенер и маску
            serialPort.addEventListener(new PortReader(), SerialPort.MASK_RXCHAR);

        } catch (SerialPortException ex)
        {
            System.out.println(ex);
        }
    }

    public void sendToCom(String s) throws SerialPortException {
        //Отправляем запрос устройству
        while (!s.equals("stop")) {
            serialPort.writeBytes(s.getBytes());
        }
    }

    public void closeComPort() throws SerialPortException {
        serialPort.closePort();
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
