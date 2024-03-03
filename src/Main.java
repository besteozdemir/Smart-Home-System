import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
public class Main {
    public static ArrayList<Devices> devices = new ArrayList<>();
    public static ArrayList<SmartLamp> smartlamp = new ArrayList<>();
    public static ArrayList<SmartLampColor> smartlampcolor = new ArrayList<>();
    public static ArrayList<SmartCamera> smartcamera = new ArrayList<>();
    public static ArrayList<SmartPlug> smartplug = new ArrayList<>();
    public static ArrayList<Devices> onDevices = new ArrayList<>();
    public static ArrayList<SmartPlug> plugDevices = new ArrayList<>();
    public static List<Devices> sortdevices = new ArrayList<>();
    public static List<Devices> sortdevices2 = new ArrayList<>();
    public static ArrayList<Devices> nulldevices = new ArrayList<>();
    public static ArrayList<LocalDateTime> noptimes = new ArrayList<>();
    public static ArrayList<SmartCamera> devicesToRemove = new ArrayList<>();
    public static ArrayList<SmartPlug> devicesToRemove1 = new ArrayList<>();
    public static ArrayList<SmartLamp> devicesToRemove2 = new ArrayList<>();
    public static ArrayList<SmartLampColor> devicesToRemove3 = new ArrayList<>();
    public static LocalDateTime dateTime;
    public static void main(String[] args) {
        String readFile = args[0];
        String writeFile = args[1];
        FileWriting.writeToFile(writeFile, "", false, false);
        String[] lines = FileReading.readFile(readFile);
        for (int i = 0; i < lines.length; i++) {
            String[] arr = (lines[i].split("\t"));
            if (lines[0].startsWith("SetInitialTime")) {
                if (arr.length > 1) {
                    if (arr[0].equals("SetInitialTime")) {
                        FileWriting.writeToFile(writeFile, "COMMAND: " + lines[i], true, true);
                        if (i == 0) {
                            if (arr.length == 2) {
                                try {
                                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss");
                                    dateTime = LocalDateTime.parse(arr[1], formatter);
                                    FileWriting.writeToFile(writeFile, "SUCCESS: Time has been set to " + dateTime.format(formatter) + "!", true, true);
                                } catch (Exception e) {
                                    FileWriting.writeToFile(writeFile,  "ERROR: Format of the initial date is wrong! Program is going to terminate!", true, true);
                                    break;
                                }
                            } else {
                                FileWriting.writeToFile(writeFile,  "ERROR: First command must be set initial time! Program is going to terminate!", true, true);
                            }
                        } else {
                            FileWriting.writeToFile(writeFile,  "ERROR: Erroneous command!", true, true);
                        }
                    }
                    if (arr[0].equals("SetTime")) {
                        FileWriting.writeToFile(writeFile,  "COMMAND: " + lines[i], true, true);
                        if (arr.length == 2) {
                            try {
                                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss");
                                LocalDateTime newDateTime = LocalDateTime.parse(arr[1], formatter);
                                if (newDateTime.isAfter(dateTime)) {
                                    dateTime = newDateTime.withYear(newDateTime.getYear()).withMonth(newDateTime.getMonthValue()).withDayOfMonth(newDateTime.getDayOfMonth()).withHour(newDateTime.getHour()).withMinute(newDateTime.getMinute()).withSecond(newDateTime.getSecond());
                                } else if (newDateTime.equals(dateTime)) {
                                    FileWriting.writeToFile(writeFile,  "ERROR: There is nothing to change!", true, true);
                                } else {
                                    FileWriting.writeToFile(writeFile,  "ERROR: Time cannot be reversed!", true, true);
                                }
                            } catch (Exception e) {
                                FileWriting.writeToFile(writeFile,  "ERROR: Time format is not correct!", true, true);
                            }
                        } else {
                            FileWriting.writeToFile(writeFile,  "ERROR: Erroneous command!", true, true);
                        }
                    }
                    if (arr[0].equals("SkipMinutes")) {
                        FileWriting.writeToFile(writeFile,  "COMMAND: " + lines[i], true, true);
                        if (arr.length == 2) {
                            try {
                                if (Integer.parseInt(arr[1]) > 0) {
                                    dateTime = dateTime.plusMinutes(Integer.parseInt(arr[1]));
                                } else if (Integer.parseInt(arr[1]) == 0) {
                                    FileWriting.writeToFile(writeFile,  "ERROR: There is nothing to skip!", true, true);
                                } else if (Integer.parseInt(arr[1]) < 0) {
                                    FileWriting.writeToFile(writeFile,  "ERROR: Time cannot be reversed!", true, true);
                                }
                            } catch (Exception e) {
                                FileWriting.writeToFile(writeFile,  "ERROR: Erroneous command!", true, true);
                            }
                        } else {
                            FileWriting.writeToFile(writeFile,  "ERROR: Erroneous command!", true, true);
                        }
                    }
                    if (arr[1].equals("SmartLamp")) {
                        if (arr[0].equals("Add")) {
                            FileWriting.writeToFile(writeFile,  "COMMAND: " + lines[i], true, true);
                            switch (arr.length) {
                                case 3:
                                    if (SmartLamp.isInSmartLamp(arr[2], devices)) {
                                        FileWriting.writeToFile(writeFile,  "ERROR: There is already a smart device with same name!", true, true);
                                    } else {
                                        smartlamp.add(new SmartLamp(arr[2]));
                                        devices.add(new SmartLamp(arr[2]));
                                    }
                                    break;
                                case 4:
                                    if (SmartLamp.isInSmartLamp(arr[2], devices)) {
                                        FileWriting.writeToFile(writeFile,  "ERROR: There is already a smart device with same name!", true, true);
                                    } else if (SmartLamp.checkInitialStatus(arr[3])) {
                                        smartlamp.add(new SmartLamp(arr[2], arr[3]));
                                        devices.add(new SmartLamp(arr[2], arr[3]));
                                    } else {
                                        FileWriting.writeToFile(writeFile,"ERROR: Erroneous command!" , true ,true);
                                    }
                                    break;
                                case 6:
                                    if (SmartLamp.isInSmartLamp(arr[2], devices)) {
                                        FileWriting.writeToFile(writeFile,  "ERROR: There is already a smart device with same name!", true, true);
                                    } else if (SmartLamp.checkInitialStatus(arr[3])) {
                                        if (SmartLamp.checkKelvin(Integer.parseInt(arr[4]))) {
                                            if (SmartLamp.checkBrightness(Integer.parseInt(arr[5]))) {
                                                smartlamp.add(new SmartLamp(arr[2], arr[3], Integer.parseInt(arr[4]), Integer.parseInt(arr[5])));
                                                devices.add(new SmartLamp(arr[2], arr[3], Integer.parseInt(arr[4]), Integer.parseInt(arr[5])));
                                            }
                                            else {
                                                FileWriting.writeToFile(writeFile , "ERROR: Brightness must be in range of 0%-100%!" , true , true);
                                            }
                                        } else {
                                            FileWriting.writeToFile(writeFile , "ERROR: Kelvin value must be in range of 2000K-6500K!" , true , true);
                                        }
                                    } else {
                                        FileWriting.writeToFile(writeFile,"ERROR: Erroneous command!" , true , true );
                                    }
                                    break;
                                default:
                                    FileWriting.writeToFile(writeFile,  "ERROR: Erroneous command!", true, true);
                                    break;
                            }
                        }
                    }
                    if (arr[1].equals("SmartCamera")) {
                        if (arr[0].equals("Add")) {
                            FileWriting.writeToFile(writeFile, "COMMAND: " + lines[i], true, true);
                            switch (arr.length) {
                                case 4:
                                    if (SmartCamera.isInSmartCamera(arr[2], devices)) {
                                        FileWriting.writeToFile(writeFile,  "ERROR: There is already a smart device with same name!", true, true);
                                    } else if (SmartCamera.checkMegabytes(Double.parseDouble(arr[3]))) {
                                        smartcamera.add(new SmartCamera(arr[2], Double.parseDouble(arr[3])));
                                        devices.add(new SmartCamera(arr[2], Double.parseDouble(arr[3])));
                                        for (SmartCamera device : smartcamera) {
                                            if (device.getInitialStatus().equals("On")) {
                                                smartcamera.get(smartcamera.size() - 1).onTime = dateTime;
                                                onDevices.add(device);
                                            }
                                        }
                                    } else {
                                        FileWriting.writeToFile(writeFile,"ERROR: Megabyte value has to be a positive number!",true,true);
                                    }
                                    break;
                                case 5:
                                    if (SmartCamera.isInSmartCamera(arr[2], devices)) {
                                        FileWriting.writeToFile(writeFile,  "ERROR: There is already a smart device with same name!", true, true);
                                    } else{
                                        if (SmartCamera.checkMegabytes(Double.parseDouble(arr[3]))) {
                                            if (SmartCamera.checkInitialStatus(arr[4])) {
                                                smartcamera.add(new SmartCamera(arr[2], Double.parseDouble(arr[3]) , arr[4]));
                                                devices.add(new SmartCamera(arr[2], Double.parseDouble(arr[3]), arr[4]));
                                                for (SmartCamera device : smartcamera) {
                                                    if (device.getInitialStatus().equals("On")) {
                                                        smartcamera.get(smartcamera.size() - 1).onTime = dateTime;
                                                        onDevices.add(device);
                                                    }
                                                }
                                            } else {
                                                FileWriting.writeToFile(writeFile,"ERROR: Erroneous command!" , true ,true);
                                            }
                                        } else {
                                            FileWriting.writeToFile(writeFile,"ERROR: Megabyte value has to be a positive number!",true,true);
                                        }
                                    }
                                    break;
                                default:
                                    FileWriting.writeToFile(writeFile,  "ERROR: Erroneous command!", true, true);
                                    break;
                            }
                        }
                    }
                    if (arr[1].equals("SmartPlug")) {
                        if (arr[0].equals("Add")) {
                            FileWriting.writeToFile(writeFile, "COMMAND: " + lines[i], true, true);
                            switch (arr.length) {
                                case 3:
                                    if (SmartPlug.isInSmartPlug(arr[2], devices)) {
                                        FileWriting.writeToFile(writeFile,  "ERROR: There is already a smart device with same name!", true, true);
                                    } else {
                                        smartplug.add(new SmartPlug(arr[2]));
                                        devices.add(new SmartPlug(arr[2]));
                                    }
                                    break;
                                case 4:
                                    if (SmartPlug.isInSmartPlug(arr[2], devices)) {
                                        FileWriting.writeToFile(writeFile,  "ERROR: There is already a smart device with same name!", true, true);
                                    } else if (SmartPlug.checkInitialStatus(arr[3])) {
                                        smartplug.add(new SmartPlug(arr[2], arr[3]));
                                        devices.add(new SmartPlug(arr[2], arr[3]));
                                        for (SmartPlug device : smartplug) {
                                            if (device.getInitialStatus().equals("On")) {
                                                onDevices.add(device);
                                            }
                                        }
                                    } else {
                                        FileWriting.writeToFile(writeFile,"ERROR: Erroneous command!" , true ,true);
                                    }
                                    break;
                                case 5:
                                    if (SmartPlug.isInSmartPlug(arr[2], devices)) {
                                        FileWriting.writeToFile(writeFile,  "ERROR: There is already a smart device with same name!", true, true);
                                    } else if (SmartPlug.checkInitialStatus(arr[3])) {
                                        if (SmartPlug.checkAmper(Double.parseDouble(arr[4]))) {
                                            smartplug.add(new SmartPlug(arr[2], arr[3], Double.parseDouble(arr[4])));
                                            devices.add(new SmartPlug(arr[2], arr[3], Double.parseDouble(arr[4])));
                                            plugDevices.add(new SmartPlug(arr[2], arr[3], Double.parseDouble(arr[4])));
                                            for (SmartPlug device : smartplug) {
                                                if (device.getInitialStatus().equals("On")) {
                                                    device.onTime = dateTime;
                                                    onDevices.add(device);
                                                }
                                            }
                                        } else {
                                            FileWriting.writeToFile(writeFile,"ERROR: Ampere value must be a positive number!",true,true);
                                        }
                                    } else {
                                        FileWriting.writeToFile(writeFile,"ERROR: Erroneous command!" , true ,true);
                                    }
                                    break;
                                default:
                                    FileWriting.writeToFile(writeFile,  "ERROR: Erroneous command!", true, true);
                            }
                        }
                    }
                    if (arr[1].equals("SmartColorLamp")) {
                        if (arr[0].equals("Add")) {
                            FileWriting.writeToFile(writeFile, "COMMAND: " + lines[i], true, true);
                            switch (arr.length) {
                                case 3:
                                    if (SmartLampColor.isInSmartLampColor(arr[2], devices)) {
                                        FileWriting.writeToFile(writeFile,  "ERROR: There is already a smart device with same name!", true, true);
                                    } else {
                                        SmartLampColor smartLampColor = new SmartLampColor(arr[2]);
                                        smartlampcolor.add(smartLampColor);
                                        devices.add(smartLampColor);
                                    }
                                    break;
                                case 4:
                                    if (SmartLampColor.isInSmartLampColor(arr[2], devices)) {
                                        FileWriting.writeToFile(writeFile,  "ERROR: There is already a smart device with same name!", true, true);
                                    } else if (SmartLampColor.checkInitialStatus(arr[3])) {
                                        smartlampcolor.add(new SmartLampColor(arr[2] , arr[3]));
                                        devices.add(new SmartLampColor(arr[2] , arr[3]));
                                    } else {
                                        FileWriting.writeToFile(writeFile,"ERROR: Erroneous command!" , true ,true);
                                    }
                                    break;
                                case 6:
                                    if (arr[4].startsWith("0x")) {
                                        if (SmartLampColor.isInSmartLampColor(arr[2], devices)) {
                                            FileWriting.writeToFile(writeFile,  "ERROR: There is already a smart device with same name!", true, true);
                                        } else if (SmartLampColor.checkInitialStatus(arr[3])) {
                                            if (SmartLampColor.checkDecimal(arr[4] , writeFile)) {
                                                if (SmartLampColor.checkBrightness(Integer.parseInt(arr[5]))) {
                                                    smartlampcolor.add(new SmartLampColor(arr[2] , arr[3] , arr[4] , Integer.parseInt(arr[5])));
                                                    devices.add(new SmartLampColor(arr[2] , arr[3] , arr[4] , Integer.parseInt(arr[5])));
                                                }
                                                else {
                                                    FileWriting.writeToFile(writeFile , "ERROR: Brightness must be in range of 0%-100%!" , true , true);
                                                }
                                            }
                                        } else {
                                            FileWriting.writeToFile(writeFile,"ERROR: Erroneous command!" , true ,true);
                                        }
                                    } else {
                                        if (SmartLampColor.isInSmartLampColor(arr[2], devices)) {
                                            FileWriting.writeToFile(writeFile,  "ERROR: There is already a smart device with same name!", true, true);
                                        } else if (SmartLampColor.checkInitialStatus(arr[3])) {
                                            if (SmartLampColor.checkKelvin(Integer.parseInt(arr[4]))) {
                                                if (SmartLampColor.checkBrightness(Integer.parseInt(arr[5]))) {
                                                    smartlampcolor.add(new SmartLampColor(arr[2] , arr[3] , Integer.parseInt(arr[4]) , Integer.parseInt(arr[5])));
                                                    devices.add(new SmartLampColor(arr[2] , arr[3] , Integer.parseInt(arr[4]) , Integer.parseInt(arr[5])));
                                                }
                                                else {
                                                    FileWriting.writeToFile(writeFile , "ERROR: Brightness must be in range of 0%-100%!" , true , true);
                                                }
                                            } else {
                                                FileWriting.writeToFile(writeFile , "ERROR: Kelvin value must be in range of 2000K-6500K!" , true , true);
                                            }
                                        } else {
                                            FileWriting.writeToFile(writeFile,"ERROR: Erroneous command!" , true ,true);
                                        }
                                    }
                                    break;
                                default:
                                    FileWriting.writeToFile(writeFile,  "ERROR: Erroneous command!", true, true);
                            }
                        }
                    }
                    if (arr[0].equals("Remove")) {
                        FileWriting.writeToFile(writeFile, "COMMAND: " + lines[i], true, true);
                        if (arr.length == 2) {
                            for (SmartCamera device : smartcamera) {
                                if (arr[1].equals(device.getName())) {
                                    device.setInitialStatus("Off");
                                    if (onDevices.contains(device)) {
                                        Duration duration = Duration.between(device.getOnTime(), dateTime);
                                        long minutes = duration.toMinutes();
                                        device.setMinutes(minutes);
                                        device.setMegaBytes2(device.getMegaBytes() * device.getMinutes());
                                    }
                                    FileWriting.writeToFile(writeFile, "SUCCESS: Information about removed smart device is as follows:" , true, true);
                                    FileWriting.writeToFile(writeFile, String.valueOf((device)), true, true);
                                    devicesToRemove.add(device);
                                }
                            }
                            smartcamera.removeAll(devicesToRemove);
                            devices.removeAll(devicesToRemove);
                            for (SmartPlug device : smartplug) {
                                if(arr[1].equals(device.getName())) {
                                    device.setInitialStatus("Off");
                                    if (onDevices.contains(device)) {
                                        Duration duration = Duration.between(device.getOnTime(), dateTime);
                                        double minutes = duration.toMinutes();
                                        device.setHours(minutes / 60);
                                        device.setEnergy(220 * device.getAmper() * device.getHours());
                                    }
                                    FileWriting.writeToFile(writeFile, "SUCCESS: Information about removed smart device is as follows:" , true, true);
                                    FileWriting.writeToFile(writeFile, String.valueOf((device)), true, true);
                                    devicesToRemove1.add(device);
                                }
                            }
                            smartplug.removeAll(devicesToRemove1);
                            devices.removeAll(devicesToRemove1);
                            for (SmartLamp device : smartlamp) {
                                if(arr[1].equals(device.getName())) {
                                    device.setInitialStatus("Off");
                                    FileWriting.writeToFile(writeFile, "SUCCESS: Information about removed smart device is as follows:" , true, true);
                                    FileWriting.writeToFile(writeFile, String.valueOf((device)), true, true);
                                    devicesToRemove2.add(device);
                                }
                            }
                            smartlamp.removeAll(devicesToRemove2);
                            devices.removeAll(devicesToRemove2);
                            for (SmartLampColor device : smartlampcolor) {
                                if(arr[1].equals(device.getName())) {
                                    devicesToRemove3.add(device);
                                    device.setInitialStatus("Off");
                                    FileWriting.writeToFile(writeFile, "SUCCESS: Information about removed smart device is as follows:" , true, true);
                                    FileWriting.writeToFile(writeFile, String.valueOf((device)), true, true);
                                }
                            }
                            smartlampcolor.removeAll(devicesToRemove3);
                            devices.removeAll(devicesToRemove3);
                        } else {
                            FileWriting.writeToFile(writeFile,  "ERROR: Erroneous command!", true, true);
                        }
                    }
                    if (arr[0].equals("Switch")) {
                        FileWriting.writeToFile(writeFile, "COMMAND: " + lines[i], true, true);
                        if (arr.length == 3) {
                            if(Devices.isInDevices(arr[1] , devices)) {
                                for (SmartPlug device : smartplug) {
                                    if (arr[1].equals(device.getName())) {
                                        if (!arr[2].equals(device.getInitialStatus())) {
                                            device.setInitialStatus(arr[2]);
                                            if (device.getInitialStatus().equals("Off") && onDevices.contains(device) && plugDevices.contains(device)) {
                                                try {
                                                    Duration duration = Duration.between(device.onTime, dateTime);
                                                    double minutes = duration.toMinutes();
                                                    device.setHours(minutes / 60);
                                                    device.setEnergy(220 * device.getAmper() * device.getHours());
                                                } catch (Exception e) {}
                                            }
                                        } else {
                                            FileWriting.writeToFile(writeFile, "ERROR: This device is already switched " + device.getInitialStatus() + "!", true, true);
                                        }
                                    }
                                }
                                for (SmartCamera device : smartcamera) {
                                    if (arr[1].equals(device.getName())) {
                                        if (!arr[2].equals(device.getInitialStatus())) {
                                            device.setInitialStatus(arr[2]);
                                            if (device.getInitialStatus().equals("Off") && onDevices.contains(device)) {
                                                Duration duration = Duration.between(device.getOnTime(), dateTime);
                                                long minutes = duration.toMinutes();
                                                device.setMinutes(minutes);
                                                device.setMegaBytes2(device.getMegaBytes() * device.getMinutes());
                                            }
                                        } else {
                                            FileWriting.writeToFile(writeFile, "ERROR: This device is already switched " + device.getInitialStatus() + "!", true, true);
                                        }
                                    }
                                }
                                for (SmartLamp device : smartlamp) {
                                    if (arr[1].equals(device.getName())) {
                                        if (!arr[2].equals(device.getInitialStatus())) {
                                            device.setInitialStatus(arr[2]);
                                        } else {
                                            FileWriting.writeToFile(writeFile, "ERROR: This device is already switched " + device.getInitialStatus() + "!", true, true);
                                        }
                                    }
                                }
                                for (SmartLampColor device : smartlampcolor) {
                                    if (arr[1].equals(device.getName())) {
                                        if (!arr[2].equals(device.getInitialStatus())) {
                                            device.setInitialStatus(arr[2]);
                                        } else {
                                            FileWriting.writeToFile(writeFile, "ERROR: This device is already switched " + device.getInitialStatus() + "!", true, true);
                                        }
                                    }
                                }
                            } else {
                                FileWriting.writeToFile(writeFile, "ERROR: There is not such a device!", true, true);
                            }
                        } else {
                            FileWriting.writeToFile(writeFile,  "ERROR: Erroneous command!", true, true);
                        }
                    }
                    if (arr[0].equals("SetKelvin")) {
                        FileWriting.writeToFile(writeFile, "COMMAND: " + lines[i], true, true);
                        if (arr.length == 3) {
                            if (SmartLamp.isASmartLamp(arr[1], smartlamp) || SmartLampColor.isASmartLampColor2(arr[1], smartlampcolor)) {
                                if (SmartLamp.checkKelvin(Integer.parseInt(arr[2]))) {
                                    for (SmartLamp device : smartlamp) {
                                        if(arr[1].equals(device.getName())) {
                                            device.setKelvin(Integer.parseInt(arr[2]));
                                        }
                                    }
                                } else {
                                    FileWriting.writeToFile(writeFile , "ERROR: Kelvin value must be in range of 2000K-6500K!" , true , true);
                                }
                            } else {
                                FileWriting.writeToFile(writeFile,"ERROR: This device is not a smart lamp!",true,true);
                            }
                        }  else {
                            FileWriting.writeToFile(writeFile,  "ERROR: Erroneous command!", true, true);
                        }
                    }
                    if (arr[0].equals("SetBrightness")) {
                        FileWriting.writeToFile(writeFile, "COMMAND: " + lines[i], true, true);
                        if (arr.length == 3) {
                            if (SmartLamp.isASmartLamp(arr[1] , smartlamp) || SmartLampColor.isASmartLampColor2(arr[1] , smartlampcolor)) {
                                if (SmartLamp.checkBrightness(Integer.parseInt(arr[2]))) {
                                    for (SmartLamp device : smartlamp) {
                                        if (arr[1].equals(device.getName())) {
                                            device.setBrightness(Integer.parseInt(arr[2]));
                                        }
                                    }
                                } else {
                                    FileWriting.writeToFile(writeFile , "ERROR: Brightness must be in range of 0%-100%!" , true , true);
                                }
                            } else {
                                FileWriting.writeToFile(writeFile,"ERROR: This device is not a smart lamp!",true,true);
                            }
                        } else {
                            FileWriting.writeToFile(writeFile,  "ERROR: Erroneous command!", true, true);
                        }
                    }
                    if (arr[0].equals("SetWhite")) {
                        FileWriting.writeToFile(writeFile, "COMMAND: " + lines[i], true, true);
                        if (arr.length == 4) {
                            if (SmartLampColor.isASmartLampColor2(arr[1] , smartlampcolor) || SmartLamp.isASmartLamp(arr[1] , smartlamp)) {
                                if (SmartLampColor.checkKelvin(Integer.parseInt(arr[2]))) {
                                    if (SmartLampColor.checkBrightness(Integer.parseInt(arr[3]))) {
                                        for (SmartLampColor device : smartlampcolor) {
                                            if (arr[1].equals(device.getName())) {
                                                device.setKelvin(Integer.parseInt(arr[2]));
                                                device.setBrightness(Integer.parseInt(arr[3]));
                                            }
                                        }
                                    } else {
                                        FileWriting.writeToFile(writeFile , "ERROR: Brightness must be in range of 0%-100%!" , true , true);
                                    }
                                } else {
                                    FileWriting.writeToFile(writeFile , "ERROR: Kelvin value must be in range of 2000K-6500K!" , true , true);
                                }
                            } else {
                                FileWriting.writeToFile(writeFile,"ERROR: This device is not a smart lamp!",true,true);
                            }
                        } else {
                            FileWriting.writeToFile(writeFile,  "ERROR: Erroneous command!", true, true);
                        }
                    }
                    if (arr[0].equals("SetColorCode")) {
                        FileWriting.writeToFile(writeFile, "COMMAND: " + lines[i], true, true);
                        if (arr.length == 3) {
                            if (SmartLampColor.isASmartLampColor(arr[1], smartlampcolor)) {
                                if (SmartLampColor.checkDecimal(arr[2] , writeFile)) {
                                    for (SmartLampColor device : smartlampcolor) {
                                        if (arr[1].equals(device.getName())) {
                                            device.setDecimal(arr[2]);
                                        }
                                    }
                                }
                            } else {
                                FileWriting.writeToFile(writeFile , "ERROR: This device is not a smart color lamp!" , true , true);
                            }
                        } else {
                            FileWriting.writeToFile(writeFile,  "ERROR: Erroneous command!", true, true);
                        }
                    }
                    if (arr[0].equals("SetColor")) {
                        FileWriting.writeToFile(writeFile, "COMMAND: " + lines[i], true, true);
                        if (arr.length == 4) {
                            if (SmartLampColor.isASmartLampColor(arr[1], smartlampcolor)) {
                                if (SmartLampColor.checkDecimal(arr[2] , writeFile)) {
                                    if (SmartLampColor.checkBrightness(Integer.parseInt(arr[3]))) {
                                        for (SmartLampColor device : smartlampcolor) {
                                            if (arr[1].equals(device.getName())) {
                                                device.setDecimal(arr[2]);
                                                device.setBrightness(Integer.parseInt(arr[3]));
                                            }
                                        }
                                    } else {
                                        FileWriting.writeToFile(writeFile , "ERROR: Brightness must be in range of 0%-100%!" , true , true);
                                    }
                                }
                            } else {
                                FileWriting.writeToFile(writeFile,"ERROR: This device is not a smart color lamp!" , true ,true);
                            }
                        } else {
                            FileWriting.writeToFile(writeFile,  "ERROR: Erroneous command!", true, true);
                        }
                    }
                    if (arr[0].equals("SetSwitchTime")) {
                        FileWriting.writeToFile(writeFile, "COMMAND: " + lines[i], true, true);
                        if (arr.length == 3) {
                            try {
                                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss");
                                LocalDateTime time = LocalDateTime.parse(arr[2], formatter);
                                if (time.isAfter(dateTime)) {
                                    for (SmartCamera device : smartcamera) {
                                        if (device.getName().equals(arr[1])) {
                                            device.setSwitchTime(time.withYear(time.getYear()).withMonth(time.getMonthValue()).withDayOfMonth(time.getDayOfMonth()).withHour(time.getHour()).withMinute(time.getMinute()).withSecond(time.getSecond()));
                                        }
                                    }
                                    for (SmartPlug device : smartplug) {
                                        if (device.getName().equals(arr[1])) {
                                            device.setSwitchTime(time.withYear(time.getYear()).withMonth(time.getMonthValue()).withDayOfMonth(time.getDayOfMonth()).withHour(time.getHour()).withMinute(time.getMinute()).withSecond(time.getSecond()));
                                        }
                                    }
                                    for (SmartLamp device : smartlamp) {
                                        if (device.getName().equals(arr[1])) {
                                            device.setSwitchTime(time.withYear(time.getYear()).withMonth(time.getMonthValue()).withDayOfMonth(time.getDayOfMonth()).withHour(time.getHour()).withMinute(time.getMinute()).withSecond(time.getSecond()));
                                        }
                                    }
                                    for (SmartLampColor device : smartlampcolor) {
                                        if (device.getName().equals(arr[1])) {
                                            device.setSwitchTime(time.withYear(time.getYear()).withMonth(time.getMonthValue()).withDayOfMonth(time.getDayOfMonth()).withHour(time.getHour()).withMinute(time.getMinute()).withSecond(time.getSecond()));
                                        }
                                    }
                                } else {
                                    FileWriting.writeToFile(writeFile, "ERROR: Switch time cannot be in the past!", true, true);
                                }
                            } catch (Exception e) {
                                FileWriting.writeToFile(writeFile, "ERROR: Time format is not correct!", true, true);
                            }
                        } else {
                            FileWriting.writeToFile(writeFile,  "ERROR: Erroneous command!", true, true);
                        }
                    }
                    if (arr[0].equals("PlugIn")) {
                        FileWriting.writeToFile(writeFile, "COMMAND: " + lines[i], true, true);
                        if (arr.length == 3) {
                            if (SmartPlug.isASmartPlug(arr[1], smartplug)) {
                                if (!SmartPlug.isInPlugDevices(arr[1], plugDevices)) {
                                    if (SmartPlug.checkAmper(Double.parseDouble(arr[2]))) {
                                        for (SmartPlug device : smartplug) {
                                            if (arr[1].equals(device.getName())) {
                                                device.setAmper(Double.parseDouble(arr[2]));
                                                plugDevices.add(device);
                                                if (onDevices.contains(device) && plugDevices.contains(device)) {
                                                    device.onTime = dateTime;
                                                }
                                            }
                                        }
                                    } else {
                                        FileWriting.writeToFile(writeFile,"ERROR: Ampere value must be a positive number!",true,true);
                                    }
                                } else {
                                    FileWriting.writeToFile(writeFile,"ERROR: There is already an item plugged in to that plug!",true,true);
                                }
                            } else {
                                FileWriting.writeToFile(writeFile,"ERROR: This device is not a smart plug!",true,true);
                            }
                        } else {
                            FileWriting.writeToFile(writeFile,  "ERROR: Erroneous command!", true, true);
                        }
                    }
                    if (arr[0].equals("PlugOut")) {
                        FileWriting.writeToFile(writeFile, "COMMAND: " + lines[i], true, true);
                        if (arr.length == 2) {
                            if (SmartPlug.isASmartPlug(arr[1] , smartplug)) {
                                if (SmartPlug.isInPlugDevices2(arr[1] , plugDevices)) {
                                    for ( SmartPlug device : smartplug) {
                                        if (arr[1].equals(device.getName()) && onDevices.contains(device)) {
                                            Duration duration = Duration.between(device.onTime, dateTime);
                                            double minutes = duration.toMinutes();
                                            device.setHours(minutes / 60);
                                            device.setEnergy((220 * device.getAmper() * device.getHours()));
                                            plugDevices.remove(device);
                                            device.setAmper(0);
                                        } else {
                                            plugDevices.remove(device);
                                            device.setAmper(0);
                                        }
                                    }
                                } else {
                                    FileWriting.writeToFile(writeFile,"ERROR: This plug has no item to plug out from that plug!",true,true);
                                }
                            } else {
                                FileWriting.writeToFile(writeFile,"ERROR: This device is not a smart plug!",true,true);
                            }
                        } else {
                            FileWriting.writeToFile(writeFile,  "ERROR: Erroneous command!", true, true);
                        }
                    }
                    if (arr[0].equals("ChangeName")) {
                        FileWriting.writeToFile(writeFile, "COMMAND: " + lines[i], true, true);
                        if (arr.length == 3) {
                            if(arr[1].equals(arr[2])) {
                                FileWriting.writeToFile(writeFile, "ERROR: Both of the names are the same, nothing changed!", true, true);
                            } else {
                                if (Devices.isInDevices(arr[2] ,devices)) {
                                    FileWriting.writeToFile(writeFile, "ERROR: There is already a smart device with same name!", true, true);
                                } else {
                                    for (SmartLamp device : smartlamp) {
                                        if(arr[1].equals(device.getName())) {
                                            device.setName(arr[2]);
                                        }
                                    }
                                    for (SmartLampColor device : smartlampcolor) {
                                        if(arr[1].equals(device.getName())) {
                                            device.setName(arr[2]);
                                        }
                                    }
                                    for (SmartPlug device : smartplug) {
                                        if(arr[1].equals(device.getName())) {
                                            device.setName(arr[2]);
                                        }
                                    }
                                    for (SmartCamera device : smartcamera) {
                                        if(arr[1].equals(device.getName())) {
                                            device.setName(arr[2]);
                                        }
                                    }
                                }
                            }
                        } else {
                            FileWriting.writeToFile(writeFile,  "ERROR: Erroneous command!", true, true);
                        }
                    }
                } else {
                    if(arr[0].equals("SetInitialTime")) {
                        FileWriting.writeToFile(writeFile, "COMMAND: " + lines[i], true, true);
                        FileWriting.writeToFile(writeFile, "ERROR: First command must be set initial time! Program is going to terminate!", true, true);
                        break;
                    }
                    if (arr[0].equals("Nop")) {
                        FileWriting.writeToFile(writeFile, "COMMAND: " + lines[i], true, true);
                        try {
                            for (Devices device : sortdevices) {
                                if (device.getSwitchTime() != null) {
                                    noptimes.add(device.getSwitchTime());
                                }
                            }
                            for (LocalDateTime times : noptimes) {
                                if(times.isBefore(dateTime) || times.isEqual(dateTime)) {
                                    noptimes.remove(times);
                                }
                            }
                        } catch (Exception e) {}
                        try {
                            LocalDateTime closestDateTime = noptimes.get(0);
                            Duration closestDuration = Duration.between(dateTime, closestDateTime);
                            for (LocalDateTime time : noptimes) {
                                Duration duration = Duration.between(dateTime, time);
                                if (duration.isNegative()) {
                                    continue;
                                }
                                if (duration.compareTo(closestDuration) < 0) {
                                    closestDuration = duration;
                                    closestDateTime = time;
                                }
                            }
                            dateTime = closestDateTime;
                        } catch (Exception e) {
                            FileWriting.writeToFile(writeFile, "ERROR: There is nothing to switch!", true, true);
                        }
                    } else if (arr[0].equals("ZReport")) {
                        FileWriting.writeToFile(writeFile, "COMMAND: " + lines[i], true, true);
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss");
                        FileWriting.writeToFile(writeFile, "Time is:\t" + dateTime.format(formatter), true, true);
                        for (SmartPlug device : smartplug) {
                            try {
                                if (!sortdevices.contains(device)){
                                    if (!device.getSwitchTime().toString().equals("null")) {
                                        sortdevices.add(device);
                                    }
                                } else if (sortdevices.contains(device) && device.getSwitchTime() == null) {
                                    sortdevices.remove(device);
                                    sortdevices2.add(device);

                                }
                            } catch (Exception e) {}
                        }
                        for (SmartCamera device : smartcamera) {
                            try {
                                if (!sortdevices.contains(device)){
                                    if (!device.getSwitchTime().toString().equals("null")) {
                                        sortdevices.add(device);
                                    }
                                } else if (sortdevices.contains(device) && device.getSwitchTime() == null) {
                                    sortdevices.remove(device);
                                    sortdevices2.add(device);
                                }
                            } catch (Exception e) {}
                        }
                        for (SmartLamp device : smartlamp) {
                            try {
                                if (!sortdevices.contains(device)) {
                                    if (!device.getSwitchTime().toString().equals("null")) {
                                        sortdevices.add(device);
                                    }
                                } else if (sortdevices.contains(device) && device.getSwitchTime() == null) {
                                    sortdevices.remove(device);
                                    sortdevices2.add(device);
                                }
                            } catch (Exception e) {}
                        }
                        for (SmartLampColor device : smartlampcolor) {
                            try {
                                if (!sortdevices.contains(device)) {
                                    if (!device.getSwitchTime().toString().equals("null")) {
                                        sortdevices.add(device);
                                    }
                                } else if (sortdevices.contains(device) && device.getSwitchTime() == null) {
                                    sortdevices.remove(device);
                                    sortdevices2.add(device);
                                }
                            } catch (Exception e) {}
                        }
                        try {
                            sortdevices.sort(Comparator.comparing(Devices::getSwitchTime));
                            for (Devices device : sortdevices) {
                                if (device.getSwitchTime() != null) {
                                    if(device.getSwitchTime().isAfter(dateTime)) {
                                        FileWriting.writeToFile(writeFile, String.valueOf(device), true, true);
                                    } else if (device.getSwitchTime().equals(dateTime)) {
                                        device.setSwitchTime(null);
                                        nulldevices.add(device);

                                    } else {
                                        device.setSwitchTime(null);
                                        nulldevices.add(device);
                                    }
                                }
                            }
                        } catch (Exception e) {
                        }
                        for (Devices device : nulldevices) {
                            FileWriting.writeToFile(writeFile, String.valueOf(device), true, true);
                        }
                        for (SmartPlug device : smartplug) {
                            if (!sortdevices.contains(device) && !nulldevices.contains(device)) {
                                FileWriting.writeToFile(writeFile, String.valueOf(device), true, true);
                            }
                        }
                        for (SmartCamera device : smartcamera) {
                            if (!sortdevices.contains(device) && !nulldevices.contains(device)) {
                                FileWriting.writeToFile(writeFile, String.valueOf(device), true, true);
                            }
                        }
                        for (SmartLamp device : smartlamp) {
                            if (!sortdevices.contains(device) && !nulldevices.contains(device)) {
                                FileWriting.writeToFile(writeFile, String.valueOf(device), true, true);
                            }
                        }
                        for (SmartLampColor device : smartlampcolor) {
                            if (!sortdevices.contains(device) && !nulldevices.contains(device)) {
                                FileWriting.writeToFile(writeFile, String.valueOf(device), true, true);
                            }
                        }
                    } else {
                        FileWriting.writeToFile(writeFile, "COMMAND: " + lines[i], true, true);
                        FileWriting.writeToFile(writeFile,  "ERROR: Erroneous command!", true, true);
                    }
                }
            } else {
                FileWriting.writeToFile(writeFile, "COMMAND: " + lines[i], true, true);
                FileWriting.writeToFile(writeFile, "ERROR: First command must be set initial time! Program is going to terminate!", true, true);
                break;
            }
        }
    }
}