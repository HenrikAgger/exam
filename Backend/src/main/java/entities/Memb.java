/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

/**
 *
 * @author Henrik
 */

@Entity
@NamedQuery(name = "Memb.deleteAllRows", query = "DELETE from Memb")
public class Memb implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String mName;
    private String sd;
    private String acc;

    public Memb() {
    }

    public Memb(String mName, String sd, String acc) {
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

    public void setmAcc(String acc) {
        this.acc = acc;
    }

    
}
