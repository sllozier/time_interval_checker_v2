package main;

/**
 * Name: Sarah L. Lozier
 * Class: CMSC 215 - 6380
 * Project: Project 4
 * Date: March 5th, 2024
 * Description: This class represents a time with hours, minutes, and a meridian
 * (AM or PM).
 * It implements the Comparable interface to allow comparison between time
 * objects.
 * The class ensures immutability and performs input validation when
 * constructing time objects.
 */

public class Time implements Comparable<Time> {

    private final int hour;
    private final int minute;
    private final String meridian;

    /**
     * Constructs a Time object using hour, minute, and meridian values.
     * Assumes inputs are valid as they are obtained from ComboBoxes.
     *
     * @param hour     the hour part of the time.
     * @param minute   the minute part of the time.
     * @param meridian the meridian indicator ("AM" or "PM").
     */
    public Time(int hour, int minute, String meridian) {
        this.hour = hour;
        this.minute = minute;
        this.meridian = meridian;
    }

    @Override
    public int compareTo(Time other) {
        System.out.println("THIS HOUR: " + this.hour);
        System.out.println("THIS MERIDIAN: " + this.meridian);
        // Conversion to a comparable value, considering AM/PM
        int thisTimeInMinutes = ((this.hour % 12) + (this.meridian.equals("PM") ? 12 : 0)) * 60 + this.minute;
        int otherTimeInMinutes = ((other.hour % 12) + (other.meridian.equals("PM") ? 12 : 0)) * 60 + other.minute;

        return Integer.compare(thisTimeInMinutes, otherTimeInMinutes);
    }

    @Override
    public String toString() {
        return String.format("%02d:%02d %s", hour, minute, meridian);
    }

    // private final int hour;
    // private final int minute;
    // private final String meridian;

    // public Time(int hour, int minute, String meridian) throws InvalidTime {
    // if (hour < 1 || hour > 12 || minute < 0 || minute > 59 ||
    // (!meridian.equals("AM") && !meridian.equals("PM"))) {
    // throw new InvalidTime("Invalid time format");
    // }
    // this.hour = hour;
    // this.minute = minute;
    // this.meridian = meridian;
    // }

    // public Time(String time) throws InvalidTime {
    // String[] parts = time.split("[: ]");
    // int hour = Integer.parseInt(parts[0]);
    // int minute = Integer.parseInt(parts[1]);
    // String meridian = parts[2];
    // if (hour < 1 || hour > 12 || minute < 0 || minute > 59 ||
    // (!meridian.equals("AM") && !meridian.equals("PM"))) {
    // throw new InvalidTime("Invalid time format");
    // }
    // this.hour = hour;
    // this.minute = minute;
    // this.meridian = meridian;
    // }

    // @Override
    // public int compareTo(Time other) {
    // // Convert both this time and the other time to a 24-hour format for
    // comparison
    // int thisMinutes = (this.hour % 12) + (this.meridian.equals("PM") ? 12 : 0);
    // int otherMinutes = (other.hour % 12) + (other.meridian.equals("PM") ? 12 :
    // 0);

    // thisMinutes = thisMinutes * 60 + this.minute; // Convert hours to minutes and
    // add minute for precise comparison
    // otherMinutes = otherMinutes * 60 + other.minute;

    // return Integer.compare(thisMinutes, otherMinutes);
    // }

    // @Override
    // public String toString() {
    // return String.format("%02d:%02d %s", hour, minute, meridian);
    // }

}
