package com.example.workshop3;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component
public class Contacts {
    private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void createFile(Contact contact, String fileName) throws IOException {
        File file = new File(this.path + "/" + fileName + ".txt");
        
        file.createNewFile();

        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);

        bw.write(contact.toString());

        bw.flush();
        bw.close();
        fw.close();
    }

    public Contact getFile(String fileName) throws IOException {
        File file = new File(this.path + "/" + fileName + ".txt");

        if (file.exists()) {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            Contact contact = new Contact();

            contact.setName(br.readLine().split("=")[1]);
            contact.setEmail(br.readLine().split("=")[1]);
            contact.setPhone(br.readLine().split("=")[1]);
            contact.setDateOfBirth(LocalDate.parse(br.readLine().split("=")[1]));
            
            br.close();
            fr.close();

            return contact;
        }

        return null;
    }

    public Map<String, String> getAllContacts() throws IOException {
        Path dir = Paths.get(this.path);
        Map<String, String> contactMap = new HashMap<>();
        
        List<String> ids = Files.list(dir).map(Path::getFileName)
                                                .map(Path::toString)
                                                .map(f -> f.split("\\.")[0])
                                                .collect(Collectors.toList());

        List<Contact> contacts = ids.stream().map(f -> {
            try {
                return this.getFile(f);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }).collect(Collectors.toList());

        for (int i = 0; i < ids.size(); i++) {
            contactMap.put(ids.get(i), contacts.get(i).getName());
        }

        return contactMap;
    }


}
