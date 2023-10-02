package com.dharshni.jcomponent;

import android.os.Parcel;
import android.os.Parcelable;

public class AdData implements Parcelable {
    private String brand;
    private String title;
    private String description;
    private double price;
    private String category;
    private String condition;
    private String location;
    private String imageUrl;
    private boolean liked;

    private String email;

    private int phone;
    private String id;

    public AdData() {
        // Default constructor required for Firebase database
    }

    public AdData(String brand, String title, String description, double price, String category, String condition, String location, String imageUrl,String email,int phone) {
        this.brand = brand;
        this.title = title;
        this.description = description;
        this.price = price;
        this.category = category;
        this.condition = condition;
        this.location = location;
        this.imageUrl = imageUrl;
        this.email=email;
        this.phone=phone;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    // Parcelable implementation
    protected AdData(Parcel in) {
        brand = in.readString();
        title = in.readString();
        description = in.readString();
        price = in.readDouble();
        category = in.readString();
        condition = in.readString();
        location = in.readString();
        imageUrl = in.readString();
        phone=in.readInt();
        email=in.readString();
    }

    public static final Creator<AdData> CREATOR = new Creator<AdData>() {
        @Override
        public AdData createFromParcel(Parcel in) {
            return new AdData(in);
        }

        @Override
        public AdData[] newArray(int size) {
            return new AdData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(brand);
        dest.writeString(title);
        dest.writeString(description);
        dest.writeDouble(price);
        dest.writeString(category);
        dest.writeString(condition);
        dest.writeString(location);
        dest.writeString(imageUrl);
        dest.writeInt(phone);
        dest.writeString(email);

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
