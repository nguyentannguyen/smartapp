package com.smartapp.data.repository;


import com.smartapp.data.domain.T_Account;
import com.smartapp.data.domain.T_User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:test-application-context.xml")
public class UserRepositoryTest {

	@Autowired private UserRepository userRepository;
    @Autowired private AccountRepository accountRepository;

	@Test
	public void read() {
		T_User user = userRepository.read(1L);
		Assert.assertEquals(user.getEmail(), "ntnguyen2603@gmail.com");
        Assert.assertEquals(user.getAccount().getIdentifier(), "nguyen_tan_nguyen");
	}

	@Test
	public void create() {
        T_Account account = accountRepository.loadByIdentifier("nguyen_tan_nguyen");
        T_User user = new T_User();
        user.setEmail("ntnguyen2603@yahoo.com");
        user.setAccount(account);

        T_User userNew=  userRepository.create(user);
        Assert.assertEquals(userNew.getEmail(), "ntnguyen2603@yahoo.com");
	}

    @Test
    public void update() {
        T_Account account = accountRepository.loadByIdentifier("nguyen_tan_nguyen");
        T_User user = userRepository.loadByEmail("ntnguyen2603@gmail.com",account.getIdentifier());
        user.setOpenid("http://spring.security.test.myopenid.com/");

        T_User userNew =  userRepository.update(user);
        Assert.assertEquals(userNew.getOpenid(), "http://spring.security.test.myopenid.com/");
    }
}
