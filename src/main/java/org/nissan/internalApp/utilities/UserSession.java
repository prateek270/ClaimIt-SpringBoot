package org.nissan.internalApp.utilities;

public class UserSession {

	private static String zid;
	private static String name;
	private static String post;
	private static String profilepicArr;
	
	public static String getZid() {
		return zid;
	}

	public static void setZid(String zid) {
		UserSession.zid = zid;
	}

	public static String getName() {
		return name;
	}	

	public static void setName(String name) {
		UserSession.name = name;
	}

	public static String getPost() {
		return post;
	}

	public static void setPost(String post) {
		UserSession.post = post;
	}

	public static String getProfilepicArr() {
		return profilepicArr;
	}

	public static void setProfilepicArr(String profilepicArr) {
		UserSession.profilepicArr = profilepicArr;
	}
	
}
