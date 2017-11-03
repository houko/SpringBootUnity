package info.xiaomo.core.untils

import java.io.FileInputStream
import java.io.IOException
import java.util.*
import javax.mail.*
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

/**
 * 把今天最好的表现当作明天最新的起点．．～
 * いま 最高の表現 として 明日最新の始発．．～
 * Today the best performance  as tomorrow newest starter!
 * Created by IntelliJ IDEA.
 *
 * @author : xiaomo
 * github: https://github.com/xiaomoinfo
 * email: xiaomo@xiaomo.info
 *
 * Date: 2016/4/511:00
 * Description: 发送邮件
 * Copyright(©) 2015 by xiaomo.
 */
object MailUtil {
    private var USERNAME: String? = null
    private var PASSWORD: String? = null

    /**
     * 获取Session
     */
    private val getSession: Session
        @Throws(IOException::class)
        get() {
            val props = Properties()
            val dir = System.getProperty("user.dir")
            val `is` = FileInputStream(dir + "/website/src/main/resources/config/application.properties")
            props.load(`is`)
            USERNAME = props["mail.username"].toString()
            PASSWORD = props["mail.password"].toString()
            val authenticator = object : Authenticator() {
                override fun getPasswordAuthentication(): PasswordAuthentication {
                    return PasswordAuthentication(USERNAME, PASSWORD)
                }
            }
            return Session.getDefaultInstance(props, authenticator)
        }

    fun send(toEmail: String, subject: String, content: String) {
        val session: Session
        try {
            session = getSession
            val msg = MimeMessage(session)
            msg.setFrom(InternetAddress(USERNAME!!))
            val address = arrayOf(InternetAddress(toEmail))
            msg.setRecipients(Message.RecipientType.TO, address)
            msg.subject = subject
            msg.sentDate = Date()
            msg.setContent(content, "text/html;charset=utf-8")
            Transport.send(msg)
        } catch (mex: Exception) {
            mex.printStackTrace()
        }

    }

    /**
     * 返回激活链接
     *
     * @param email email
     * @return 有3个参数 email password  time
     */
    fun redirectValidateUrl(email: String, password: String): String {
        val now = TimeUtil.nowOfMills
        val sb = StringBuilder("点击下面链接激活账号，48小时生效，否则重新注册账号，链接只能使用一次，请尽快激活！</br>")
        sb.append("<a href=\"http://localhost:8080/user/validate?email=")
        sb.append(email)
        sb.append("&password=")
        sb.append(password)
        sb.append("&time=")
        sb.append(now)
        sb.append("\">")
        sb.append("http://localhost:8080/user/validate?email=")
        sb.append(email)
        sb.append("&password=")
        sb.append(password)
        sb.append("&time=")
        sb.append(now)
        sb.append("</a><br/>")
        sb.append("<span style='float:right;padding-right:4%'>小莫</span></br>")
        sb.append("<span style='float:right'>")
        sb.append(TimeUtil.formatDate)
        sb.append("</span></br>")
        return sb.toString()
    }
}
