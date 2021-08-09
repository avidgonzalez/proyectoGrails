package miapp

import domain.RentBookDTO
import domain.RentRensponseDTO
import grails.converters.JSON

class RentController {

    RentServicesService rentServicesService

    static allowedMethods = [index: 'GET', searchBook: 'GET', rentBooks: 'POST',searchRentBook:'GET']

    def index() { }

    def searchBook(){
        def book = rentServicesService.searchBook(Integer.valueOf(params.list('idBook').get(0)))
        render(contentType: "application/json") {book}
    }

    def searchBooks(){
        def books = rentServicesService.searchBooks()
        render(contentType: "application/json") {books}
    }

    def rentBooks(RentBookDTO rentBook){
       def books = rentServicesService.rentBooks(rentBook)
       render(contentType: "application/json") {books}
    }

    def searchRentBook(){
        RentRensponseDTO response = rentServicesService.searchRentBook(params.list('idRent'))
        render response  as JSON
    }
}
