package com.ms.user.producer;

import com.ms.user.dto.EmailDto;
import com.ms.user.model.UserModel;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UserProducer {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Value(value = "${broker.queue.email.name}")
    private String routingKey;

    public void publishMessagenEmail(UserModel userModel){
        var emailDTO = new EmailDto();

        emailDTO.setUserId(userModel.getUserId());
        emailDTO.setEmailTO(userModel.getEmail());
        emailDTO.setSubject("Cadastro realizado com sucesso!!");
        emailDTO.setText(userModel.getName() + ", SEJA BEM VINDO!! \n " + "Agradeço o seu cadastro");

        rabbitTemplate.convertAndSend("", routingKey, emailDTO);
    }
}
