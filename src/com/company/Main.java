package com.company;
import java.util.Scanner;
import java.util.ArrayList;

public class Main
{
    public static void main(String[] args)
    {
        Menu.menu();
    }

    static int checkInputInt()
    {
        Scanner in = new Scanner(System.in);
        while (!in.hasNextInt())
        {
            System.out.print("Type correct! ");
            in.next();
        }
        return in.nextInt();
    }

    static double checkInputDouble()
    {
        Scanner in = new Scanner(System.in);
        while (!in.hasNextDouble())
        {
            System.out.print("Type correct! ");
            in.next();
        }
        return in.nextDouble();
    }
}

class Menu
{
    Scanner in = new Scanner(System.in);
    static ArrayList<User> list = new ArrayList<User>();
    static ArrayList<Product> list2 = new ArrayList<Product>();

    public static void menu()
    {
        System.out.println("1. Add new user to the system. ");
        System.out.println("2. Add new product to the system. ");
        System.out.println("3. Delete user from the system. ");
        System.out.println("4. Delete product from the system. ");
        System.out.println("5. Show all users. ");
        System.out.println("6. Show all products. ");
        System.out.println("7. Buy products. ");
        System.out.println("8. Show user's purchases. ");
        System.out.println("9. Show users who bought specific product. ");
        System.out.println("10. Exit the application. ");
        System.out.print("Choose your function: ");
        int menuNum = Main.checkInputInt();
        switch (menuNum)
        {
            case 1:
                list.add(new User());
                menu();
                break;
            case 2:
                list2.add(new Product());
                menu();
                break;
            case 3:
                User.delete();
                menu();
                break;
            case 4:
                Product.delete();
                menu();
                break;
            case 5:
                for(int i = 0; i < list.size(); i++)
                    System.out.println((i + 1) + " user's full name = " + list.get(i).firstName + " " +  list.get(i).lastName + "\n" + (i + 1) +  " user's amount of money = " + list.get(i).amountOfMoney + "\n" + (i + 1) + " user's ID = " + list.get(i).id + "\n\n");
                if(list.size() == 0)
                    System.out.println("There are 0 users. \n\n");
                menu();
                break;
            case 6:
                for(int i = 0; i < list2.size(); i++)
                    System.out.println((i + 1) + " product's name = " + list2.get(i).name + "\n"  + (i + 1) +  " product's price = " + list2.get(i).price + "\n" + (i + 1) + " product's ID = " + list2.get(i).id + "\n\n");
                if(list2.size() == 0)
                    System.out.println("There are 0 products. \n\n");
                menu();
                break;
            case 7:
                Product.purchase();
                menu();
                break;
            case 8:
                User.findProducts();
                menu();
                break;
            case 9:
                Product.findUsers();
                menu();
                break;
            case 10:
                System.out.print("You have exit the application. ");
                return;
            default:
                System.out.println("Type existing number of buttons. Try again. \n\n");
                menu();
        }
    }
}

class User
{
    Scanner in = new Scanner(System.in);

    public int id;
    public String firstName, lastName;
    public double amountOfMoney;
    public String[] purchases = new String[1000];

    User()
    {
        System.out.print("Type your first name: ");
        firstName = in.nextLine();
        System.out.print("Type your last name: ");
        lastName = in.nextLine();
        System.out.print("Type your amount of money: ");
        do
        {
            amountOfMoney = Main.checkInputInt();
            if (amountOfMoney < 0)
                System.out.println("Incorrect value. Try again! ");
        }
        while (amountOfMoney < 0);
        id = ((int) (Math.random() * 1000000));
        System.out.println("Your first name = " + firstName + "\nYour last name = " + lastName + "\nYour amount of money = " + amountOfMoney + "\nYour ID = " + id + "\n\n");
    }

    static void delete()
    {
        int typedId;
        boolean isDeleted = false;

        if(Menu.list.size() == 0)
            System.out.println("There are 0 products. \n\n");
        else
        {
            System.out.print("Type user's ID: ");
            typedId = Main.checkInputInt();
            for (int i = 0; i < Menu.list.size(); i++)
            {
                if (typedId == Menu.list.get(i).id)
                {
                    System.out.println("User " + Menu.list.get(i).firstName + " " + Menu.list.get(i).lastName + " was deleted. \n\n");
                    Menu.list.remove(i);
                    isDeleted = true;
                }
            }
            if (isDeleted == false)
                System.out.println("There are no users with that ID. \n\n");
        }
    }

    static void findProducts()
    {
        int typedUserId = 0;
        boolean isUserFound = false, somethingIsBought = false;

        if (Menu.list.size() == 0)
            System.out.println("There are 0 users. \n\n");
        else if (Menu.list2.size() == 0)
            System.out.println("There are 0 products. \n\n");
        else
        {
            System.out.print("Type user's ID: ");
            typedUserId = Main.checkInputInt();
            for (int i = 0; i < Menu.list.size(); i++)
                 {
                if (typedUserId == Menu.list.get(i).id)
                    isUserFound = true;
            }
            if (isUserFound == false)
            {
                System.out.println("There are no users with that ID. \n\n");
                return;
            }
            for(int i = 0; i < Menu.list.size(); i++)
            {
                for (int j = 0; j < Menu.list2.size(); j++)
                {
                    for (int k = 0; k < Menu.list.get(i).purchases.length; k++)
                    {
                        if (Menu.list.get(i).purchases[k] == Menu.list2.get(j).name && Menu.list.get(i).id == typedUserId)
                        {
                            System.out.println("User " + Menu.list.get(i).firstName + " " + Menu.list.get(i).lastName + " bought this product - " + Menu.list2.get(j).name);
                            somethingIsBought = true;
                        }
                    }
                }
            }
            if (somethingIsBought == false)
                System.out.println("Nothing was bought by this user. \n\n");
            System.out.print("\n\n");
        }
    }
}

