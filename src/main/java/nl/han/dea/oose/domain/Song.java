package nl.han.dea.oose.domain;

/**
 * Created by root on 22-3-17.
 */
public class Song extends Track {

    private String album;

    public Song(){}

    public Song(String preformer, String title, String url, float duration, String type , String album){
        super(preformer, title, url, duration, type);
        this.album = album;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }
}
