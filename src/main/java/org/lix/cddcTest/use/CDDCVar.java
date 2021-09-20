package org.lix.cddcTest.use;

/**
 * @author lix-bi
 * @date 2021/9/20 12:20
 **/
public class CDDCVar<T> {
    /**
     * 名称
     */
    private String name;
    /**
     * 值
     */
    private T value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
