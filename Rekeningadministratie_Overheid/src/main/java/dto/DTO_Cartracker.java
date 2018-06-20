/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import domain.Cartracker;

/**
 *
 * @author Laurent
 */
public class DTO_Cartracker {
    private long id;
    private String hardware;

    public DTO_Cartracker(Cartracker cartracker) {
        this.id = cartracker.getId();
        this.hardware = cartracker.getHardware();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getHardware() {
        return hardware;
    }

    public void setHardware(String hardware) {
        this.hardware = hardware;
    }
}
