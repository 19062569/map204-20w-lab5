package com.soogreyhounds.soogreyhoundsmobile;

public class Photo
{
    int id;
    private String uuid;
    private String title;
    private String url;
    private String note;
    private String mPerson;
    public String getPerson() {
        return mPerson;
    }
    public void setPerson(String person) {
        mPerson = person;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUUID() {
        return uuid;
    }

    public void setUUID(String uuid) {
        this.uuid = uuid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getURL() {
        return url;
    }

    public void setURL(String url) {
        this.url = url;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
