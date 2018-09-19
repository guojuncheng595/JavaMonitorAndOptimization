package cn.gjc.monitor_tuning.cn.gjc.monitor_turning.chapter2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/***
 *@ClassName MemoryController
 *@Description 模拟内存溢出
 *@Auther gjc
 *@Date 2018/9/19 9:38
 *
 **/

@RestController
public class MemoryController {

    private List<User> userList = new ArrayList<User>();
    private List<Class<?>> classList = new ArrayList<Class<?>>();

    /**
     * -Xmx32M(最大内存) -Xms32M(最小内存)  堆内存溢出
     * @return
     */
    @GetMapping("/heap")
    public String heap(){
        int i = 0;
        while (true) {
            userList.add(new User(i++, UUID.randomUUID().toString()));
        }
    }

    /**
     * -XX:MetaspaceSize=32M -XX:MaxMetaspaceSize=32M 非堆内存溢出 Metaspace
     * @return
     */
    @GetMapping("/nonheap")
    public String nonheap(){
        while (true) {
            classList.addAll(Metaspace.createClasss());
        }
    }

}
