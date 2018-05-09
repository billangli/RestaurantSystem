# RestaurantSystem
CSC207 Project

## Overview
A Java program that keeps track of customer orders, inventory and human resources of a restaurant

## Roles
* Bill: Networking, event handling
* Donghee: 
* Jerry: 
* Justin:

### Special Features
* Networking
* Logging
* Receipt Customization

### Setup

### Networking
* This project uses the client-server model where the server keeps a master copy of the data and each client is an instance of an employee
* The client keeps a local copy of inventory data to make sure commands are valid and requests a change in the server every time a change is made
* The server then broadcasts the changes to all clients
* We created a Packet object that contains an item and a protocol that represents what the message contains
* The clients and server communicate via serialized Packets

### Design Patterns
* Model View Controller (for the user interface)
* Observer (for networking)
* Singleton
* Factory
