package com.yiranzhaojiu.book.services.impl;

import com.yiranzhaojiu.book.dao.BookMapper;
import com.yiranzhaojiu.book.services.IBookFacade;
import com.yiranzhaojiu.springmvc.springframework.beans.factory.annotation.Autowired;


public class BookFacadeService implements IBookFacade {

    @Autowired
    public BookMapper bookMapper;

    public BookMapper getBookMapper() {
        return this.bookMapper;
    }

}
