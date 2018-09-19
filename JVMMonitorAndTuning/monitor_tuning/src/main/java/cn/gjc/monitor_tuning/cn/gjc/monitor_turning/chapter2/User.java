package cn.gjc.monitor_tuning.cn.gjc.monitor_turning.chapter2;

/***
 *@ClassName User
 *@Description TODO
 *@Auther gjc
 *@Date 2018/9/19 9:40
 *
 **/
public class User {

    private int id;
    private String name;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
