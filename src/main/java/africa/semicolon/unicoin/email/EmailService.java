package africa.semicolon.unicoin.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@RequiredArgsConstructor
public class EmailService implements EmailSender{
    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void send(String to, String email) throws MessagingException {
        MimeMessage mailMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mailMessage, "");
        mimeMessageHelper.setSubject("confirm your email address");
        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setFrom("freeborn@gmail");
        mimeMessageHelper.setText(email,true);
        javaMailSender.send(mailMessage);

    }
}
