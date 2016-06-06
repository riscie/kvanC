package ch.fhnw.kvanc;

import java.io.IOException;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import ch.fhnw.kvanc.model.Email;

public class WebSocketEndpoint extends TextWebSocketHandler {

	WebSocketSession session;

	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) {
		System.out.println("handleTextMessage fired");
	}

	public void afterConnectionEstablished(WebSocketSession session) {
		this.session = session;
		System.out.println("Websocket Connection established");

	}

	public void afterConnectionClosed(WebSocketSession session, org.springframework.web.socket.CloseStatus status) {
		System.out.println("Websocket Connection closed");
		this.session = null;

	}

	public void pushEmail(Email email) throws IOException {
		String emailString = email.getSender() + ":" + email.getRecipient();
		TextMessage websocketMessage = new TextMessage(emailString);
		if (this.session != null) {
			session.sendMessage(websocketMessage);
		} else {
			System.out.println("No sesion object");
		}
	}

}
