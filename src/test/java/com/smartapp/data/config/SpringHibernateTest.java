package com.smartapp.data.config;

import com.smartapp.data.domain.T_Account;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:test-config-spring-hibernate.xml")
public class SpringHibernateTest {

	@Autowired
	private SessionFactory sessionFactory;

	@Test
	public void retrieveAccount() {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from T_Account a where a.id=:id").setInteger("id", 1);
		T_Account a = (T_Account) query.uniqueResult();
		session.close();
        Assert.assertEquals(a.getIdentifier(), "nguyen_tan_nguyen");
        Assert.assertEquals(a.getUsers().get(0).getEmail(), "ntnguyen2603@gmail.com");
	}

	@Test @Transactional
	public void updateAccount() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from T_Account a where a.id=:id").setInteger("id", 1);
		T_Account a = (T_Account) query.uniqueResult();
		a.setName("foo");
	}

}
