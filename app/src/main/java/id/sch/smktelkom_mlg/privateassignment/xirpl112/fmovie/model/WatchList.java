package id.sch.smktelkom_mlg.privateassignment.xirpl112.fmovie.model;

import com.orm.SugarRecord;

import java.io.Serializable;

/**
 * Created by Faiqoh on 14/05/2017.
 */

public class WatchList extends SugarRecord implements Serializable {
    public String title;
    public String overview;
    public int color;

    public WatchList() {
    }

    public WatchList(String title, String overview, int color) {
        this.title = title;
        this.overview = overview;
        this.color = color;
    }
}
