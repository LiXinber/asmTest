package org.lix.cddcTest.use;

import java.math.BigDecimal;

/**
 * @author lix-bi
 * @date 2021/9/20 12:13
 **/
public class BigDecimalFucTest extends CDDCFunction{
    @Override
    public CDDCResult apply(CDDCContext cddcContext) {
        return new CDDCResult(new BigDecimal("1").add(cddcContext.getVarBigDecimalValue("ABC")));
    }
}
