package cn.beihua.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import cn.beihua.comkou.JieShou;
import cn.beihua.vo.*;

public class YkgDao extends HibernateDaoSupport{

	public List<Pro> findCode() {
		String hql = "from Pro ";
		@SuppressWarnings("unchecked")
		List<Pro> list = this.getHibernateTemplate().find(hql);
		if(list==null)
			return null;
		return list;
	}
}
