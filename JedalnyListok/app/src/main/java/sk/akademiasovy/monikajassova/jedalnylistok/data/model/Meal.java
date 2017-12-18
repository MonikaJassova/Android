package sk.akademiasovy.monikajassova.jedalnylistok.data.model;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by monika.jassova on 11/29/2017.
 */

@Entity(tableName = "meals", foreignKeys = @ForeignKey(entity = MealCategory.class, parentColumns = "id", childColumns = "categoryId"))
public class Meal implements Parcelable {
    @PrimaryKey
    @NonNull
    private String id;
    private String name;
    private String categoryId;
    @Ignore
    private CategoryMeal category;
    @Embedded
    private ServingSize servingSize;
    @Ignore
    private String description;
    @Ignore
    private List<String> addOnIds;
    @Ignore
    private Integer displaySeq;

    public Meal(){

    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public Meal(String id, String name, ServingSize servingSize, CategoryMeal category){
        this.id = id;
        this.name = name;
        this.servingSize = servingSize;
        this.category = category;
        this.categoryId = category.getId();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CategoryMeal getCategory() {
        return category;
    }

    public void setCategory(CategoryMeal category) {
        this.category = category;
    }

    public ServingSize getServingSize() {
        return servingSize;
    }

    public void setServingSize(ServingSize servingSize) {
        this.servingSize = servingSize;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getAddOnIds() {
        return addOnIds;
    }

    public void setAddOnIds(List<String> addOnIds) {
        this.addOnIds = addOnIds;
    }

    public Integer getDisplaySeq() {
        return displaySeq;
    }

    public void setDisplaySeq(Integer displaySeq) {
        this.displaySeq = displaySeq;
    }

    protected Meal(Parcel in) {
        id = in.readString();
        name = in.readString();
        categoryId = in.readString();
        servingSize = (ServingSize) in.readValue(ServingSize.class.getClassLoader());
        if (in.readByte() == 0x01) {
            addOnIds = new ArrayList<String>();
            in.readList(addOnIds, String.class.getClassLoader());
        } else {
            addOnIds = null;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(categoryId);
        dest.writeValue(servingSize);
        if (addOnIds == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(addOnIds);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Meal> CREATOR = new Parcelable.Creator<Meal>() {
        @Override
        public Meal createFromParcel(Parcel in) {
            return new Meal(in);
        }

        @Override
        public Meal[] newArray(int size) {
            return new Meal[size];
        }
    };
}
