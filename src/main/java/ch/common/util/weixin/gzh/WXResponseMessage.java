package ch.common.util.weixin.gzh;

import ch.common.bean.weixingzh.Music;
import ch.common.bean.weixingzh.NewsItem;
import ch.common.bean.weixingzh.Video;

import java.util.Map;

/**
 * Description:
 *
 * @author cy
 * @date 2019年01月21日 14:58
 * version 1.0
 */
public class WXResponseMessage {

    /**
     * 构造文本消息
     * @param map 封装了解析结果的Map
     * @param content 文本消息内容
     * @return 文本消息XML字符串
     */
    public static String buildTextMessage(Map<String, String> map, String content) {
        //发送方帐号
        String fromUserName = map.get("FromUserName");
        // 开发者微信号
        String toUserName = map.get("ToUserName");
        /**
         * 文本消息XML数据格式
         * <xml>
         <ToUserName><![CDATA[toUser]]></ToUserName>
         <FromUserName><![CDATA[fromUser]]></FromUserName>
         <CreateTime>1348831860</CreateTime>
         <MsgType><![CDATA[text]]></MsgType>
         <Content><![CDATA[this is a test]]></Content>
         <MsgId>1234567890123456</MsgId>
         </xml>
         */
        return String.format(
                "<xml>" +
                        "<ToUserName><![CDATA[%s]]></ToUserName>" +
                        "<FromUserName><![CDATA[%s]]></FromUserName>" +
                        "<CreateTime>%s</CreateTime>" +
                        "<MsgType><![CDATA[text]]></MsgType>" +
                        "<Content><![CDATA[%s]]></Content>" +
                        "</xml>",
                fromUserName, toUserName,System.currentTimeMillis(), content);
    }


    /**
     * 构造图片消息
     * @param map 封装了解析结果的Map
     * @param mediaId 通过素材管理接口上传多媒体文件得到的id
     * @return 图片消息XML字符串
     */
    public static String buildImageMessage(Map<String, String> map, String mediaId) {
        //发送方帐号
        String fromUserName = map.get("FromUserName");
        // 开发者微信号
        String toUserName = map.get("ToUserName");
        /**
         * 图片消息XML数据格式
         *<xml>
         <ToUserName><![CDATA[toUser]]></ToUserName>
         <FromUserName><![CDATA[fromUser]]></FromUserName>
         <CreateTime>12345678</CreateTime>
         <MsgType><![CDATA[image]]></MsgType>
         <Image>
         <MediaId><![CDATA[media_id]]></MediaId>
         </Image>
         </xml>
         */
        return String.format(
                "<xml>" +
                        "<ToUserName><![CDATA[%s]]></ToUserName>" +
                        "<FromUserName><![CDATA[%s]]></FromUserName>" +
                        "<CreateTime>%s</CreateTime>" +
                        "<MsgType><![CDATA[image]]></MsgType>" +
                        "<Image>" +
                        "   <MediaId><![CDATA[%s]]></MediaId>" +
                        "</Image>" +
                        "</xml>",
                fromUserName, toUserName, System.currentTimeMillis(), mediaId);
    }


    /**
     * 构造音乐消息
     * @param map 封装了解析结果的Map
     * @param music 封装好的音乐消息内容
     * @return 音乐消息XML字符串
     */
    public static String buildMusicMessage(Map<String, String> map, Music music) {
        //发送方帐号
        String fromUserName = map.get("FromUserName");
        // 开发者微信号
        String toUserName = map.get("ToUserName");
        /**
         * 音乐消息XML数据格式
         *<xml>
         <ToUserName><![CDATA[toUser]]></ToUserName>
         <FromUserName><![CDATA[fromUser]]></FromUserName>
         <CreateTime>12345678</CreateTime>
         <MsgType><![CDATA[music]]></MsgType>
         <Music>
         <Title><![CDATA[TITLE]]></Title>
         <Description><![CDATA[DESCRIPTION]]></Description>
         <MusicUrl><![CDATA[MUSIC_Url]]></MusicUrl>
         <HQMusicUrl><![CDATA[HQ_MUSIC_Url]]></HQMusicUrl>
         <ThumbMediaId><![CDATA[media_id]]></ThumbMediaId>
         </Music>
         </xml>
         */
        return String.format(
                "<xml>" +
                        "<ToUserName><![CDATA[%s]]></ToUserName>" +
                        "<FromUserName><![CDATA[%s]]></FromUserName>" +
                        "<CreateTime>%s</CreateTime>" +
                        "<MsgType><![CDATA[music]]></MsgType>" +
                        "<Music>" +
                        "   <Title><![CDATA[%s]]></Title>" +
                        "   <Description><![CDATA[%s]]></Description>" +
                        "   <MusicUrl><![CDATA[%s]]></MusicUrl>" +
                        "   <HQMusicUrl><![CDATA[%s]]></HQMusicUrl>" +
                        "</Music>" +
                        "</xml>",
                fromUserName, toUserName, System.currentTimeMillis(), music.getTitle(), music.getDescription(), music.getMusicUrl(), music.getHqMusicUrl());
    }


