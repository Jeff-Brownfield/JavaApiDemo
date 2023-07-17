package com.example.javaapidemo.Controller;

import java.util.HashMap;

public record Parameters (String searchTerm, String sortOrder, int resultsPerPage, int currentPage) {

    public HashMap<String, String> checkInvalidParams() {
        HashMap<String, String> invalidParams = new HashMap<>();
        if(!searchTerm.equals("") && !searchTerm.matches("^\\pL+[\\pL\\pZ\\pP]{0,}")) {
            invalidParams.put("Search Term", searchTerm);
        }
        if(!sortOrder.toUpperCase().equals("DESC") && !sortOrder.toUpperCase().equals("ASC")){
            invalidParams.put("Sort Order", sortOrder);
        }
        if(resultsPerPage < 1 || resultsPerPage > 50) {
            invalidParams.put("Results Per Page", Integer.toString(resultsPerPage));
        }
        if(currentPage < 1) {
            invalidParams.put("Current Page", Integer.toString(currentPage));
        }
        return invalidParams;
    }
}
