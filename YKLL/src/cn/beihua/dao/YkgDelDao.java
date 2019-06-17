package cn.beihua.dao;

import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.junit.Test;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.beihua.vo.Com;
import cn.beihua.vo.Pro;
import cn.beihua.vo.Product;

public class YkgDelDao extends HibernateDaoSupport{


	public void deletePid(Integer pid) {
		Pro pro=this.getHibernateTemplate().get(Pro.class, pid);
		if(pro!=null)
		 this.getHibernateTemplate().delete(pro);
	}
	
}
