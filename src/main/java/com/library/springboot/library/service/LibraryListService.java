package com.library.springboot.library.service;

import org.springframework.stereotype.Service;

import com.library.springboot.library.dto.repository.TbLibraryListRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class LibraryListService {
    
    private final TbLibraryListRepository tbLibraryListRepository;

    public int asd()
    {
        return tbLibraryListRepository.libCode;
    }
}
