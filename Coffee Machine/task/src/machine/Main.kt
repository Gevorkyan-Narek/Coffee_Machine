package machine

import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    val coffeeMachine = CoffeeMachine()
    while (coffeeMachine.key) {
        if (coffeeMachine.status == CoffeeMachine.Currancy.ACTION || coffeeMachine.status == CoffeeMachine.Currancy.TAKE ||
                coffeeMachine.status == CoffeeMachine.Currancy.REMAINING)
            coffeeMachine.action(coffeeMachine.status, "")
        else if (coffeeMachine.status == CoffeeMachine.Currancy.WAIT)
            coffeeMachine.action(CoffeeMachine.Currancy.valueOf(scanner.next().toUpperCase()), "")
        else coffeeMachine.action(coffeeMachine.status, scanner.next())
    }
}

class CoffeeMachine {
    var key = true
    var status = Currancy.ACTION
        get() = field
    var sum = 550
    var water = 400
    var milk = 540
    var gram = 120
    var cups = 9

    fun action(value: Currancy, secondValue: String) {
        if (status != value) status = value
        when (status) {
            Currancy.ACTION -> {
                print("Write action (buy, fill, take, remaining, exit): ")
                status = Currancy.WAIT
            }
            Currancy.BUY -> {
                print("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino," +
                        "\nback - to main menu: ")
                if (secondValue == "1") {
                    if (water < 250) {
                        println("Sorry, not enough water!")
                    } else if (gram < 16) {
                        println("Sorry, not enough coffee beans!")
                    } else {
                        println("I have enough resources, making you a coffee")
                        water -= 250
                        gram -= 16
                        sum += 4
                        cups--
                        status = Currancy.ACTION
                    }
                } else if (secondValue == "2") {
                    if (water < 350) {
                        println("Sorry, not enough water!")
                    } else if (gram < 20) {
                        println("Sorry, not enough coffee beans!")
                    } else if (milk < 75) {
                        println("Sorry, not enough milk")
                    } else {
                        println("I have enough resources, making you a coffee")
                        water -= 350
                        milk -= 75
                        gram -= 20
                        sum += 7
                        cups--
                        status = Currancy.ACTION
                    }
                } else if (secondValue == "3") {
                    if (water < 200) {
                        println("Sorry, not enough water!")
                    } else if (gram < 12) {
                        println("Sorry, not enough coffee beans!")
                    } else if (milk < 100) {
                        println("Sorry, not enough milk")
                    } else {
                        println("I have enough resources, making you a coffee")
                        water -= 200
                        milk -= 100
                        gram -= 12
                        sum += 6
                        cups--
                        status = Currancy.ACTION
                    }
                } else if (secondValue == "back") {
                    status = Currancy.ACTION
                }
            }
            Currancy.FILL -> {
                print("Write how many ml of water do you want to add: ")
                status = Currancy.FILL_WATER
            }
            Currancy.TAKE -> {
                print("I gave you $$sum")
                sum -= sum
                status = Currancy.ACTION
            }
            Currancy.REMAINING -> {
                println("\nThe coffee machine has:\n$water of water" +
                        "\n$milk of milk" +
                        "\n$gram of coffee beans" +
                        "\n$cups of disposable cups" +
                        "\n$$sum of money")
                status = Currancy.ACTION
            }
            Currancy.FILL_WATER -> {
                water += secondValue.toInt()
                print("Write how many ml of milk do you want to add: ")
                status = Currancy.FILL_MILK
            }
            Currancy.FILL_MILK -> {
                milk += secondValue.toInt()
                print("Write how many grams of coffee beans do you want to add: ")
                status = Currancy.FILL_COFFEE
            }
            Currancy.FILL_COFFEE -> {
                gram += secondValue.toInt()
                print("Write how many disposable cups of coffee do you want to add: ")
                status = Currancy.FILL_CUPS
            }
            Currancy.FILL_CUPS -> {
                cups += secondValue.toInt()
                status = Currancy.ACTION
            }
            Currancy.EXIT -> key = false
        }
    }

    enum class Currancy {
        ACTION,
        BUY,
        FILL,
        TAKE,
        REMAINING,
        FILL_WATER,
        FILL_MILK,
        FILL_COFFEE,
        FILL_CUPS,
        WAIT,
        EXIT
    }
}