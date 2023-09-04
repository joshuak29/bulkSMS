package com.bulksms.users;

public record User(String name, boolean isAdmin, String username, String password) {
}