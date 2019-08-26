package org.learnings.hadoop;

public class NCDCRecordParser {
    private static final int MISSING_TEMP = 9999;

    private String year;
    private int airTemperature;
    private String quality;
    private boolean airTemperatureMalformed;

    public void parse(String record){
        this.year = record.substring(15,19);

        String airTemperatureString;
        if(record.charAt(87) == '+'){
            airTemperatureString = record.substring(88,92);
            airTemperature = Integer.parseInt(airTemperatureString);
        } else if(record.charAt(87) == '-') {
            airTemperatureString = record.substring(87,92);
            airTemperature = Integer.parseInt(airTemperatureString);
        } else {
            airTemperatureMalformed = true;
        }

        this.quality = record.substring(92,93);

    }

    public boolean isMissingTemperature(){
        return airTemperature == MISSING_TEMP;
    }

    public boolean isValidTemp(){
        return !airTemperatureMalformed && airTemperature != MISSING_TEMP && quality.matches("[01459]");
    }

    public boolean isAirTemperatureMalformed() {
        return airTemperatureMalformed;
    }

    public String getYear() {
        return year;
    }

    public int getYearInt() {
        return Integer.parseInt(year);
    }

    public int getAirTemperature() {
        return airTemperature;
    }

    public String getQuality() {
        return quality;
    }
}
