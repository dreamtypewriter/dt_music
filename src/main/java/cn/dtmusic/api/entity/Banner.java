package cn.dtmusic.api.entity;

public class Banner {

    private Integer bannerId;

    private Integer bannerNo;

    private String bannerPath;

    private Byte bannerStatus;

    public Integer getBannerId() {
        return bannerId;
    }

    public void setBannerId(Integer bannerId) {
        this.bannerId = bannerId;
    }

    public String getBannerPath() {
        return bannerPath;
    }

    public void setBannerPath(String bannerPath) {
        this.bannerPath = bannerPath;
    }

    public Byte getBannerStatus() {
        return bannerStatus;
    }

    public void setBannerStatus(Byte bannerStatus) {
        this.bannerStatus = bannerStatus;
    }

    public Integer getBannerNo() {
        return bannerNo;
    }

    public void setBannerNo(Integer bannerNo) {
        this.bannerNo = bannerNo;
    }
}
