package cn.zhangguifen.entities;

import lombok.Data;

/**
 * Created by yangken on 2020/9/10.
 */
@Data
public class Goods {
    private long id;
    private String goodname;
    private String description;
    private String create_by;
    private Integer store;

    public Integer getStore() {
        return store;
    }

    public void setStore(Integer store) {
        this.store = store;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGoodname() {
        return goodname;
    }

    public void setGoodname(String goodname) {
        this.goodname = goodname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreate_by() {
        return create_by;
    }

    public void setCreate_by(String create_by) {
        this.create_by = create_by;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "goodname='" + goodname + '\'' +
                ", description='" + description + '\'' +
                ", create_by='" + create_by + '\'' +
                '}';
    }
}