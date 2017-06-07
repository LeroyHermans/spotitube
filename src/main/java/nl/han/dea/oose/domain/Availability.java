package nl.han.dea.oose.domain;

/**
 * Created by root on 22-3-17.
 */
public class Availability {

    private boolean offlineAvailable;

    public void toggle(){
        this.offlineAvailable =! this.offlineAvailable;
    }

    public boolean isOfflineAvailable(){
        return offlineAvailable;
    }

}
