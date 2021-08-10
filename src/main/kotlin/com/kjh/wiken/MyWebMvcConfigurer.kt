package com.kjh.wiken

import com.kjh.wiken.interceptor.BeforeActionInterceptor
import com.kjh.wiken.interceptor.NeedLoginInterceptor
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

// 역할 : 앱의 복잡한 세팅을 담당, 간단한 세팅은 application.yml이 담당
@Configuration
class MyWebMvcConfigurer(
    private val beforeActionInterceptor: BeforeActionInterceptor,
    private val needLoginInterceptor: NeedLoginInterceptor
) : WebMvcConfigurer {
    // 이 함수는 앱이 실행되고 나서 딱 1번 실행된다. 즉 요청마다 실행되지 않는다.
    override fun addInterceptors(registry: InterceptorRegistry) {
        // 인터셉터에게 어떤 요청이 들어왔을 때 일 해야하는지 알려준다.
        registry.addInterceptor(beforeActionInterceptor)
            .addPathPatterns("/**")
            .excludePathPatterns("/resource/**")
            .excludePathPatterns("/error")

        registry.addInterceptor(needLoginInterceptor)
            .addPathPatterns("/member/findLoginId")
            .addPathPatterns("/member/doFindLoginId")
            .addPathPatterns("/member/findLoginPw")
            .addPathPatterns("/member/doFindLoginPw")
            .addPathPatterns("/ken")
            .addPathPatterns("/ken/{id}/edit")
            .addPathPatterns("/ken/doWrite")
            .addPathPatterns("/ken/doModify")
    }
}
