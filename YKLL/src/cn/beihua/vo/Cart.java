package cn.beihua.vo;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 购物车对象
 * 
 * @author 传智.郭嘉
 * 
 */
public class Cart implements Serializable{
	// 购物车属性
	// 购物项集合:Map的key就是商品pid,value:购物项
	//private Map<Integer, CartItem> map = new LinkedHashMap<Integer, CartItem>();
	private Map<Integer, Pro> map = new LinkedHashMap<Integer, Pro>();
	// Cart对象中有一个叫cartItems属性.
	public Collection<Pro> getCartItems(){
		return map.values();
	}
	
	// 购物总计:
	private double total;

	public double getTotal() {
		return total;
	}

	// 购物车的功能:
	// 1.将购物项添加到购物车
	public void addCart(Pro pro) {
		// 判断购物车中是否已经存在该购物项:
		/*
		 *  * 如果存在:
		 *  	* 数量增加
		 *  	* 总计 = 总计 + 购物项小计
		 *  * 如果不存在:
		 *  	* 向map中添加购物项
		 *  	* 总计 = 总计 + 购物项小计
		 */
		// 获得商品id.
		Integer pid = pro.getPid();
		// 判断购物车中是否已经存在该购物项:
		if(map.containsKey(pid)){
			// 存在
			Pro _cartItem =  map.get(pid);// 获得购物车中原来的购物项
			//_cartItem.setCount(_cartItem.getCount()+pro.getCount());
		}else{
			// 不存在
			map.put(pid, pro);
		}
		// 设置总计的值
		//total += pro.getSubtotal();
	}

	// 2.从购物车移除购物项
	public void removeCart(Integer pid) {
		// 将购物项移除购物车:
		
		Pro cartItem =  map.remove(pid);
		System.out.println("cart"+pid);
		// 总计 = 总计 -移除的购物项小计:
		//total -= cartItem.getSubtotal();
		System.out.println("test");
	}

	// 3.清空购物车
	public void clearCart() {
		// 将所有购物项清空
		map.clear();
		// 将总计设置为0
		total = 0;
	}
}
