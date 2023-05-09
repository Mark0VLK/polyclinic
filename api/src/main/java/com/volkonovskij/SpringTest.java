package com.volkonovskij;

import com.volkonovskij.aspect.Counter;
import com.volkonovskij.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringTest {
    private final static Logger logger = Logger.getLogger(SpringTest.class);
    public static void main(String[] args) {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(
                "com.volkonovskij"
        );
        UserService userService = applicationContext.getBean("userServiceImpl", UserService.class);
        Counter counter = applicationContext.getBean("counter", Counter.class);

//        logger.info(userService.findAll());
//        logger.info(userService.findById(9L));
//        logger.info(userService.changedOverTime(1));
//        logger.info(userService.update(61L, new User(9L, "1t28w", "lt682avw",
//                "+375298832456", "asdf@email.com","KH103456t8277")));
//        logger.info(userService.create(new User("ka", "qaacd123)", "2ueu1a90",
//                "Muu0_erk@emaail","uu99ea904")));
//        logger.info(userService.emailAndPhoneNumber());
        logger.info(
                "\nfindAll method was called " + counter.getFindAll() + " times\n" +
                "findById method was called " + counter.getFindById() + " times\n" +
                "create method was called " + counter.getCreate() + " times\n" +
                "update method was called " + counter.getUpdate() + " times\n" +
                "delete method was called " + counter.getDelete() + " times\n" +
                "emailAndPhoneNumber method was called " + counter.getEmailAndPhoneNumber() + " times\n" +
                "changedOverTime method was called " + counter.getChangedOverTime() + " times\n"
        );
    }
}