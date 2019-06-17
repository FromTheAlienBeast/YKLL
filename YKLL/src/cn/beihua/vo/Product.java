package cn.beihua.vo;

/**
 * `pid` INT(11) NOT NULL AUTO_INCREMENT,
  state VARCHAR(11) DEFAULT NULL,
  site VARCHAR(10) DEFAULT NULL,
   CODE VARCHAR(10) DEFAULT NULL,
  `pname` VARCHAR(255) DEFAULT NULL,
  `shop_price` DOUBLE DEFAULT NULL,
  `image` VARCHAR(255) DEFAULT NULL,
 * @author Taeyeon-Serenity
 *
 */
public class Product {
	private Integer pid;
	private String state;
	private String site;
	private String code;
	private String pname;
	private Double shop_price;
	private String image;
	
	/*private Product(){}
	private static Product product=new Product();
	public static Product getProduct(){
		return product;
	}*/
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
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
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public Double getShop_price() {
		return shop_price;
	}
	public void setShop_price(Double shop_price) {
		this.shop_price = shop_price;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	
	
}