    /**
     * 构造视频消息
     * @param map 封装了解析结果的Map
     * @param video 封装好的视频消息内容
     * @return 视频消息XML字符串
     */
    public static String buildVideoMessage(Map<String, String> map, Video video) {
        //发送方帐号
        String fromUserName = map.get("FromUserName");
        // 开发者微信号
        String toUserName = map.get("ToUserName");
        /**
         * 音乐消息XML数据格式
         *<xml>
         <ToUserName><![CDATA[toUser]]></ToUserName>
         <FromUserName><![CDATA[fromUser]]></FromUserName>
         <CreateTime>12345678</CreateTime>
         <MsgType><![CDATA[video]]></MsgType>
         <Video>
         <MediaId><![CDATA[media_id]]></MediaId>
         <Title><![CDATA[title]]></Title>
         <Description><![CDATA[description]]></Description>
         </Video>
         </xml>
         */
        return String.format(
                "<xml>" +
                        "<ToUserName><![CDATA[%s]]></ToUserName>" +
                        "<FromUserName><![CDATA[%s]]></FromUserName>" +
                        "<CreateTime>%s</CreateTime>" +
                        "<MsgType><![CDATA[video]]></MsgType>" +
                        "<Video>" +
                        "   <MediaId><![CDATA[%s]]></MediaId>" +
                        "   <Title><![CDATA[%s]]></Title>" +
                        "   <Description><![CDATA[%s]]></Description>" +
                        "</Video>" +
                        "</xml>",
                fromUserName, toUserName, System.currentTimeMillis(), video.getMediaId(), video.getTitle(), video.getDescription());
    }

    /**
     * 构造语音消息
     * @param map 封装了解析结果的Map
     * @param mediaId 通过素材管理接口上传多媒体文件得到的id
     * @return 语音消息XML字符串
     */
    public static String buildVoiceMessage(Map<String, String> map, String mediaId) {
        //发送方帐号
        String fromUserName = map.get("FromUserName");
        // 开发者微信号
        String toUserName = map.get("ToUserName");
        /**
         * 语音消息XML数据格式
         *<xml>
         <ToUserName><![CDATA[toUser]]></ToUserName>
         <FromUserName><![CDATA[fromUser]]></FromUserName>
         <CreateTime>12345678</CreateTime>
         <MsgType><![CDATA[voice]]></MsgType>
         <Voice>
         <MediaId><![CDATA[media_id]]></MediaId>
         </Voice>
         </xml>
         */
        return String.format(
                "<xml>" +
                        "<ToUserName><![CDATA[%s]]></ToUserName>" +
                        "<FromUserName><![CDATA[%s]]></FromUserName>" +
                        "<CreateTime>%s</CreateTime>" +
                        "<MsgType><![CDATA[voice]]></MsgType>" +
                        "<Voice>" +
                        "   <MediaId><![CDATA[%s]]></MediaId>" +
                        "</Voice>" +
                        "</xml>",
                fromUserName, toUserName, System.currentTimeMillis(), mediaId);
    }


    /**
     * 构造图文消息
     * @param map 封装了解析结果的Map
     * @return 图文消息XML字符串
     */
    public static String buildNewsMessage(Map<String, String> map) {
        String fromUserName = map.get("FromUserName");
        // 开发者微信号
        String toUserName = map.get("ToUserName");
        NewsItem item = new NewsItem();
        item.setTitle("微信开发学习总结（一）——微信开发环境搭建");
        item.setDescription ("工欲善其事，必先利其器。要做微信公众号开发，那么要先准备好两样必不可少的东西：\n" +
                "\n" +
                "　　1、要有一个用来测试的公众号。\n" +
                "\n" +
                "　　2、用来调式代码的开发环境");
        item.setPicUrl("http://images2015.cnblogs.com/blog/289233/201601/289233-20160121164317343-2145023644.png");
        item.setUrl("http://www.cnblogs.com/xdp-gacl/p/5149171.html");
        String itemContent1 = buildSingleItem(item);

        NewsItem item2 = new NewsItem();
        item2.setTitle ("微信开发学习总结（二）——微信开发入门");
        item2.setDescription ("微信服务器就相当于一个转发服务器，终端（手机、Pad等）发起请求至微信服务器，微信服务器然后将请求转发给我们的应用服务器。应用服务器处理完毕后，将响应数据回发给微信服务器，微信服务器再将具体响应信息回复到微信App终端。");
        item2.setPicUrl("");
        item2.setUrl ("http://www.cnblogs.com/xdp-gacl/p/5151857.html");
        String itemContent2 = buildSingleItem(item2);


        String content = String.format("<xml>\n" +
                "<ToUserName><![CDATA[%s]]></ToUserName>\n" +
                "<FromUserName><![CDATA[%s]]></FromUserName>\n" +
                "<CreateTime>%s</CreateTime>\n" +
                "<MsgType><![CDATA[news]]></MsgType>\n" +
                "<ArticleCount>%s</ArticleCount>\n" +
                "<Articles>\n" + "%s" +
                "</Articles>\n" +
                "</xml> ", fromUserName, toUserName, System.currentTimeMillis(), 2, itemContent1 + itemContent2);
        return content;

    }

    /**
     * 生成图文消息的一条记录
     *
     * @param item
     * @return
     */
    private static String buildSingleItem(NewsItem item) {
        String itemContent = String.format("<item>\n" +
                "<Title><![CDATA[%s]]></Title> \n" +
                "<Description><![CDATA[%s]]></Description>\n" +
                "<PicUrl><![CDATA[%s]]></PicUrl>\n" +
                "<Url><![CDATA[%s]]></Url>\n" +
                "</item>", item.getTitle(), item.getDescription(), item.getPicUrl(), item.getUrl());
        return itemContent;
    }


}
