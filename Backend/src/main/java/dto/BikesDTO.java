/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Bike;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Henrik
 */
public class BikesDTO {
    List<BikeDTO> all = new ArrayList<>();

    public BikesDTO(List<Bike> bikeEntities) {
        for (Bike bikeEntity : bikeEntities) {
            all.add(new BikeDTO(bikeEntity));
        }
    }

    public List<BikeDTO> getAll() {
        return all;
    }

    public void setAll(List<BikeDTO> all) {
        this.all = all;
    }
}
