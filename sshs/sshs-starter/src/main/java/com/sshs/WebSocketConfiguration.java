package com.sshs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Configuration
@EnableWebSocket
public class WebSocketConfiguration implements WebSocketConfigurer {
	public static class MyWebSocketHandler extends TextWebSocketHandler {
		@Override
		public void afterConnectionEstablished(WebSocketSession session) throws Exception {
			session.sendMessage(new TextMessage("Hello Client"));
		}

		@Override
		protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
			System.out.println("Received: " + message.getPayload());
			session.sendMessage(new TextMessage("Received " + message.getPayload()));
		}
	}

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(new MyWebSocketHandler(), "/rawwebsocket");
	}
}