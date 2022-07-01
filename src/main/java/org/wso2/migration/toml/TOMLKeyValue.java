package org.wso2.migration.toml;
/**
 * @author Harsha Thirimanna
 */
public class TOMLKeyValue extends TOMLBaseLine{
    private String key ;
    private String value ;
    public TOMLKeyValue(String baseLineString) {
        super(TOMLTypeEnum.KEY_VALUE, baseLineString);
        baseLineString = baseLineString.trim();
        String[] baseLineStringArray = baseLineString.split("=");
        this.key = baseLineStringArray[0];
        this.value = baseLineStringArray[1];
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
