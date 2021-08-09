package domain

import grails.rest.Resource
import org.grails.databinding.BindingFormat

@Resource(uri='/rents',formats=['json', 'xml'])
class Rent {
    static hasMany = [books: Book]
    static hasOne = [person: Person]
    static fetchMode = [books: 'eager',person: 'eager']

    Date outDate
    Date expirationDate

    Rent(Set<Book> listBook, Person person, Date outDate, Date expirationDate){
        this.books = listBook
        this.person = person
        this.outDate = outDate
        this.expirationDate = expirationDate
    }

    Rent() {

    }
}
