package com.fpl.core.request;

public interface IRequestMailSender<Input> {

	 void sendMail(Input input);
}
