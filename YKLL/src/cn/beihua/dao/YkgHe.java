package cn.beihua.dao;

import java.util.List;
import org.junit.Test;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;


public class YkgHe extends HibernateDaoSupport{
	//求和
		//@Test
		public double subtotal(){
			String hqlc="select count(*) from Pro";
			List<Number> listc = this.getHibernateTemplate().find(hqlc);
			long count=listc.get(0).longValue();
			if (count!=0) {
				String hql = "select sum(shop_price) from Pro";
				List<Number> list = this.getHibernateTemplate().find(hql);
				if (list != null && list.size() > 0) {
					return list.get(0).doubleValue();
				}
			}
			return 0;
		
		}
}
