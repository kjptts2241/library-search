package com.library.springboot.library.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.library.springboot.library.dao.repository.TbLibraryListRepository;
import com.library.springboot.library.dto.LibraryListDto;

@RequiredArgsConstructor
@Service
public class LibraryListService {
    
    private final TbLibraryListRepository tbLibraryListRepository;

    @Transactional
    public List<LibraryListDto> LibraryListSearch() throws IOException { // 도서관 데이터를 dto에 저장 하고 return 해주기 위한 함수

        ObjectMapper mapper = new ObjectMapper(); // list를 dto에 mapping 해주기 위한 mapper 생성
                                                 // Mapping 파일에 기재된 SQL을 호출하기 위한 인터페이스
        String Librarylist = new Gson().toJson(tbLibraryListRepository.findAll()); // Gson().toJson으로 변환 시켜서 도서관 데이터를 list 변수에 저장

        List<LibraryListDto> LibraryListDto = Arrays.asList(mapper.readValue(Librarylist, LibraryListDto[].class)); // list를 dto에 List형식으로 dtos에 mapping

        return LibraryListDto; // LibraryListDto 타입인 List를 return 해준다.
    }
}
