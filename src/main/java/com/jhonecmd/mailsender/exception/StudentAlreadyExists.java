package com.jhonecmd.mailsender.exception;

public class StudentAlreadyExists extends RuntimeException{
    public StudentAlreadyExists() {
        super("Student already exists!");
    }
}
