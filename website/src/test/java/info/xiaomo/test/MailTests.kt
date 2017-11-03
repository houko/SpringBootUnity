package info.xiaomo.test

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.FileSystemResource
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import java.io.File


@RunWith(SpringJUnit4ClassRunner::class)
class MailTests {

    @Autowired
    private val mailSender: JavaMailSender? = null


    @Test
    @Throws(Exception::class)
    fun sendSimpleMail() {

        val message = SimpleMailMessage()
        message.from = "dyc87112@qq.com"
        message.setTo("dyc87112@qq.com")
        message.subject = "主题：简单邮件"
        message.text = "测试邮件内容"

        mailSender!!.send(message)
    }

    @Test
    @Throws(Exception::class)
    fun sendAttachmentsMail() {

        val mimeMessage = mailSender!!.createMimeMessage()

        val helper = MimeMessageHelper(mimeMessage, true)
        helper.setFrom("dyc87112@qq.com")
        helper.setTo("dyc87112@qq.com")
        helper.setSubject("主题：有附件")
        helper.setText("有附件的邮件")

        val file = FileSystemResource(File("weixin.jpg"))
        helper.addAttachment("附件-1.jpg", file)
        helper.addAttachment("附件-2.jpg", file)

        mailSender.send(mimeMessage)
    }

    @Test
    @Throws(Exception::class)
    fun sendInlineMail() {

        val mimeMessage = mailSender!!.createMimeMessage()

        val helper = MimeMessageHelper(mimeMessage, true)
        helper.setFrom("dyc87112@qq.com")
        helper.setTo("dyc87112@qq.com")
        helper.setSubject("主题：嵌入静态资源")
        helper.setText("<html><body><img src=\"cid:weixin\" ></body></html>", true)

        val file = FileSystemResource(File("weixin.jpg"))
        helper.addInline("weixin", file)

        mailSender.send(mimeMessage)
    }


}
