package cn.shuhan.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * 所有的其他请求都会来到这个方法中
 * 在这个方法中，提取出客户端传来的 JWT Token，并进行解析，并查看用户角色等信息
 * 首先从请求头中提取出 authorization 字段，这个字段对应的value就是用户的token。
 * 将提取出来的token字符串转换为一个Claims对象，再从Claims对象中提取出当前用户名和用户角色，
 * 创建一个UsernamePasswordAuthenticationToken放到当前的Context中，然后执行过滤链使请求继续执行下去。
 */
public class JwtFilter extends GenericFilterBean {
    /**
     * 所有的其他请求都会来到这个方法中
     * 在这个方法中，提取出客户端传来的 JWT Token，并进行解析，并查看用户角色等信息
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String jwtToken = req.getHeader("authorization");
        logger.info("**************" + jwtToken);

        Claims claims = Jwts.parser()
                .setSigningKey("lengyu")   //对应加密那儿
                .parseClaimsJws(jwtToken.replace("Bearer", ""))
                .getBody();
        String username = claims.getSubject();//获取当前用户名
        List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList((String) claims.get("authorities"));
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, null, authorities);
        SecurityContextHolder.getContext().setAuthentication(token);
        filterChain.doFilter(req, servletResponse);
    }
}
