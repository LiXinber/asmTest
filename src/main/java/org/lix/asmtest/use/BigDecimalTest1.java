package org.lix.asmtest.use;

import java.math.BigDecimal;

/**
 * @author lix-bi
 * @date 2021/9/15 14:23
 **/
public class BigDecimalTest1 {
    public BigDecimal add(){
        return new BigDecimal("3").add(new BigDecimal("4"));
    }
    public BigDecimal multiply(){
        return new BigDecimal("3").multiply(new BigDecimal("4"));
    }
    public BigDecimal divide(){
        return new BigDecimal("3").divide(new BigDecimal("4"));
    }
}
