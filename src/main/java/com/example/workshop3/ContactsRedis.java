package com.example.workshop3;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ContactsRedis {

    @Autowired
    public RedisTemplate<String, String> template;

    public void addContact(Contact contact, String id) {
        template.opsForValue().set(id, contact.toString());
    }

    public Contact getContactById(String id) {
        String contactString = template.opsForValue().get(id);
        String[] input = contactString.split(", ");
        return new Contact(input[0], input[1], input[2], LocalDate.parse(input[3]));
    }

    public Map<String, String> getAllContacts()  {
        Set<String> keys= template.keys("*");
        Map<String, String> contactMap = new HashMap<>();

        for (String key: keys) {
            String contactString = template.opsForValue().get(key);
            String[] input = contactString.split(", ");
            Contact contact = new Contact(input[0], input[1], input[2], LocalDate.parse(input[3]));
            contactMap.put(key, contact.getName());
        }
        return contactMap;
    }
    
}
