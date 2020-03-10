package com.example.njoroapp.Models;

public class Courses {
        private String cname, description,  image, category, cid, date, time;

    public Courses() {
    }
//Entity Courses contains the following attributes.
    public Courses(String cname, String description, String image,
            String category, String cid, String date, String time) {
        this.cname = cname;
        this.description = description;
     
        this.image = image;
        this.category = category;
        this.cid = cid;
        this.date = date;
        this.time = time;
    }
//this are the Get and Set methods for Courses.
// They get the values from the input section and update (Set) in the Courses section in the Database
        public String getCname() {
        return cname;
    }

        public void setCname(String cname) {
        this.cname = cname;
    }

        public String getDescription() {
        return description;
    }

        public void setDescription(String description) {
        this.description = description;
    }

        public String getImage() {
        return image;
    }

        public void setImage(String image) {
        this.image = image;
    }

        public String getCategory() {
        return category;
    }

        public void setCategory(String category) {
        this.category = category;
    }

        public String getcid() {
        return cid;
    }

        public void setcid(String cid) {
        this.cid = cid;
    }

        public String getDate() {
        return date;
    }

        public void setDate(String date) {
        this.date = date;
    }

        public String getTime() {
        return time;
    }

        public void setTime(String time) {
        this.time = time;
    }


}
