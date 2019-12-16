package com.worthcare;

public class Medicines {

    public String name, image, Description;

    public Medicines(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public Medicines(String name, String image, String Description) {
        this.name = name;
        this.image = image;
        this.Description = Description;
    }
}
