package com.example.javaapidemo.Model;

import java.util.ArrayList;

public record Result (ArrayList<Contact> contacts, int totalContacts, int currentPage) {
}
