package cn.beihua.service;

import java.util.List;

import cn.beihua.dao.YkgDao;
import cn.beihua.dao.YkgDelDao;
import cn.beihua.vo.Pro;

public class YkgDelService {
	private YkgDelDao ykgDelDao;

	public void setYkgDelDao(YkgDelDao ykgDelDao) {
		this.ykgDelDao = ykgDelDao;
	}
	
	public void deletePid(Integer pid) {
		 ykgDelDao.deletePid(pid);
	}
	
}
