import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
/**
 * Represents a smart lamp device that can be controlled by a smart home system.
 * Inherits from the Devices class and includes additional properties such as
 * color temperature and brightness.
 */
public class SmartLamp extends Devices {
    private int kelvin = 4000;
    private int brightness = 100;
    /**
     * Constructs a new SmartLamp object with the specified name, initial status,
     * color temperature and brightness.
     *
     * @param name          the name of the smart lamp
     * @param initialStatus the initial status of the smart lamp ("On" or "Off")
     * @param kelvin        the color temperature of the smart lamp in Kelvin
     * @param brightness    the brightness of the smart lamp (0-100%)
     */
    public SmartLamp(String name, String initialStatus , int kelvin , int brightness) {
        super(name, initialStatus);
        this.brightness = brightness;
        this.kelvin = kelvin;
    }
    /**
     * Constructs a new SmartLamp object with the specified name, initial status,
     * and color temperature. The brightness will be set to 100% by default.
     *
     * @param name          the name of the smart lamp
     * @param initialStatus the initial status of the smart lamp ("On" or "Off")
     * @param kelvin        the color temperature of the smart lamp in Kelvin
     */
    public SmartLamp(String name , String initialStatus , int kelvin) {
        super(name , initialStatus);
        this.kelvin = kelvin;
    }
    /**
     * Constructs a new SmartLamp object with the specified name and initial status.
     * The color temperature and brightness will be set to their default values.
     *
     * @param name          the name of the smart lamp
     * @param initialStatus the initial status of the smart lamp ("On" or "Off")
     */
    public SmartLamp(String name , String initialStatus) {
        super(name , initialStatus);
    }
    /**
     * Constructs a new SmartLamp object with the specified name.
     * The initial status, color temperature and brightness will be set to their default values.
     *
     * @param name          the name of the smart lamp
     */
    public SmartLamp(String name) {
        super(name);
    }
    /**
     * Checks if a device with the given name is a smart lamp.
     *
     * @param name    the name of the device to be checked
     * @param devices the list of devices to search for the device with the given name
     * @return true if a device with the given name is a smart lamp, false otherwise
     */
    public static boolean isInSmartLamp(String name, ArrayList<Devices> devices) {
        for (Devices device : devices) {
            if (device.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }
    /**
     * Checks if the initial status is valid.
     *
     * @param initialstatus the initial status to be checked
     * @return true if the initial status is valid, false otherwise
     */
    public static boolean checkInitialStatus(String initialstatus) {
        if (initialstatus.equals("Off")){
            return true;
        } else if (initialstatus.equals("On")) {
            return true;
        } else {
            return false;
        }
    }
    /**
     * Checks if the kelvin value is valid.
     *
     * @param kelvin the kelvin value to be checked
     * @return true if the kelvin value is valid, false otherwise
     */
    public static boolean checkKelvin(int kelvin) {
        if (kelvin <= 6500 && kelvin >= 2000) {
            return true;
        } else {
            return false;
        }
    }
    /**
     * Checks if the brightness value is valid.
     *
     * @param brightness the brightness value to be checked
     * @return true if the brightness value is valid, false otherwise
     */
    public static boolean checkBrightness(int brightness) {
        if( brightness <= 100 && brightness >= 0) {
            return true;
        } else {
            return false;
        }
    }
    /**
     * Checks if a device with the given name is a smart lamp.
     *
     * @param name    the name of the device to be checked
     * @param devices the list of smart lamps to search for the smart lamp with the given name
     * @return true if a device with the given name is a smart lamp, false otherwise
     */

    public static boolean isASmartLamp(String name, ArrayList<SmartLamp> devices) {
        for (SmartLamp device : devices) {
            if (device.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }
    /**
     * Overrides the equals method to compare two SmartLamp objects based on their names.
     *
     * @param obj the object to compare
     * @return true if the objects are equal (based on name), false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof SmartLamp) {
            SmartLamp other = (SmartLamp) obj;
            return this.getName().equals(other.getName());
        }
        return false;
    }
    /**
     * Overrides the getSwitchTime method to return the switch time of the SmartLamp object.
     *
     * @return the switch time of the SmartLamp object
     */
    @Override
    public LocalDateTime getSwitchTime() {
        return super.getSwitchTime();
    }
    /**
     * Overrides the setSwitchTime method to set the switch time of the SmartLamp object.
     *
     * @param switchTime the switch time to set
     */
    @Override
    public void setSwitchTime(LocalDateTime switchTime) {
        super.setSwitchTime(switchTime);
    }
    /**
     * Overrides the getInitialStatus method to return the initial status of the SmartLamp object.
     *
     * @return the initial status of the SmartLamp object
     */
    @Override
    public String getInitialStatus() {
        return super.getInitialStatus();
    }
    /**
     * Overrides the setInitialStatus method to set the initial status of the SmartLamp object.
     *
     * @param initialStatus the initial status to set
     */
    @Override
    public void setInitialStatus(String initialStatus) {
        super.setInitialStatus(initialStatus);
    }
    /**
     * Overrides the toString method to return a string representation of the SmartLamp object.
     *
     * @return a string representation of the SmartLamp object
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss");
        String switchTime;
        if (getSwitchTime() == null) {
            switchTime = "null";
        } else {
            switchTime = getSwitchTime().format(formatter);
        }
        String a;
        a = getInitialStatus().toLowerCase(Locale.ROOT);
        return "Smart Lamp " + getName() + " is " + a + " and its color value is " + getKelvin() + "K with " + this.brightness + "% brightness, and its time to switch its status is " + switchTime + ".";
    }
    /**
     * Overrides the getName method to return the name of the SmartLamp object.
     *
     * @return the name of the SmartLamp object
     */
    @Override
    public String getName() {
        return super.getName();
    }
    /**
     * Overrides the setName method to set the name of the SmartLamp object.
     *
     * @param name the name to set
     */
    @Override
    public void setName(String name) {
        super.setName(name);
    }
    /**
     * Returns the kelvin value of the smart lamp.
     *
     * @return the kelvin value of the smart lamp
     */
    public int getKelvin() {
        return kelvin;
    }
    /**

     * Sets the kelvin value of the smart lamp.
     *
     * @param kelvin the kelvin value to be set
     */
    public void setKelvin(int kelvin) {
        this.kelvin = kelvin;
    }
    /**

     * Returns the brightness value of the smart lamp.
     *
     * @return the brightness value of the smart lamp
     */
    public int getBrightness() {
        return brightness;
    }
    /**

     * Sets the brightness value of the smart lamp.
     *
     * @param brightness the brightness value to be set
     */
    public void setBrightness(int brightness) {
        this.brightness = brightness;
    }
}