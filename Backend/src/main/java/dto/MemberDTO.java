/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Member;
import java.util.Objects;
/**
 *
 * @author Henrik
 */
public class MemberDTO {

    private Long id;
    private String name;
    private String sd;
    private String acc;

    public MemberDTO() {
    }

    public MemberDTO(Member m) {
        this.name = m.getName();
        this.sd = m.getSd();
        this.acc = m.getAcc();
        this.id = m.getId();    
    }

    public MemberDTO(String name, String sd, String acc) {
        this.name = name;
        this.sd = sd;
        this.acc = acc;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + Objects.hashCode(this.name);
        hash = 97 * hash + Objects.hashCode(this.sd);
        hash = 97 * hash + Objects.hashCode(this.acc);
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
        final MemberDTO other = (MemberDTO) obj;
        if (!Objects.equals(this.name, other.name)) {
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
