package org.nissan.internalApp.services;

/******
 * Service Class that will communicate to the firebase server
 * while pushing the notifications
 */

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
 
import org.springframework.http.HttpEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
 
@Service
public class AndroidPushNotificationsService {
 
	private static final String FIREBASE_SERVER_KEY = "AAAAVKVLdOQ:APA91bHdQBFd41l7lOv8S5zhlPg7Y2eeEn49gPpgsiKdibvmWFRuashD-yIYARVdJQHoXQjPVX-0zWVxzDbV88ASlxl-88UYipAJwFt5cs6VVAsosipUXCmjv-CBBkNBMw9HsY9BsBH-  ";
	private static final String FIREBASE_API_URL = "https://fcm.googleapis.com/fcm/send";
	
	@Async
	public CompletableFuture<String> send(HttpEntity<String> entity) {
 
		RestTemplate restTemplate = new RestTemplate();
 
		ArrayList<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
		interceptors.add(new HeaderRequestInterceptor("Authorization", "key=" + FIREBASE_SERVER_KEY));
		interceptors.add(new HeaderRequestInterceptor("Content-Type", "application/json"));
		restTemplate.setInterceptors(interceptors);
 
		String firebaseResponse = restTemplate.postForObject(FIREBASE_API_URL, entity, String.class);
 
		return CompletableFuture.completedFuture(firebaseResponse);
	}
}