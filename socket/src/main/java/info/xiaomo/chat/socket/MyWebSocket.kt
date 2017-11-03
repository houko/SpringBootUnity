package info.xiaomo.chat.socket

import info.xiaomo.core.untils.HtmlUtil
import info.xiaomo.core.untils.TimeUtil
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.io.IOException
import java.util.concurrent.CopyOnWriteArraySet
import javax.websocket.OnClose
import javax.websocket.OnMessage
import javax.websocket.OnOpen
import javax.websocket.Session
import javax.websocket.server.ServerEndpoint

/**
 * 把今天最好的表现当作明天最新的起点．．～
 * いま 最高の表現 として 明日最新の始発．．～
 * Today the best performance  as tomorrow newest starter!
 * Created by IntelliJ IDEA.
 *
 *
 *
 * @author : xiaomo
 * github: https://github.com/xiaomoinfo
 * email: xiaomo@xiaomo.info
 *
 *
 * Date: 2016/11/3 16:36
 * Description: 用户实体类
 * Copyright(©) 2015 by xiaomo.
 */

@ServerEndpoint("/websocket")
@Component

class MyWebSocket {
    private var session: Session? = null

    /**
     * 有人进入房间
     *
     * @param session session
     */
    @OnOpen
    fun onOpen(session: Session) {
        this.session = session
        webSocketSet.add(this)
        addOnlineCount()
        LOGGER.info("有新用户加入!当前在线人数为:{}", onlineCount)
    }

    /**
     * 有人离开房间
     */
    @OnClose
    fun onClose() {
        webSocketSet.remove(this)
        subOnlineCount()
        println("有一用户关闭!当前在线人数为" + onlineCount)
    }

    /**
     * 发消息
     *
     * @param message message
     * @throws IOException IOException
     */
    @OnMessage
    @Throws(IOException::class)
    fun onMessage(message: String) {
        val date = "<font color='green'>" + TimeUtil.getDateNow(TimeUtil.DATE_PATTERN) + "</font></br>"
        // 群发消息
        for (item in webSocketSet) {
            item.sendMessage(date + message)
        }
        LOGGER.info("客户端消息:{}", HtmlUtil.delHTMLTag(message))
    }

    /**
     * 发送消息
     *
     * @param message message
     * @throws IOException IOException
     */
    @Throws(IOException::class)
    private fun sendMessage(message: String) {
        this.session!!.basicRemote.sendText(message)
    }

    companion object {

        private val LOGGER = LoggerFactory.getLogger(MyWebSocket::class.java)
        /**
         * 获取在线人数
         *
         * @return 在线人数
         */
        @get:Synchronized private var onlineCount = 0
        private val webSocketSet = CopyOnWriteArraySet<MyWebSocket>()

        /**
         * 添加在线人数
         */
        @Synchronized private fun addOnlineCount() {
            MyWebSocket.onlineCount++
        }

        /**
         * 减少在线人数
         */
        @Synchronized private fun subOnlineCount() {
            MyWebSocket.onlineCount--
        }
    }


}
