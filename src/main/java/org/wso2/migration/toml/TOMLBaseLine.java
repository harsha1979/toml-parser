package org.wso2.migration.toml;
/**
 * @author Harsha Thirimanna
 */
public abstract class TOMLBaseLine {
    private TOMLTypeEnum tomlTypeEnum = null ;
    private StringBuilder baseLineString =  new StringBuilder();

    public TOMLBaseLine(TOMLTypeEnum tomlTypeEnum, String baseLineString) {
        this.tomlTypeEnum = tomlTypeEnum;
        this.baseLineString.append(baseLineString).append("\n");
    }

    public TOMLBaseLine(TOMLTypeEnum tomlTypeEnum, String baseLineString, Boolean isNewLineAdded) {
        this.tomlTypeEnum = tomlTypeEnum;
        this.baseLineString.append(baseLineString);
        if(isNewLineAdded){
            this.baseLineString.append("\n");
        }
    }

    public TOMLBaseLine(String baseLineString) {
        this.baseLineString.append(baseLineString).append("\n");
    }

    public String getBaseLineString() {
        return baseLineString.toString();
    }

    protected TOMLBaseLine appendBaseLineString(String baseLineString){
        this.baseLineString.append(baseLineString);
        return this;
    }

    public boolean isType(TOMLTypeEnum tomlTypeEnum){
        if(tomlTypeEnum.name().equals(this.tomlTypeEnum.name())){
            return true;
        }
        return false;
    }
}
