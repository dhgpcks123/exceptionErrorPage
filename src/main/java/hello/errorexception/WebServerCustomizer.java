package hello.errorexception;

import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class WebServerCustomizer implements WebServerFactoryCustomizer<ConfigurableWebServerFactory> {
    //스프링이 제공하는 커스터마이징
    @Override
    public void customize(ConfigurableWebServerFactory factory) {
        ErrorPage errorPage404 = new ErrorPage(HttpStatus.NOT_FOUND, "/error-page/404");
        //NOT_FOUND 에러 발생시 /error-page/400 이동
        ErrorPage errorPage500 = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error-page/500");
        ErrorPage errorPageEx = new ErrorPage(RuntimeException.class, "/error-page/500");
        // 500 예외는 서버 오류기 때문에 예외 발생한 경우도 그냥 500으로 처리했음. 분리해도 됨
        //런타임 Exception예외 자식도 함께 처리 됨

        factory.addErrorPages(errorPage404, errorPage500, errorPageEx);
    }
}
