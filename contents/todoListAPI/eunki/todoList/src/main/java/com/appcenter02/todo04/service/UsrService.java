package com.appcenter02.todo04.service;

import com.appcenter02.todo04.repository.UsrRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UsrService {

    private final UsrRepository usrRepository;


}
