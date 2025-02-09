package com.juaracoding.tugasakhir.service.impl;


import com.juaracoding.tugasakhir.config.OtherConfig;
import com.juaracoding.tugasakhir.core.IService;
import com.juaracoding.tugasakhir.dto.validasi.ValUserDTO;
import com.juaracoding.tugasakhir.model.User;
import com.juaracoding.tugasakhir.repository.UserRepository;
import com.juaracoding.tugasakhir.util.GlobalResponse;
import com.juaracoding.tugasakhir.util.LoggingFile;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

/*
Created By IntelliJ IDEA 2024.3 (Community Edition)
Build #IC-243.21565.193, built on November 13, 2024
@Author USER Febby Tri Andika
Java Developer
Created on 06/02/2025 14:26
@Last Modified 06/02/2025 14:26
Version 1.0
*/

@Service
public class UserServiceImpl implements IService<User> {


    /**
     * Platform Code : AUT
     * Modul Code : 02
     * FV = Failed Validation
     * FE = Failed Error
     */

    @Autowired
    private ModelMapper modelMapper ;

    @Autowired
    UserRepository userRepository;




    @Override
    public ResponseEntity<Object> save(User user, HttpServletRequest request) {
        try{
            if(user==null){
                return GlobalResponse.dataTidakValid("FVAUT02001",request);
            }
            user.setCreatedBy("Paul");
            user.setCreatedDate(new Date());
            userRepository.save(user);
        }catch (Exception e){
            LoggingFile.logException("UserService","save --> Line 42",e, OtherConfig.getEnableLogFile());
            return GlobalResponse.dataGagalDisimpan("FEAUT02001",request);
        }

        return GlobalResponse.dataBerhasilDisimpan(request);
    }

    @Override
    @Transactional
    public ResponseEntity<Object> update(Long id, User user, HttpServletRequest request) {
        try{
            if(user==null){
                return GlobalResponse.dataTidakValid("FVAUT02011",request);
            }
            Optional<User> userOptional = userRepository.findById(id);
            if(!userOptional.isPresent()){
                return GlobalResponse.dataTidakDitemukan(request);
            }
            User userDB = userOptional.get();
            userDB.setUpdatedBy("Reksa");
            userDB.setUpdatedDate(new Date());
            userDB.setFirstName(user.getFirstName());
            userDB.setRole(user.getRole());//ini relasi nya

        }catch (Exception e){
            LoggingFile.logException("UserService","update --> Line 75",e, OtherConfig.getEnableLogFile());
            return GlobalResponse.dataGagalDiubah("FEAUT02011",request);
        }
        return GlobalResponse.dataBerhasilDiubah(request);
    }

    @Override
    @Transactional
    public ResponseEntity<Object> delete(Long id, HttpServletRequest request) {
        try{
            Optional<User> userOptional = userRepository.findById(id);
            if(!userOptional.isPresent()){
                return GlobalResponse.dataTidakDitemukan(request);
            }
            userRepository.deleteById(id);
        }catch (Exception e){
            LoggingFile.logException("UserService","delete --> Line 111",e, OtherConfig.getEnableLogFile());
            return GlobalResponse.dataGagalDiubah("FEAUT02021",request);
        }
        return GlobalResponse.dataBerhasilDihapus(request);
    }


    @Override
    public ResponseEntity<Object> findAll(Pageable pageable, HttpServletRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<Object> findById(Long id, HttpServletRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<Object> findByParam(Pageable pageable, String columnName, String value, HttpServletRequest request) {
        return null;
    }

    public User convertToUser(ValUserDTO userDTO){
        return modelMapper.map(userDTO,User.class);
    }
}


