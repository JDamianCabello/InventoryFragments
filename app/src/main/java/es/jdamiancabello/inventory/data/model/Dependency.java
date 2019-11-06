package es.jdamiancabello.inventory.data.model;

/*
Clase modelo Dependency
 */
public class Dependency {
    private String name;
    private String shortName;
    private String description;
    private String uriImage;

    public Dependency(String name, String shortName, String description, String uriImage) {
        this.name = name;
        this.shortName = shortName;
        this.description = description;
        this.uriImage = uriImage;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUriImage() {
        return uriImage;
    }

    public void setUriImage(String uriImage) {
        this.uriImage = uriImage;
    }
}
