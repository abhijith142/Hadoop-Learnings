package org.learnings.hadoop;

public class NCDCRecordParser {
    private static final int MISSIN_TEMP = 9999;

    private String year;
    private int airTemperature;
    private String quality;

    public void parse(String record){
        this.year = record.substring(15,19);

        String airTempString;
        if(record.charAt(87) == '+'){
            airTempString = record.substring(88,92);
        } else {
            airTempString = record.substring(87,92);
        }

        this.airTemperature = Integer.parseInt(airTempString);

        this.quality = record.substring(92,93);

    }

    public boolean isValidTemp(){
        return airTemperature != MISSIN_TEMP && quality.matches("[01459]");
    }

    public String getYear() {
        return year;
    }

    public int getAirTemperature() {
        return airTemperature;
    }
}
