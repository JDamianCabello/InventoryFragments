package es.jdamiancabello.inventory.data.model;

import android.os.Parcel;
import android.os.Parcelable;

/*
Clase modelo Dependency
 */
public class Dependency implements Parcelable {
    private String name;
    private String shortName;
    private String description;
    private String uriImage;
    private String year;

    public Dependency(String name, String shortName, String description, String year) {
        this.name = name;
        this.shortName = shortName;
        this.description = description;
        this.year = year;
    }

    public Dependency(String name, String shortName, String description,String year, String uriImage) {
        this.name = name;
        this.shortName = shortName;
        this.description = description;
        this.uriImage = uriImage;
    }

    protected Dependency(Parcel in) {
        name = in.readString();
        shortName = in.readString();
        description = in.readString();
        uriImage = in.readString();
        year = in.readString();
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

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(shortName);
        dest.writeString(description);
        dest.writeString(uriImage);
        dest.writeString(year);
    }
}
