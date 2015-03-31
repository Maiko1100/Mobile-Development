package com.testapplication.wfcmainpage;

/**
 * Created by Nick Zwaans on 3-3-2015.
 * Provides een standaard class voor faciliteiten. Array sFacilities uit de main wordt gevult met onderstaande attributen.
 */
public class Facility {

	private int id;
	private String facilityNaam;
	private String telefoonNummer;
	private String website;

	public Facility() {
	}


	public Facility(String facilityNaam, String telefoonNummer, String website) {
		super();
		this.facilityNaam = facilityNaam;
		this.telefoonNummer = telefoonNummer;
		this.website = website;
	}

	//getters & setters

	public void setId(int id) {
		this.id = id;
	}


	public int getId() {

		return id;
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

	@Override
	public String toString() {
		return "Facility{" +
				"id=" + id +
				", facilityNaam='" + facilityNaam + '\'' +
				", telefoonNummer='" + telefoonNummer + '\'' +
				", website='" + website + '\'' +
				'}';
	}
}




