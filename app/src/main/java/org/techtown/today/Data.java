package org.techtown.today;

import android.util.Log;

public class Data {

    private String task;
    private Boolean check;

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        Log.d("Data", "Data-------------"+task);
        this.task = task;
    }

    public Boolean getCheck() {
        return check;
    }

    public void setCheck(Boolean check) {
        Log.d("Data", "Data-------------"+check);
        this.check = check;
    }

}
