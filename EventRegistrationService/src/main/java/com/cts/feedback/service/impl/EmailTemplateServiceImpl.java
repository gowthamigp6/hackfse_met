package com.cts.feedback.service.impl;

import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cts.feedback.event.UserEventRegistration;
import com.cts.feedback.repository.UserEventRegistrationRepository;
import com.cts.feedback.service.EmailTemplateService;

@Service
@Transactional
public class EmailTemplateServiceImpl implements EmailTemplateService {

	@Autowired
	private JavaMailSender emailSender;

	@Autowired
	private UserEventRegistrationRepository userEventRegistrationRepository;

	@Override
	public void sendMailToUserEvent(String eventId) throws Exception {

		sendEmail(userEventRegistrationRepository.findAll());

	}

	private void sendEmail(List<UserEventRegistration> regList) throws MessagingException {
		System.out.println(regList);
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("gow.cute.gmail.com");
		message.setTo("gow.cute.gmail.com");
		message.setSubject("Event List");
		message.setText(regList.toString());
		emailSender.send(message);
	}

}
