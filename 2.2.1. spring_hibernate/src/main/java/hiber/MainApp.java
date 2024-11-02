package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);
      CarService carService = context.getBean(CarService.class);

      List <User> users = new ArrayList<>();
      users.add(new User("User1", "Lastname1" , "user1@mail.ru"));
      users.add(new User("User2", "Lastname2" , "user2@mail.ru"));
      users.add(new User("User3", "Lastname3" , "user3@mail.ru"));
      users.add(new User("User4", "Lastname4" , "user4@mail.ru"));

      List <Car> cars = new ArrayList<>();
      cars.add(new Car("BMW1", 1));
      cars.add(new Car("BMW2", 2));
      cars.add(new Car("BMW3", 3));
      cars.add(new Car("BMW4", 4));

      for(User user : users) {
         userService.add(user);
      }

      for(Car car : cars) {
         carService.add(car);
      }

      List <User> usersDb = userService.listUsers();
      List <Car> carDb = carService.listCars();

      for (int i = 0; i < usersDb.size(); i++) {
         if (i < carDb.size()) {
            usersDb.get(i).setUserCar(carDb.get(i));
         }
      }

      for (User user : usersDb) {
         userService.add(user);
      }

      List<User> usersList = userService.listUsers();
      for (User user : usersList) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = "+user.getUserCar());
         System.out.println();
      }

      System.out.println(userService.getUserByCar("BMW1", 1));

      context.close();
   }
}
