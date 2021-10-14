package com.thinkifyapi.app.restapithinkify.models;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tags")
public class Tags {
    @Transient
    public static final String SEQUENCE_NAME = "tags_sequence";

    @Id
    private String id;

    private long tag_id;
    private String epcColorLED;
    private int baudrate;
    private String serialPort;
    private String passcode;
    private String epc;
    private String tid;
    private String user;
    private Boolean active;

    public Tags() {}
    
    public Tags(String epcColorHex, int baudrate, String serialPort, String passcode,
                String epc, String tid, String user, Boolean active) {
        this.epcColorLED = epcColorHex;
        this.baudrate = baudrate;
        this.serialPort = serialPort;
        this.passcode = passcode;
        this.epc = epc;
        this.tid = tid;
        this.user = user;
        this.active = active;
    }

    public String getId() { return id; }

    public long getTag_id() { return tag_id; }

    public void setTag_id(long tag_id) { this.tag_id =  tag_id; }
      
    public void setEpcColorLED(String epcColorLED) { this.epcColorLED =  epcColorLED;}

    public String getEpcColorLED() { return epcColorLED; }

    public void setBaudrate(int baudrate) { this.baudrate =  baudrate;}

    public int getBaudrate() { return baudrate; }

    public void setSerialPort(String serialPort) { this.serialPort =  serialPort;}

    public String getSerialPort() { return serialPort; }

    public void setPasscode(String passcode) { this.passcode =  passcode;}

    public String getPasscode() { return passcode; }

    public void setEpc(String epc) { this.epc =  epc;}

    public String getEpc() { return epc; }

    public void setTid(String tid) { this.tid =  tid;}

    public String getTid() { return tid; }

    public void setUser(String user) { this.user =  user;}

    public String getUser() { return user; }

    public void setActive(Boolean active) { this.active =  active;}

    public Boolean getActive() { return active; }

    public String toString() {
        return "Tags [id=" + id + ", epcColorLED=" + epcColorLED + 
        ", baudrate=" + baudrate + ", serialPort=" + serialPort + ", passcode=" +
        passcode + ", epc=" + epc + ", tid=" + tid + ", user=" + user + ", active=" + active + "]";
    }
}
