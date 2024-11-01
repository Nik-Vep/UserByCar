package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User user1 = new User("User1", "Lastname1" , "user1@mail.ru");
      User user2 = new User("User2", "Lastname2" , "user2@mail.ru");
      User user3 = new User("User3", "Lastname3" , "user3@mail.ru");
      User user4 = new User("User4", "Lastname4" , "user4@mail.ru");

      user1.setUserCar(new Car("BMW",1));
      user2.setUserCar(new Car("Lada",2));
      user3.setUserCar(new Car("Porsche",3));
      user4.setUserCar(new Car("Ford",4));

      userService.add(user1);
      userService.add(user2);
      userService.add(user3);
      userService.add(user4);

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = "+user.getUserCar());
         System.out.println();
      }

      System.out.println(userService.getUserByCar("BMW", 1));
      System.out.println(userService.getUserByCar("Lada", 2));
      System.out.println(userService.getUserByCar("Porsche", 3));
      System.out.println(userService.getUserByCar("Ford", 4));


      context.close();
   }
}
