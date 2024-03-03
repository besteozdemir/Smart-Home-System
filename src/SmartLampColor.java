import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

/**
 * The SmartLampColor class represents a smart lamp device with color and brightness control.
 * It extends the Devices class and includes additional fields and methods for color and brightness control.
 */
public class SmartLampColor extends Devices {

    private String decimal;     // The decimal representation of the lamp's color
    private int kelvin = 4000;  // The color temperature of the lamp, in Kelvin
    private int brightness = 100;   // The brightness level of the lamp, as a percentage

    /**
     * Constructs a SmartLampColor object with the given name, initial status, decimal color value, and brightness level.
     *
     * @param name the name of the smart lamp
     * @param initialStatus the initial status of the smart lamp (on or off)
     * @param decimal the decimal representation of the color of the lamp (e.g. "#FF0000" for red)
     * @param brightness the initial brightness level of the lamp, as a percentage (0-100)
     */
    public SmartLampColor(String name, String initialStatus, String decimal, int brightness) {
        super(name, initialStatus);
        this.decimal = decimal;
        this.brightness = brightness;
    }

    /**
     * Constructs a SmartLampColor object with the given name and initial status.
     *
     * @param name the name of the smart lamp
     * @param initialStatus the initial status of the smart lamp (on or off)
     */
    public SmartLampColor(String name, String initialStatus) {
        super(name, initialStatus);
    }

    /**
     * Constructs a SmartLampColor object with the given name.
     *
     * @param name the name of the smart lamp
     */
    public SmartLampColor(String name) {
        super(name);
    }

