package nl.han.dea.oose.domain;

import java.util.Calendar;

public class Video extends Track {

    private int playCount;
    private String publicationDate;
    private String description;

    public Video(){}

    public Video(String preformer, String title, String url, float duration, String type , int playCount, String cal, String description){
        super(preformer, title, url, duration, type);
        this.playCount = playCount;
        this.publicationDate = cal;
        this.description = description;
    }

    public int getPlayCount() {
        return playCount;
    }

    public void setPlayCount(int playCount) {
        this.playCount = playCount;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
