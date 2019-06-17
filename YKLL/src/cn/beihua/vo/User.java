package cn.beihua.vo;

/**
 * `uid` INT(11) NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(20) DEFAULT NULL,
 * @author Taeyeon-Serenity
 *
 */
public class User {
	private Integer uid;
	private String username;
	
	/*private User(){}
	private static User user=new User();
	public static User getUser(){
		return user;
	}*/
	
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	
}
