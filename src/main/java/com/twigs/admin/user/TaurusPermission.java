package com.twigs.admin.user;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "TAURUS_PERMISSION")
public class TaurusPermission implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "TAURUS_ID")
    private int taurusId;
    @Column(name = "NAME")
    private String name;

    public TaurusPermission() {
    }

    public TaurusPermission(int taurusId, String name) {
        this.taurusId = taurusId;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTaurusId() {
        return taurusId;
    }

    public void setTaurusId(int taurusId) {
        this.taurusId = taurusId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
