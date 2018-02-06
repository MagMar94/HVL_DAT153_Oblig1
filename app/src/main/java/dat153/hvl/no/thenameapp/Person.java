package dat153.hvl.no.thenameapp;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Magnus on 05.02.2018.
 */
@Entity
public class Person {

    public Person(String name, String imageFileName){
        this.name = name;
        this.imageFileName = imageFileName;
    }

    @PrimaryKey
    private int id;

    @ColumnInfo
    private String name;

    @ColumnInfo
    private String imageFileName;

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getImageFileName(){
        return imageFileName;
    }

    public void setImageFileName(String fileName){
        imageFileName = fileName;
    }
}
