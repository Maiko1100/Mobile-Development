package com.testapplication.wfcmainpage.models;

/**
 * Created by Nick Zwaans on 3-3-2015.
 * Provides een standaard class voor een faciliteit. Array sFacilities uit de main wordt gevult met onderstaande attributen.
 */
public class Facility {

	private int id;
	private String facilityNaam;
	private String telefoonNummer;
	private String website;
    private String tower;
    private String etage;
    private String showRoom;
    private String email;
    private String damesMode;
    private String herenMode;
    private String kinderMode;
    private String Accessoires;
    private String voorraad;
    private String xlDames;
    private String xlHeren;
    private String sportKleding;
    private String bruidsKleding;
    private String babySpullen;
    private String badMode;

	public Facility() {
	}

    public Facility(String facilityNaam, String telefoonNummer, String website, String tower, String etage, String showRoom, String email, String damesMode, String herenMode, String kinderMode, String accessoires, String voorraad, String xlDames, String xlHeren, String sportKleding, String bruidsKleding, String babySpullen, String badMode) {
        this.facilityNaam = facilityNaam;
        this.telefoonNummer = telefoonNummer;
        this.website = website;
        this.tower = tower;
        this.etage = etage;
        this.showRoom = showRoom;
        this.email = email;
        this.damesMode = damesMode;
        this.herenMode = herenMode;
        this.kinderMode = kinderMode;
        Accessoires = accessoires;
        this.voorraad = voorraad;
        this.xlDames = xlDames;
        this.xlHeren = xlHeren;
        this.sportKleding = sportKleding;
        this.bruidsKleding = bruidsKleding;
        this.babySpullen = babySpullen;
        this.badMode = badMode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFacilityNaam() {
        return facilityNaam;
    }

    public void setFacilityNaam(String facilityNaam) {
        this.facilityNaam = facilityNaam;
    }

    public String getTelefoonNummer() {
        return telefoonNummer;
    }

    public void setTelefoonNummer(String telefoonNummer) {
        this.telefoonNummer = telefoonNummer;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getTower() {
        return tower;
    }

    public void setTower(String tower) {
        this.tower = tower;
    }

    public String getEtage() {
        return etage;
    }

    public void setEtage(String etage) {
        this.etage = etage;
    }

    public String getShowRoom() {
        return showRoom;
    }

    public void setShowRoom(String showRoom) {
        this.showRoom = showRoom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDamesMode() {
        return damesMode;
    }

    public void setDamesMode(String damesMode) {
        this.damesMode = damesMode;
    }

    public String getHerenMode() {
        return herenMode;
    }

    public void setHerenMode(String herenMode) {
        this.herenMode = herenMode;
    }

    public String getKinderMode() {
        return kinderMode;
    }

    public void setKinderMode(String kinderMode) {
        this.kinderMode = kinderMode;
    }

    public String getAccessoires() {
        return Accessoires;
    }

    public void setAccessoires(String accessoires) {
        Accessoires = accessoires;
    }

    public String getVoorraad() {
        return voorraad;
    }

    public void setVoorraad(String voorraad) {
        this.voorraad = voorraad;
    }

    public String getXlDames() {
        return xlDames;
    }

    public void setXlDames(String xlDames) {
        this.xlDames = xlDames;
    }

    public String getXlHeren() {
        return xlHeren;
    }

    public void setXlHeren(String xlHeren) {
        this.xlHeren = xlHeren;
    }

    public String getSportKleding() {
        return sportKleding;
    }

    public void setSportKleding(String sportKleding) {
        this.sportKleding = sportKleding;
    }

    public String getBruidsKleding() {
        return bruidsKleding;
    }

    public void setBruidsKleding(String bruidsKleding) {
        this.bruidsKleding = bruidsKleding;
    }

    public String getBabySpullen() {
        return babySpullen;
    }

    public void setBabySpullen(String babySpullen) {
        this.babySpullen = babySpullen;
    }

    public String getBadMode() {
        return badMode;
    }

    public void setBadMode(String badMode) {
        this.badMode = badMode;
    }
    public boolean isEmpty(String test) {
        if (test.isEmpty()) {
            return true;
        } else {
            return false;
        }


    }
    @Override
    public String toString() {
        return "Facility{" +
                "badMode='" + badMode + '\'' +
                ", babySpullen='" + babySpullen + '\'' +
                ", bruidsKleding='" + bruidsKleding + '\'' +
                ", sportKleding='" + sportKleding + '\'' +
                ", xlHeren='" + xlHeren + '\'' +
                ", xlDames='" + xlDames + '\'' +
                ", voorraad='" + voorraad + '\'' +
                ", Accessoires='" + Accessoires + '\'' +
                ", kinderMode='" + kinderMode + '\'' +
                ", herenMode='" + herenMode + '\'' +
                ", damesMode='" + damesMode + '\'' +
                ", email='" + email + '\'' +
                ", showRoom='" + showRoom + '\'' +
                ", etage='" + etage + '\'' +
                ", tower='" + tower + '\'' +
                ", website='" + website + '\'' +
                ", telefoonNummer='" + telefoonNummer + '\'' +
                ", facilityNaam='" + facilityNaam + '\'' +
                ", id=" + id +
                '}';
    }
}
