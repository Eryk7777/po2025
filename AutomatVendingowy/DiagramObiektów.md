### Diagram Obiektów (PlantUML)

Ten diagram przedstawia stan pamięci aplikacji w momencie, gdy użytkownik wrzucił **3.50 zł** (wybrano monety: 2zł, 1zł, 0.50zł), ale jeszcze nie zatwierdził zakupu.

```java
@startuml

title Snapshot: Runtime State (Balance: 3.50 PLN)

' Ustawienia graficzne
skinparam objectBackgroundColor #FEFECE
skinparam objectBorderColor #A80036
skinparam linetype ortho

package "Heap Memory" {

    ' --- GŁÓWNA INSTANCJA ---
    object "vm : VendingMachine" as vm {
        balance = 3.50
    }

    ' --- AKTUALNY STAN (PaymentState bo balance > 0) ---
    object "currentState : PaymentState" as state {
        ' State trzyma referencję do kontekstu
    }

    ' --- PODZESPOŁY ---
    object "cashRegister : CashRegister" as cr {
        ' Stan monet (zwiększony o wrzucone 3.50zł)
        coins = {
            5.0 : 10,
            2.0 : 11,
            1.0 : 11,
            0.5 : 11,
            0.2 : 10,
            0.1 : 10
        }
    }

    object "inventory : InventoryManager" as inv {
        ' Mapa ilości produktów (ID -> Ilość)
        quantities = {
            1 : 5,
            2 : 5,
            3 : 5
        }
    }

    object "dispenser : Dispenser" as disp {
        ' Obiekt bezstanowy
    }

    ' --- KONKRETNE OBIEKTY PRODUKTÓW ---
    object "p1 : Snack" as snack1 {
        id = 1
        name = "Sprunk"
        price = 3.20
    }

    object "p2 : Snack" as snack2 {
        id = 2
        name = "Cola"
        price = 3.50
    }

    object "p3 : Snack" as snack3 {
        id = 3
        name = "7-Nights"
        price = 6.00
    }
}

' --- RELACJE MIĘDZY OBIEKTAMI ---

' Agregacja w maszynie
vm -- cr : cashRegister
vm -- inv : inventory
vm -- disp : dispenser
vm -- state : currentState

' Relacja zwrotna stanu
state -- vm : machine

' Powiązanie inwentarza z produktami (HashMap<Integer, Product>)
inv -- snack1 : products[1]
inv -- snack2 : products[2]
inv -- snack3 : products[3]

' Notatka wyjaśniająca
note top of vm
  SCENARIUSZ:
  Użytkownik wrzucił 3.50 zł.
  Stan maszyny zmienił się na PaymentState.
  Liczba monet w CashRegister wzrosła.
end note

@enduml
