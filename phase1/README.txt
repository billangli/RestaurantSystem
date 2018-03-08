Group 0214
-----------------
Donghee Kim
Gihyun Kim
Yujie Miao
Ang Li (Bill-linux, Bill)


menu.txt
===========================================
-this file is used to initialize the menu
- please enter dish in this format:
dishName;cost;firstIngredient:defaultAmount:minAmount:maxAmount,second Ingredient...
- no space in between, except dishName and ingredientName.
- dishName, cost, and ingredient are separated by semicolon
- ingredient, default, allowed subtraction, and allowed addition are separated by colon
- ingredients are separated by comma
- this file will be recreated as an empty file by the program when deleted


starter.txt
===========================================
- this file is used to initialize the program with the number of employees, table, and number of ingredients
- please only enter one integer in the first 4 lines:
    1st number represents the amount of Tables
    2nd number represents the number of Servers
    3rd number represents the number of Cooks
    4th number represents the number of Managers
- each line after fourth line are inventory stock, represented in this format:
    ingredientName,currentAmount,lowerThreshold
- no space after the comma
- all amount are integers
- when the ingredients are below the lowerThreshold, the program will write to request.txt requesting for more inventory
- this file will be recreated as an empty file by the program when deleted


event.txt
===========================================
- Dish is in the following format:
    no adjustments: dishName
    with adjustments: dishName:ingredient±a_ingredient2±b_..._ingredientN±n
        this code adds a units of ingredient1, subtracts b units of ingredient2, and subtracts n units of ingredientN.
    the ingredients are separated by comma
- Order is the following format:
    (dish1)|(dish2)|...|(dishN)

- please enter commands in this format:
    employeeType;employeeID;methodName;(parametersSeparatedByCommaWithoutSpace)

- we have the following methods with their respective parameters:

Cook
-----------------
Cook;employeeID;orderReceived;()
    confirms that an order has been received

Cook;employeeID;orderReady;(dishNumber)
    confirms that a dish with dishNumber has been cooked (each dish cooked has an unique dishNumber starting from 1 like McDonalds)

Cook;employeeID;receiveIngredient;(ingredientName,quantity)
    receive the ingredientName with the specified quantity and updates the inventory, we cannot do anything if it is not int our inventory

Manager
-----------------
Manager;employeeID;checkInventory;()
    print out inventory to console

Manager;employeeID;receiveIngredient;(ingredientName,quantity)
    receive the ingredientName with the specified quantity and updates the inventory, we cannot do anything if it is not int our inventory

Server
-----------------
Server;employeeID;takeSeat;(tableNumber)
    customers take seat at Table i, with this server

Server;employeeID;enterMenu;(tableNumber,order)
    enters the order for cook to confirm for table at tableNumber

Server;employeeID;deliverDishCompleted;(dishNumber)
    this server successfully delivered the dish with the dishNumber

Server;employeeID;deliverDishFailed;(dishNumber)
    the current dish this server delivered to the customer has failed, the customer no longer wants it

Server;employeeID;printBill;(tableNumber)
    this server print a bill to console for table with tableNumber

Server;employeeID;clearTable;(tableNumber)
    this server clear the table with tableNumber, which means current Table clears all its data (not more Order, Server and cost = 0)

- event.txt some invalid input if the employeeID, methodNames are not entered correctly, also if the dishNumber, tableNumber are invalid, it will be addressed, but this will not happen in phase 2 because we will only give the user valid input in the GUI to enter into the system


request.txt
===========================================
- it contains all items that the Manager going to order from
- it will be in the following format with a space in between name and amount:
    ingredientName1  amount1
    ingredientName2  amount2
          ...          ...
    ingredientNameN  amountN

- after the request is sent, the manager should manually clear the request.txt
- we are assuming no ingredient is below threshold when the program starts, so if an ingredient is below threshold when the program starts, request.txt won’t generate a new line

Corner Cases
===========================================
1. server delivers dish before the dish is made - This exception will not occur in phase 2 because we will make the Server select the delivered dish from the GUI, which only gives valid options (i.e. cooked dishes) so that the program will not have NullPointerException
2. invalid employeeID - Warns the user of this error in input and does not call the method to run. This should not exist in phase 2 as input will not be in the form of event.txt, but specified input according to the GUI
3. note: Our plan for phase 2 is to have the Manager enter the menu and ingredients with GUI, so all the exceptions caused by parsing the the input from event.txt should not happen.
4. we will implement save data (e.g. inventory) to offline text files in phase 2, since by then the user will be able to close the program properly

UML Format
===========================================
hollow arrow - inheritance
solid arrow - association
diamond arrow - composition
underline - static
italics - abstract method
hollow shape - variable/constant
filled shape - method
red square - private
blue triangle - package-private
green circle - public