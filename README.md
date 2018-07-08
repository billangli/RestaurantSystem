# RestaurantSystem
CSC207 Project

## Overview
Restaurant System is a Java-based program that tracks customer orders, inventory, and employees for restaurants. 

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
* Restaurant System uses a client-server model. The server stores a master copy of the data and each employee is a client.
* The client keeps a local copy of the inventory data to ensure queries are valid. All changes in data are submitted to the server.
* The server broadcasts the edited data to all of the clients.
* A Packet object stores a data item and a protocol, and is used to represent a message unit. 
* The clients and server communicate via serialized Packets.

### Design Patterns
* Model View Controller (for the user interface)
* Observer (for networking)
* Singleton
* Factory
