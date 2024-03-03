import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

/**
 * The SmartPlug class represents a device that can be plugged in and turned on or off.
 * It tracks the device's name, status (on/off), amperage, voltage, energy consumption,
 * and on-time in the form of a LocalDateTime object.
 */
public class SmartPlug extends Devices{

    /**
     * The amperage of the device in amps (A).
     */
    private double amper;

    /**
     * The voltage of the device in volts (V). The default value is 220V.
     */
    private double voltage = 220;

    /**
     * The amount of energy consumed by the device in kilowatt-hours (kWh).
     */
    private double energy;

    /**
     * The time the device was turned on, represented as a LocalDateTime object.
     */
    public LocalDateTime onTime;

    /**
     * The number of hours the device has been on for.
     */
    public double hours;

    /**
     * Constructs a SmartPlug object with the given name, initial status, and amperage.
     *
     * @param name the name of the device
     * @param initialStatus the initial status of the device (on/off)
     * @param amper the amperage of the device in amps (A)
     */
    public SmartPlug(String name , String initialStatus , double amper) {
        super(name , initialStatus);
        this.amper = amper;
    }

    /**
     * Constructs a SmartPlug object with the given name and initial status. The amperage
     * is set to 0.0 by default.
     *
     * @param name the name of the device
     * @param initialStatus the initial status of the device (on/off)
     */
    public SmartPlug(String name , String initialStatus) {
        super(name, initialStatus);
    }

    /**
     * Constructs a SmartPlug object with the given name. The initial status and amperage
     * are set to off and 0.0 respectively by default.
     *
     * @param name the name of the device
     */
    public SmartPlug(String name) {
        super(name);
    }
    /**
     * Checks if a device with the specified name exists in the list of devices.
     * @param name the name of the device to search for
     * @param devices the list of devices to search in
     * @return true if a device with the specified name exists in the list, false otherwise
     */
    public static boolean isInSmartPlug(String name, ArrayList<Devices> devices) {
        for (Devices device : devices) {
            if (device.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if a device with the specified name exists in the list of smart plugs.
     * @param name the name of the smart plug to search for
     * @param devices the list of smart plugs to search in
     * @return true if a smart plug with the specified name exists in the list, false otherwise
     */
    public static boolean isASmartPlug(String name, ArrayList<SmartPlug> devices) {
        for (SmartPlug device : devices) {
            if (device.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if a smart plug with the specified name already has a device plugged in.
     * @param name the name of the smart plug to check
     * @param devices the list of smart plugs to check in
     * @return true if a device is already plugged in to the smart plug, false otherwise
     */
    public static boolean isInPlugDevices(String name, ArrayList<SmartPlug> devices) {
        for (SmartPlug device : devices) {
            if (device.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if a smart plug with the specified name has a device plugged in to it.
     * @param name the name of the smart plug to check
     * @param devices the list of smart plugs to check in
     * @return true if a device is plugged in to the smart plug, false otherwise
     */
    public static boolean isInPlugDevices2(String name, ArrayList<SmartPlug> devices) {
        for (SmartPlug device : devices) {
            if (device.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the specified initial status is a valid value.
     * @param initialstatus the initial status to check
     * @return true if the initial status is "On" or "Off", false otherwise
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
     * Checks if the specified amper value is a valid value.
     * @param amper the amper value to check
     * @return true if the amper value is greater than 0, false otherwise
     */
    public static boolean checkAmper(double amper) {
        if(amper > 0) {
            return true;
        } else {
            return false;
        }
    }
    /**
     * Checks if this SmartPlug object is equal to the given object.
     *
     * @param obj the object to compare to this SmartPlug object
     * @return true if the given object is a SmartPlug object with the same name as this SmartPlug object, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof SmartPlug) {
            SmartPlug other = (SmartPlug) obj;
            return this.getName().equals(other.getName());
        }
        return false;
    }

    /**
     * Returns a string representation of this SmartPlug object.
     *
     * @return a string representation of this SmartPlug object
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
        return "Smart Plug " + getName() + " is " + a + " and consumed " + getEnergy()
                + "W so far (excluding current device), and its time to switch its status is " + switchTime + ".";
    }

    /**
     * Sets the name of this SmartPlug object to the given name.
     *
     * @param name the new name of this SmartPlug object
     */
    @Override
    public void setName(String name) {
        super.setName(name);
    }

    /**
     * Returns the name of this SmartPlug object.
     *
     * @return the name of this SmartPlug object
     */
    @Override
    public String getName() {
        return super.getName();
    }

    /**
     * Returns the time at which the status of this SmartPlug object is scheduled to be switched.
     *
     * @return the time at which the status of this SmartPlug object is scheduled to be switched, or null if there is no scheduled switch time
     */
    @Override
    public LocalDateTime getSwitchTime() {
        return super.getSwitchTime();
    }

    /**
     * Sets the time at which the status of this SmartPlug object is scheduled to be switched to the given time.
     *
     * @param switchTime the new switch time for this SmartPlug object
     */
    @Override
    public void setSwitchTime(LocalDateTime switchTime) {
        super.setSwitchTime(switchTime);
    }

    /**
     * Gets the number of hours the device has been operating.
     *
     * @return the number of hours the device has been operating
     */
    public double getHours() {
        return hours;
    }

    /**
     * Sets the number of hours the device has been operating.
     *
     * @param hours the number of hours the device has been operating
     */
    public void setHours(double hours) {
        this.hours = hours;
    }

    /**
     * Gets the date and time the device was turned on.
     *
     * @return the date and time the device was turned on
     */
    public LocalDateTime getOnTime() {
        return onTime;
    }

    /**
     * Sets the date and time the device was turned on.
     *
     * @param onTime the date and time the device was turned on
     */
    public void setOnTime(LocalDateTime onTime) {
        this.onTime = onTime;
    }

    /**
     * Gets the voltage of the device.
     *
     * @return the voltage of the device
     */
    public double getVoltage() {
        return voltage;
    }

    /**
     * Sets the voltage of the device.
     *
     * @param voltage the voltage of the device
     */
    public void setVoltage(double voltage) {
        this.voltage = voltage;
    }

    /**
     * Gets the amount of energy consumed by the device.
     *
     * @return the amount of energy consumed by the device
     */
    public double getEnergy() {
        return energy;
    }

    /**
     * Sets the amount of energy consumed by the device.
     *
     * @param energy the amount of energy consumed by the device
     */
    public void setEnergy(double energy) {
        this.energy = energy;
    }

    /**
     * Gets the amperage of the device.
     *
     * @return the amperage of the device
     */
    public double getAmper() {
        return amper;
    }

    /**
     * Sets the amperage of the device.
     *
     * @param amper the amperage of the device
     */
    public void setAmper(double amper) {
        this.amper = amper;
    }
}
