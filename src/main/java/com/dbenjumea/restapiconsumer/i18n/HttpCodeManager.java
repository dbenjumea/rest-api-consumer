package com.dbenjumea.restapiconsumer.i18n;

import java.util.ListResourceBundle;

public class HttpCodeManager extends ListResourceBundle{

    private static final Object[][] httpCodesMatrix= {
            {"204", "204 - No Content"},
            {"206", "206 - Partial Content"},
            {"300", "300 - Multiple Choices"},
            {"400", "400 - Bad Request"},
            {"401", "401 - Unauthorized"},
            {"403", "403 - Forbidden"},
            {"404", "404 - Not Found"},
            {"405", "405 - Method Not Allowed"},
            {"406", "406 - Not Acceptable"},
            {"408", "408 - Request Timeout"},
            {"409", "409 - Conflict"},
            {"417", "417 - Expectation Failed"},
            {"500", "500 - Internal Server Error"},
            {"501", "501 - Not Implemented"},
            {"502", "502 - Bad Gateway"},
            {"503", "503 - Service Unavailable"},
            {"504", "504 - Gateway Timeout"},
            {"505", "505 - HTTP Version Not Supported"},
            {"DEFAULT", "No specific message for status code"}
    };

    @Override
    protected Object[][] getContents() {
        return httpCodesMatrix;
    }
}
