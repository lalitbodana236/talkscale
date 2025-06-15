package com.projectone.api.config;


import com.projectone.api.util.RequestContextUtil;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class FeignClientInterceptor implements RequestInterceptor {
    
    //    @Override
//    public void apply(RequestTemplate template) {
//        System.out.println("FeignClientInterceptor "+template);
//        ServletRequestAttributes attributes =
//                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//
//        if (attributes != null) {
//            HttpServletRequest request = attributes.getRequest();
//            String authHeader = request.getHeader("Authorization");
//
//            if (authHeader != null) {
//                template.header("Authorization", authHeader); // Attach JWT to outgoing Feign call
//            }
//        }
//    }
    @Override
    public void apply(RequestTemplate template) {
        System.out.println("FeignClientInterceptor triggered: " + template.url());
        
        String token = RequestContextUtil.getTokenFromRequestContext();
        System.out.println("Token in Feign interceptor: " + token);
        
        if (token != null && !token.isEmpty()) {
            template.header("Authorization", "Bearer " + token); // set header only once
        } else {
            System.out.println("No token found in request context!");
        }
    }
}

