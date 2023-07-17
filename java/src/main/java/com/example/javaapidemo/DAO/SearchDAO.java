package com.example.javaapidemo.DAO;

import com.example.javaapidemo.Model.Result;

public interface SearchDAO {

    Result retrieveContacts(String searchTerm, String sortOrder, int resultsPerPage, int currentPage);

}
