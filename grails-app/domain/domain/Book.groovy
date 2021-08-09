package domain

import grails.rest.Resource

@Resource(uri='/books',formats=['json', 'xml'])
class Book {

    Integer ISBN
    String title

}
