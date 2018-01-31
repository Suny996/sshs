package com.sshs.core.handler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

public class TestWebSocketHander {
	static Map<String, WebSocketSession> sessions = new HashMap<String, WebSocketSession>();

	public static void login(WebSocketSession session) {
		sessions.put(session.getId(), session);
	}

	public static void logout(WebSocketSession session) {
		sessions.remove(session.getId());
	}

	public static void replayAllMember(TextMessage message, WebSocketSession session) {
		System.out.println("Received: " + message.getPayload());
		if (message.getPayload().startsWith("n:")) {
			session.getAttributes().put("name", message.getPayload().substring(2));
			return;
		}
		try {
			for (WebSocketSession s : sessions.values()) {
				String name = (String) session.getAttributes().get("name");
				if (s.getId().equals(session.getId())) {
					name = "我";
				}
				s.sendMessage(new TextMessage(name + " 说：" + message.getPayload()));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
