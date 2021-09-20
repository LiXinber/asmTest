package org.lix.cddcTest.use;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lix-bi
 * @date 2021/9/20 12:14
 **/
public class CDDCContext {

    /**
     * 除法配置
     */
    private CDDCContext.DivisorConfig divisorConfig;

    public DivisorConfig getDivisorConfig() {
        return divisorConfig;
    }

    public void setDivisorConfig(DivisorConfig divisorConfig) {
        this.divisorConfig = divisorConfig;
    }

    /**
     * 除法配置
     */
    public static class DivisorConfig {
        /**
         * 精度
         */
        private int scale;
        /**
         * 舍入配置
         */
        private RoundingMode roundingMode;


        public int getScale() {
            return scale;
        }

        public void setScale(int scale) {
            this.scale = scale;
        }

        public RoundingMode getRoundingMode() {
            return roundingMode;
        }

        public void setRoundingMode(RoundingMode roundingMode) {
            this.roundingMode = roundingMode;
        }
    }

    /**
     * 变量字典
     */
    private Map<String, CDDCVar> mapVar = new HashMap<>();

    /**
     * 变量赋值
     * @param cddcVar
     */
    public void setVar(CDDCVar cddcVar) {
        mapVar.put(cddcVar.getName(), cddcVar);
    }

    /**
     * 变量赋值
     * @param name
     * @param value
     */
    public void setVar(String name, Object value) {
        CDDCVar cddcVar = new CDDCVar();
        cddcVar.setName(name);
        cddcVar.setValue(value);
        mapVar.put(name, cddcVar);
    }

    /**
     * 变量取值
     * @param name
     * @return
     */
    public CDDCVar getVar(String name) {
        return mapVar.getOrDefault(name, null);
    }

    /**
     * 变量取值
     * @param name
     * @return
     */
    public Object getVarValue(String name) {
        CDDCVar cddcVar = mapVar.get(name);
        if (cddcVar != null) {
            return cddcVar.getValue();
        }
        return null;
    }

    /**
     * 变量取值
     * @param name
     * @return
     */
    public BigDecimal getVarBigDecimalValue(String name){
        CDDCVar cddcVar=getVar(name);
        if(cddcVar!=null){
            return (BigDecimal)cddcVar.getValue();
        }
        return null;
    }

    /**
     * 扩展数据，用于业务使用
     */
    private Map<String, Object> extData = new HashMap<>();

    /**
     * 默认内容
     * @return
     */
    public static CDDCContext getDefault() {
        CDDCContext content = new CDDCContext();
        CDDCContext.DivisorConfig divisorConfig = new CDDCContext.DivisorConfig();
        divisorConfig.setRoundingMode(RoundingMode.HALF_UP);
        divisorConfig.setScale(8);
        content.setDivisorConfig(divisorConfig);
        return content;
    }

    public Map<String, CDDCVar> getMapVar() {
        return mapVar;
    }

    public void setMapVar(Map<String, CDDCVar> mapVar) {
        this.mapVar = mapVar;
    }
}
