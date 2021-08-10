package miapp

import domain.Book
import domain.Person
import domain.Rent
import domain.RentBookDTO
import domain.RentRensponseDTO
import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(RentServicesService)
@Mock([Book,RentBookDTO])
class RentServicesServiceSpec extends Specification {

    private RentServicesService rentServices

    def setup() {
        rentServices = new RentServicesService()
        mockDomain(Book)
    }

    def cleanup() {
    }

    void "Validar searchBook"() {
        setup:
           Book.get(_) >> new Book(title:"The Stand",ISBN: 123456789)
           Book book = new Book(title:"The Stand",ISBN: 123456789).save()
        //given:
        //    Book book = new Book(title:"The Stand",ISBN: 123456789)
        //and:
            //Book.get(1) >> book

        when:
            Book result = service.searchBook(1)

        then:
            result.getId() == 1
            result.getTitle() == "The Stand"
            result.getISBN() == 123456789
    }

    void "Validar searchBooks"() {
        given:
        def stubbedRepository = Stub(Book){
            findAll(_) >>  [new Book(title:"The Stand",ISBN: 123456789),
                            new Book(title:"The Shining",ISBN:789465132)]
        }
        new Book(title:"The Stand",ISBN: 123456789).save()
        new Book(title:"The Shining",ISBN:789465132).save()

        when:
        def result = service.searchBooks()

        then:
        result.size() == 2
        result[0].getId() == 1
        result[0].getTitle() == "The Stand"
        result[0].getISBN() == 123456789

        result[1].getId() == 2
        result[1].getTitle() == "The Shining"
        result[1].getISBN() == 789465132
    }

    void "Validar rentBooks"() {
        given:
        RentBookDTO rentBook = new RentBookDTO(person: new Person(idDocument: 10,name: "David Prueba"), listBook: [1,2])
        /*new Rent(books: [new Book(title:"The Stand",ISBN: 123456789),
                    new Book(title:"The Shining",ISBN:789465132)],
                expirationDate: new Date() +8,
                outDate: new Date(),
                person: new Person(idDocument: 10,name: "David Prueba")).save()

        def stubbedRepository = Stub(Rent){
            get(_) >>  new Rent(books: [new Book(title:"The Stand",ISBN: 123456789),
                                         new Book(title:"The Shining",ISBN:789465132)],
                    expirationDate: new Date() +8,
                    outDate: new Date(),
                    person: new Person(idDocument: 10,name: "David Prueba"))
        }*/

        new Book(title:"The Stand",ISBN: 123456789).save()
        new Book(title:"The Shining",ISBN:789465132).save()

        when:
        Rent result = service.rentBooks(rentBook)

        then:
        result.books.size() == 2
        result.books != null
    }

    void "Validar searchRentBook"(){
        given:
        new Rent(books: [new Book(title:"The Shining",ISBN:789465132),
                         new Book(title:"The Stand",ISBN: 123456789)],
                expirationDate: new Date() +8,
                outDate: new Date(),
                person: new Person(idDocument: 10,name: "David Prueba")).save()
        when:
        RentRensponseDTO result = service.searchRentBook(1)

        then:
        result != null
        result.getBooks().size() == 2
        result.getBooks()[0].getId() == 1
        result.getBooks()[0].getTitle() == "The Stand"
        result.getBooks()[0].getISBN() == 123456789

        result.getBooks()[1].getId() == 2
        result.getBooks()[1].getTitle() == "The Shining"
        result.getBooks()[1].getISBN() == 789465132

    }
}
