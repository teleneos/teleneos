package org.meruvian.yama.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service(value = "asyncMailSender")
public class AsyncMailSender implements MailSender {
	
	@Autowired
	private MailSender javaMailSender;

	private TaskExecutor taskExecutor;

	@Autowired
	public AsyncMailSender(TaskExecutor taskExecutor) {
		this.taskExecutor = taskExecutor;
	}

	public void send(SimpleMailMessage simpleMessage) throws MailException {
		taskExecutor.execute(new AsyncMailTask(simpleMessage));
	}

	public void send(SimpleMailMessage[] simpleMessages) throws MailException {
		for (SimpleMailMessage message : simpleMessages) {
			send(message);
		}
	}

	private class AsyncMailTask implements Runnable {

		private SimpleMailMessage message;

		private AsyncMailTask(SimpleMailMessage message) {
			this.message = message;
		}

		public void run() {
			javaMailSender.send(message);
		}
	}
}