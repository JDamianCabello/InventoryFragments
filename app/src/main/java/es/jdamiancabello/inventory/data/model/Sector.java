package es.jdamiancabello.inventory.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Sector implements Parcelable {
    private String name;
    private String shortName;
    private Dependency dependency;
    private String sectorDescription;
    private String urlImage;

    public Sector(){}

    public Sector(String name, String shortName, Dependency dependency, String sectorDescription, String urlImage) {
        this.name = name;
        this.shortName = shortName;
        this.dependency = dependency;
        this.sectorDescription = sectorDescription;
        this.urlImage = urlImage;
    }

    protected Sector(Parcel in) {
        name = in.readString();
        shortName = in.readString();
        dependency = in.readParcelable(Dependency.class.getClassLoader());
        sectorDescription = in.readString();
        urlImage = in.readString();
    }

    public static final Creator<Sector> CREATOR = new Creator<Sector>() {
        @Override
        public Sector createFromParcel(Parcel in) {
            return new Sector(in);
        }

        @Override
        public Sector[] newArray(int size) {
            return new Sector[size];
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

    public Dependency getDependency() {
        return dependency;
    }

    public void setDependency(Dependency dependency) {
        this.dependency = dependency;
    }

    public String getSectorDescription() {
        return sectorDescription;
    }

    public void setSectorDescription(String sectorDescription) {
        this.sectorDescription = sectorDescription;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    @NonNull
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
        dest.writeParcelable(dependency, flags);
        dest.writeString(sectorDescription);
        dest.writeString(urlImage);
    }
}
