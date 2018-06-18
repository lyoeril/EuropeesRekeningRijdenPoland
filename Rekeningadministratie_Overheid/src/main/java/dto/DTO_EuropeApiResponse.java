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
public class DTO_EuropeApiResponse {
    private long uuid;
    private Double tripTotalKM;
    private Double tripPrice;
    
    public DTO_EuropeApiResponse(Cartracker cartracker) {
        this.uuid = cartracker.getId();
        this.tripTotalKM = cartracker.getHardware();
    }

    public long getId() {
        return uuid;
    }

    public void setId(long id) {
        this.uuid = id;
    }

    public String getHardware() {
        return hardware;
    }

    public void setHardware(String hardware) {
        this.hardware = hardware;
    }
}
