package org.wso2.migration.toml;
/**
 * @author Harsha Thirimanna
 */
public class TOMLComment extends TOMLBaseLine{

    public TOMLComment(String baseLineString) {
        super(TOMLTypeEnum.COMMENT, baseLineString);
    }

    public TOMLComment(String baseLineString, Boolean isNewLineAdded) {
        super(TOMLTypeEnum.COMMENT, baseLineString, isNewLineAdded);
    }
}
