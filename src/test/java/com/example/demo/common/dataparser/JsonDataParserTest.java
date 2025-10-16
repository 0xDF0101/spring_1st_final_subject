package com.example.demo.common.dataparser;

import com.example.demo.account.dto.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.io.ResourceLoader;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class JsonDataParserTest {


    // 1. 가짜 객체 (파일 읽기 기능을 가로챔)
//    @Mock
//    private ResourceLoader resourceLoader;
//
//    // 2. 테스트 대상 (Mock 객체를 여기에 주입)
//    @InjectMocks
//    private AccountService accountService;
//
//    // 테스트가 실행되기 전에 Mockito 설정을 초기화
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    // 네가 테스트하고 싶은 accounts() 메서드 테스트
//    @Test
//    void accounts_ShouldReturnCorrectlyParsedList() {
//        // 3. 테스트에 사용할 가짜 JSON 문자열
//        final String MOCK_JSON = "[" +
//                "{\"아이디\": 1, \"이름\": \"선도형\"}," +
//                "{\"아이디\": 2, \"이름\": \"sando\"}" +
//                "]";
//
//        // 4. Mocking 설정: resourceLoader.loadResource()가 호출되면 가짜 JSON을 반환하도록 설정
//        when(resourceLoader.loadResource("accounts.json")).thenReturn(MOCK_JSON);
//
//        // 5. 테스트 실행
//        List<Account> result = accountService.accounts();
//
//        // 6. 검증 (Assertion)
//        // 리스트가 null이 아니며, 크기가 2인지 확인
//        assertThat(result).isNotNull().hasSize(2);
//
//        // 첫 번째 계좌의 이름이 정확한지 확인 (파싱 로직이 잘 작동했는지 검증)
//        assertThat(result.get(0).getName()).isEqualTo("선도형");
//
//        // (선택) resourceLoader가 정확히 한 번 호출되었는지 확인
//        verify(resourceLoader, times(1)).loadResource("accounts.json");
//    }
}