class Product
{
    Scanner in = new Scanner(System.in);

    public int id;
    public String name;
    public double price;

    Product()
    {
        System.out.print("Type the name of a product: ");
        name = in.nextLine();
        System.out.print("Type the price of a product: ");
        do
        {
            price = Main.checkInputDouble();
        if (price < 0)
            System.out.println("Incorrect value. Try again! ");
        }
        while(price < 0);
        id = ((int) (Math.random() * 1000000));
        System.out.println("The name of a product = " + name + "\nThe price of a product = " + price + "\nProduct's ID = " + id + "\n\n");
    }

    static void delete()
    {
        int typedId;
        boolean isDeleted = false;

        if(Menu.list2.size() == 0)
            System.out.println("There are 0 products. \n\n");
        else
        {
            System.out.print("Type product's ID: ");
            typedId = Main.checkInputInt();
            for (int i = 0; i < Menu.list2.size(); i++)
            {
                if (typedId == Menu.list2.get(i).id)
                {
                    System.out.println("Product " + Menu.list2.get(i).name + " was deleted. \n\n");
                    Menu.list2.remove(i);
                    isDeleted = true;
                }
            }
            if (isDeleted == false)
                System.out.println("There are no products with that ID. \n\n");
        }
    }

    static void purchase()
    {
        int typedUserId, typedProductId, num1 = 0, num2 = 0, x = 0;
        boolean isUserFound = false, isProductFound = false;

        if(Menu.list2.size() == 0)
            System.out.println("There are 0 products. \n\n");
        else if(Menu.list.size() == 0)
            System.out.println("There are 0 users. \n\n");
        else
        {
            System.out.print("Type user's ID: ");
            typedUserId = Main.checkInputInt();
            for (int i = 0; i < Menu.list.size(); i++)
            {
                if (typedUserId == Menu.list.get(i).id)
                {
                    isUserFound = true;
                    num1 = i;
                }
            }
            if (isUserFound == false)
            {
                System.out.println("There are no users with that ID. \n\n");
                return;
            }
            System.out.print("Type product's ID: ");
            typedProductId = Main.checkInputInt();
            for (int i = 0; i < Menu.list2.size(); i++)
            {
                if (typedProductId == Menu.list2.get(i).id)
                {
                    isProductFound = true;
                    num2 = i;
                }
            }
            if (isProductFound == false)
            {
                System.out.println("There are no products with that ID. \n\n");
                return;
            }
            if(Menu.list.get(num1).amountOfMoney < Menu.list2.get(num2).price)
            {
                System.out.println("You have not enough money to buy this product. \n\n");
                return;
            }
            else
            {
                System.out.println("You have successfully bought this product. \n\n");
                Menu.list.get(num1).amountOfMoney -= Menu.list2.get(num2).price;
                do
                {
                    if(Menu.list.get(num1).purchases[x] == null)
                    {
                        Menu.list.get(num1).purchases[x] = Menu.list2.get(num2).name;
                        break;
                    }
                    else
                    {
                        x++;
                     }
                }
                while(Menu.list.get(num1).purchases[x-1] != null);
            }
        }
    }

    static void findUsers()
    {
        int typedProductId;
        boolean isProductFound = false, somebodyBought = false;

        if (Menu.list2.size() == 0)
            System.out.println("There are 0 products. \n\n");
        else if (Menu.list.size() == 0)
            System.out.println("There are 0 users. \n\n");
        else
        {
            System.out.print("Type product's ID: ");
            typedProductId = Main.checkInputInt();
            for (int i = 0; i < Menu.list2.size(); i++)
            {
                if (typedProductId == Menu.list2.get(i).id)
                    isProductFound = true;
            }
            if (isProductFound == false)
            {
                System.out.println("There are no products with that ID. \n\n");
                return;
            }
            for(int i = 0; i < Menu.list2.size(); i++)
            {
                for (int j = 0; j < Menu.list.size(); j++)
                {
                    for (int k = 0; k < Menu.list.get(j).purchases.length; k++)
                    {
                        if (Menu.list.get(j).purchases[k] == Menu.list2.get(i).name && Menu.list2.get(i).id == typedProductId)
                        {
                            System.out.println("User " + Menu.list.get(j).firstName + " " + Menu.list.get(j).lastName + " bought this product - " + Menu.list2.get(i).name);
                            somebodyBought = true;
                        }
                    }
                }
            }

            if (somebodyBought == false)
                System.out.println("Nobody bought this product. \n\n");
            System.out.print("\n\n");
        }
    }
}

