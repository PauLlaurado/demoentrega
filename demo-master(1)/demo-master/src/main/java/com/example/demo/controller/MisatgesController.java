package com.example.demo.controller;

import com.example.demo.Translator;
import com.example.demo.domain.dto.ResponseList;
import com.example.demo.domain.dto.ResponseMessage;
import com.example.demo.domain.model.Misatges;
import com.example.demo.domain.model.projection.ProjectionMisatges;
import com.example.demo.repository.MisatgesRepository;
import com.example.demo.repository.UserRepository;
import com.sun.org.apache.xpath.internal.functions.FuncTranslate;
import com.sun.org.apache.xpath.internal.operations.Gt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/missatges")
public class MisatgesController {


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MisatgesRepository misatgesrepository;


    @GetMapping("/leer")
    public ResponseEntity<?> findallmessage(Authentication authentication) {
        UUID id = userRepository.findByUsername(authentication.getName()).userid;
        if (userRepository.findByUsername(authentication.getName()).role.equals("t")) {
            return ResponseEntity.ok().body(new ResponseList(misatgesrepository.findBy(ProjectionMisatges.class)));
        } else {
            return ResponseEntity.ok().body(new ResponseList(misatgesrepository.findByIduserenviatOrIduserremitent(id, id, ProjectionMisatges.class)));
        }
    }


    @GetMapping("/leer/{id}")
    public ResponseEntity<?> findmessage(Authentication authentication, @PathVariable UUID id) {
        if (userRepository.findByUsername(authentication.getName()).role.equals("t")) {

            return ResponseEntity.ok().body(new ResponseList(misatgesrepository.findByIduserenviatOrIduserremitent(id, id, ProjectionMisatges.class)));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ResponseMessage.message("No ets administrador com per poder veure els misatges del usuari cercat "));
    }

    @GetMapping("/send/{id}")
    public ResponseEntity<?> findmessagesend(Authentication authentication, @PathVariable UUID id) {
        if (userRepository.findByUsername(authentication.getName()).role.equals("t")) {

            return ResponseEntity.ok().body(new ResponseList(misatgesrepository.findByIduserenviat(id, ProjectionMisatges.class)));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ResponseMessage.message("El id autenticat no coincideix amb lintent de cerca a traves del id path"));
    }
    @GetMapping("/recived/{id}")
    public ResponseEntity<?> findmessagerecived(Authentication authentication, @PathVariable UUID id) {
        if (userRepository.findByUsername(authentication.getName()).role.equals("t")) {

            return ResponseEntity.ok().body(new ResponseList(misatgesrepository.findByIduserenviatOrIduserremitent(id, id, ProjectionMisatges.class)));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ResponseMessage.message("El id autenticat no coincideix amb lintent de cerca a traves del id path"));

    }


    @PostMapping("/enviar/{id}")
    public ResponseEntity<Object> enviarmensaje(@RequestBody String sms, Authentication authentication,@PathVariable UUID id) throws Exception {
        String fromLang="es";
        String toLang="en";
        Misatges misatges1=new Misatges();
        misatges1.iduserremitent=userRepository.findByUsername(authentication.getName()).userid;
        misatges1.iduserenviat=id;
        misatges1.missatges=sms;

        String traduccio=Translator.translate(fromLang,toLang,sms);
        System.out.println(traduccio);
        misatges1.traduccio=traduccio;
        misatgesrepository.save(misatges1);
        return ResponseEntity.ok().build();
    }
}
