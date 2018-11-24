package fiu.idea.idea;

public class Idea {
    private String name;
    private String description;
    private String postedBy;


    public Idea(String name, String description, String postedBy) {
        this.name = name;
        this.description = description;
        this.postedBy = postedBy;
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

    public String getPostedBy() {
        return postedBy;
    }

    public void setPostedBy(String postedBy) {
        this.postedBy = postedBy;
    }
}
