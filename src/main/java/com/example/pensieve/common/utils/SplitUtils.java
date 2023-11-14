package com.example.pensieve.common.utils;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

//https://github.com/intotherealworld/jamo/blob/main/JamoUtils.java 여기서 가져옴
//https://cfdf.tistory.com/45 유니---코드
//https://www.unicode.org/charts/PDF/U1100.pdf unicode 공식문서
//TODO 참고 :https://symbl.cc/kr/unicode/table/
//TODO 분리된 걸 클라이언트한테 꼭 보여줄 필요없음. db구성만 잘해서 리스트를 유니코드로 치환하면됨
@Configuration
@NoArgsConstructor
@Slf4j
public class SplitUtils {

    public static final String [] CHOSUNG = {"ㄱ", "ㄲ", "ㄴ", "ㄷ", "ㄸ", "ㄹ", "ㅁ", "ㅂ", "ㅃ",
            "ㅅ","ㅆ", "ㅇ", "ㅈ", "ㅉ", "ㅊ", "ㅋ", "ㅌ", "ㅍ", "ㅎ"};
    public static final String [] JUNGSUNG = {"ㅏ", "ㅐ", "ㅑ", "ㅒ", "ㅓ", "ㅔ", "ㅕ", "ㅖ", "ㅗ", "ㅘ",
            "ㅙ", "ㅚ", "ㅛ", "ㅜ", "ㅝ", "ㅞ", "ㅟ", "ㅠ", "ㅡ", "ㅢ", "ㅣ"};
    public static final String [] JONGSUNG = {"", "ㄱ", "ㄲ", "ᆪ", "ᆫ", "ᆬ", "ᆭ", "ㄷ",
            "ㄹ", "ᆰ", "ᆱ", "ᆲ", "ᆳ", "ᆴ", "ᆵ", "ᆶ", "ㅁ", "ㅂ", "ᆹ", "ᆺ", "ᆻ", "ᆼ",
            "ᆽ", "ㅊ", "ㅋ", "ㅌ", "ㅍ", "ㅎ"};

    public static List<List<String>> split(String target) {
        return Arrays.asList(target.split(""))
                .stream()
                .map(SplitUtils::splitOne)
                .collect(Collectors.toList());
    }

    public static List<String> splitOne(String target) {
        int codePoint = Character.codePointAt(target, 0);
        System.out.println(codePoint);
        log.info("codePoint : {}",codePoint);
        if (codePoint >= 0xAC00 && codePoint <= 0xD79D) { //한글이면 ? 분해하여 리스트로 반환
            int startValue = codePoint - 0xAC00;
            int jong = startValue % 28;
            int jung = ((startValue - jong) / 28) % 21;
            int cho = (((startValue - jong) / 28) - jung) / 21;
            log.info("초성 : {}",cho);
            log.info("중성 : {}",jung);
            log.info("종성 : {}",jong);
            return List.of(CHOSUNG[cho], JUNGSUNG[jung], JONGSUNG[jong]);
        }
        else if ((codePoint >= 0x0041 && codePoint<=0x007A)//영어
                || (codePoint>=0x0030 &&codePoint<=0x0039)//숫자
                || (codePoint>=0x0021 && codePoint<=0x002F)){ //특문
            return List.of(String.valueOf(target.charAt(0)));
        }
        return List.of(target, "", "");
    }

}

