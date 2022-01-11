package anhtester.com.config;

import anhtester.com.helpers.Props;

public class Constants {
    public static final int IMPLICIT_WAIT = Integer.parseInt(Props.getValue("IMPLICIT_WAIT"));
    public static final int PAGE_LOAD_TIMEOUT  = Integer.parseInt(Props.getValue("PAGE_LOAD_TIMEOUT"));
    public static final int DEFAULT_WAIT = Integer.parseInt(Props.getValue("DEFAULT_WAIT"));
}
