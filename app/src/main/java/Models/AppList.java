package Models;

import android.graphics.drawable.Drawable;

public class AppList {

    String appName,appPackage;
    Drawable icon_Image;

    public AppList(String appName, String appPackage, Drawable icon_Image) {
        this.appName = appName;
        this.appPackage = appPackage;
        this.icon_Image = icon_Image;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppPackage() {
        return appPackage;
    }

    public void setAppPackage(String appPackage) {
        this.appPackage = appPackage;
    }

    public Drawable getIcon_Image() {
        return icon_Image;
    }

    public void setIcon_Image(Drawable icon_Image) {
        this.icon_Image = icon_Image;
    }
}


