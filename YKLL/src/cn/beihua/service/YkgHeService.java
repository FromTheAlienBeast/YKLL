package cn.beihua.service;

import cn.beihua.dao.YkgHe;

public class YkgHeService {

	private YkgHe ykgHe;

	public void setYkgHe(YkgHe ykgHe) {
		this.ykgHe = ykgHe;
	}
	
	public double subtotal(){
		return ykgHe.subtotal();
	}
}
