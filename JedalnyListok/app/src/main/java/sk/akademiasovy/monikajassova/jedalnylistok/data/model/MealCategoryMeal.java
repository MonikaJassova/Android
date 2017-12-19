package sk.akademiasovy.monikajassova.jedalnylistok.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by monika.jassova on 11/28/2017.
 */

public class MealCategoryMeal implements Parcelable {
    private String id;
    private String name;
    private Object photo;
    private CategoryMeal category;
    private ServingSize servingSize;
    private String description;
    private List<String> addOnIds;
    private Integer displaySeq;

    public MealCategoryMeal(){}

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Object getPhoto() {
        return photo;
    }

    public void setPhoto(final Object photo) {
        this.photo = photo;
    }

    public CategoryMeal getCategory() {
        return category;
    }

    public void setCategory(final CategoryMeal category) {
        this.category = category;
    }

    public ServingSize getServingSize() {
        return servingSize;
    }

    public void setServingSize(final ServingSize servingSize) {
        this.servingSize = servingSize;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public List<String> getAddOnIds() {
        return addOnIds;
    }

    public void setAddOnIds(final List<String> addOnIds) {
        this.addOnIds = addOnIds;
    }

    public Integer getDisplaySeq() {
        return displaySeq;
    }

    public void setDisplaySeq(final Integer displaySeq) {
        this.displaySeq = displaySeq;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    protected MealCategoryMeal(Parcel in) {
        id = in.readString();
        name = in.readString();
        photo = (Object) in.readValue(Object.class.getClassLoader());
        category = (CategoryMeal) in.readValue(CategoryMeal.class.getClassLoader());
        servingSize = (ServingSize) in.readValue(ServingSize.class.getClassLoader());
        description = in.readString();
        if (in.readByte() == 0x01) {
            addOnIds = new ArrayList<String>();
            in.readList(addOnIds, String.class.getClassLoader());
        } else {
            addOnIds = null;
        }
        displaySeq = in.readByte() == 0x00 ? null : in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeValue(photo);
        dest.writeValue(category);
        dest.writeValue(servingSize);
        dest.writeString(description);
        if (addOnIds == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(addOnIds);
        }
        if (displaySeq == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(displaySeq);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<MealCategoryMeal> CREATOR = new Parcelable.Creator<MealCategoryMeal>() {
        @Override
        public MealCategoryMeal createFromParcel(Parcel in) {
            return new MealCategoryMeal(in);
        }

        @Override
        public MealCategoryMeal[] newArray(int size) {
            return new MealCategoryMeal[size];
        }
    };
}
