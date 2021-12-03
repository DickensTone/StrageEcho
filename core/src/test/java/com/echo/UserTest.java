package com.echo;

import com.echo.core.model.User;
import com.echo.core.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserTest {
    @Autowired
    UserRepository userRepository;

    @Test
    public void UserJPATest(){
        User user = new User();
        int id = 90087;
        
        Optional<User> userOptional = userRepository.findById(id);
        userOptional.ifPresent(u->userRepository.deleteById(id));


        user.setId(id);
        user.setEmail("8888");
        user.setName("hello");
        user = userRepository.save(user);

        userOptional = userRepository.findById(user.getId());
        Assert.assertTrue(userOptional.isPresent());
        Assert.assertEquals(user.getId(), userOptional.get().getId());
    }


}
