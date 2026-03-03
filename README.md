# 🛒 Store Management System

## 📖 Overview

This project is a **Store Management System** developed over the course of two academic years as part of a university course. It began in the first year as an **Object-Oriented Programming (OOP)** assignment and was expanded in the second year with **Object-Oriented Design (OOD)** principles and design patterns.

The system manages customers, products, sellers, and purchases through a console-based Java application using clean object-oriented structure.

---

## 🚀 Features

- 🧍 Add, view, and manage **customers** and **sellers**
- - 📦 Add products to sellers and customers
  - - 💳 **Payment** processing and purchase history
    - - 🗂️ Browse products by **category**
      - - 🔁 Choose from **cart history**
        - - 🛠️ Array operations: copy, reverse, sort, count names
          - - 💾 Save and restore list state (Memento pattern)
           
            - ---

            ## 🧠 Design Patterns

            | Pattern | Classes |
            |---|---|
            | **Facade** | `StoreFacade` — single entry point for all store operations |
            | **Singleton** | `StoreFacade.getInstance()` — one shared store instance |
            | **Factory** | `CustomerFactory`, `SellerFactory`, `ProductFactory`, `AddressFactory` |
            | **Command** | `Command` interface + `PrintNamesCommand`, `CopyArrayCommand`, `ReverseArrayCommand`, `SortNamesCommand`, `NameCounterCommand`, `SaveListCommand`, `RestoreListCommand` |
            | **Observer** | `Observer` / `Subject` interfaces for event-driven updates |
            | **Adapter** | `CustomListIteratorAdapter` — adapts a custom list to standard iterator |
            | **Memento** | `Manager` (with inner `Memento` class) — saves and restores list state |

            ---

            ## 🗂️ Project Structure

            ```
            OOP-OOD---Project/
            │
            ├── Main2.java                      # Entry point — console menu
            │
            ├── Models
            │   ├── User.java                   # Base class for store users
            │   ├── Customer.java               # Customer (extends User)
            │   ├── Seller.java                 # Seller (extends User)
            │   ├── Manager.java                # Manages name lists + Memento logic
            │   ├── Product.java                # Product entity
            │   ├── Purchase.java               # Purchase/transaction record
            │   ├── Address.java                # Address entity
            │   └── Category.java               # Product category enum/class
            │
            ├── Factories
            │   ├── CustomerFactory.java
            │   ├── SellerFactory.java
            │   ├── ProductFactory.java
            │   └── AddressFactory.java
            │
            ├── Commands
            │   ├── Command.java                # Command interface
            │   ├── PrintNamesCommand.java
            │   ├── CopyArrayCommand.java
            │   ├── ReverseArrayCommand.java
            │   ├── SortNamesCommand.java
            │   ├── NameCounterCommand.java
            │   ├── SaveListCommand.java
            │   └── RestoreListCommand.java
            │
            ├── Patterns
            │   ├── StoreFacade.java            # Facade + Singleton
            │   ├── Observer.java               # Observer interface
            │   ├── Subject.java                # Subject interface
            │   ├── CustomListIteratorAdapter.java  # Adapter pattern
            │   └── EmptyCartException.java     # Custom exception
            │
            └── .gitignore
            ```

            > **Note:** All files currently live in the root directory (flat structure). The groupings above represent their logical roles.
            >
            > ---
            >
            > ## 🛠️ Technologies
            >
            > - **Language:** Java 17+
            > - - **IDE:** IntelliJ IDEA
            >   - - **Build:** No build tool required — compile and run directly with `javac`
            >    
            >     - ---
            >
            > ## ▶️ How to Run
            >
            > ### Prerequisites
            > - Java 17 or higher installed ([download here](https://www.oracle.com/java/technologies/downloads/))
            >
            > - ### Steps
            >
            > - 1. Clone the repository:
            >   2.    ```bash
            >            git clone https://github.com/Shawn-Farberov/OOP-OOD---Project.git
            >            cd OOP-OOD---Project
            >            ```
            >
            > 2. Compile all Java files:
            > 3.    ```bash
            >          javac -d out *.java
            >          ```
            >
            > 3. Run the application:
            > 4.    ```bash
            >          java -cp out ArthurAndShawn.Main2
            >          ```
            >
            > 4. Follow the on-screen menu to interact with the store.
            >
            > 5. ---
            >
            > 6. ## 👥 Authors
            >
            > 7. - **Shawn Farberov** & **Arthur** — developed as a collaborative academic project
