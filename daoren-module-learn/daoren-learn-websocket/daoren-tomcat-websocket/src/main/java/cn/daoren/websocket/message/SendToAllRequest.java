package cn.daoren.websocket.message;

import lombok.Data;

@Data
public class SendToAllRequest implements Message {
    public static final String TYPE = "SEND_TO_ALL_REQUEST";

    /**
     * 消息编号
     */
    private String msgId;
    /**
     * 内容
     */
    private String content;
}
