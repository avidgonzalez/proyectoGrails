package miapp

import grails.rest.RestfulController
import domain.Book

class BookController extends RestfulController {
    static responseFormats = ['json', 'xml']
    BookController() {
        super(Book)
    }
}
