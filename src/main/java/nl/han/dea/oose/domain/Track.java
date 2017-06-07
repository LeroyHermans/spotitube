package nl.han.dea.oose.domain;

public class Track {

    protected String preformer;
    protected String title;
    protected String url;
    protected float duration;
    protected String type;

    public Availability availability;

    public Track(){}

    public Track(String preformer, String title){
        this.preformer = preformer;
        this.title = title;
    }

    public Track(String preformer, String title, String url, float duration, String type){
        this.preformer = preformer;
        this.title = title;
        this.url = url;
        this.duration = duration;
        this.type = type;
    }

    public String getPreformer() {
        return preformer;
    }

    public void setPreformer(String preformer) {
        this.preformer = preformer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType(){return type;}

    public void setType(String type) {this.type = type;}

    public float getDuration() {
        return duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }
}
