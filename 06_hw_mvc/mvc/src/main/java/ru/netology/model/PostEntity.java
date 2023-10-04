package ru.netology.model;

public class PostEntity {
    private int id;
    private String content;

    private boolean isRemove;

    public PostEntity() {
    }

    public PostEntity(int id, String content, boolean  isRemove) {
        this.id = id;
        this.content = content;
        this.isRemove = isRemove;
    }

    public int getId() {
        return id;
    }

    public boolean getIsRemoved() {
        return isRemove;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setIsRemoved(boolean isRemove) {
        this.isRemove = isRemove;
    }
}
