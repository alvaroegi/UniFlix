package com.example.uniflix.ServiceControllers;

import com.example.uniflix.Entities.User;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class UserServiceController {
    private Map<Long, User> usuarios = new ConcurrentHashMap<>();
    private AtomicLong lastId = new AtomicLong();
    public UserServiceController(){
        User alvaro = new User("alvaro", "alvaro123");
        long idA = lastId.incrementAndGet();
        alvaro.setId(idA);
        usuarios.put(idA, alvaro);

        User raul = new User("raul","raul123");
        long idR = lastId.incrementAndGet();
        raul.setId(idR);
        usuarios.put(idR, raul);

        User alex = new User("alex", "alex123");
        long idAl = lastId.incrementAndGet();
        alex.setId(idAl);
        usuarios.put(idAl, alex);
    }
    public User logService(String user, String pass) {
        long i = 1;
        if(!usuarios.isEmpty()) {
            while(i <= lastId.longValue() && (usuarios.get(i)==null || !usuarios.get(i).getName().equals(user))){
                i++;
            }
        }
        if (i > lastId.longValue() || (usuarios.get(i)!=null && !usuarios.get(i).getPass().equals(pass))) {
            return null;
        } else {
            User u = new User(user, pass);
            u.setId(usuarios.get(i).getId());
            return u;
        }
    }

    public boolean signService(String user, String pass) {
        long i = 1;
        if(!usuarios.isEmpty()) {
            while(i <= lastId.longValue() && (usuarios.get(i)==null || !usuarios.get(i).getName().equals(user))){
                i++;
            }
        }
        if(i <= usuarios.size()){
            return false;
        } else {
            User u = new User(user, pass);
            long id = lastId.incrementAndGet();
            u.setId(id);
            usuarios.put(id, u);
            return true;
        }
    }

    public User getUser(long id) {
        return usuarios.get(id);
    }

    public void addUser(User u) {
        long id = lastId.incrementAndGet();
        u.setId(id);
        usuarios.put(id, u);
    }

    public User deleteUser(long id) {
        return usuarios.remove(id);
    }

    public void updateUser(long id, User u) {
        usuarios.put(id, u);
    }

    public Collection<User> getAllUsers() {
        return usuarios.values();
    }

}