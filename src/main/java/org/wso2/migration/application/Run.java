package org.wso2.migration.application;

import org.wso2.migration.toml.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.stream.Stream;

/**
 * @author Harsha Thirimanna
 */
public class Run {

    public static void main(String[] args){

        String sourceTOMLFileContent = loadFile("deployment-3.2.0.toml");
        String destTOMLFileContent = loadFile("deployment-4.1.0.toml");

        TOML sourceTOML = TOMLObjectFactory.marshall(sourceTOMLFileContent);
        TOML destTOML = TOMLObjectFactory.marshall(destTOMLFileContent);

        Map<String, TOMLTable> sourceTOMLTableMap = sourceTOML.getTOMLTableMap();
        Map<String, TOMLTable> destTOMLTableMap = destTOML.getTOMLTableMap();

        for (String sourceTableName : sourceTOMLTableMap.keySet()) {
            TOMLTable sourceTOMLTable = sourceTOMLTableMap.get(sourceTableName);
            TOMLTable destTOMLTable = destTOMLTableMap.get(sourceTableName);

            if(destTOMLTable == null){
                destTOML.appendTOMLNewLine(new TOMLNewLine(""));
                destTOML.appendNewTOMLTable(sourceTOMLTable);
                destTOML.appendTOMLComment(new TOMLComment("#Migrated Table "));
            }else{
                Map<String, TOMLKeyValue> sourceKeyValueMap = sourceTOMLTable.getKeyValueMap();
                Map<String, TOMLKeyValue> destKeyValueMap = destTOMLTable.getKeyValueMap();
                for (String sourceKey : sourceKeyValueMap.keySet()) {
                    TOMLKeyValue destTOMLKeyValue = destKeyValueMap.get(sourceKey);
                    if(destTOMLKeyValue == null){
                        TOMLKeyValue sourceTOMLKeyValue = sourceKeyValueMap.get(sourceKey);
                        destTOMLTable.appendTOMLNewLine(new TOMLNewLine(""));
                        destTOMLTable.appendTOMLBaseLine(sourceTOMLKeyValue);
                        destTOMLTable.appendTOMLComment(new TOMLComment("#Migrated KeyValue "));
                    }
                }
            }
        }

        String tomlString = TOMLObjectFactory.unmarshal(destTOML);
        createTOML(tomlString, "deployment-4.1.0-migrated.toml");
        System.out.println(">>>>>>");
        //System.out.println(tomlString,"deployment-4.1.0.toml");

    }

    private static void createTOML(String content, String file){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String loadFile(String fileName){
        StringBuilder fileContentBuilder = new StringBuilder();
        Stream<String> stream = null;
        try {
            stream = Files.lines(Paths.get(fileName), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Read the content with Stream
        stream.forEach(s -> fileContentBuilder.append(s).append("\n"));
        return fileContentBuilder.toString();
    }
}
