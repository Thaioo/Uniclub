package com.example.uniclub06.Reponse;

import lombok.Data;

@Data
public class BaseReponse {
    private int statusCode=200;
    private String Message;
    private  Object Data;
}
