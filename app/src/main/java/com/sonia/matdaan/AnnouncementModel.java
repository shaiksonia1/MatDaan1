package com.sonia.matdaan;


public class AnnouncementModel {

    public String heading;

    public AnnouncementModel(){

    }

    public AnnouncementModel(String heading, String content) {
        this.heading = heading;

    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }


}
