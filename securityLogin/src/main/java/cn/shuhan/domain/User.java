package cn.shuhan.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Data
public class User {

    private static List<String> USERLIST = Arrays.asList("zhangsan","lisi","wangwu");
    private static List<String> ROLELIST = Arrays.asList("admin","user1","user2");
    private static List<String> PERMISSIONLIST = Arrays.asList("admin","/hello","/test");

    private String username;
    private String password;
    private Map<String,List<String>> roles;
    private Map<String,List<String>> permissions;

    public static boolean hasUsername(String username){
        return USERLIST.contains(username);
    }

    public static List<String> getRole(String username){
        if ("zhangsan".equals(username)){
            return Arrays.asList("admin");
        }
        if ("lisi".equals(username)){
            return Arrays.asList("user1");
        }
        if ("wangwu".equals(username)){
            return Arrays.asList("user2");
        }
        return null;
    }

    public static List<String> getPermission(String role){
        if ("admin".equals(role)){
            return Arrays.asList("admin");
        }
        if ("user1".equals(role)){
            return Arrays.asList("user1");
        }
        if ("user2".equals(role)){
            return Arrays.asList("user2");
        }
        return null;
    }
}
