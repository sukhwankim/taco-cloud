package tacos.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*  뷰 컨트롤러
 *  WebMvcConfigurer 인터페이스를 구현함.  스프링 MVC를 구성하는 메서드
 *                   인터페이스임에도 정의 된 모든 메서드의 기본적인 구현을 제공함.
 *
 *  하나 이상의 뷰 컨트롤러를 등록하기 위해 ViewControllerRegistry 를 인자로 받는다
 */

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry){
        registry.addViewController("/").setViewName("home");
    }

}
