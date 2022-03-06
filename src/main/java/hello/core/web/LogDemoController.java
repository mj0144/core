package hello.core.web;

import hello.core.common.Mylogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class LogDemoController {

    private final LogDemoService logDemoService;
    //private final ObjectProvider<Mylogger> myloggerProvider;
    private final Mylogger mylogger;

    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) throws InterruptedException {
        //Mylogger mylogger = myloggerProvider.getObject();
        String requestURL = request.getRequestURL().toString();

        System.out.println("mylogger : " + mylogger.getClass());

        mylogger.setRequestURL(requestURL);

        mylogger.log("Controller test");
        Thread.sleep(1000L);

        logDemoService.logic("testId");
        return "OK";
    }

}
