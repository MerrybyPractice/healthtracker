package com.example.healthtracker;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Diary {

    @PrimaryKey(autoGenerate = true)
    private long id;

    private String title;

    private String quantity;

    private String description;

    private String timestamp;

    public Diary(){};

    public Diary(String title, String quantity, String description, String timestamp){
        this.title = title;
        this.quantity = quantity;
        this.description = description;
        this.timestamp = timestamp;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
