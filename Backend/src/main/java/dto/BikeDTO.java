/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Bike;
import java.util.Objects;

/**
 *
 * @author Henrik
 */
public class BikeDTO {

    private Long id;
    private String make;
    private double size;
    private String gender;
    private double gears;
    private double dayPrice;

    public BikeDTO() {
    }

    public BikeDTO(Bike b) {
        this.make = b.getMake();
        this.size = b.getSize();
        this.gender = b.getGender();
        this.gears = b.getGears();
        this.dayPrice = b.getDayPrice();
        this.id = b.getId();
    }

    public BikeDTO(String make, double size, String gender, double gears, double dayPrice) {
        this.make = make;
        this.size = size;
        this.gender = gender;
        this.gears = gears;
        this.dayPrice = dayPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public double getGears() {
        return gears;
    }

    public void setGears(double gears) {
        this.gears = gears;
    }

    public double getDayPrice() {
        return dayPrice;
    }

    public void setDayPrice(double dayPrice) {
        this.dayPrice = dayPrice;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 43 * hash + Objects.hashCode(this.id);
        hash = 43 * hash + Objects.hashCode(this.make);
        hash = 43 * hash + (int) (Double.doubleToLongBits(this.size) ^ (Double.doubleToLongBits(this.size) >>> 32));
        hash = 43 * hash + Objects.hashCode(this.gender);
        hash = 43 * hash + (int) (Double.doubleToLongBits(this.gears) ^ (Double.doubleToLongBits(this.gears) >>> 32));
        hash = 43 * hash + (int) (Double.doubleToLongBits(this.dayPrice) ^ (Double.doubleToLongBits(this.dayPrice) >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BikeDTO other = (BikeDTO) obj;
        if (Double.doubleToLongBits(this.size) != Double.doubleToLongBits(other.size)) {
            return false;
        }
        if (Double.doubleToLongBits(this.gears) != Double.doubleToLongBits(other.gears)) {
            return false;
        }
        if (Double.doubleToLongBits(this.dayPrice) != Double.doubleToLongBits(other.dayPrice)) {
            return false;
        }
        if (!Objects.equals(this.make, other.make)) {
            return false;
        }
        if (!Objects.equals(this.gender, other.gender)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
    
    
    
    
}
