package com.cartisan.modern.common.mail;

import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = Application.class)
public class OutboxIntegrationTest {
    @Autowired
    private Outbox outbox;

    //@Ignore
    //@Test
    public void send() {
        Mail mail = new Mail();
        mail.setSubject("Test subject");
        mail.setText("Text body");
        mail.setFrom("NONEXIST@example.com");
        mail.setTo("zhang_colin <zhang_colin@163.com>");

        assertThat(outbox.send(mail)).isTrue();
    }
}