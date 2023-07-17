package com.example.javaapidemo.DAO;

import com.example.javaapidemo.Model.Contact;
import com.example.javaapidemo.Model.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcSearchDAO implements SearchDAO {

    private final JdbcTemplate jdbcTemplate;
    private Logger log;

    public JdbcSearchDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        log = LoggerFactory.getLogger(this.getClass());
    }

    @Override
    public Result retrieveContacts(String searchTerm, String sortOrder, int resultsPerPage, int currentPage) {

        //TODO Need Try Catch for database calls


        ArrayList<Contact> contacts = new ArrayList<>();
        int totalResults = 0;
        int offset = (currentPage -1) * resultsPerPage;
        String correctedSearchTerm = "'%" + searchTerm + "%'";
        Contact contact;
        String query;
        SqlRowSet results;
        String query2;

        if(searchTerm.equals("")) {
            query = "SELECT COUNT (*) AS count " +
                    "FROM contacts;";
            results = jdbcTemplate.queryForRowSet(query);
            while(results.next()){
                totalResults = results.getInt("count");
            }
            query2  = "SELECT * " +
                    "FROM contacts " +
                    "ORDER BY name " + (sortOrder.toUpperCase().equals("DESC")?"DESC":"ASC") + " " +
                    "LIMIT ? OFFSET ?;";
            results = jdbcTemplate.queryForRowSet(query2, resultsPerPage, offset);
        } else {
            query = "SELECT COUNT (*) AS count " +
                    "FROM contacts " +
                    "WHERE name LIKE '%' || ? || '%';";
            results = jdbcTemplate.queryForRowSet(query, searchTerm);
            while(results.next()){
                totalResults = results.getInt("count");
            }
            query2  = "SELECT * " +
                    "FROM contacts " +
                    "WHERE name LIKE '%' || ? || '%' " +
                    "ORDER BY name " + (sortOrder.toUpperCase().equals("DESC")?"DESC":"ASC") + " " +
                    "LIMIT ? OFFSET ?;";
            results = jdbcTemplate.queryForRowSet(query2, searchTerm, resultsPerPage, offset);
        }

        while(results.next()) {
            contact = mapRowToContact(results);
            contacts.add(contact);
        }

        Result result = new Result(contacts, totalResults, currentPage);

        log.info("Found " + totalResults + " results with: {}/{}/{}/{}",searchTerm, sortOrder, resultsPerPage, currentPage);
        return result;
    }

    private Contact mapRowToContact(SqlRowSet results) {
        Contact contact = new Contact(results.getString("name"),
                                      results.getString("phone_number"));
        return contact;
    }
}
