package sk.akademiasovy.monikajassova.jedalnylistok.data.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by monika.jassova on 11/28/2017.
 */

public class ServingSize implements Parcelable {
    private String size;
    private Double price;

    public ServingSize(String size, Double price) {
        this.size = size;
        this.price = price;
    }

    public String getSize() {
        return size;
    }

    public void setSize(final String size) {
        this.size = size;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(final Double price) {
        this.price = price;
    }

    protected ServingSize(Parcel in) {
        size = in.readString();
        price = in.readByte() == 0x00 ? null : in.readDouble();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(size);
        if (price == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeDouble(price);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<ServingSize> CREATOR = new Parcelable.Creator<ServingSize>() {
        @Override
        public ServingSize createFromParcel(Parcel in) {
            return new ServingSize(in);
        }

        @Override
        public ServingSize[] newArray(int size) {
            return new ServingSize[size];
        }
    };
}
