package hello.core.singleton;

public class SingletonService {

    // 자바가 뜰때 static 영역에 new 실행해서 자기자신 생성해서 instance에 들어있음
    private static final SingletonService instance = new SingletonService();

    public static SingletonService getInstance(){
        return instance;
    }

    // 외부에서 생성자를 통해 객체 생성하지 못하도록 하기 위해
    private SingletonService(){

    }

    public void logic(){
        System.out.println("싱글톤 객체 로직 호출");
    }
}
