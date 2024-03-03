import java.time.LocalDateTime;
import java.util.ArrayList;

public class Devices {
    private String name;
    private String initialStatus = "Off";
    private LocalDateTime switchTime;

    /**
     * Constructs a new Devices object with a specified name and initial status.
     *
     * @param name the name of the device
     * @param initialStatus the initial status of the device, either "On" or "Off"
     */
    public Devices(String name , String initialStatus) {
        this.initialStatus = initialStatus;
        this.name = name;
    }

    /**
     * Constructs a new Devices object with a specified name and default initial status of "Off".
     *
     * @param name the name of the device
     */
    public Devices(String name) {
        this.name = name;
    }
    /**
     * Returns true if this Devices object has the same name as the specified object.
     *
     * @param obj the object to compare to this Devices object
     * @return true if the specified object is equal to this Devices object, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Devices) {
            Devices other = (Devices) obj;
            return this.getName().equals(other.getName());
        }
        return false;
    }

    /**
     * Returns true if a Devices object with the specified name is in the list of devices.
     *
     * @param name the name of the device to search for
     * @param devices the list of devices to search in
     * @return true if a device with the specified name is in the list, false otherwise
     */
    public static boolean isInDevices(String name, ArrayList<Devices> devices) {
        for (Devices device : devices) {
            if (device.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the time at which this device was last switched on or off.
     *
     * @return the time at which this device was last switched on or off
     */
    public LocalDateTime getSwitchTime() {
        return switchTime;
    }

    /**
     * Sets the time at which this device was last switched on or off.
     *
     * @param switchTime the time at which this device was last switched on or off
     */
    public void setSwitchTime(LocalDateTime switchTime) {
        this.switchTime = switchTime;
    }

    /**
     * Returns the initial status of this device.
     *
     * @return the initial status of this device, either "On" or "Off"
     */
    public String getInitialStatus() {
        return initialStatus;
    }

    /**
     * Sets the initial status of this device.
     *
     * @param initialStatus the initial status of this device, either "On" or "Off"
     */
    public void setInitialStatus(String initialStatus) {
        this.initialStatus = initialStatus;
    }

    /**
     * Returns the name of this device.
     *
     * @return the name of this device
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of this device.
     *
     * @param name the new name for this device
     */
    public void setName(String name) {
        this.name = name;
    }
}