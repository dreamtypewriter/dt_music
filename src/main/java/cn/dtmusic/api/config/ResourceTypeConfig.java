package cn.dtmusic.api.config;

import java.util.HashMap;

/**
 * @ description:
 * @ date:      2020/10/4
 * @ time:      16:09
 * @ author:    Zhang wei
 * @ since:     1.0.0
 */
public class ResourceTypeConfig {
    static private final HashMap<Byte, String> typeNameMap = new HashMap<>();
    static private final HashMap<Byte, String> typeUrlMap = new HashMap<>();
    static public final byte TYPE_SONG = 1;
    static public final byte TYPE_SINGER = 2;
    static public final byte TYPE_SONG_LIST = 3;
    static public final byte TYPE_ALBUM = 4;
    static public final byte TYPE_SHARE = 5;
    static public final byte TYPE_COMMENT = 6;
    static public final byte TYPE_REPLY = 7;

    static {
        typeNameMap.put(TYPE_SONG, "单曲");
        typeNameMap.put(TYPE_SINGER, "歌手");
        typeNameMap.put(TYPE_SONG_LIST, "歌单");
        typeNameMap.put(TYPE_ALBUM, "专辑");
        typeNameMap.put(TYPE_SHARE, "动态");
        typeNameMap.put(TYPE_COMMENT, "评论");
        typeNameMap.put(TYPE_REPLY, "回复");
        // 资源类型对应url
        typeUrlMap.put(TYPE_SONG, "/song/content");
        typeUrlMap.put(TYPE_SINGER, "/singer/content");
        typeUrlMap.put(TYPE_SONG_LIST, "/songList/content");
        typeUrlMap.put(TYPE_ALBUM, "/album/content");
    }

    public static String getResourceTypeName(Byte resourceType) {
        return typeNameMap.get(resourceType);
    }

    public static String getResourceUrl(Byte resourceType, Integer resourceId) {
        return typeUrlMap.get(resourceType) + "?id=" + resourceId;
    }
}
