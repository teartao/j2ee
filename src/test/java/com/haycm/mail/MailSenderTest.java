package com.haycm.mail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.mail.internet.MimeMessage;

/**
 * Created by Administrator on 2015/8/20.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/applicationContext.xml"})
public class MailSenderTest {

    @Autowired
    private JavaMailSenderImpl mailSender;

    @Test
    public void send()throws Exception{
        MimeMessage mail = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mail,"utf-8");
        mimeMessageHelper.setFrom(mailSender.getUsername());
        mimeMessageHelper.setTo("yyang12581@126.com");
        mimeMessageHelper.setSubject("测试");
        mimeMessageHelper.setText("<div>\n" +
                "    <table>\n" +
                "        <tr style='color: #0000cc'>\n" +
                "            <td>序号</td>\n" +
                "            <td>名称</td>\n" +
                "            <td>省份</td>\n" +
                "        </tr>\n" +
                "    </table>\n" +
                "</div>\n",true);
        mailSender.send(mail);
    }
}
