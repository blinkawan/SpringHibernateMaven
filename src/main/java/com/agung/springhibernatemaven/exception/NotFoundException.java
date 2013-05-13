/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agung.springhibernatemaven.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author awanlabs
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException{
    public NotFoundException(){
        
    }
}
