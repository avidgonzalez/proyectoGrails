package miapp

import domain.Book
import domain.Person
import domain.Rent
import domain.RentBookDTO
import domain.RentRensponseDTO
import grails.transaction.Transactional

import java.text.SimpleDateFormat

@Transactional
class RentServicesService {

    def serviceMethod() {
    }

    @Transactional
    def searchBook(Integer id){
        def book = Book.get(id)
        return book
    }

    @Transactional
    def searchBooks(){
        def books = Book.findAll()
        return books
    }

    @Transactional
    def rentBooks(RentBookDTO rentBook){
        List<Book> listBook = new ArrayList<>()
        for (Integer id : rentBook.getListBook()){
            listBook.add(Book.get(id))
        }
        //println new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date())
        //def newdate = new Date().parse("d/M/yyyy H:m:s", new Date())

        return new Rent(listBook.toSet(), rentBook.getPerson(), new Date(), new Date() + 8).save()
    }

    @Transactional
    def searchRentBook(def id){
        def rent = Rent.get(id)
        Person personResponse = new Person(idDocument: rent.person.idDocument, name: rent.person.name)
        println(rent.books.stream().forEach({ a -> println(a.getId()) }))
        def response = new RentRensponseDTO(outDate: rent.outDate, expirationDate: rent.expirationDate, person: rent.person,books: rent.books )
        return response
    }

}
