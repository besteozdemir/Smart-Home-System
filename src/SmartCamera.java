import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
/**
 * The SmartCamera class represents a device that is capable of capturing images and videos and storing them in a certain amount of storage.
 * It extends the Devices class.
 */
public class SmartCamera extends Devices{
    private double megaBytes;
    public LocalDateTime onTime;
    private long minutes;
    private double megaBytes2;
    /**
     * Constructs a new SmartCamera object with the specified name, amount of storage in mega bytes and initial status.
     *
     * @param name the name of the SmartCamera.
     * @param megaBytes the amount of storage in mega bytes that the SmartCamera has.
     * @param initialStatus the initial status of the SmartCamera.
     */
    public SmartCamera(String name , double megaBytes , String initialStatus) {
        super(name , initialStatus);
        this.megaBytes = megaBytes;
    }
    /**
     * Constructs a new SmartCamera object with the specified name and amount of storage in mega bytes.
     *
     * @param name the name of the SmartCamera.
     * @param megaBytes the amount of storage in mega bytes that the SmartCamera has.
     */
    public SmartCamera(String name , double megaBytes) {
        super(name);
        this.megaBytes = megaBytes;
    }
    /**
     * Constructs a new SmartCamera object with the specified name.
     *
     * @param name the name of the SmartCamera.
     */
    public SmartCamera(String name) {
        super(name);
    }
    /**
     * This method checks if the initial status is valid
     *
     * @param initialstatus The initial status of the device
     * @return boolean indicating if initial status is valid or not
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
     * This method checks if a device with a given name exists in the smart camera devices list
     *
     * @param name The name of the device to check for
     * @param devices The list of devices in the smart camera system
     * @return boolean indicating if the device is present or not
     */
    public static boolean isInSmartCamera(String name, ArrayList<Devices> devices) {
        for (Devices device : devices) {
            if (device.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }
    /**
     * This method checks if the number of megabytes is valid (positive number)
     *
     * @param megabytes The number of megabytes to check
     * @return boolean indicating if the megabyte value is valid or not
     */
    public static boolean checkMegabytes(double megabytes) {
        if(megabytes > 0) {
            return true;
        } else {
            return false;
        }
    }
    /**
     * This method overrides the equals method to check if the SmartCamera object is equal to another object by comparing their names
     *
     * @param obj The object to compare to
     * @return boolean indicating if the two objects are equal or not
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof SmartCamera) {
            SmartCamera other = (SmartCamera) obj;
            return this.getName().equals(other.getName());
        }
        return false;
    }

    /**
     * This method overrides the toString method to return a string representation of the SmartCamera object
     *
     * @return String representation of the SmartCamera object
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
        return "Smart Camera " + getName() + " is " + a + " and used " + getMegaBytes2() + "MB of storage so far (excluding current device), and its time to switch its status is " + switchTime + ".";
    }

    /**
     * This method returns the switch time of the SmartCamera object
     *
     * @return LocalDateTime indicating the switch time
     */
    @Override
    public LocalDateTime getSwitchTime() {
        return super.getSwitchTime();
    }

    /**
     * This method sets the switch time of the SmartCamera object
     *
     * @param switchTime The LocalDateTime object to set as the switch time
     */
    @Override
    public void setSwitchTime(LocalDateTime switchTime) {
        super.setSwitchTime(switchTime);
    }

    /**
     * This method sets the name of the SmartCamera object
     *
     * @param name The String value to set as the name
     */
    @Override
    public void setName(String name) {
        super.setName(name);
    }

    /**
     * This method returns the name of the SmartCamera object
     *
     * @return String indicating the name of the SmartCamera object
     */
    @Override
    public String getName() {
        return super.getName();
    }
    /**
     * This method returns the current amount of megabytes used by the SmartCamera object
     *
     * @return the amount of megabytes used by the SmartCamera object
     */
    public double getMegaBytes2() {
        return megaBytes2;
    }

    /**
     * This method sets the amount of megabytes used by the SmartCamera object
     *
     * @param megaBytes2 the amount of megabytes to set
     */
    public void setMegaBytes2(double megaBytes2) {
        this.megaBytes2 = megaBytes2;
    }

    /**
     * This method returns the on time of the SmartCamera object
     *
     * @return LocalDateTime indicating the on time
     */
    public LocalDateTime getOnTime() {
        return onTime;
    }

    /**
     * This method sets the on time of the SmartCamera object
     *
     * @param onTime the LocalDateTime object to set as the on time
     */
    public void setOnTime(LocalDateTime onTime) {
        this.onTime = onTime;
    }

    /**
     * This method returns the amount of minutes the SmartCamera object has been on
     *
     * @return long indicating the amount of minutes the SmartCamera object has been on
     */
    public long getMinutes() {
        return minutes;
    }

    /**
     * This method sets the amount of minutes the SmartCamera object has been on
     *
     * @param minutes the amount of minutes to set
     */
    public void setMinutes(long minutes) {
        this.minutes = minutes;
    }

    /**
     * This method returns the current amount of megabytes used by the SmartCamera object (including the current device)
     *
     * @return the amount of megabytes used by the SmartCamera object (including the current device)
     */
    public double getMegaBytes() {
        return megaBytes;
    }

    /**
     * This method sets the amount of megabytes used by the SmartCamera object (including the current device)
     *
     * @param megaBytes the amount of megabytes to set
     */
    public void setMegaBytes(double megaBytes) {
        this.megaBytes = megaBytes;
    }

}
