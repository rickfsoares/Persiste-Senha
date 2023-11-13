package br.edu.ifpb.gerasenha

class Password {
    var id: Int
    var description: String
    var generatedPassword: String
    var createdDate: Int
    var updatedDate: Int

    constructor(description: String, generatedPassword: String) {
        this.id = -1
        this.description = description
        this.generatedPassword = generatedPassword
        this.createdDate = -1
        this.updatedDate = -1
    }

    constructor(id: Int, description: String, generatedPassword: String, createdDate: Int, updatedDate: Int) {
        this.id = id
        this.description = description
        this.generatedPassword = generatedPassword
        this.createdDate = createdDate
        this.updatedDate = updatedDate
    }


}