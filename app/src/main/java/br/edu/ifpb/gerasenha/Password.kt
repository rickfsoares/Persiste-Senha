package br.edu.ifpb.gerasenha

import java.io.Serializable

class Password : Serializable{
    private var id: Int
    private var description: String
    private var generatedPassword: String
    private var createdDate: Int
    private var updatedDate: Int

    private var hasCaptalize: Boolean = false
    private var hasNumbers: Boolean = false
    private var hasSymbols: Boolean = false

    constructor(description: String, generatedPassword: String, hasCaptalize: Boolean = false,
                hasNumbers: Boolean = false, hasSymbols: Boolean = false) {
        this.id = -1
        this.description = description
        this.generatedPassword = generatedPassword
        this.createdDate = -1
        this.updatedDate = -1

        this.hasCaptalize = hasCaptalize
        this.hasNumbers = hasNumbers
        this.hasSymbols = hasSymbols
    }

    constructor(id: Int, description: String, generatedPassword: String, createdDate: Int, updatedDate: Int) {
        this.id = id
        this.description = description
        this.generatedPassword = generatedPassword
        this.createdDate = createdDate
        this.updatedDate = updatedDate
    }

    fun getId(): Int {
        return this.id
    }

    fun getDescription(): String {
        return this.description
    }

    fun setDescription(description: String) {
        this.description = description
    }

    fun getGeneratedPassword(): String {
        return this.generatedPassword
    }

    fun setGeneratedPassword(genPasswd: String) {
        this.generatedPassword = generatedPassword
    }

    fun getHasNumbers(): Boolean {
        return this.hasNumbers
    }

    fun setHasNumbers(status: Boolean) {
        this.hasNumbers = status
    }

    fun getHasSymbols(): Boolean {
        return this.hasSymbols
    }

    fun setHasSymbols(status: Boolean) {
        this.hasSymbols = status
    }

    fun getHasCapitalize(): Boolean {
        return this.hasCaptalize
    }

    fun setHasCapitalize(status: Boolean) {
        this.hasCaptalize = status
    }

    override fun toString(): String {
        return "${this.description} (${this.generatedPassword.length})"
    }



}