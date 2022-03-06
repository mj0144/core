package hello.core.lifeCycle;

//import org.springframework.beans.factory.DisposableBean;
//import org.springframework.beans.factory.InitializingBean;

// 패키지에 javax가  붙어있으면 자바가 공식적으로 지원하는 것.즉, spring아니여도 다른 컨테이너에서도 사용가능
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

//public class NetworkClient implements InitializingBean , DisposableBean {
public class NetworkClient {
    private String url;

    public NetworkClient(){
        System.out.println("생성자 호출, url : " + url);
        connect();
        call("초기화 연결 메세지");


    }


    public void setUrl(String url) {
        this.url = url;
    }

    // 서비스 시작시 호출
    public void connect(){
        System.out.println("connect : " + url);
    }

    public void call(String messge){
        System.out.println("messge = " + messge);
    }

    //서비스 종료시 호출
    public void disconnect(){
        System.out.println("close: " + url);
    }

    // 의존관계 주입이 끝나면 실행됨.
//    @Override
//    public void afterPropertiesSet() throws Exception {
//        System.out.println("afgerPropertiesSet() -- 의존관계 끝나고 호출!!");
//        connect();
//        call("초기화 연결 메세지");
//    }

    @PostConstruct
    public void init() {
        System.out.println("init() -- 초기화!!");
        connect();
        call("초기화 연결 메세지");
    }


//    @Override
//    public void destroy() throws Exception {
//        System.out.println("컨테이터 끝났어!!!");
//        disconnect();
//    }

    @PreDestroy
    public void close() {
        System.out.println("close() -- 컨테이터 끝났어!!!");
        disconnect();
    }

}
