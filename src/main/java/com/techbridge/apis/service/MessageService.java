package com.techbridge.apis.service;

import com.techbridge.apis.excepions.CustomErrorException;
import com.techbridge.apis.excepions.CustomExtraErrorException;
import com.techbridge.apis.model.Message;
import com.techbridge.apis.model.dto.MessageDto;
import com.techbridge.apis.repository.MessageRepository;
import com.techbridge.apis.util.PasswordEncoder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class MessageService {
    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public ResponseEntity<Object> getMessages() {
        List<MessageDto> MessageDtos =  messageRepository.findAll().stream()
                .filter(Objects::nonNull)
                .map(dto -> new MessageDto(dto.getMsgId(),dto.getCmpId(),dto.getUsrId(),dto.getPhone(),dto.getMsg()))
                .toList();

        throw new CustomExtraErrorException(HttpStatus.OK, "Records successfully retrieved",MessageDtos);
    }

    public ResponseEntity<Object> getMessage(Long id) {
        Optional<Message> Message = messageRepository.findById(id);
        if (Message.isEmpty()) {
            throw new CustomErrorException(HttpStatus.NOT_FOUND, "No such message with id: " + id);
        }
        MessageDto MessageDto = Message.map(dto -> new MessageDto(dto.getMsgId(),dto.getCmpId(),dto.getUsrId(),dto.getPhone(),dto.getMsg())).orElseThrow(null);
        throw new CustomExtraErrorException(HttpStatus.OK, "Records successfully retrieved", MessageDto);
    }

    public ResponseEntity<Object> createNewMessage(Message message) {
        String hashMsg  = PasswordEncoder.get_SHA_512_SecurePassword(message.getPhone()+message.getMsg());
        Optional<Message> optionalMessage = messageRepository.findByHash(hashMsg);
        if (optionalMessage.isPresent()) {
            throw new CustomErrorException(HttpStatus.FOUND, "Message already exists");
        }
        if (null != message.getMsg() && !message.getMsg().isEmpty()) {
            message.setHashmessage(hashMsg);
            messageRepository.save(message);
            throw new CustomErrorException(HttpStatus.CREATED, "Message created successfully");
        } else {
            throw new CustomErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Something has gone wrong");
        }
    }

    public ResponseEntity<Object> deleteMessage(Long id) {
        boolean exists = messageRepository.existsById(id);
        if (!exists) {
            throw new CustomErrorException(HttpStatus.NOT_FOUND, "No such user with id: " + id);
        }
        messageRepository.deleteById(id);
        throw new CustomErrorException(HttpStatus.OK, "Message deleted Successfully");
    }

    @Transactional
    public ResponseEntity<Object> updateMessage(Long MessageId, MessageDto dto) {
        Message message = messageRepository.findById(MessageId).orElseThrow(() ->
                new IllegalArgumentException("No such Message with id: " + MessageId));
        if (!Objects.isNull(dto)) {
            message.setMsg(dto.getMsg());
            messageRepository.save(message);
            throw new CustomErrorException(HttpStatus.CREATED, "Message updated successfully");
        }

        throw new CustomErrorException(HttpStatus.CREATED, "No update done");
    }
}


