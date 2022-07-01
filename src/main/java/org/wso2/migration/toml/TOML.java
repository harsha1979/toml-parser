package org.wso2.migration.toml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
/**
 * @author Harsha Thirimanna
 */
public class TOML {

    private ArrayList<TOMLBaseLine> tomlBaseLineArrayList = new ArrayList<>();
    private Map<String, TOMLTable> tomlTableMap = new HashMap<>();

    public void addNewTOMLTable(TOMLTable tomlBaseLine){
        tomlBaseLineArrayList.add(tomlBaseLine);
        tomlTableMap.put(tomlBaseLine.getTomlTableName().getBaseLineString(), tomlBaseLine);
    }

    public void appendNewTOMLTable(TOMLTable tomlBaseLine){
        tomlBaseLineArrayList.add(0,tomlBaseLine);
        tomlTableMap.put(tomlBaseLine.getTomlTableName().getBaseLineString(), tomlBaseLine);
    }

    public void appendTOMLComment(TOMLComment tomlComment){
        tomlBaseLineArrayList.add(0,tomlComment);
    }

    public void appendTOMLNewLine(TOMLNewLine tomlNewLine){
        tomlBaseLineArrayList.add(0,tomlNewLine);
    }

    public ArrayList<TOMLBaseLine> getTOMLBaseLineArrayList() {
        return tomlBaseLineArrayList;
    }

    public Map<String, TOMLTable> getTOMLTableMap() {
        return tomlTableMap;
    }
}
