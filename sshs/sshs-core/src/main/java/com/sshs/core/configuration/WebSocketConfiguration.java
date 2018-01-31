package com.sshs.core.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.sshs.core.handler.TestWebSocketHander;

@Configuration
@EnableWebSocket
public class WebSocketConfiguration implements WebSocketConfigurer {

	public static class MyWebSocketHandler extends TextWebSocketHandler {

		@Override
		public void afterConnectionEstablished(WebSocketSession session) throws Exception {
			TestWebSocketHander.login(session);
		}

		@Override
		public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
			TestWebSocketHander.logout(session);
			super.afterConnectionClosed(session, status);
		}

		@Override
		protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
			TestWebSocketHander.replayAllMember(message, session);
		}
	}

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(new MyWebSocketHandler(), "/wstest");
	}
}