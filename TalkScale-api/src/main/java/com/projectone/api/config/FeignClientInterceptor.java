package com.projectone.api.config;



import feign.RequestInterceptor;
import feign.RequestTemplate;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Configuration
@RequiredArgsConstructor
public class FeignClientInterceptor implements RequestInterceptor {
    
    @Override
    public void apply(RequestTemplate template) {
        System.out.println("FeignClientInterceptor "+template);
        ServletRequestAttributes attributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        
        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();
            String authHeader = request.getHeader("Authorization");
            
            if (authHeader != null) {
                template.header("Authorization", authHeader); // Attach JWT to outgoing Feign call
            }
        }
    }
}

