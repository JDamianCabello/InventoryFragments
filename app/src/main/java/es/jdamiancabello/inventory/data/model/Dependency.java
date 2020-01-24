package es.jdamiancabello.inventory.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

/*
Clase modelo Dependency
 */
@Entity
public class Dependency implements Parcelable {
    @NonNull
    private String name;

    @PrimaryKey
    @NonNull
    private String shortName;
    @NonNull
    private String description;
    @NonNull
    private String uriImage;
    @NonNull
    private String inventory;


    public Dependency(String name, String shortName, String description,String inventory, String uriImage) {
        this.name = name;
        this.shortName = shortName;
        this.description = description;
        this.uriImage = uriImage;
        this.inventory = inventory;
    }

    @Ignore
    protected Dependency(Parcel in) {
        name = in.readString();
        shortName = in.readString();
        description = in.readString();
        uriImage = in.readString();
        inventory = in.readString();
    }

    public static final Creator<Dependency> CREATOR = new Creator<Dependency>() {
        @Override
        public Dependency createFromParcel(Parcel in) {
            return new Dependency(in);
        }

        @Override
        public Dependency[] newArray(int size) {
            return new Dependency[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUriImage() {
        return uriImage;
    }

    public void setUriImage(String uriImage) {
        this.uriImage = uriImage;
    }


    @Override
    public String toString() {
        return name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @NonNull
    public String getInventory() {
        return inventory;
    }

    public void setInventory(@NonNull String inventory) {
        this.inventory = inventory;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(shortName);
        dest.writeString(description);
        dest.writeString(uriImage);
        dest.writeString(inventory);
    }
}
