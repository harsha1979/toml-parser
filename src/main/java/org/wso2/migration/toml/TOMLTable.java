package org.wso2.migration.toml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
/**
 * @author Harsha Thirimanna
 */
public class TOMLTable extends TOMLBaseLine{

    private TOMLTableName tomlTableName = null;
    private ArrayList<TOMLBaseLine> baseLineArrayList = new ArrayList<>();
    private Map<String, TOMLKeyValue> keyValueMap = new HashMap<>();


    public TOMLTable(String tableName) {
        super(TOMLTypeEnum.TABLE,tableName);
        tomlTableName = new TOMLTableName(tableName);
    }

    public void addTOMLBaseLine(TOMLBaseLine tomlBaseLine){
        baseLineArrayList.add(tomlBaseLine);
        appendBaseLineString(tomlBaseLine.getBaseLineString());
        if(tomlBaseLine.isType(TOMLTypeEnum.KEY_VALUE)){
            TOMLKeyValue tomlKeyValue = (TOMLKeyValue)tomlBaseLine ;
            keyValueMap.put(tomlKeyValue.getKey(), tomlKeyValue);
        }
    }

    public void appendTOMLBaseLine(TOMLBaseLine tomlBaseLine){
        baseLineArrayList.add(0, tomlBaseLine);
        appendBaseLineString(tomlBaseLine.getBaseLineString());
        if(tomlBaseLine.isType(TOMLTypeEnum.KEY_VALUE)){
            TOMLKeyValue tomlKeyValue = (TOMLKeyValue)tomlBaseLine ;
            keyValueMap.put(tomlKeyValue.getKey(), tomlKeyValue);
        }
    }

    public void appendTOMLNewLine(TOMLNewLine tomlNewLine){
        baseLineArrayList.add(0,tomlNewLine);
    }

    public void appendTOMLComment(TOMLComment tomlComment){
        baseLineArrayList.add(0, tomlComment);
    }

    public TOMLTableName getTomlTableName() {
        return tomlTableName;
    }

    public TOMLKeyValue getTOMLKeyValue(String key){
        return keyValueMap.get(key);
    }

    public Map<String, TOMLKeyValue> getKeyValueMap() {
        return keyValueMap;
    }

    public void setKeyValueMap(Map<String, TOMLKeyValue> keyValueMap) {
        this.keyValueMap = keyValueMap;
    }

    public ArrayList<TOMLBaseLine> getBaseLineArrayList() {
        return baseLineArrayList;
    }
}
