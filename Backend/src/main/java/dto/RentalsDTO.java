/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Rental;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Henrik
 */
public class RentalsDTO {

    List<RentalDTO> all = new ArrayList<>();

    public RentalsDTO(List<Rental> rentalEntities) {
        for (Rental rentalEntity : rentalEntities) {
            all.add(new RentalDTO(rentalEntity));
        }
    }

    public List<RentalDTO> getAll() {
        return all;
    }

    public void setAll(List<RentalDTO> all) {
        this.all = all;
    }
}
