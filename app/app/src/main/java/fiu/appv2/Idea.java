package fiu.appv2;

public class Idea {
    private String name;
    private String description;


    public Idea(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Idea() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
