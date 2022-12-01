package com.adme.admedemo.utils;

import java.util.UUID;

public class NameUtils {

    // 서버에 저장하는 파일 : 서부 내부에서 관리하는 파일은 유일한 이름을 생성하는 UUID를 사용해서 충돌을 피함(+확장자)
    public static String createStoreFileName (String originalFilename){
        String uuid = UUID.randomUUID().toString(); // UUID
//        String ext = extractExt(originalFilename); // 확장자
//        return uuid + "." + ext; // 서버에 저장하는 파일명 : UUID + 확장자
        // 예시>> 51041c62-8634-4274-801d-61a7d994edb.png
        return uuid;
    }

    // 사용자의 이미지 파일의 확장자 추출(.png/.jpg ...)
    public static String extractExt (String originalFilename){
        int pos = originalFilename.lastIndexOf(".");
        return originalFilename.substring(pos);
    }
}
