package com.onetool.server;

import com.onetool.server.blueprint.Blueprint;
import com.onetool.server.blueprint.repository.BlueprintRepository;
import com.onetool.server.diabetes.Diabetes;
import com.onetool.server.diabetes.repository.DiabetesRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("test")
@Component
public class TestDataLoader implements CommandLineRunner {

    private final BlueprintRepository blueprintRepository;
    private final DiabetesRepository diabetesRepository;

    public TestDataLoader(BlueprintRepository blueprintRepository, DiabetesRepository diabetesRepository) {
        this.blueprintRepository = blueprintRepository;
        this.diabetesRepository = diabetesRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadBlueprints();
        loadDiabetes();
    }

    private void loadBlueprints() {
        if (blueprintRepository.count() == 0) {
            blueprintRepository.save(
                    Blueprint.builder()
                            .blueprintName("대한민국 마을")
                            .blueprintDetails("대한민국의 어느 마을의 청사진입니다.")
                            .creatorName("윤성원 작가")
                            .standardPrice(50000L)
                            .salePrice(40000L)
                            .program("CAD")
                            .downloadLink("https://onetool.com/download")
                            .extension(".exe")
                            .blueprintImg("https://s3.bucket.image.com/")
                            .categoryId(1L)
                            .secondCategory("주거")
                            .build()
            );
            blueprintRepository.save(
                    Blueprint.builder()
                            .blueprintName("일본 마을")
                            .blueprintDetails("일본의 어느 마을의 청사진입니다.")
                            .creatorName("성원 작가")
                            .standardPrice(20000L)
                            .salePrice(8000L)
                            .program("CAD")
                            .downloadLink("https://onetool.com/download")
                            .extension(".exe")
                            .secondCategory("공공")
                            .blueprintImg("https://s3.bucket.image.com/")
                            .categoryId(2L)
                            .build()
            );
            blueprintRepository.save(
                    Blueprint.builder()
                            .blueprintName("일본 마을")
                            .blueprintDetails("일본의 어느 마을의 청사진입니다.")
                            .creatorName("성원 작가")
                            .standardPrice(20000L)
                            .salePrice(8000L)
                            .program("CAD")
                            .downloadLink("https://onetool.com/download")
                            .extension(".exe")
                            .secondCategory("도로")
                            .blueprintImg("https://s3.bucket.image.com/")
                            .categoryId(3L)
                            .build()
            );
            blueprintRepository.save(
                    Blueprint.builder()
                            .blueprintName("일본 마을")
                            .blueprintDetails("일본의 어느 마을의 청사진입니다.")
                            .creatorName("성원 작가")
                            .standardPrice(20000L)
                            .salePrice(8000L)
                            .program("CAD")
                            .downloadLink("https://onetool.com/download")
                            .extension(".exe")
                            .secondCategory("기계부품")
                            .blueprintImg("https://s3.bucket.image.com/")
                            .categoryId(4L)
                            .build()
            );
            blueprintRepository.save(
                    Blueprint.builder()
                            .blueprintName("일본 마을")
                            .blueprintDetails("일본의 어느 마을의 청사진입니다.")
                            .creatorName("성원 작가")
                            .standardPrice(20000L)
                            .salePrice(8000L)
                            .program("CAD")
                            .downloadLink("https://onetool.com/download")
                            .extension(".exe")
                            .secondCategory("전기")
                            .blueprintImg("https://s3.bucket.image.com/")
                            .categoryId(5L)
                            .build()
            );
            blueprintRepository.save(
                    Blueprint.builder()
                            .blueprintName("일본 마을")
                            .blueprintDetails("일본의 어느 마을의 청사진입니다.")
                            .creatorName("성원 작가")
                            .standardPrice(20000L)
                            .salePrice(8000L)
                            .program("CAD")
                            .downloadLink("https://onetool.com/download")
                            .extension(".exe")
                            .blueprintImg("https://s3.bucket.image.com/")
                            .categoryId(6L)
                            .build()
            );
        }
    }

