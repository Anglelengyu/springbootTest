package cn.shuhan.controller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerTest {

    @GetMapping("/login")
    public String login(String username){
        return "没有权限，只能登录";
    }

    @GetMapping("/")
    public String happy(){
        return "欢迎";
    }


    @PreAuthorize("hasAuthority('user1')")  //配置单个角色权限
    @GetMapping("/hello")
    public String hello(){
        return "helle";
    }

    @PreAuthorize("hasAnyAuthority('admin','user1')")  //可以配置多个角色权限 hasAnyAuthority
    @GetMapping("/admin")
    public String admin(){
        return "admin";
    }

    @PreAuthorize("hasAuthority('user2')")
    @GetMapping("/test")
    public String test(){
        return "test";
    }
}
