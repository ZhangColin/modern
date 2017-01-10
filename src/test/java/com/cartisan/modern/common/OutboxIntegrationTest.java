package com.cartisan.modern.common;

import com.cartisan.modern.Application;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class OutboxIntegrationTest {
    @Autowired
    private Outbox outbox;

    //@Ignore
    //@Test
    public void send(){
        Mail mail = new Mail();
        mail.setSubject("Test subject");
        mail.setText("Text body");
        mail.setFrom("NONEXIST@example.com");
        mail.setTo("zhang_colin <zhang_colin@163.com>");

        assertTrue(outbox.send(mail));
    }
}