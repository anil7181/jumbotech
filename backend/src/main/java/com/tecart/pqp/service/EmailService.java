package com.tecart.pqp.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tecart.pqp.entity.base.EmailMessage;
import com.tecart.pqp.entity.base.EmailServer;
import com.tecart.pqp.repository.EmailMessageRepo;
import com.tecart.pqp.utils.StringUtils;
import com.tecart.pqp.utils.constants.MasterConstants;

@Service
public class EmailService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private EmailMessageRepo emailRepository;
	
	@Autowired
	private EmailServerService emailServerService;

	public void processToSendMail(String entityCode) {
		if (entityCode != null && !entityCode.equalsIgnoreCase("")) {
			List<EmailMessage> mailingList = emailRepository.getUnSendEmailBased(0);

			for (int i = 0; i < mailingList.size(); i++) {
				try {
					EmailServer emailServer = emailServerService.findEmailServerDetailsByOrgId(MasterConstants.DEFAULT_ORG_ID);
					EmailMessage emailMessageToBeTriggered = mailingList.get(i);
					triggerUnsendEmail(emailServer, emailMessageToBeTriggered);
				} catch (Exception e) {
					Date date = new Date();
					emailRepository.setMailStatus(mailingList.get(i).getId(), 2, e.getMessage(), date);
					logger.info("Error occurred in processToSendMail method".concat(e.getMessage()));
					emailRepository.setMailStatus(mailingList.get(i).getId(), 2, e.getMessage(), date);
				}
			}
		}
	}

	public void triggerUnsendEmail(EmailServer emailServer, EmailMessage emailMessageToBeTriggered)
			throws Exception {
		logger.info("Entering into triggerUnsendEmail method");

		if (emailServer != null) {

			String toAdd = emailMessageToBeTriggered.getEmailTo();
			String[] to = {};
			if (toAdd != null && !toAdd.equals("")) {
				to = toAdd.split(";");
			}

			String toEmail = "";
			InternetAddress[] toAddress = new InternetAddress[to.length];
			for (int itt = 0; itt < to.length; itt++) { // changed from a
														// while loop
				if (to[itt] != null) {
					toEmail = to[itt].trim();
					if (toEmail != null && !toEmail.equals("")) {
						try {
							toAddress[itt] = new InternetAddress(toEmail);
						} catch (Exception e) {
							logger.error("Invalid TO Email-->:".concat(toEmail));
							logger.error(new StringUtils().convertException(e));
							Date date = new Date();
							emailRepository.setMailStatus(emailMessageToBeTriggered.getId(), 2,
									e.getMessage().concat(" - ").concat(toEmail), date);

						}
					}
				}
			}

			String ccAdd = emailMessageToBeTriggered.getEmailCc();
			if (ccAdd == null || ccAdd.equals("")) {
			} else {
				ccAdd = ccAdd + ";";
				;
			}
			String[] cc = {};
			if (ccAdd != null && !ccAdd.equals("")) {
				cc = ccAdd.split(";");
			}
			// CC ADDRESS LIST IS BUILD, THE TO LIST WILL BE TAKEN FROM THE
			InternetAddress[] ccAddress = new InternetAddress[cc.length];
			String ccEmail = "";
			for (int icc = 0; icc < cc.length; icc++) { // changed from a
														// while loop
				if (cc[icc] != null) {
					ccEmail = cc[icc].trim();
					if (ccEmail != null) {
						try {
							ccAddress[icc] = new InternetAddress(ccEmail);
						} catch (Exception e) {
							logger.error("Invalid CC Email-->:".concat(ccEmail));
							logger.error(new StringUtils().convertException(e));
							Date date = new Date();
							emailRepository.setMailStatus(emailMessageToBeTriggered.getId(), 2,
									e.getMessage().concat(" - ").concat(ccEmail), date);

						}
					}
				}
			}

			String bcAdd = emailMessageToBeTriggered.getEmailBcc();
			if (bcAdd == null || bcAdd.equals("")) {
			} else {
				bcAdd = bcAdd + ";";
				;
			}
			String[] bc = {};
			if (bcAdd != null && !bcAdd.equals("")) {
				bc = bcAdd.split(";");
			}
			// BCC ADDRESS LIST IS BUILD, THE TO LIST WILL BE TAKEN FROM THE
			InternetAddress[] bcAddress = new InternetAddress[bc.length];
			String bcEmail = "";
			for (int ibc = 0; ibc < bc.length; ibc++) { // changed from a
														// while loop
				if (bc[ibc] != null) {
					bcEmail = bc[ibc].trim();
					if (bcEmail != null) {
						try {
							bcAddress[ibc] = new InternetAddress(bcEmail);
						} catch (Exception e) {
							logger.error("Invalid BCC Email-->:".concat(bcEmail));
							logger.error(new StringUtils().convertException(e));
							Date date = new Date();
							emailRepository.setMailStatus(emailMessageToBeTriggered.getId(), 2,
									e.getMessage().concat(" - ").concat(bcEmail), date);
						}
					}
				}
			}

			String smtpHost = emailServer.getSmtpHost();
			String smtpUserName = emailServer.getSmtpUserName();
			String smtpPassword = emailServer.getSmtpPassword();
			int smtpPortNumber = emailServer.getSmtpPortNumber();
			String fromEmailId = emailServer.getFromEmail();
			String fromEmailName = emailServer.getFromEmailName();
			String emailContentType = MasterConstants.EMAIL_CONTENT_TYPE_TEXT_OR_HTML;
			String charSet = MasterConstants.EMAIL_CONTENT_CHAR_SET_ISO_8859_1;
			String emailContentWithCharSet = emailContentType.concat("; charset=").concat(charSet);

			Properties props = new Properties();
			props.put("mail.transport.protocol", "smtp");
			props.put("mail.smtp.port", smtpPortNumber);
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");

			Session session = Session.getInstance(props);
			session.setDebug(true);
			Transport transport = session.getTransport();

			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(fromEmailId, fromEmailName));

			msg.setRecipients(Message.RecipientType.TO, toAddress);
			msg.setRecipients(Message.RecipientType.CC, ccAddress);
			msg.setRecipients(Message.RecipientType.BCC, bcAddress);
			msg.setSubject(MimeUtility.encodeText(emailMessageToBeTriggered.getEmailSubject().trim(), charSet, "Q"));
			msg.setContent(emailMessageToBeTriggered.getEmailBody().trim(), emailContentWithCharSet);
			msg.setSentDate(new Date());

			MimeBodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setText(emailMessageToBeTriggered.getEmailSubject().trim(), charSet);
			messageBodyPart.setText(emailMessageToBeTriggered.getEmailBody().trim(), charSet);
			messageBodyPart.setContent(emailMessageToBeTriggered.getEmailBody().trim(), emailContentWithCharSet);
			
			
			MimeMultipart multipart = new MimeMultipart("related");
			multipart.addBodyPart(messageBodyPart);
			msg.setContent(multipart);
			
			try {

				if (emailMessageToBeTriggered.getAttachmentPath() != null
						&& !emailMessageToBeTriggered.getAttachmentPath().isEmpty()) {
					String filePathList = emailMessageToBeTriggered.getAttachmentPath();
					// Get Reference File Attachment and Split with ; if has more than one
					ArrayList<String> fileList = new ArrayList<String>(Arrays.asList(filePathList.split(";")));
					// Iterate with each file path list and add to attachment

					for (int j = 0; j < fileList.size(); j++) {
						FileDataSource fds = new FileDataSource(fileList.get(j).toString());
						Path filePath = Paths.get(fileList.get(j).toString());
						if ((filePath.toString() != "null" && !filePath.toString().equals(""))) {
							if (Files.size(filePath) > 0) {
								MimeBodyPart mbp2 = new MimeBodyPart();
								mbp2.setDataHandler(new DataHandler(fds));
								mbp2.setFileName(fds.getName());
								multipart.addBodyPart(mbp2);
							} else {
								String errMsg = new String(fds.getName().concat(" file size is <= 0 "));
								emailRepository.setMailStatus(emailMessageToBeTriggered.getId(), 2, errMsg,
										new Date());
								throw new Exception(errMsg);
							}
						}
					}
				}
				this.sendMail(transport, msg, ccAdd, ccAddress, bcAdd, bcAddress,
						emailMessageToBeTriggered.getId(), smtpHost, smtpUserName, smtpPassword);

			} catch (Exception e) {
				String errormsg = "Error occured while Send Mail".concat(e.getMessage());
				logger.error(errormsg);
				logger.error(new StringUtils().convertException(e));
				throw new Exception(errormsg);
			}
		}
	}

	private void sendMail(Transport transport, Message msg, String ccAdd, InternetAddress[] ccAddress, String bcAdd,
			InternetAddress[] bcAddress, int emailMsgId, String smtpHost, String smtpUserName, String smtpPassword)
			throws Exception {
		logger.info("Entering into sendMail method");
		try {
			transport.connect(smtpHost, smtpUserName, smtpPassword);
			transport.sendMessage(msg, msg.getRecipients(Message.RecipientType.TO));

			if (ccAdd != null && !ccAdd.equals("") && ccAddress.length > 0) {
				transport.sendMessage(msg, msg.getRecipients(Message.RecipientType.CC));
			}
			if (bcAdd != null && !bcAdd.equals("") && bcAddress.length > 0) {
				transport.sendMessage(msg, msg.getRecipients(Message.RecipientType.BCC));
			}
			Date date = new Date();
			emailRepository.setMailStatus(emailMsgId, 1, "Successfully sent message to server", date);
			logger.info("Mail Send Sucessfully");
		} catch (Exception e) {
			String errormsg = "Error occured while Send Mail".concat(e.getMessage());
			Date date = new Date();
			emailRepository.setMailStatus(emailMsgId, 2, e.getMessage(), date);
			logger.error(new StringUtils().convertException(e));
			logger.error(errormsg);
			throw new Exception(errormsg);
		}
	}

	public EmailMessage saveEmailMessage(EmailMessage emailMessage) {
		logger.info("Inside saveEmailMessage method in service");

		return emailRepository.saveAndFlush(emailMessage);
	}


}
