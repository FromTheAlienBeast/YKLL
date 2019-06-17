package cn.beihua.vo;

/**
 * cid INT(11) NOT NULL AUTO_INCREMENT,
   username VARCHAR(10) DEFAULT NULL,
   state VARCHAR(11) DEFAULT NULL,
   site VARCHAR(10) DEFAULT NULL,
   CODE VARCHAR(10) DEFAULT NULL,
 * 
 * @author Taeyeon-Serenity
 *
 */
public class Com {
	private Integer cid;
	private String userName;
	private String state;
	private String site;
	private String code;
	/*//将构造方法私有化
	private Com(){
		
	}
	//提供实例
	private static Com com=new Com();  
	//提供外部访问的接口
	 //提供外部访问接口  
    public static Com getCom(){  
        return com;  
    }  */
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	
}
