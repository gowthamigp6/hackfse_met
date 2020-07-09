package com.cts.ddd.application.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cts.ddd.application.EmailTemplateService;
import com.cts.ddd.application.UserEventRegistrationService;
import com.cts.feedback.event.UserEventRegistration;

@Service
@Transactional
public class EmailTemplateServiceImpl implements EmailTemplateService {

	@Autowired
	private JavaMailSender emailSender;

	@Autowired
	private UserEventRegistrationService userEventRegistrationService;

	@Value("${spring.mail.username}")
	private String fromEmail;

	@Value("${event.mail.subject}")
	private String subject;

	@Override
	public void sendMailToUserEvent(String eventId) {
		sendEmail(userEventRegistrationService.getByEventId(eventId));
	}

	private void sendEmail(List<UserEventRegistration> regList) {
		for (UserEventRegistration reg : regList) {

			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom(fromEmail);
			message.setTo(reg.getUser().getEmail());
			message.setSubject(subject + " reg.getEventSummaryDetails().getEventId() ");
			message.setText(regList.toString());
			emailSender.send(message);
		}
	}

}
