package com.assignment.pharmapartners.helper;


import com.google.gson.Gson;

/**
 * This is class is responsible for parsing the input from the client-side,
 * to the expected class. Its parse the string json to an (Currency) Class.
 */
public class JsonLogic {

    private static final Gson gson = new Gson();

    public static Object getObject(Class expected, String json) {
        Object result = null;
        try {
            result = gson.fromJson(json, expected);
        } catch (Exception error) {
            System.err.println("An error occurred while converting the object to a class");
            System.err.println(error);
        }
        return result;
    }

}
