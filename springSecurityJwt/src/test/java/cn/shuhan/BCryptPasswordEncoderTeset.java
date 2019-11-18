package cn.shuhan;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MyApplication.class)
public class BCryptPasswordEncoderTeset {

//    @Autowired
//    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Test
    public void jiami(){
//        System.out.println(bCryptPasswordEncoder.encode("123456"));
        //BCryptPasswordEncoder 对相同密码加密结果可能不一样
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        System.out.println(bCryptPasswordEncoder.encode("1"));
        System.out.println(bCryptPasswordEncoder.matches("1","$2a$10$usmASSUxqidbn2RrQi4jdeVWUcFyTfmwZgTxSy8FIXQ5CVpm/0qEa"));
        System.out.println(bCryptPasswordEncoder.matches("1","$2a$10$oc48dRax29UcHLYluDoP4.63zGa1aEMe/9NsArg95N9ny.v8TYXme"));
        System.out.println("helloword**************************");
    }
}
