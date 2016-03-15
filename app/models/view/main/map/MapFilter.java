package models.view.main.map;

import play.data.format.Formats;

import java.util.Date;

public class MapFilter {

    @Formats.DateTime(pattern = "yyyy-MM-dd")
    public Date startDate;
    public static final String START_DATE_FIELD = "startDate";

    @Formats.DateTime(pattern = "yyyy-MM-dd")
    public Date endDate;
    public static final String END_DATE_FIELD = "endDate";
}
