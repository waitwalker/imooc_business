package com.example.lib_audio.mediaplayer.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Keep;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Unique;

import java.io.Serializable;

///
/// @name AudioBean
/// @description 歌曲实体类(Model)
/// @author liuca
/// @date 2020/8/15
///
@Entity public class AudioBean implements Serializable {

    private static final long serialVersionUID = -8849228294348905620L;


    @Id public String id;

    // 音频地址
    @NotNull @Unique public String mUrl;

    // 名称
    @NotNull public String name;

    // 作者
    @NotNull public String author;

    // 专辑
    @NotNull public String album;

    // 专辑信息
    @NotNull public String albumInfo;

    // 专辑封面地址
    @NotNull public String albumPic;

    // 时长
    @NotNull public String totalTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getmUrl() {
        return mUrl;
    }

    public void setmUrl(String mUrl) {
        this.mUrl = mUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getAlbumInfo() {
        return albumInfo;
    }

    public void setAlbumInfo(String albumInfo) {
        this.albumInfo = albumInfo;
    }

    public String getAlbumPic() {
        return albumPic;
    }

    public void setAlbumPic(String albumPic) {
        this.albumPic = albumPic;
    }

    public String getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(String totalTime) {
        this.totalTime = totalTime;
    }

    @Keep
    public AudioBean(String id, String mUrl, String name, String author, String album, String albumInfo, String albumPic, String totalTime) {
        this.id = id;
        this.mUrl = mUrl;
        this.name = name;
        this.author = author;
        this.album = album;
        this.albumInfo = albumInfo;
        this.albumPic = albumPic;
        this.totalTime = totalTime;
    }

    @Keep public AudioBean() {
    }

    ///
    /// @name equals
    /// @description 两个对象相等比较
    /// @author liuca
    /// @date 2020/8/15
    ///
    @Override public boolean equals(Object other) {
        if (other == null) return false;
        if (!(other instanceof AudioBean)) return false;
        return ((AudioBean) other).id.equals(this.id);
    }

    public String getMUrl() {
        return this.mUrl;
    }

    public void setMUrl(String mUrl) {
        this.mUrl = mUrl;
    }
}
