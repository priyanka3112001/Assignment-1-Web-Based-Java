package example.jdbc.bean;

import java.time.LocalDate;

public class Article {

    private int id;
    private String name;
    private String category;
    private LocalDate dateCreated;
    private String creatorName;

    public Article(int id, String name, String category, LocalDate dateCreated, String creatorName) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.dateCreated = dateCreated;
        this.creatorName = creatorName;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    @Override
    public String toString() {
        return "Article [id=" + id + ", name=" + name + ", category=" + category 
               + ", dateCreated=" + dateCreated + ", creatorName=" + creatorName + "]";
    }
}
