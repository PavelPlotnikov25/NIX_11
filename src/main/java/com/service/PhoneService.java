package com.service;

import com.annotations.Autowired;
import com.annotations.Singleton;
import com.model.phone.Manufacturer;
import com.model.phone.OperationSystem;
import com.model.phone.Phone;
import com.repository.DB.DBPhoneRepository;
import com.repository.CrudRepository;
import com.repository.PhoneRepository;


import java.io.*;

import java.time.Instant;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Singleton
public class PhoneService extends ProductService<Phone> {

    @Autowired
    public PhoneService(CrudRepository<Phone> repository) {
        super(repository);
    }

    private static PhoneService instance;


    public static PhoneService getInstance() {
        if (instance == null) {
            instance = new PhoneService(PhoneRepository.getInstance());
        }
        return instance;
    }


    @Override
    protected Phone createProduct() {
        return new Phone(
                "Title- " + RANDOM.nextInt(111),
                RANDOM.nextInt(500),
                RANDOM.nextDouble(1000),
                "MODEL - " + RANDOM.nextInt(),
                getRandomManufacturer());
    }

    private Manufacturer getRandomManufacturer() {
        final Manufacturer[] values = Manufacturer.values();
        final int index = RANDOM.nextInt(values.length);
        return values[index];
    }

    public boolean checkDetails(String checkedDetail) {
        return repository.getAll()
                .stream()
                .flatMap(phone -> phone.getDetails().stream())
                .anyMatch(detail -> detail.equals(checkedDetail));
    }

    public void createProductFromXml(String fileName) throws IOException {
        String dataString = Utils.getStringFromFile(fileName);
        final String regexForXml = "(?s)<title>(.+?)</title>.+?<model>(.+?)</model>.+?<price currency=\"(.+?)\">(.+?)</price>.+?<manufacturer>(.+?)</manufacturer>.+?<created>(.+?)</created>.+?<count>(.+?)</count>.+?ation>(.+?)</designation>.+?<version>(.+?)</version>";
        Pattern pattern = Pattern.compile(regexForXml);
        Matcher matcher = pattern.matcher(dataString);
        while (matcher.find()) {
            OperationSystem operationSystem = new OperationSystem(matcher.group(8), Integer.parseInt(matcher.group(9)));
            Phone phone = new Phone(
                    matcher.group(1),
                    Integer.parseInt(matcher.group(7)),
                    Double.parseDouble(matcher.group(4)),
                    matcher.group(2),
                    Manufacturer.valueOf(matcher.group(5)),
                    Date.from(Instant.parse(matcher.group(6))),
                    matcher.group(3),
                    operationSystem);
            repository.save(phone);
        }
    }

    public void createProductFromJson(String fileName) throws IOException {
        String dataString = Utils.getStringFromFile(fileName);
        final String regexForJson = "(?s).+?\"title\": \"(.+?)\".+?\"model\": \"(.+?)\".+?\"price\": \"(.+?)\".+?\"currency\": \"(.+?)\".+?\"manufacturer\": \"(.+?)\".+?\"created\": \"(.+?)\".+\"count\": \"(.+?)\".+\"designation\": \"(.+?)\".+?\"version\": \"(.+?)\"";
        Pattern pattern = Pattern.compile(regexForJson);
        Matcher matcher = pattern.matcher(dataString);
        while (matcher.find()) {
            OperationSystem operationSystem = new OperationSystem(matcher.group(8), Integer.parseInt(matcher.group(9)));
            Phone phone = new Phone(
                    matcher.group(1),
                    Integer.parseInt(matcher.group(7)),
                    Double.parseDouble(matcher.group(3)),
                    matcher.group(2),
                    Manufacturer.valueOf(matcher.group(5)),
                    Date.from(Instant.parse(matcher.group(6))),
                    matcher.group(4),
                    operationSystem);
            repository.save(phone);
        }
    }
}
