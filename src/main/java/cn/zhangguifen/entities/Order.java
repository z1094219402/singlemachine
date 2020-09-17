package cn.zhangguifen.entities;

/**
 * Created by yangken on 2020/9/10.
 */
public class Order {
    private long id;
    private long uid;
    private long pid;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public long getPid() {
        return pid;
    }

    public void setPid(long pid) {
        this.pid = pid;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", uid=" + uid +
                ", pid=" + pid +
                '}';
    }
}