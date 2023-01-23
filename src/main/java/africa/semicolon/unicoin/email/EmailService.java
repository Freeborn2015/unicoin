package africa.semicolon.unicoin.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailService implements EmailSender {

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    @Async
    public void send(String to, String email) throws MessagingException {
        try {
            MimeMessage mailMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mailMessage, "utf-8");
            mimeMessageHelper.setSubject("confirm your email address");
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setFrom("freeborn@gmail");
            mimeMessageHelper.setText(email, true);
            javaMailSender.send(mailMessage);
        } catch (MessagingException e) {
            log.info("problem 1 :" + e.getMessage());
            throw new RuntimeException(e);
        } catch (MailException e) {
            log.info("problem 2 :" + e.getMessage());
        }
    }
}
