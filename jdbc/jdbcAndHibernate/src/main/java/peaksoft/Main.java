package peaksoft;
import peaksoft.dao.UserDaoHibernateImpl;
import peaksoft.dao.UserDaoJdbcImpl;
import peaksoft.service.UserService;
import peaksoft.service.UserServiceImpl;


public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserService userService = new UserServiceImpl();
        //userService.createUsersTable();
        // userService.dropUsersTable();
        //userService.saveUser("Meerim","Ismanalieva",(byte) 17);
        //userService.getAllUsers().forEach(System.out::println);
        // userService.cleanUsersTable();
        UserDaoHibernateImpl userDao = new UserDaoHibernateImpl();
        // userDao.createUsersTable();
        //userDao.saveUser("MEERIM","ISMANALIEVA",(byte) 18);
        //userDao.removeUserById( 1);
        //System.out.println(userDao.getAllUsers());
        // userDao.cleanUsersTable();
        UserDaoJdbcImpl userDaoJdbc = new UserDaoJdbcImpl();
        //userDaoJdbc.createUsersTable();
//        userDaoJdbc.saveUser("MEERIM","ISMANALIEVA",(byte) 18);
//        userDaoJdbc.saveUser("MEERIM","ISMANALIEVA",(byte) 18);
//userDaoJdbc.removeUserById(2);
        //System.out.println(userDaoJdbc.getAllUsers());
        //userDaoJdbc.cleanUsersTable();
    }
}
