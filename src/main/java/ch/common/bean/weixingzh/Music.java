package ch.common.bean.weixingzh;

/**
 * Description:
 *
 * @author cy
 * @date 2019年01月21日 15:04
 * version 1.0
 */
public class Music {

    private String title;
    private String description;
    private String musicUrl;
    private String hqMusicUrl;


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

    public String getMusicUrl() {
        return musicUrl;
    }

    public void setMusicUrl(String musicUrl) {
        this.musicUrl = musicUrl;
    }

    public String getHqMusicUrl() {
        return hqMusicUrl;
    }

    public void setHqMusicUrl(String hqMusicUrl) {
        this.hqMusicUrl = hqMusicUrl;
    }
}
