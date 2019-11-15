package cn.shuhan.config;

import cn.shuhan.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserDetailService implements UserDetailsService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("******************用户名："+ username);
        if (!User.hasUsername(username)){
            throw new UsernameNotFoundException("用户"+username+"不存在");
        }
        //获取角色
        List<String> role = User.getRole(username);
        logger.info("************用户"+username+"角色是："+role.toString());

        List<String> permissionList = new ArrayList<>();
        if (role != null){
            //获得权限
            permissionList = User.getPermission(role.get(0));
        }else {
            permissionList.add("none_menu");
        }

        //返回User是Security中提供的    这密码是admin   正常应该是数据库中取出的密码
        return new org.springframework.security.core.userdetails.User(username,"$2a$10$VKblrkEe1cJUdELkFYGtu.vmAPizbs2vy8QBdOzRCGPdDzmcWJOA2", AuthorityUtils.createAuthorityList(permissionList.toArray(new String[permissionList.size()])));
    }
}
