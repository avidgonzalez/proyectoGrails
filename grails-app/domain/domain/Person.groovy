package domain

import grails.rest.Resource

@Resource(uri='/person',formats=['json', 'xml'])
class Person {

    static belongsTo = [rent: Rent]

    Integer idDocument
    String name

    static constraints = {
    }

    static mapping = {
        rent lazy: false
    }

}
