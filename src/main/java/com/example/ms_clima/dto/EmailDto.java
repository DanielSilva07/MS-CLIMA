package com.example.ms_clima.dto;

public record EmailDto (
        String emailTo,
        String subject,
        String text
){}
