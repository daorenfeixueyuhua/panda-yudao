package cn.daoren.websocket.message;

import lombok.Data;

@Data
public class SendToUserRequest implements Message {
    public static final String TYPE = "SEND_TO_USER_REQUEST";

    /**
     * 消息编号
     */
    private String msgId;
    /**
     * 内容
     */
    private String content;


    public SendToUserRequest setMsgId(String msgId) {
        this.msgId = msgId;
        return this;
    }

    public SendToUserRequest setContent(String content) {
        this.content = content;
        return this;
    }
}
