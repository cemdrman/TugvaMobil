package org.tugva.basaksehir.tugvabasaksehir.Models;

/**
 * Created by cdirman on 29.7.2016.
 */
public class Icerik {
    private int id;
    private String icerik;
    private String icerikBaslik;
    private String icerikUrl;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIcerik() {
        return icerik;
    }

    public void setIcerik(String icerik) {
        this.icerik = icerik;
    }

    public String getIcerikBaslik() {
        return icerikBaslik;
    }

    public void setIcerikBaslik(String icerikBaslik) {
        this.icerikBaslik = icerikBaslik;
    }

    public String getIcerikUrl() {
        return icerikUrl;
    }

    public void setIcerikUrl(String icerikUrl) {
        this.icerikUrl = icerikUrl;
    }
}
