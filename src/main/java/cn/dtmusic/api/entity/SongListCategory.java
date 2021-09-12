package cn.dtmusic.api.entity;

/**
 * @ description:
 * @ date:      2020/10/16
 * @ time:      14:41
 * @ author:    Zhang wei
 * @ since:     1.0.0
 */
public class SongListCategory {
    private Integer songListId;
    private Integer categoryId;

    public SongListCategory() {
    }

    public SongListCategory(Integer songListId, Integer categoryId) {
        this.songListId = songListId;
        this.categoryId = categoryId;
    }

    public Integer getSongListId() {
        return songListId;
    }

    public void setSongListId(Integer songListId) {
        this.songListId = songListId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
}
