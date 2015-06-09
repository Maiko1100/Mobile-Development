package com.testapplication.wfcmainpage.models;

/**
 * Created by Nick Zwaans on 3-3-2015.
 * Provides een standaard class voor een faciliteit. Array sFacilities uit de main wordt gevult met onderstaande attributen.
 */
public class Facility {

    private int id;
    private String mFacilityName;
    private String mTelefoonNummer;
    private String mWebsite;
    private String mTower;
    private String mEtage;
    private String mShowRoom;
    private String mEmail;
    private String mDamesMode;
    private String mHerenMode;
    private String mKinderMode;
    private String mAccessoires;
    private String mVoorraad;
    private String mXlDames;
    private String mXlHeren;
    private String mSportKleding;
    private String mBruidsKleding;
    private String mBabySpullen;
    private String mBadMode;
    private String mOntwerper;
    private String mDiversen;
    private String mQuickResponse;
    private String mCollectie;
    private String mOpAfspraak;

    public Facility() {
    }

    public Facility(String pFacilityName, String pTelefoonNummer, String pWebsite, String pTower, String pEtage,
                    String pShowRoom, String pEmail, String pDamesMode, String pHerenMode, String pKinderMode,
                    String pAccesoires, String pVoorraad, String pXlDames, String pXlHeren, String pSportKleding,
                    String pBruidsKleding, String pBabySpullen, String pBadMode, String pOntwerper, String pDiversen,
                    String pQuickResponse, String pCollectie, String pOpAfspraak) {
        mFacilityName = pFacilityName;
        mTelefoonNummer = pTelefoonNummer;
        mWebsite = pWebsite;
        mTower = pTower;
        mEtage = pEtage;
        mShowRoom = pShowRoom;
        mEmail = pEmail;
        mDamesMode = pDamesMode;
        mHerenMode = pHerenMode;
        mKinderMode = pKinderMode;
        mAccessoires = pAccesoires;
        mVoorraad = pVoorraad;
        mXlDames = pXlDames;
        mXlHeren = pXlHeren;
        mSportKleding = pSportKleding;
        mBruidsKleding = pBruidsKleding;
        mBabySpullen = pBabySpullen;
        mBadMode = pBadMode;
        mOntwerper = pOntwerper;
        mDiversen = pDiversen;
        mQuickResponse = pQuickResponse;
        mCollectie = pCollectie;
        mOpAfspraak = pOpAfspraak;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getmFacilityName() {
        return mFacilityName;
    }

    public void setmFacilityName(String mFacilityName) {
        this.mFacilityName = mFacilityName;
    }

    public String getmTelefoonNummer() {
        return mTelefoonNummer;
    }

    public void setmTelefoonNummer(String mTelefoonNummer) {
        this.mTelefoonNummer = mTelefoonNummer;
    }

    public String getmWebsite() {
        return mWebsite;
    }

    public void setmWebsite(String mWebsite) {
        this.mWebsite = mWebsite;
    }

    public String getmTower() {
        return mTower;
    }

    public void setmTower(String mTower) {
        this.mTower = mTower;
    }

    public String getmEtage() {
        return mEtage;
    }

    public void setmEtage(String mEtage) {
        this.mEtage = mEtage;
    }

    public String getmShowRoom() {
        return mShowRoom;
    }

    public void setmShowRoom(String mShowRoom) {
        this.mShowRoom = mShowRoom;
    }

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public String getmDamesMode() {
        return mDamesMode;
    }

    public void setmDamesMode(String mDamesMode) {
        this.mDamesMode = mDamesMode;
    }

    public String getmHerenMode() {
        return mHerenMode;
    }

    public void setmHerenMode(String mHerenMode) {
        this.mHerenMode = mHerenMode;
    }

    public String getmKinderMode() {
        return mKinderMode;
    }

    public void setmKinderMode(String mKinderMode) {
        this.mKinderMode = mKinderMode;
    }

    public String getmAccessoires() {
        return mAccessoires;
    }

    public void setmAccessoires(String mAccessoires) {
        this.mAccessoires = mAccessoires;
    }

    public String getmVoorraad() {
        return mVoorraad;
    }

    public void setmVoorraad(String mVoorraad) {
        this.mVoorraad = mVoorraad;
    }

    public String getmXlDames() {
        return mXlDames;
    }

    public void setmXlDames(String mXlDames) {
        this.mXlDames = mXlDames;
    }

    public String getmXlHeren() {
        return mXlHeren;
    }

    public void setmXlHeren(String mXlHeren) {
        this.mXlHeren = mXlHeren;
    }

    public String getmSportKleding() {
        return mSportKleding;
    }

    public void setmSportKleding(String mSportKleding) {
        this.mSportKleding = mSportKleding;
    }

    public String getmBruidsKleding() {
        return mBruidsKleding;
    }

    public void setmBruidsKleding(String mBruidsKleding) {
        this.mBruidsKleding = mBruidsKleding;
    }

    public String getmBabySpullen() {
        return mBabySpullen;
    }

    public void setmBabySpullen(String mBabySpullen) {
        this.mBabySpullen = mBabySpullen;
    }

    public String getmBadMode() {
        return mBadMode;
    }

    public void setmBadMode(String mBadMode) {
        this.mBadMode = mBadMode;
    }

    public String getmOntwerper (){ return mOntwerper; }

    public void setmOntwerper (String mOntwerper){ this.mOntwerper = mOntwerper; }

    public String getmDiversen (){ return mDiversen; }

    public void setmDiversen (String mDiversen){ this.mDiversen = mDiversen; }

    public String getmQuickResponse (){ return mQuickResponse; }

    public void setmQuickResponse (String mQuickResponse){ this.mQuickResponse = mQuickResponse; }

    public String getmCollectie (){ return mCollectie; }

    public void setmCollectie (String mCollectie){ this.mCollectie = mCollectie; }

    public String getmOpAfspraak (){ return mOpAfspraak; }

    public void setmOpAfspraak (String mOpAfspraak){ this.mOpAfspraak = mOpAfspraak; }


    public boolean isLeeg(int mode) {
        switch (mode) {
            case 0:
                return mDamesMode.isEmpty();
            case 1:
                return mHerenMode.isEmpty();
            case 2:
                return mKinderMode.isEmpty();
            case 3:
                return mAccessoires.isEmpty();
            case 4:
                return mVoorraad.isEmpty();
            case 5:
                return mXlDames.isEmpty();
            case 6:
                return mXlHeren.isEmpty();
            case 7:
                return mSportKleding.isEmpty();
            case 8:
                return mBruidsKleding.isEmpty();
            case 9:
                return mBabySpullen.isEmpty();
            case 10:
                return mBadMode.isEmpty();
            case 11:
                return mOntwerper.isEmpty();
            case 12:
                return mDiversen.isEmpty();
            case 13:
                return mQuickResponse.isEmpty();
            case 14:
                return mCollectie.isEmpty();
            case 15:
                return mOpAfspraak.isEmpty();
            default:
                return false;
        }
    }

    public boolean isListLeeg(int mode) {
        switch (mode) {
            case 1:
                return mDamesMode.isEmpty();
            case 2:
                return mHerenMode.isEmpty();
            case 3:
                return mKinderMode.isEmpty();
            case 4:
                return mAccessoires.isEmpty();
            case 5:
                return mXlDames.isEmpty();
            case 6:
                return mXlHeren.isEmpty();
            case 7:
                return mSportKleding.isEmpty();
            case 8:
                return mBruidsKleding.isEmpty();
            case 9:
                return mBabySpullen.isEmpty();
            case 10:
                return mBadMode.isEmpty();
            case 11:
                return mOntwerper.isEmpty();
            case 12:
                return mDiversen.isEmpty();
            case 13:
                return mQuickResponse.isEmpty();
            case 14:
                return mCollectie.isEmpty();
            case 15:
                return mOpAfspraak.isEmpty();
            default:
                return false;
        }
    }


    @Override
    public String toString() {
        return "Facility{" +
                "mBadMode='" + mBadMode + '\'' +
                ", mBabySpullen='" + mBabySpullen + '\'' +
                ", mBruidsKleding='" + mBruidsKleding + '\'' +
                ", mSportKleding='" + mSportKleding + '\'' +
                ", mXlHeren='" + mXlHeren + '\'' +
                ", mXlDames='" + mXlDames + '\'' +
                ", mVoorraad='" + mVoorraad + '\'' +
                ", mAccessoires='" + mAccessoires + '\'' +
                ", mKinderMode='" + mKinderMode + '\'' +
                ", mHerenMode='" + mHerenMode + '\'' +
                ", mDamesMode='" + mDamesMode + '\'' +
                ", mEmail='" + mEmail + '\'' +
                ", mShowRoom='" + mShowRoom + '\'' +
                ", mEtage='" + mEtage + '\'' +
                ", mTower='" + mTower + '\'' +
                ", mWebsite='" + mWebsite + '\'' +
                ", mTelefoonNummer='" + mTelefoonNummer + '\'' +
                ", mFacilityName='" + mFacilityName + '\'' +
                ", id=" + id +
                '}';
    }
}
