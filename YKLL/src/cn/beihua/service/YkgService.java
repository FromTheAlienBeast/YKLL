package cn.beihua.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.beihua.dao.YkgDao;
import cn.beihua.vo.Pro;
import cn.beihua.vo.Product;

@Transactional
public class YkgService {

	//注入dao 
	private YkgDao ykgDao;

	public void setYkgDao(YkgDao ykgDao) {
		this.ykgDao = ykgDao;
	}
	
	public List<Pro> findCode() {
		return ykgDao.findCode();
	}

}
