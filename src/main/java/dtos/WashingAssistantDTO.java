package dtos;

import entities.WashingAssistant;

import java.util.ArrayList;
import java.util.List;

public class WashingAssistantDTO {
    private long id;
    private String name;
    private String primaryLanguage;
    private int yearsOfExp;
    private int pricePrHour;

    public WashingAssistantDTO(long id, String name, String primaryLanguage, int yearsOfExp, int pricePrHour) {
        this.id = id;
        this.name = name;
        this.primaryLanguage = primaryLanguage;
        this.yearsOfExp = yearsOfExp;
        this.pricePrHour = pricePrHour;
    }

    public WashingAssistantDTO(WashingAssistant w) {
        //if (w.getId() != null)
            this.id = w.getId();
        this.name = w.getName();
        this.primaryLanguage = w.getPrimaryLanguage();
        this.yearsOfExp = w.getYearsOfExp();
        this.pricePrHour = w.getPricePrHour();
    }

    public WashingAssistantDTO( String name, String primaryLanguage, int yearsOfExp, int pricePrHour) {
        this.name = name;
        this.primaryLanguage = primaryLanguage;
        this.yearsOfExp = yearsOfExp;
        this.pricePrHour = pricePrHour;
    }



    public static List<WashingAssistantDTO> getDtos(List<WashingAssistant> ws){
        List<WashingAssistantDTO> wdtos = new ArrayList<>();
        ws.forEach(w->wdtos.add(new WashingAssistantDTO(w)));
        return wdtos;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrimaryLanguage() {
        return primaryLanguage;
    }

    public void setPrimaryLanguage(String primaryLanguage) {
        this.primaryLanguage = primaryLanguage;
    }

    public int getYearsOfExp() {
        return yearsOfExp;
    }

    public void setYearsOfExp(int yearsOfExp) {
        this.yearsOfExp = yearsOfExp;
    }

    public int getPricePrHour() {
        return pricePrHour;
    }

    public void setPricePrHour(int pricePrHour) {
        this.pricePrHour = pricePrHour;
    }


}
