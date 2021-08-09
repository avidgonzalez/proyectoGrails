package miapp

import domain.Book
import domain.Person
import domain.Rent
import domain.RentBookDTO
import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(RentServicesService)
@Mock([Book])
class RentServicesServiceSpec extends Specification {

    private RentServicesService rentServices

    def setup() {
        rentServices = new RentServicesService()
        mockDomain(Book)
        /*rentServices.set
        Book bookStub = Stub(Book)*/
    }

    def cleanup() {
    }

    void "Validar searchBook"() {
        setup:
           //Book.get(_) >> new Book(title:"The Stand",ISBN: 123456789)
           //Book book = new Book(title:"The Stand",ISBN: 123456789).save()
        Book book = new Book(title:"The Stand",ISBN:743894792)
        //given:
        //    Book book = new Book(title:"The Stand",ISBN: 123456789)
        //and:
            //Book.get(1) >> book

        when:
            Book result = service.searchBook(1)

        then:
            /*result.getId() == 1
            result.getTitle() == "The Stand"
            result.getISBN() == 123456789*/

        1 * Book.get(_) >> book
        result.getId() == 1
    }

    void "Test prueba" (){
        given:
        Book book = new Book(title:"The Stand",ISBN: 123456789)
        def bookspy = spy(book)
        1*bookspy.get(_) >> book

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

        when:
        def result = service.searchBooks()

        then:
        result.size() == 2
        result.get(0).getId() == 1
        result.get(0).getTitle() == "The Stand"
        result.get(0).getISBN() == 123456789

        result.get(1).getId() == 2
        result.get(1).getTitle() == "The Shining"
        result.get(1).getISBN() == 789465132
    }

    void "Validar rentBooks"() {
        given:
        RentBookDTO rentBook = new RentBookDTO(person: new Person(idDocument: 10,name: "David Prueba"), listBook: [1,2])

        def stubbedRepository = Stub(Rent){
            get(_) >>  new Rent(books: [new Book(title:"The Stand",ISBN: 123456789),
                                         new Book(title:"The Shining",ISBN:789465132)],
                    expirationDate: new Date() +8,
                    outDate: new Date(),
                    person: new Person(idDocument: 10,name: "David Prueba"))
        }

        when:
        def result = service.rentBooks(rentBook)

        then:
        result.books.size() == 2
        result.books != null
    }
}
