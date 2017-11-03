package info.xiaomo.mybatis.domain


/**
 * @author : xiaomo
 */

class User {

    private var id: Long? = null

    private var name: String? = null

    private var age: Int? = null

    constructor(id: Long?, name: String, age: Int?) {
        this.id = id
        this.name = name
        this.age = age
    }

    constructor(name: String, age: Int?) {
        this.name = name
        this.age = age
    }

}
