package id.umpkindev.starfans.models;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

// mapping data dari firestore ke sebuah class

public class EventModel {
    private String title;
    private String description;
    private List<String> images;
    private String id;

    public String getId() {
        return id;
    }

    public EventModel(String id, Map<String,Object> data) {
        this.title = (String) data.get("title");
        this.description = (String) data.get("description");
        this.images = Arrays.asList(((String) data.get("images")).split(","));
        this.id = id;
    }

    //untuk poster tampilan awal
    public String poster(){
        return images.get(0);
    }
    public String getDescription() {
        return description;
    }

    //untuk imagenya
    public List<String> getImages() {
        return images;
    }

    public String getTitle() {
        return title;
    }
}
