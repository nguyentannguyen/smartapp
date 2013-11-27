package com.smartapp.data.repository;


import com.smartapp.data.domain.T_Account;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:test-application-context.xml")
public class AccountRepositoryTest {

	@Autowired
	private AccountRepository accountRepository;

	@Test
	public void read() {
		T_Account account = accountRepository.read(1L);
		Assert.assertEquals(account.getIdentifier(), "nguyen_tan_nguyen");
        Assert.assertEquals(account.getUsers().get(0).getEmail(), "ntnguyen2603@gmail.com");
	}

	@Test
	public void create() {
        T_Account account = new T_Account();
        account.setIdentifier("tan_nguyen_nguyen");

        T_Account a =  accountRepository.create(account);
        Assert.assertEquals(a.getIdentifier(), "tan_nguyen_nguyen");
	}

    @Test
    public void update() {
        T_Account account = accountRepository.loadByIdentifier("nguyen_tan_nguyen");
        account.setUuid("6c1f75b8-4918-48d3-b433-cdaadd0f3bb9");

        T_Account a =  accountRepository.update(account);
        Assert.assertEquals(a.getUuid(), "6c1f75b8-4918-48d3-b433-cdaadd0f3bb9");
    }
}
