package es.jdamiancabello.inventory.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(
        indices = {@Index(value = {"dependency_pk"},unique = true)},
        foreignKeys = @ForeignKey(entity = Dependency.class,childColumns = "dependency_pk",
        parentColumns = "shortName",onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE
))
public class Sector implements Parcelable {
    @NonNull
    private String name;
    @PrimaryKey
    @NonNull
    private String shortName;
    @NonNull
    private String dependency_pk;
    @NonNull
    private String sectorDescription;
    @NonNull
    private String urlImage;

    @Ignore
    public Sector(){}

    public Sector(String name, String shortName, String dependency_pk, String sectorDescription, String urlImage) {
        this.name = name;
        this.shortName = shortName;
        this.dependency_pk = dependency_pk;
        this.sectorDescription = sectorDescription;
        this.urlImage = urlImage;
    }

    @Ignore
    protected Sector(Parcel in) {
        name = in.readString();
        shortName = in.readString();
        dependency_pk = in.readString();
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

    @NonNull
    public String getDependency_pk() {
        return dependency_pk;
    }

    public void setDependency_pk(@NonNull String dependency_pk) {
        this.dependency_pk = dependency_pk;
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
        dest.writeString(dependency_pk);
        dest.writeString(sectorDescription);
        dest.writeString(urlImage);
    }
}
