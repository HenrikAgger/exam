/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Memb;
import java.util.Objects;
/**
 *
 * @author Henrik
 */
public class MembDTO {

    private Long id;
    private String mName;
    private String sd;
    private String acc;

    public MembDTO() {
    }

    public MembDTO(Memb m) {
        this.mName = m.getmName();
        this.sd = m.getSd();
        this.acc = m.getAcc();
        this.id = m.getId();        
    }

    public MembDTO(String mName, String sd, String acc) {
        this.mName = mName;
        this.sd = sd;
        this.acc = acc;
    }
    
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getSd() {
        return sd;
    }

    public void setSd(String sd) {
        this.sd = sd;
    }

    public String getAcc() {
        return acc;
    }

    public void setAcc(String acc) {
        this.acc = acc;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.id);
        hash = 29 * hash + Objects.hashCode(this.mName);
        hash = 29 * hash + Objects.hashCode(this.sd);
        hash = 29 * hash + Objects.hashCode(this.acc);
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
        final MembDTO other = (MembDTO) obj;
        if (!Objects.equals(this.mName, other.mName)) {
            return false;
        }
        if (!Objects.equals(this.sd, other.sd)) {
            return false;
        }
        if (!Objects.equals(this.acc, other.acc)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }


    
    
    

}
