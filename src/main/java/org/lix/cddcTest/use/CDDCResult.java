package org.lix.cddcTest.use;

/**
 * @author lix-bi
 * @date 2021/9/20 12:15
 **/
public class CDDCResult<T> {
    public CDDCResult() {
    }

    public CDDCResult(T value) {
        this.value = value;
    }

    /**
     * 值
     */
    private T value;

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
