package domain

class RentRensponseDTO {

    Date outDate
    Date expirationDate
    Person person
    Book[] books

    RentRensponseDTO(Date outDate, Date expirationDate, Person person, Set<Book> listBook){
        this.books = listBook
        this.person = person
        this.outDate = outDate
        this.expirationDate = expirationDate
    }
    RentRensponseDTO(Date outDate, Date expirationDate, Person person, Book[] listBook){
        this.books = listBook
        this.person = person
        this.outDate = outDate
        this.expirationDate = expirationDate
    }

    RentRensponseDTO() {

    }
}
