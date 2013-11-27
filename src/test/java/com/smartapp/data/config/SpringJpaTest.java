package com.smartapp.data.config;


import com.smartapp.data.domain.T_Account;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:test-config-spring-jpa.xml")
public class SpringJpaTest {

	@PersistenceContext
	private EntityManager entityManager;

	@Test
	public void retrieveAccount() {
		Query query = entityManager.createQuery("from T_Account a where a.id=:id").setParameter("id", 1L);
		T_Account a = (T_Account) query.getSingleResult();
		Assert.assertEquals(a.getIdentifier(), "nguyen_tan_nguyen");
        Assert.assertEquals(a.getUsers().get(0).getEmail(), "ntnguyen2603@gmail.com");
	}

	@Test
	public void updateAccount() {
		Query query = entityManager.createQuery("from T_Account a where a.id=:id").setParameter("id", 1L);
		T_Account a = (T_Account) query.getSingleResult();
		a.setName("foo");
	}

    @Test
    @Transactional
    public void createAccount() throws InterruptedException{
        T_Account account = new T_Account();
        account.setIdentifier("tan_nguyen_nguyen");
        account.setUuid("uid_tan_nguyen_nguyen");
        entityManager.persist(account);

        Query query = entityManager.createQuery("from T_Account a where a.identifier=:identifier").setParameter("identifier", "tan_nguyen_nguyen");
        T_Account result = (T_Account) query.getSingleResult();
        Assert.assertEquals(result.getIdentifier(), "tan_nguyen_nguyen");
    }

}
