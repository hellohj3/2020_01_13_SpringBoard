package com.springboard.blog;

import java.util.Date;

/**
 * Class for holding email information
 * @author MyHyem
 * @since 2020.02.08
 * @version 1.0
 * @see
 *
 * <pre>
 * << Modification Information >>
 *
 *   Modified date       Modifier              Modifications
 *  --------------      ----------       ---------------------------
 *  2020.02.08          MyHyem              Initial Creation
 *  </pre>
 */
public class EmailVO {
    private String emailTo;
    private String emailFrom;
    private String emailTitle;
    private String emailContent;

    public String getEmailTo() {
        return emailTo;
    }

    public void setEmailTo(String emailTo) {
        this.emailTo = emailTo;
    }

    public String getEmailFrom() {
        return emailFrom;
    }

    public void setEmailFrom(String emailFrom) {
        this.emailFrom = emailFrom;
    }

    public String getEmailTitle() {
        return emailTitle;
    }

    public void setEmailTitle(String emailTitle) {
        this.emailTitle = emailTitle;
    }

    public String getEmailContent() {
        return emailContent;
    }

    public void setEmailContent(String emailContent) {
        this.emailContent = emailContent;
    }

    @Override
    public String toString() {
        return "EmailVO{" +
                "emailTo='" + emailTo + '\'' +
                ", emailFrom='" + emailFrom + '\'' +
                ", emailTitle='" + emailTitle + '\'' +
                ", emailContent='" + emailContent + '\'' +
                '}';
    }
}
