/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Memb;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Henrik
 */
public class MembsDTO {
        List<MembDTO> all = new ArrayList<>();

    public MembsDTO(List<Memb> membEntities) {
        for (Memb membtestEntity : membEntities) {
            all.add(new MembDTO(membtestEntity));
        }
    }

    public List<MembDTO> getAll() {
        return all;
    }

    public void setAll(List<MembDTO> all) {
        this.all = all;
    }
}
