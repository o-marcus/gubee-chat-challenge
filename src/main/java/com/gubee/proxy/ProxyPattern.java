package com.gubee.proxy;

import com.gubee.annotation.Transactional;
import com.gubee.repository.MessageRepository;

import java.util.UUID;

//  Nessa classe não usei um subject porque o banco de dados é em memória
public class ProxyPattern implements MessageRepository {

    private final MessageRepository repository;

    public ProxyPattern(MessageRepository repository) {
        this.repository = repository;
    }

    @Override
    public void saveMessage(UUID id, String message) {
        String methodName = "saveMessage";
        try {
            var method = repository.getClass().getMethod(methodName, UUID.class, String.class);
            if (method.isAnnotationPresent(Transactional.class)) {
                System.out.println("\nIniciando execução do método: " + method.getName());
                repository.saveMessage(id, message);
                System.out.println("Finalizando execução do método " + method.getName() + "com sucesso");
            }
        }
        catch (NoSuchMethodException e) {
            System.err.println("Finalizando execução do método " + methodName + " com erro");
            e.printStackTrace();
        }
    }

}

