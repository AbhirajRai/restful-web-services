package com.udemy.rest.webservices.restful_web_services.users;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDaoService {
    //Jpa/hibernate > Database
    // UserDaoService > static list

    private static List<User> users = new ArrayList<>();
    private static int usercount = 0;

    /** A static block is a block of code that runs automatically
        only once when the class is loaded into memory.
        They want to pre-fill dummy data.
        Because class loads → static block executes → then rest(main method) happens.
     */

    static {
        users.add(new User (++usercount,"abhi", LocalDate.now()));
        users.add(new User (++usercount,"kush", LocalDate.now().minusYears(20)));
        users.add(new User (++usercount,"red", LocalDate.now().minusYears(25)));
        users.add(new User (++usercount,"car", LocalDate.now().minusYears(10)));
    }

    public List<User> findAll()
    {
        return users;
    }

    public User findOne(int id)
    {
        for(User user: users)
        {
            if(user.getId() == id)
            {
                return user;
            }
        }
        return null;
    }

    public void deleteById(int id)
    {
        for(User user: users)
        {
            if(user.getId() == id)
            {
                users.remove(id);
            }
        }
    }

    public User save(User user)
    {
        user.setId(++usercount);
        users.add(user);
        return user;
    }
}
