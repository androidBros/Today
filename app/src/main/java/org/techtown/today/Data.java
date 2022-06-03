package org.techtown.today;

public class Data {


    private int ID;
    private String task;
    private Boolean check;
    private int date;
    private String searchTasks;

    public int getID(){ return ID; }

    public void setID(int ID){ this.ID = ID; }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public Boolean getCheck() {
        return check;
    }

    public void setCheck(Boolean check) {
        this.check = check;
    }

    public int getDate(){ return date; }

    public void setDate(int date){ this.date = date; }

    public String getSearchTasks() {
        return searchTasks;
    }

    public void setSearchTasks(String searchTasks) {
        this.searchTasks = searchTasks;
    }

}
