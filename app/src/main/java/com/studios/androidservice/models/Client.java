package com.studios.androidservice.models;

public class Client {
    //
    // "Name": "Madan",
    //         "Phone": "9841252876",
    //         "Password": "12345",
    //         "Email": "test1@gmail.com",
    //         "Business": "Barber",
    //         "Description": "I am a Barber",
    //         "Cost": "200",
    //         "Latitude": "24.009",
    //         "Longitude": "52.009",
    //         "Area": "Baneshwor"

    public String Phone;
    public String Password;
    public String Name;
    public String Area;
    public String Email;
    public String Latitude;
    public String Longitude;

    public Client() {
    }

    public Client(String phone, String password, String name, String area, String email, String latitude, String longitude) {
        Phone = phone;
        Password = password;
        Name = name;
        Area = area;
        Email = email;
        Latitude = latitude;
        Longitude = longitude;
    }
}
