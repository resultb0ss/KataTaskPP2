package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        UserServiceImpl userService = (UserServiceImpl) UserServiceImpl.getInstance();

        userService.createUsersTable();

        User ivanIvanov = new User("Ivan", "Ivanov", (byte) 22);
        User petrPetrov = new User("Petr", "Petrov", (byte) 26);
        User polinaSergeeva = new User("Polina", "Sergeeva", (byte) 25);
        User elenaSmirnova = new User("Elen", "Smirnova", (byte) 34);

        userService.saveUser(ivanIvanov.getName(), ivanIvanov.getLastName(), ivanIvanov.getAge());
        System.out.println("User с именем - " + ivanIvanov.getName() + " добавлен в базу данных");

        userService.saveUser(petrPetrov.getName(), petrPetrov.getLastName(), petrPetrov.getAge());
        System.out.println("User с именем - " + petrPetrov.getName() + " добавлен в базу данных");

        userService.saveUser(polinaSergeeva.getName(), polinaSergeeva.getLastName(), polinaSergeeva.getAge());
        System.out.println("User с именем - " + polinaSergeeva.getName() + " добавлен в базу данных");

        userService.saveUser(elenaSmirnova.getName(), elenaSmirnova.getLastName(), elenaSmirnova.getAge());
        System.out.println("User с именем - " + elenaSmirnova.getName() + " добавлен в базу данных");

        List<User> userList = userService.getAllUsers();
        for (User user : userList) {
            System.out.println(user);
        }

        userService.cleanUsersTable();
        userService.dropUsersTable();

    }
}
