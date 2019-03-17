package org.nissan.internalApp.utilities;

/****
 * FCMPushNotifications - Class that will be used to PUSH the notifications to the desired end user
 * 						  using TokenID(FCM_TOKEN)	
 * 
 * */

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.cloudinary.json.JSONException;
import org.cloudinary.json.JSONObject;
import org.nissan.internalApp.services.AndroidPushNotificationsService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class FCMPushNotifications {

	public ResponseEntity<String> send(String TOPIC, String title, String message, String fcm_token, AndroidPushNotificationsService apnServices) throws JSONException {

		
		//TOPIC  ---- TO BE USED
		
		
		
		JSONObject body = new JSONObject();
		body.put("to", fcm_token);
		body.put("priority", "high");

		JSONObject data = new JSONObject();
		data.put("title", title);
		data.put("body", message);

		body.put("data", data);

		HttpEntity<String> request = new HttpEntity<>(body.toString());

		CompletableFuture<String> pushNotification = apnServices.send(request);
		CompletableFuture.allOf(pushNotification).join();

		try {
			String firebaseResponse = pushNotification.get();

			return new ResponseEntity<>(firebaseResponse, HttpStatus.OK);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}

		return new ResponseEntity<>("Push Notification ERROR!", HttpStatus.BAD_REQUEST);
	}
}