    private void loadDiabetes() {
        if (diabetesRepository.count() == 0) {
            diabetesRepository.save(
                    Diabetes.builder()
                            .diabetesName("토마토포크스튜 세트")
                            .category("category1")
                            .standardPrice(9500L)
                            .diabetesDetails("전자레인지에 데우기만 하면 풍미 깊은 따뜻한 포크 스튜를 준비했어요.")
                            .diabetesImg("https://image.greating.co.kr/IL/item/202309/27/B_A914A19892F74F9295E976B482198BE0.jpg")
                            .diabetesDetailsImg("https://postfiles.pstatic.net/MjAyNDA3MzBfMTQ0/MDAxNzIyMzM3Njc5MDMz.TBNHFl3ASzM_LDDHAAbzAlZ4hsfMStS9UktUo2sfDYAg.lGWXaCPv_87SHmPTq_dHjJkVzSvsoQUGsVl9n-YJre8g.PNG/KakaoTalk_20240730_200636500_09.png?type=w773")
                            .capacity("500")
                            .calorie("200kcal")
                            .storage("냉장")
                            .build()
            );
            diabetesRepository.save(
                    Diabetes.builder()
                            .diabetesName("취나물 소불고기 & 두부 찹스테이크 세트")
                            .category("category2")
                            .standardPrice(9500L)
                            .diabetesDetails("향긋한 취나물을 넣어 만든 소불고기를 준비했어요.")
                            .diabetesImg("https://image.greating.co.kr/IL/item/202309/27/B_C5E1A04F09EF4639B7A93B685E44804B.jpg")
                            .diabetesDetailsImg("https://postfiles.pstatic.net/MjAyNDA3MzBfMTE2/MDAxNzIyMzM3Njk2NzI0._FupLxARieORov2jwoH22dsG6kR5QJVKgjf7DT2WaiYg.fmKD8nBYrjrUklT6UD3Bcg-Z9smlfOAfWxy124fUbGIg.PNG/KakaoTalk_20240730_200636500_08.png?type=w773")
                            .capacity("252")
                            .calorie("330kcal")
                            .storage("냉장")
                            .build()
            );
            diabetesRepository.save(
                    Diabetes.builder()
                            .diabetesName("쿵파오 치킨 세트")
                            .category("category3")
                            .standardPrice(9500L)
                            .diabetesDetails("신선한 닭고기와 고추의 매콤함 그리고 땅콩의 고소함이 잘 어우러진 쿵파오 치킨을 소개해요.")
                            .diabetesImg("https://image.greating.co.kr/IL/item/202310/11/B_3780A886A0694FC18BA8638036FD07A8.jpg")
                            .diabetesDetailsImg("https://postfiles.pstatic.net/MjAyNDA3MzBfMjUw/MDAxNzIyMzM3NzI1MTQ3.D9zzN4GbRIuSnpCeZ2vsA_h_xKvCYvEysBKtG7PfuM8g.obIcU70n92gyDYPygNrKZaA6LpPnPWs1sgOgF4mThQ8g.PNG/KakaoTalk_20240730_200636500_06.png?type=w773")
                            .capacity("267")
                            .calorie("355kcal")
                            .storage("냉장")
                            .build()
            );
            diabetesRepository.save(
                    Diabetes.builder()
                            .diabetesName("숙주 소불고기 세트")
                            .category("category4")
                            .standardPrice(9500L)
                            .diabetesDetails("한국인이라면 누구나 좋아하는 달큰한 소불고기 요리를 준비했어요.")
                            .diabetesImg("https://image.greating.co.kr/IL/item/202310/11/B_3780A886A0694FC18BA8638036FD07A8.jpg")
                            .diabetesDetailsImg("https://postfiles.pstatic.net/MjAyNDA3MzBfMjUw/MDAxNzIyMzM3NzI1MTQ3.D9zzN4GbRIuSnpCeZ2vsA_h_xKvCYvEysBKtG7PfuM8g.obIcU70n92gyDYPygNrKZaA6LpPnPWs1sgOgF4mThQ8g.PNG/KakaoTalk_20240730_200636500_06.png?type=w773")
                            .capacity("267")
                            .calorie("355kcal")
                            .storage("냉장")
                            .build()
            );
            diabetesRepository.save(
                    Diabetes.builder()
                            .diabetesName("돼지고기 톳볶음 & 닭살 김치찜 세트")
                            .category("category5")
                            .standardPrice(9500L)
                            .diabetesDetails("바다의 불로초라 불리는 톳은 철, 칼슘 등 무기질이 풍부한 건강 식재료예요.")
                            .diabetesImg("https://image.greating.co.kr/IL/item/202308/09/B_C5B41B0341344345B5011A2F9A93F458.jpg")
                            .diabetesDetailsImg("https://postfiles.pstatic.net/MjAyNDA3MzBfMTkg/MDAxNzIyMzM3NzczMjM3.DmvwyFPYSws0yh6VIO0vd5naenXbN5mW6ktVR2nyU10g.B8tguwgs7CESbDnt7C2lg8MsAIDsCBqjw1EHRSM6V4cg.PNG/KakaoTalk_20240730_200636500_05.png?type=w773")
                            .capacity("262")
                            .calorie("290kcal")
                            .storage("냉장")
                            .build()
            );
            diabetesRepository.save(
                    Diabetes.builder()
                            .diabetesName("전복내장톳솥밥 세트")
                            .category("category1")
                            .standardPrice(9500L)
                            .diabetesDetails("영양 가득한 전복 내장을 넣고 고슬고슬하게 지은 솥밥을 소개해요.")
                            .diabetesImg("https://image.greating.co.kr/IL/item/202303/15/B_FB37A93F7C3F4E47925E15C1C45C9ADB.jpg")
                            .diabetesDetailsImg("https://postfiles.pstatic.net/MjAyNDA3MzBfNTcg/MDAxNzIyMzM3Nzc2NjU2.Id8PmaGPTwSE2vqzoeubX35DBp-HteSS26sHpL7LGekg.VrzblDk8bpX6drbKwiUOEfLBMnVNBy-jOIX3hz74EJIg.PNG/KakaoTalk_20240730_200636500_04.png?type=w773")
                            .capacity("549")
                            .calorie("465kcal")
                            .storage("냉동")
                            .build()
            );
        }
    }
}