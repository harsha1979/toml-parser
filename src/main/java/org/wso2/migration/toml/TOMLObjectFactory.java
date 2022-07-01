package org.wso2.migration.toml;

import java.util.ArrayList;
import java.util.Scanner;
/**
 * @author Harsha Thirimanna
 */
public class TOMLObjectFactory {

    private String tomlString;
    private TOML toml = new TOML();

    private static TOMLObjectFactory tomlObjectFactory = null;


    public static String unmarshal(TOML toml){
        StringBuilder tomlStringBuilder = new StringBuilder();
        ArrayList<TOMLBaseLine> tomlBaseLineArrayList = toml.getTOMLBaseLineArrayList();
        for (TOMLBaseLine tomlBaseLine : tomlBaseLineArrayList) {
            if(tomlBaseLine.isType(TOMLTypeEnum.TABLE)){
                TOMLTable tomlTable = (TOMLTable)tomlBaseLine ;
                tomlStringBuilder.append(tomlTable.getTomlTableName().getBaseLineString());
                ArrayList<TOMLBaseLine> baseLineArrayList = tomlTable.getBaseLineArrayList();
                for (TOMLBaseLine baseLine : baseLineArrayList) {
                    tomlStringBuilder.append(baseLine.getBaseLineString());
                }
            }else{
                tomlStringBuilder.append(tomlBaseLine.getBaseLineString());
                System.out.println("");
            }
        }
        return tomlStringBuilder.toString();
    }

    public static TOML marshall(String tomlString){

        tomlObjectFactory = new TOMLObjectFactory();
        tomlObjectFactory.setTomlString(tomlString);

        try {
            Scanner scanner = new Scanner(tomlString);
            TOMLTable tomlTable = null;
            while (scanner.hasNextLine()) {
                String baseLine = scanner.nextLine();
                baseLine = baseLine.trim();
                String firstChar = "";
                if(baseLine.length()>0){
                    firstChar = baseLine.substring(0,1);
                }
                TOMLBaseLine tomlBaseLine = null;
                switch(firstChar){
                    case "#":
                        tomlBaseLine = new TOMLComment(baseLine);
                        tomlTable.addTOMLBaseLine(tomlBaseLine);
                        break;
                    case "[":
                        tomlTable = new TOMLTable(baseLine);
                        tomlObjectFactory.getTOML().addNewTOMLTable(tomlTable);
                        break;
                    case "":
                        tomlBaseLine = new TOMLNewLine(baseLine);
                        tomlTable.addTOMLBaseLine(tomlBaseLine);
                        break;
                    default:
                        tomlBaseLine = new TOMLKeyValue(baseLine);
                        tomlTable.addTOMLBaseLine(tomlBaseLine);
                }
            }
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tomlObjectFactory.getTOML();
    }

    //public static StringBuilder


    private static TOMLBaseLine getTOMLBaseLine(String tomlBaseLineString){
        TOMLBaseLine tomlBaseLine = null ;
        tomlBaseLineString = tomlBaseLineString.trim();
        String firstChar = "";
        if(tomlBaseLineString.length()>0){
            firstChar = tomlBaseLineString.substring(0,1);
        }
        switch(firstChar){
            case "#":
                tomlBaseLine = new TOMLComment(tomlBaseLineString);
                break;
            case "[":
                tomlBaseLine = new TOMLTable(tomlBaseLineString);
                break;
            case "":
                tomlBaseLine = new TOMLNewLine(tomlBaseLineString);
                break;
            default:
                tomlBaseLine = new TOMLKeyValue(tomlBaseLineString);
        }
        return tomlBaseLine;
    }
    public TOML getTOML() {
        return toml;
    }
    public void setTomlString(String tomlString) {
        this.tomlString = tomlString;
    }
}
