package cn.daoren.websocket.handler;

import cn.daoren.websocket.message.SendResponse;
import cn.daoren.websocket.message.SendToOneRequest;
import cn.daoren.websocket.message.SendToUserRequest;
import cn.daoren.websocket.util.WebSocketUtil;
import org.springframework.stereotype.Component;

import javax.websocket.Session;

@Component
public class SendToOneHandler implements MessageHandler<SendToOneRequest> {
    @Override
    public void execute(Session session, SendToOneRequest message) {
        // 这里，假装直接成功
        SendResponse sendResponse = new SendResponse().setMsgId(message.getMsgId()).setCode(0);
        WebSocketUtil.send(session, SendResponse.TYPE, sendResponse);

        // 创建转发的消息
        SendToUserRequest sendToUserRequest = new SendToUserRequest().setMsgId(message.getMsgId())
                .setContent(message.getContent());
        // 广播发送
        WebSocketUtil.send(message.getToUser(), SendToUserRequest.TYPE, sendToUserRequest);
    }

    @Override
    public String getType() {
        return SendToOneRequest.TYPE;
    }
}
