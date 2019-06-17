package cn.beihua.action;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.beiahu.utils.DataSourceUtils;
import cn.beihua.comkou.JieShou;
import cn.beihua.service.YkgDelService;
import cn.beihua.service.YkgHeService;
import cn.beihua.service.YkgService;
import cn.beihua.vo.Cart;
import cn.beihua.vo.CartItem;
import cn.beihua.vo.Pro;
import cn.beihua.vo.Product;


public class Action extends ActionSupport{
	private Integer id;
	private YkgService ykgService;
	private YkgDelService ykgDelService;
	private YkgHeService ykgHeService;
	private List<Pro> cList ;
	public double d;
	public void setYkgService(YkgService ykgService) {
		this.ykgService = ykgService;
	}
	public void setYkgDelService(YkgDelService ykgDelService) {
		this.ykgDelService = ykgDelService;
	}
	public void setYkgHeService(YkgHeService ykgHeService) {
		this.ykgHeService = ykgHeService;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	//添加订单项
	public String execute(){
		//查询所有购买商品的集合
		cList = ykgService.findCode();
		// 将购买的商品存入到Session的范围:
		ActionContext.getContext().getSession().put("cList", cList);
		 d=ykgHeService.subtotal();   //合计
		ActionContext.getContext().getSession().put("d", d);
		if(cList==null)
			return "no";
		if(d==0)
			return "no";
		return "shop";  
	}
	//删除订单项
	public String removeCartto(){
		ykgDelService.deletePid(id);
		return "removeCart";
	}
	
	//结算
	public String total(){
		return "order";
	}
	
}