    /**
     * Constructs a SmartLampColor object with the given name, initial status, color temperature, and brightness level.
     *
     * @param name the name of the smart lamp
     * @param initialStatus the initial status of the smart lamp (on or off)
     * @param kelvin the color temperature of the lamp, in Kelvin
     * @param brightness the initial brightness level of the lamp, as a percentage (0-100)
     */
    public SmartLampColor(String name, String initialStatus, int kelvin, int brightness) {
        super(name, initialStatus);
        this.kelvin = kelvin;
        this.brightness = brightness;
    }
    /**
     * Checks if a device with the given name is in the list of devices and is a Smart Lamp.
     * @param name the name of the device to check
     * @param devices the list of devices to search in
     * @return true if the device with the given name is a Smart Lamp in the list of devices, false otherwise
     */
    public static boolean isInSmartLampColor(String name, ArrayList<Devices> devices) {
        for (Devices device : devices) {
            if (device.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if a device with the given name is in the list of SmartLampColor devices.
     * Prints an error message if the device is not a Smart Lamp.
     * @param name the name of the device to check
     * @param devices the list of SmartLampColor devices to search in
     * @return true if the device with the given name is a Smart Lamp in the list of devices, false otherwise
     */
    public static boolean isASmartLampColor(String name, ArrayList<SmartLampColor> devices) {
        for (SmartLampColor device : devices) {
            if (device.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if a device with the given name is in the list of SmartLampColor devices.
     * @param name the name of the device to check
     * @param devices the list of SmartLampColor devices to search in
     * @return true if the device with the given name is a Smart Lamp in the list of devices, false otherwise
     */
    public static boolean isASmartLampColor2(String name, ArrayList<SmartLampColor> devices) {
        for (SmartLampColor device : devices) {
            if (device.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if a given string represents a hexadecimal value with a maximum length of 8 characters.
     * Prints an error message if the string is not a valid hexadecimal value.
     * @param decimal the string to check
     * @return true if the string represents a valid hexadecimal value, false otherwise
     */
    public static boolean checkDecimal(String decimal , String writeFile) {
        if (decimal.startsWith("0x") && decimal.length() <= 8) {
            String hexPattern = "^[0-9a-fA-F]+$";
            boolean isValid = decimal.substring(2).matches(hexPattern);
            if (!isValid) {
                FileWriting.writeToFile(writeFile ,"ERROR: Erroneous command!" , true , true );
            }
            return isValid;
        } else {
            FileWriting.writeToFile(writeFile ,"ERROR: Color code value must be in range of 0x0-0xFFFFFF!" , true , true );
            return false;
        }
    }

    /**
     * Checks if a given string represents the initial status of a Smart Lamp (either "On" or "Off").
     * Prints an error message if the string is neither "On" nor "Off".
     * @param initialstatus the string to check
     * @return true if the string represents a valid initial status, false otherwise
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
     * Checks if the provided Kelvin value is within the range of 2000K to 6500K.
     *
     * @param kelvin The Kelvin value to be checked
     * @return true if the Kelvin value is within the range of 2000K to 6500K, false otherwise
     */
    public static boolean checkKelvin(int kelvin) {
        if (kelvin <= 6500 && kelvin >= 2000) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if the provided brightness value is within the range of 0% to 100%.
     *
     * @param brightness The brightness value to be checked
     * @return true if the brightness value is within the range of 0% to 100%, false otherwise
     */
    public static boolean checkBrightness(int brightness) {
        if( brightness <= 100 && brightness >= 0) {
            return true;
        } else {
            return false;
        }
    }
    /**
     * This method checks if two SmartLamp objects are equal.
     *
     * @param obj the object to compare to.
     * @return true if the objects are equal, false otherwise.
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
     * This method gets the switch time of the SmartLamp.
     *
     * @return the switch time of the SmartLamp.
     */
    @Override
    public LocalDateTime getSwitchTime() {
        return super.getSwitchTime();
    }

    /**
     * This method sets the switch time of the SmartLamp.
     *
     * @param switchTime the switch time to set.
     */
    @Override
    public void setSwitchTime(LocalDateTime switchTime) {
        super.setSwitchTime(switchTime);
    }

    /**
     * This method returns a string representation of the SmartLamp object.
     *
     * @return the string representation of the SmartLamp object.
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
        if (decimal == null) {
            return "Smart Color Lamp " + getName() + " is " + a + " and its color value is " + getKelvin() + "K with " + getBrightness() + "% brightness, and its time to switch its status is " +  switchTime + ".";
        } else {
            return "Smart Color Lamp " + getName() + " is " + a + " and its color value is " + getDecimal() + " with " + getBrightness() + "% brightness, and its time to switch its status is " + switchTime + ".";
        }
    }

    /**
     * This method sets the name of the SmartLamp.
     *
     * @param name the name to set.
     */
    @Override
    public void setName(String name) {
        super.setName(name);
    }

    /**
     * This method gets the name of the SmartLamp.
     *
     * @return the name of the SmartLamp.
     */
    @Override
    public String getName() {
        return super.getName();
    }

    /**
     * This method gets the initial status of the SmartLamp.
     *
     * @return the initial status of the SmartLamp.
     */
    @Override
    public String getInitialStatus() {
        return super.getInitialStatus();
    }
    /**
     * Returns the brightness level of the SmartLamp.
     *
     * @return the brightness level
     */
    public int getBrightness() {
        return brightness;
    }

    /**
     * Sets the brightness level of the SmartLamp.
     *
     * @param brightness the new brightness level
     */
    public void setBrightness(int brightness) {
        this.brightness = brightness;
    }

    /**
     * Returns the color temperature in kelvins of the SmartLamp.
     *
     * @return the kelvin value
     */
    public int getKelvin() {
        return kelvin;
    }

    /**
     * Sets the color temperature in kelvins of the SmartLamp.
     *
     * @param kelvin the new kelvin value
     */
    public void setKelvin(int kelvin) {
        this.kelvin = kelvin;
    }

    /**
     * Returns the decimal value of the color of the SmartLamp.
     *
     * @return the decimal value of the color
     */
    public String getDecimal() {
        return decimal;
    }

    /**
     * Sets the decimal value of the color of the SmartLamp.
     *
     * @param decimal the new decimal value of the color
     */
    public void setDecimal(String decimal) {
        this.decimal = decimal;
    }
}