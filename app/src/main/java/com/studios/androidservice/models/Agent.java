package com.studios.androidservice.models;

public class Agent {
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
    public String Business;
    public String Category;
    public String HomeService;
    public String Description;
    public String Latitude;
    public String Longitude;

    public Agent() {
    }

    public Agent(String phone,
                 String password,
                 String name,
                 String area,
                 String email,
                 String business,
                 String category,
                 String homeService,
                 String description,
                 String latitude,
                 String longitude)
    {
        Phone = phone;
        Password = password;
        Name = name;
        Area = area;
        Email = email;
        Business = business;
        Category = category;
        HomeService = homeService;
        Description = description;
        Latitude = latitude;
        Longitude = longitude;
    }
}
