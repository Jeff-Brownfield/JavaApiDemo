package com.example.javaapidemo.Controller;

import com.example.javaapidemo.DAO.SearchDAO;
import com.example.javaapidemo.Exception.InvalidParametersException;
import com.example.javaapidemo.Model.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@CrossOrigin
@RequestMapping("search")
public class SearchController {

    @Autowired
    private SearchDAO SearchDAO;
    private Logger log;

    public SearchController () {
        log = LoggerFactory.getLogger(this.getClass());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    //@PreAuthorize("hasRole('')")
    public Result searchNames(@RequestParam(defaultValue = "", name = "term") String searchTerm,
                              @RequestParam(defaultValue = "DESC", name = "sort") String sort,
                              @RequestParam(defaultValue = "10", name =  "qty") int resultsPerPage,
                              @RequestParam(defaultValue = "1", name = "current") int currentPage) {

         //Check parameters
         Parameters parameters = new Parameters(searchTerm, sort, resultsPerPage, currentPage);
        HashMap<String, String> invalidParams = parameters.checkInvalidParams();
         if(invalidParams.size() > 0){
             //If an invalid param is received, log message and send custom http400 status
             String message = "Received Invalid Parameters: " + invalidParams.toString();
             log.warn(message);
             throw new InvalidParametersException(message);
         }

        log.info("Received Request: {}/{}/{}/{}",searchTerm, sort, resultsPerPage, currentPage);
        return SearchDAO.retrieveContacts(searchTerm, sort, resultsPerPage, currentPage);
    }

}
