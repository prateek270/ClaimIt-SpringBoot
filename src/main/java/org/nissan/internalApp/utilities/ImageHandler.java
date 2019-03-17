package org.nissan.internalApp.utilities;

import com.cloudinary.*;	
import com.cloudinary.utils.ObjectUtils;
import java.io.IOException;
import java.util.Base64;
import java.util.Map;


public class ImageHandler {

	private Cloudinary cloudinary;

	public ImageHandler() {
		super();
		cloudinary = new Cloudinary(ObjectUtils.asMap("cloud_name", "majortom", "api_key", "455515745447629","api_secret", "bzaVX31VBJ7MVKOPMmbvObelkQ4"));
		System.out.println("initiated" + cloudinary.config.toString());
	}

	public Map<String, Object> saveImageToDB(String imgData) {

		byte[] bytea;
		try {
			bytea = Base64.getDecoder().decode(imgData.getBytes("UTF-8"));
//			Map<String, Object> result = cloudinary.uploader().unsignedUpload(bytea, "gbgthmgn",ObjectUtils.asMap("public_id", "NissanInternal/ProfilePictures/Original"));	
			System.out.println(bytea);
			return null;
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return null;
		}
	}

}
