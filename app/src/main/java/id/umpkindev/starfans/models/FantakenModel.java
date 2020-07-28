package id.umpkindev.starfans.models;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class FantakenModel {

    private String title;
    private String description;
    private List<String> images;
    private String id;



    public FantakenModel(String id, Map<String,Object> data) {
        this.title = (String) data.get("title");
        this.description = (String) data.get("description");
        this.images = Arrays.asList(((String)(data.get("images"))).split(","));
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String poster(){
        return images.get(0);
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getImages() {
        return images;
    }

}
