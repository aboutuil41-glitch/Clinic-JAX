package ma.youcode.clinic.Models;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Patient {

    private int id;
    private String Name;
    private String Firstname;
    private LocalDate birthDate;
    private String number;
    private String bloodPressure;
    private int heartRate;
    private double temperature;
    private int respiratoryRate;
    private LocalDateTime arrivalTime;

    public Patient(int id, String name, String firstname, LocalDate birthDate, String number,
            String bloodPressure, int heartRate, double temperature, int respiratoryRate,
            LocalDateTime arrivalTime) {
        this.id = id;
        Name = name;
        Firstname = firstname;
        this.birthDate = birthDate;
        this.number = number;
        this.bloodPressure = bloodPressure;
        this.heartRate = heartRate;
        this.temperature = temperature;
        this.respiratoryRate = respiratoryRate;
        this.arrivalTime = arrivalTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getFirstname() {
        return Firstname;
    }

    public void setFirstname(String firstname) {
        Firstname = firstname;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getBloodPressure() {
        return bloodPressure;
    }

    public void setBloodPressure(String bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public int getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(int heartRate) {
        this.heartRate = heartRate;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public int getRespiratoryRate() {
        return respiratoryRate;
    }

    public void setRespiratoryRate(int respiratoryRate) {
        this.respiratoryRate = respiratoryRate;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
}