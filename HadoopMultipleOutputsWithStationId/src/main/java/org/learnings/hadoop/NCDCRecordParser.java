package org.learnings.hadoop;

public class NCDCRecordParser {
    private String year;
    private int airTemperature;
    private String quality;
    private String stationId;

    public void parse(String record){
        this.stationId = record.substring(5,15);
        this.year = record.substring(15,19);
        String airTempStr;
        if(record.charAt(87) == '+'){
            airTempStr = record.substring(88,92);
        } else {
            airTempStr = record.substring(87,92);
        }

        this.airTemperature = Integer.parseInt(airTempStr);
        this.quality = record.substring(92,93);
    }

    public boolean isValidTemp(){
        return  airTemperature != 9999 && quality.matches("[01459]]");
    }

    public String getYear() {
        return year;
    }

    public int getAirTemperature() {
        return airTemperature;
    }

    public String getStationId() {
        return stationId;
    }
}
