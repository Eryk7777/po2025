### Diagram Klas (PlantUML)

```java
@startuml

skinparam classAttributeIconSize 0
skinparam linetype ortho

package vending {
    class Main {
        + {static} void main(String[] args)
        - {static} void printProductList(VendingMachine machine)
        - {static} void initDefaultInventory(VendingMachine machine)
    }
}

package vending.factory {
    class ProductFactory {
        + {static} Product createSnack(int id, String name, double price)
    }
}

package vending.model {
    interface Product <<interface>> {
        + String getName()
        + double getPrice()
        + int getId()
    }

    class Snack implements Product {
        - int id
        - String name
        - double price
        + Snack(int id, String name, double price)
        + String getName()
        + double getPrice()
        + int getId()
    }

    class CashRegister {
        - Map<Double, Integer> coins
        + CashRegister()
        + void addCoin(double coin)
        + boolean canGiveChange(double amount)
        + void processChange(double amount)
    }

    class Dispenser {
        + void releaseProduct(String productName)
    }

    class InventoryManager {
        - Map<Integer, Product> products
        - Map<Integer, Integer> quantities
        + void addProduct(Product p, int qty)
        + void restockAll()
        + boolean hasItem(int id)
        + void decrement(int id)
        + Product getProduct(int id)
        + Map<Integer, Product> getAllProducts()
        + int getQuantity(int id)
    }

    class PersistenceManager {
        - {static} String FILE_NAME
        + {static} void save(InventoryManager inventory)
        + {static} InventoryManager load()
    }

    class VendingMachine {
        - VendingState currentState
        - double balance
        - InventoryManager inventory
        - CashRegister cashRegister
        - Dispenser dispenser
        + VendingMachine()
        + void insertMoney(double amount)
        + void selectProduct(int id)
        + void refund()
        + void setState(VendingState state)
        + void setInventory(InventoryManager inventory)
        + InventoryManager getInventory()
        + double getBalance()
        + void setBalance(double b)
        + CashRegister getCashRegister()
        + Dispenser getDispenser()
    }
}

package vending.state {
    interface VendingState <<interface>> {
        + void insertMoney(double amount)
        + void selectProduct(int id)
        + void refund()
    }

    class IdleState implements VendingState {
        - VendingMachine machine
        + IdleState(VendingMachine machine)
        + void insertMoney(double amount)
        + void selectProduct(int id)
        + void refund()
    }

    class PaymentState implements VendingState {
        - VendingMachine machine
        + PaymentState(VendingMachine machine)
        + void insertMoney(double amount)
        + void selectProduct(int id)
        + void refund()
    }
}

' Relacje i Zależności
Main ..> VendingMachine : uses
Main ..> PersistenceManager : uses
Main ..> ProductFactory : uses

ProductFactory ..> Snack : creates

VendingMachine "1" *-- "1" InventoryManager : has
VendingMachine "1" *-- "1" CashRegister : has
VendingMachine "1" *-- "1" Dispenser : has
VendingMachine "1" o-- "1" VendingState : current state

IdleState --> VendingMachine : context
PaymentState --> VendingMachine : context

InventoryManager o-- Product : stores

@enduml
```
