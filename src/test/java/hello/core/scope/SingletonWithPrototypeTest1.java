package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

public class SingletonWithPrototypeTest1 {
    @Test
    void prototypeFind(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();

        Assertions.assertThat(prototypeBean1.getCount()).isEqualTo(1);

        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();

        Assertions.assertThat(prototypeBean2.getCount()).isEqualTo(1);


    }


    @Test
    void singletonClientUserPrototype(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class,PrototypeBean.class );

        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.logic();
        Assertions.assertThat(count1).isEqualTo(1);



        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean2.logic();
        //Assertions.assertThat(count2).isEqualTo(2);
        Assertions.assertThat(count2).isEqualTo(1);


    }

    @Scope("singleton")
    static class ClientBean {
        //private final PrototypeBean prototypeBean; // 생성시점에 이미 주입됨.

        @Autowired
        // 아래 둘다 가능.
        // Factory는 getObejct밖에 제공안함.
        //private ObjectProvider<PrototypeBean> prototypeBeanProvider;
        //private ObjectFactory<PrototypeBean> prototypeBeanProvider;
        private Provider<PrototypeBean> prototypeBeanProvider;

//        @Autowired
//        public ClientBean(PrototypeBean prototypeBean){
//            this.prototypeBean = prototypeBean;
//        }

        public int logic(){
//            prototypeBean.addCount(); // 이미 생성시점에 주입된 얘
//            return prototypeBean.getCount();
            //PrototypeBean PrototypeBean = prototypeBeanProvider.getObject();
            PrototypeBean PrototypeBean = prototypeBeanProvider.get();
            PrototypeBean.addCount(); // 이미 생성시점에 주입된 얘
            return PrototypeBean.getCount();

        }

    }

    @Scope("prototype")
    static class PrototypeBean{
        private int count = 0;
        public void addCount(){
            count++;
        }

        public int getCount(){
            return count;
        }

        @PostConstruct
        public void init(){
            System.out.println("PrototypeBean.inti " + this);
        }

        @PreDestroy
        public void destroy(){
            System.out.println("PrototypeBean.destroy");

        }
    }
}
