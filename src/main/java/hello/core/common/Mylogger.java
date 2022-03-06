package hello.core.common;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

@Component
/**
 * java: variable logDemoService not initialized in the default constructor
 * Controller에서 의존관계를 주입해서 사용하려고 Mylogger 내놔라 했는데.  Mylogger는 request로 들어와서 나갈때까지 살아있는 객체이므로, controller가 띄워지는 시점에는 해당 객체가 bean으로 살아있지 않음.
 */
/**
 * proxyMode = ScopedProxyMode.TARGET_CLASS : 가짜 프록시. 즉, 껍데기에 불과하고, 가짜 프록시 내에 실제 요청이 들어오면 진짜 빈을 요청하는 위임 로직이 있음.
 */
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Mylogger {
    private String uuid;
    private String requestURL;

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(String message){
        System.out.println("["+uuid+"] ["+requestURL+"] [ "+message + "]");
    }
    @PostConstruct
    public void init() {
        uuid = UUID.randomUUID().toString();
        System.out.println("["+uuid+"] request scope bean create : " + this);
    }

    @PreDestroy
    public void close(){
        System.out.println("["+uuid+"] request scope bean close : " + this);
    }
}
