package models.view.main.keyword;

import play.data.format.Formats;

import java.util.Date;

public class KeywordEventsFilter {
    @Formats.DateTime(pattern = "yyyy-MM-dd")
    public Date startDate;
    public static final String START_DATE_FIELD = "startDate";

    @Formats.DateTime(pattern = "yyyy-MM-dd")
    public Date endDate;
    public static final String END_DATE_FIELD = "endDate";

    public long keywordID;
}
