package com.example.workshop3;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ContactsRedis {

    @Autowired
    public RedisTemplate<String, Contact> template;

    public void addContact(Contact contact, String id) {
        template.opsForValue().set(id, contact);
    }

    public Contact getContactById(String id) {
        return template.opsForValue().get(id);
    }

    public Map<String, String> getAllContacts()  {
        Set<String> keys= template.keys("*");
        Map<String, String> contactMap = new HashMap<>();

        for (String key: keys) {
            Contact contact = template.opsForValue().get(key);
            contactMap.put(key, contact.getName());
        }
        return contactMap;
    }
    
}
