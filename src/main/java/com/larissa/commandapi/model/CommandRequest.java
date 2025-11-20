package com.larissa.commandapi.model;

import java.util.Map;

public class CommandRequest {
    
    private String type;
    private String deviceId;
    private Map<String, Object> payload;

    // Getters e Setters
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getDeviceId() { return deviceId; }
    public void setDeviceId(String deviceId) { this.deviceId = deviceId; }

    public Map<String, Object> getPayload() { return payload; }
    public void setPayload(Map<String, Object> payload) { this.payload = payload; }
}
