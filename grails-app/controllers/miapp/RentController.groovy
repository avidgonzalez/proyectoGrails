package miapp

import domain.Book
import domain.RentBookDTO
import domain.RentRensponseDTO
import grails.converters.JSON

class RentController {

    RentServicesService rentServicesService

    static allowedMethods = [index: 'GET', searchBook: 'GET', rentBooks: 'POST',searchRentBook:'GET']

    def index() { }

    def searchBook(){
        Book book = rentServicesService.searchBook(Integer.valueOf(params.list('idBook').get(0)))
        render(contentType: "application/json") {book}
    }

    def searchBooks(){
        Book[] books = rentServicesService.searchBooks()
        render(contentType: "application/json") {books}
    }

    def rentBooks(RentBookDTO rentBook){
       def books = rentServicesService.rentBooks(rentBook)
       render(contentType: "application/json") {books}
    }

    def searchRentBook(){
        RentRensponseDTO response = rentServicesService.searchRentBook(Integer.valueOf(params.list('idRent').get(0)))
        render response  as JSON
    }
}
