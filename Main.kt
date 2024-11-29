// Інтерфейс Крісло
interface Chair {
    fun sitOn(): String
}

// Інтерфейс Диван
interface Sofa {
    fun lieOn(): String
}

// Інтерфейс Столик
interface CoffeeTable {
    fun putOn(): String
}

// Ар-деко продукти
class ArtDecoChair : Chair {
    override fun sitOn() = "Sitting on an Art Deco chair"
}

class ArtDecoSofa : Sofa {
    override fun lieOn() = "Lying on an Art Deco sofa"
}

class ArtDecoCoffeeTable : CoffeeTable {
    override fun putOn() = "You placed something on an Art Deco coffee table"
}

// Вікторіанські продукти
class VictorianChair : Chair {
    override fun sitOn() = "Sitting on a Victorian chair"
}

class VictorianSofa : Sofa {
    override fun lieOn() = "Lying on a Victorian sofa"
}

class VictorianCoffeeTable : CoffeeTable {
    override fun putOn() = "You placed something on a Victorian coffee table"
}

// Модерн продукти
class ModernChair : Chair {
    override fun sitOn() = "Sitting on a Modern chair"
}

class ModernSofa : Sofa {
    override fun lieOn() = "Lying on a Modern sofa"
}

class ModernCoffeeTable : CoffeeTable {
    override fun putOn() = "You placed something on a Modern coffee table"
}

// Абстрактна фабрика меблів
interface FurnitureFactory {
    fun createChair(): Chair
    fun createSofa(): Sofa
    fun createCoffeeTable(): CoffeeTable
}

// Ар-деко фабрика
class ArtDecoFurnitureFactory : FurnitureFactory {
    override fun createChair(): Chair = ArtDecoChair()
    override fun createSofa(): Sofa = ArtDecoSofa()
    override fun createCoffeeTable(): CoffeeTable = ArtDecoCoffeeTable()
}

// Вікторіанська фабрика
class VictorianFurnitureFactory : FurnitureFactory {
    override fun createChair(): Chair = VictorianChair()
    override fun createSofa(): Sofa = VictorianSofa()
    override fun createCoffeeTable(): CoffeeTable = VictorianCoffeeTable()
}

// Фабрика модерн
class ModernFurnitureFactory : FurnitureFactory {
    override fun createChair(): Chair = ModernChair()
    override fun createSofa(): Sofa = ModernSofa()
    override fun createCoffeeTable(): CoffeeTable = ModernCoffeeTable()
}

class FurnitureShop(private val factory: FurnitureFactory) {
    fun furnishRoom() {
        val chair = factory.createChair()  // Створюємо крісло
        val sofa = factory.createSofa()    // Створюємо диван
        val table = factory.createCoffeeTable()  // Створюємо столик

        println(chair.sitOn())  // Виводимо результат для крісла
        println(sofa.lieOn())   // Виводимо результат для дивану
        println(table.putOn())  // Виводимо результат для столика
    }
}

fun main() {
    // Магазин з ар-деко меблями
    val artDecoShop = FurnitureShop(ArtDecoFurnitureFactory())
    println("Art Deco furniture:")
    artDecoShop.furnishRoom()

    // Магазин з вікторіанськими меблями
    val victorianShop = FurnitureShop(VictorianFurnitureFactory())
    println("\nVictorian furniture:")
    victorianShop.furnishRoom()

    // Магазин з модерн меблями
    val modernShop = FurnitureShop(ModernFurnitureFactory())
    println("\nModern furniture:")
    modernShop.furnishRoom()
}
