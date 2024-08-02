package com.onetool.server;

import com.onetool.server.blueprint.repository.BlueprintRepository;
import com.onetool.server.category.FirstCategory;
import com.onetool.server.category.FirstCategoryRepository;
import com.onetool.server.diabetes.Diabetes;
import com.onetool.server.diabetes.repository.DiabetesRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Profile("default")
@Component
public class DataLoader implements CommandLineRunner {

    private final FirstCategoryRepository firstCategoryRepository;
    private final DiabetesRepository diabetesRepository;

    public DataLoader(FirstCategoryRepository firstCategoryRepository, DiabetesRepository diabetesRepository) {
        this.firstCategoryRepository = firstCategoryRepository;
        this.diabetesRepository = diabetesRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        createCategoryIfNotExists(1L, "building");
        createCategoryIfNotExists(2L, "civil");
        createCategoryIfNotExists(3L, "interior");
        createCategoryIfNotExists(4L, "machine");
        createCategoryIfNotExists(5L, "electric");
        createCategoryIfNotExists(6L, "etc");
        createCategoryIfNotExists(7L, "category1");
        createCategoryIfNotExists(8L, "category2");
        createCategoryIfNotExists(9L, "category3");
        createCategoryIfNotExists(10L, "category4");
        createCategoryIfNotExists(11L, "category5");
        loadDiabetes();
    }

    private void createCategoryIfNotExists(Long id, String name) {
        Optional<FirstCategory> existingCategory = firstCategoryRepository.findById(id);
        if (!existingCategory.isPresent()) {
            FirstCategory category = FirstCategory.builder()
                    .id(id)
                    .name(name)
                    .build();
            firstCategoryRepository.save(category);
        }
    }

    private void saveDiabetesIfNotExists(String name, String category, Long standardPrice, String details, String imgUrl, String detailsImgUrl, String capacity, String calorie, String storage) {
        if (diabetesRepository.findByDiabetesName(name).isEmpty()) {
            Diabetes diabetes = Diabetes.builder()
                    .diabetesName(name)
                    .category(category)
                    .standardPrice(standardPrice)
                    .diabetesDetails(details)
                    .diabetesImg(imgUrl)
                    .diabetesDetailsImg(detailsImgUrl)
                    .capacity(capacity)
                    .calorie(calorie)
                    .storage(storage)
                    .build();
            diabetesRepository.save(diabetes);
        }
    }

    private void loadDiabetes() {
        saveDiabetesIfNotExists(
                "토마토포크스튜 세트",
                "category1",
                9500L,
                "전자레인지에 데우기만 하면 풍미 깊은 따뜻한 포크 스튜를 준비했어요. 토마토홀과 병아리콩, 양송이버섯 등 채소를 듬뿍 넣어 만든 스튜 소스에, 레드 와인에 재운 돼지고기와 함께 뭉근하게 끓여 내었어요.\\n\\n 여기에 전분을 묻힌 국산 닭 안심을 오븐에 담백하게 구운 로스트 치킨까지 함께 준비했어요. 아몬드 슬라이스로 풍성한 식감을 더한 새콤달콤 퍼플 코울슬로로 마지막 한입까지 깔끔하게 즐겨 보세요.\\n\\n PS. 신선한 상품을 만들기 위해 주문 후 제조합니다. 소비기한은 제조일로부터 4일이니, 안전하게 기한 안에 꼭 드세요",
                "https://image.greating.co.kr/IL/item/202309/27/B_A914A19892F74F9295E976B482198BE0.jpg",
                "https://postfiles.pstatic.net/MjAyNDA3MzBfMTQ0/MDAxNzIyMzM3Njc5MDMz.TBNHFl3ASzM_LDDHAAbzAlZ4hsfMStS9UktUo2sfDYAg.lGWXaCPv_87SHmPTq_dHjJkVzSvsoQUGsVl9n-YJre8g.PNG/KakaoTalk_20240730_200636500_09.png?type=w773",
                "500",
                "200",
                "냉장"
        );
        saveDiabetesIfNotExists(
                "취나물 소불고기 & 두부 찹스테이크 세트",
                "category2",
                9500L,
                "향긋한 취나물을 넣어 만든 소불고기를 준비했어요. 국산콩 간장과 굴소스로 만든 양념에 호주산 청정우를 재운 후 취나물과 깻잎을 넣고 볶아 담백한 맛을 느끼실 수 있을 거예요.\\n\\n곁들임 찬으로는 두부로 만든 찹스테이크가 제공돼요. 비프브라운 소스와 토마토소스에 두부와 파프리카, 양송이버섯을 볶아내 입맛 돋우기에 제격이에요.\\n\\n 여기에 신선한 샐러드에 비트 오일 드레싱을 곁들여 든든하고 건강한 한식 한 상을 드셔보세요.\\n\\n PS. 신선한 상품을 만들기 위해 주문 후 제조합니다. 소비기한은 제조일로부터 4일이니, 안전하게 기한 안에 꼭 드세요.",
                "https://image.greating.co.kr/IL/item/202309/27/B_C5E1A04F09EF4639B7A93B685E44804B.jpg",
                "https://postfiles.pstatic.net/MjAyNDA3MzBfMTE2/MDAxNzIyMzM3Njk2NzI0._FupLxARieORov2jwoH22dsG6kR5QJVKgjf7DT2WaiYg.fmKD8nBYrjrUklT6UD3Bcg-Z9smlfOAfWxy124fUbGIg.PNG/KakaoTalk_20240730_200636500_08.png?type=w773",
                "252",
                "330",
                "냉장"
        );
        saveDiabetesIfNotExists(
                "쿵파오 치킨 세트",
                "category3",
                9500L,
                "신선한 닭고기와 고추의 매콤함 그리고 땅콩의 고소함이 잘 어우러진 쿵파오 치킨을 소개해요. 마늘쫑과 마늘을 넣어 고슬고슬 볶아 낸 중화풍 마늘 볶음밥과 곁들여 드시면 더욱 맛있어요.\\n\\n여기에 알룰로스와 올리고당으로 맛을 낸 새콤달콤한 비트 피클과 신선한 하루 비타민 샐러드를 곁들여 더욱 풍성하게 즐겨보세요.\\n\\n PS. 신선한 상품을 만들기 위해 주문 후 제조합니다. 소비기한은 제조일로부터 4일이니, 안전하게 기한 안에 꼭 드세요",
                "https://image.greating.co.kr/IL/item/202310/11/B_3780A886A0694FC18BA8638036FD07A8.jpg",
                "https://postfiles.pstatic.net/MjAyNDA3MzBfMjUw/MDAxNzIyMzM3NzI1MTQ3.D9zzN4GbRIuSnpCeZ2vsA_h_xKvCYvEysBKtG7PfuM8g.obIcU70n92gyDYPygNrKZaA6LpPnPWs1sgOgF4mThQ8g.PNG/KakaoTalk_20240730_200636500_06.png?type=w773",
                "267",
                "355",
                "냉장"
        );

        saveDiabetesIfNotExists(
                "숙주 소불고기 세트",
                "category4",
                9500L,
                "한국인이라면 누구나 좋아하는 달큰한 소불고기 요리를 준비했어요. 무와 양파즙에 재운 소고기는 국산 콩간장 양념에 볶은 후 숙주를 넉넉히 곁들였답니다.\\n\\n 대파와 함께 정성스레 조려낸 두부조림은 또 다른 별미이죠. 참기름으로 고소함을 더한 무나물과 영양 가득한 아몬드 멸치볶음까지 더해 건강한 한 끼를 완성해 보세요.\\n\\n PS. 신선한 상품을 만들기 위해 주문 후 제조합니다. 소비기한은 제조일로부터 4일이니, 안전하게 기한 안에 꼭 드세요.",
                "https://image.greating.co.kr/IL/item/202310/11/B_3780A886A0694FC18BA8638036FD07A8.jpg",
                "https://postfiles.pstatic.net/MjAyNDA3MzBfMjUw/MDAxNzIyMzM3NzI1MTQ3.D9zzN4GbRIuSnpCeZ2vsA_h_xKvCYvEysBKtG7PfuM8g.obIcU70n92gyDYPygNrKZaA6LpPnPWs1sgOgF4mThQ8g.PNG/KakaoTalk_20240730_200636500_06.png?type=w773",
                "267",
                "355",
                "냉장"
        );
        saveDiabetesIfNotExists(
                "돼지고기 톳볶음 & 닭살 김치찜 세트",
                "category5",
                9500L,
                "바다의 불로초라 불리는 톳은 철, 칼슘 등 무기질이 풍부한 건강 식재료예요. 그리팅은 국산 돼지고기에 톡톡 튀는 식감과 영양이 가득한 톳을 표고버섯, 피망 등 채소를 넣고 그리팅 특제 굴 소스에 담백하게 볶아낸 돼지고기 톳 볶음을 선보여요.\\n\\n 갈릭크림소스로 맛을 낸 가지볶음은 부드럽고, 크리미한 식감이 입안 가득 풍성함을 느끼게 해 줄 거에요. 매콤 새콤한 양념에 버무린 오이무침은 식사를 개운하게 마무리해 주죠. 뜨끈한 밥과 곁들여 든든한 한 끼 식사를 완성해 보세요.\\n\\n PS. 신선한 상품을 만들기 위해 주문 후 제조합니다. 소비기한은 제조일로부터 4일이니, 안전하게 기한 안에 꼭 드세요.",
                "https://image.greating.co.kr/IL/item/202308/09/B_C5B41B0341344345B5011A2F9A93F458.jpg",
                "https://postfiles.pstatic.net/MjAyNDA3MzBfMTkg/MDAxNzIyMzM3NzczMjM3.DmvwyFPYSws0yh6VIO0vd5naenXbN5mW6ktVR2nyU10g.B8tguwgs7CESbDnt7C2lg8MsAIDsCBqjw1EHRSM6V4cg.PNG/KakaoTalk_20240730_200636500_05.png?type=w773",
                "262",
                "290",
                "냉장"
        );

        saveDiabetesIfNotExists(
                "전복내장톳솥밥 세트",
                "category1",
                9500L,
                "영양 가득한 전복 내장을 넣고 고슬고슬하게 지은 솥밥을 소개해요. 팽이버섯과 톳을 함께 졸여낸 팽이 톳 조림은 솥밥의 감칠맛과 식감을 살려줘요. 솥밥 사이사이 씹히는 다진 소라살이 바다 내음을 물씬 풍기고 잘게 다져 넣은 당근은 산뜻함을 선사해요.\\n\\n 황기를 넣고 진하게 끓여 국물 맛이 일품인 닭곰탕도 곁들였어요. 건강하고 든든한 한 끼 식사를 만나 보세요.",
                "https://image.greating.co.kr/IL/item/202303/15/B_FB37A93F7C3F4E47925E15C1C45C9ADB.jpg",
                "https://postfiles.pstatic.net/MjAyNDA3MzBfNTcg/MDAxNzIyMzM3Nzc2NjU2.Id8PmaGPTwSE2vqzoeubX35DBp-HteSS26sHpL7LGekg.VrzblDk8bpX6drbKwiUOEfLBMnVNBy-jOIX3hz74EJIg.PNG/KakaoTalk_20240730_200636500_04.png?type=w773",
                "549",
                "465",
                "냉동"
        );

        saveDiabetesIfNotExists(
                "돼지감자소스 가자미 구이 세트",
                "category2",
                9500L,
                "크리미한 돼지감자소스를 곁들인 담백한 가자미구이를 준비했어요. 돼지감자소스는 잘게 다진 돼지감자를 볶은 후 고메버터, 병아리콩과 뭉근하게 끓여 완성했어요.\n\n 통밀가루를 입힌 가자미는 오븐에 구워 더욱 쫄깃한 식감을 자랑하죠. 두툼한 국산 돼지고기가 씹히는 새콤달콤한 폭찹, 직접 담근 어니언 피클까지 더해 건강한 한상을 즐겨보세요.\n\n PS. 신선한 상품을 만들기 위해 주문 후 제조합니다. 소비기한은 제조일로부터 4일이니, 안전하게 기한 안에 꼭 드세요.",
                "https://image.greating.co.kr/IL/item/202304/10/B_BA5C4E6F60AC4B8B84A93F8C62F5D9B7.jpg",
                "https://postfiles.pstatic.net/MjAyNDA3MzBfNTUg/MDAxNzIyMzM3ODAwMDA5.HGOoe9pO21OFI6nA9QCHCXYMLvA8Ae0Yb9HTp1hW_qQg.zDsY74r_gFs2ViMr3-x7Ktr_V6a1OrkpbTq_CYEGDksg.PNG/KakaoTalk_20240730_200636500_03.png?type=w773",
                "360",
                "325",
                "냉장"
        );

        saveDiabetesIfNotExists(
                "함박스테이크 세트",
                "category3",
                9500L,
                "둥글게 뭉쳐 놓은 고기에 윤기 흐르는 갈색빛 소스, 보기만 해도 군침 도는 함박스테이크는 친근하면서도 적당한 격식을 갖춘 경양식의 대표 주자죠. 그리팅은 좋은 재료만 엄선해 만든 함박스테이크로 특별한 한 끼를 준비했어요.\n\n호주산 소고기와 국산 돼지고기를 섞어 찰지게 치댄 후, 스팀으로 익혀 부드러운 식감과 촉촉한 육즙을 살렸어요. 은은한 바질 허브향과 토마토 페이스트와 겨자를 넣어 부담 없이 즐길 수 있답니다. 여기에 담백하게 오븐에 구운 알감자와 연근 피클을 가니쉬로 곁들이면 근사한 정식이 완성돼요.\n\nPS. 신선한 상품을 만들기 위해 주문 후 제조합니다. 소비기한은 제조일로부터 4일이니, 안전하게 기한 안에 꼭 드세요.",
                "https://image.greating.co.kr/IL/item/202407/17/B_B429091BA9064BABBE59DCCA51621D4F.jpg",
                "https://postfiles.pstatic.net/MjAyNDA3MzBfMjk2/MDAxNzIyMzM3Nzc0ODg5.qDNxCAcRuppiguhccUcndymTIXnhKfTaOAj9uSiwDN0g.v1HM1hR1EY7MaGW6lYqtntxb3p3NPpDrb2FmonMfgjEg.PNG/KakaoTalk_20240730_200636500_02.png?type=w773",
                "287",
                "330",
                "냉장"
        );

        saveDiabetesIfNotExists(
                "도토리묵비빔 세트",
                "category4",
                9500L,
                "여름이면 생각나는 도토리묵은 비빔밥으로 먹어도 맛있어요. 고명으로 넣은 소고기 볶음은 양파, 파프리카와 함께 간장 소스를 넣고 감칠맛 나게 달달 볶아냈어요. 송송 썬 청양고추로 매콤함을 더하고 참기름으로 고소하게 마무리한 양념 김치도 함께 넣어 드세요.\n\n보리고추장 소스에 양파, 대파, 마늘을 넣고 볶아낸 약고추장 소스는 소스의 깊이감이 남달라요. 밥과 고명을 한데 모은 뒤 고추장 적당량을 넣고 슥슥 비벼 맛있게 드세요.\n\nPS. 신선한 상품을 만들기 위해 주문 후 제조합니다. 소비기한은 제조일로부터 4일이니, 안전하게 기한 안에 꼭 드세요.",
                "https://image.greating.co.kr/IL/item/202405/22/B_C1820374C0F74F6284CE1FC21427DB50.jpg",
                "https://postfiles.pstatic.net/MjAyNDA3MzBfMjYz/MDAxNzIyMzM3Nzc2Mjgx.aTQqXDmJny3jVy5AB-r-OScyJrzHpbDvkMuEmGbswSQg.fJlKw_EOXz3EW-339vIKX36safl3JVFZ9zKWJPhtvmcg.PNG/KakaoTalk_20240730_200636500_01.png?type=w773",
                "273",
                "315",
                "냉장"
        );
        saveDiabetesIfNotExists(
                "진저포크구이&겨자부추무침 세트",
                "category5",
                9500L,
                "국산 돼지고기의 부드러운 안심을 활용해 육즙 가득한 구이 요리를 준비했어요. 안심살은 다진 생강과 양파에 재워 잡내를 없애고 구기자 간장소스와 토마토소스, 사과 농축액을 섞어 만든 바비큐 소스를 골고루 발라 구워냈어요. 담백하게 볶은 양배추와 마늘을 함께 드시면 더욱 맛있답니다.\n\n겨자를 넣어 톡 쏘는 상큼함을 더한 부추무침까지 곁들여 풍성한 식사를 완성해 보세요.\n\nPS. 신선한 상품을 만들기 위해 주문 후 제조합니다. 소비기한은 제조일로부터 4일이니, 안전하게 기한 안에 꼭 드세요.",
                "https://image.greating.co.kr/IL/item/202308/11/B_59118ABFD3CA493594E034B844486698.jpg",
                "https://postfiles.pstatic.net/MjAyNDA3MzBfMTcz/MDAxNzIyMzM3NzcyMzI4.guGP86ZMwcCYOQuIAjWy7XuodjShAqareokx7vCE1w8g.LHue5j2loKnK6_jzU0JI1Ln9tpDLqiexpbq-OJv6aosg.PNG/KakaoTalk_20240730_200636500.png?type=w773",
                "282",
                "300",
                "냉장"
        );

        saveDiabetesIfNotExists(
                "코다리감자조림 세트",
                "category1",
                10500L,
                "정성을 다해 요리해 정갈하게 담아 낸 코다리살 감자 조림 세트를 소개해요. 한 입 크기의 코다리살을 감자와 당근, 국산 콩간장과 올리고당, 다진 마늘과 함께 푹 조려 내었어요. 매콤한 양념장에 쫄깃한 식감이 살아 있는 느타리 버섯 볶음은 입맛을 돋워 주죠.\n\n당귀향이 은은하게 느껴지는 담백한 달걀 요리에 두부 구이 샐러드까지 더해지면 동식물성 단백질을 골고루 담은 한상이 완성된답니다.\n\nPS. 신선한 상품을 만들기 위해 주문 후 제조합니다. 소비기한은 제조일로부터 4일이니, 안전하게 기한 안에 꼭 드세요.",
                "https://image.greating.co.kr/IL/item/202311/16/B_930E6B060A5C46B5B51D4DC61BCA84B2.jpg",
                "https://postfiles.pstatic.net/MjAyNDA3MzBfMTE0/MDAxNzIyMzM2OTAxOTgy.kF4T4fky1uWGu_e2X08bmwM6gUNVnz4GKea_qHEljmAg.Gr-AfoBYpIF7UbC4iZQ8NZKbzLQYALMVBff8qFQXldMg.PNG/1%EC%BD%94%EB%8B%A4%EB%A6%AC%EA%B0%90%EC%9E%90.png?type=w773",
                "256",
                "305",
                "냉장"
        );
        saveDiabetesIfNotExists(
                "가쓰오 닭안심 우엉볶음&병아리콩 두부조림 세트",
                "category2",
                10500L,
                "부드러운 닭안심과 우엉을 활용한 메뉴를 선보여요. 담백한 국산 닭 안심과 우엉을 어슷 썰어 넣어 식감과 영양을 살리고 구기자 간장, 올리고당으로 직접 만든 바비큐 소스로 양념해 볶았어요. 여기에 가쓰오부시를 더해 감칠맛이 일품이랍니다.\n\n영양이 가득한 병아리콩을 넣은 두부조림이 찬으로 제공돼요. 그리팅이 준비한 건강한 한식 한 상으로 든든하게 채워보세요.\n\nPS. 신선한 상품을 만들기 위해 주문 후 제조합니다. 소비기한은 제조일로부터 4일이니, 안전하게 기한 안에 꼭 드세요",
                "https://image.greating.co.kr/IL/item/202407/17/B_7160F64446B14F77A28255529EC3634E.jpg",
                "https://postfiles.pstatic.net/MjAyNDA3MzBfMyAg/MDAxNzIyMzM2OTE1NDk3.hw4fA-3NprXwexzQUU-L0MQVwvGjh3iy08GK9NKyaS8g.Ng6Qir-rKMx-v3iIXTXRg0Go30cFbVgmhDcLtD2tgr8g.PNG/2%EA%B0%80%EC%93%B0%EC%98%A4.png?type=w773",
                "273",
                "295",
                "냉장"
        );
        saveDiabetesIfNotExists(
                "문어제육 세트",
                "category3",
                10500L,
                "직접 만든 구기자 간장소스와 국산 고춧가루, 파기름으로 맛을 내 감칠맛이 일품인 문어 제육을 선보여요. 곁들임 찬으로 준비한 고소한 팽이버섯 계란 볶음은 고슬고슬 담백하고 밥으로 제공되는 유청단백 현미밥은 든든함을 채워줘요.\n\n신선한 깻잎 쌈 위에 강된장과 문어 제육을 올려 한 입 가득 드셔보세요. 그 후 새콤한 비트 무 생채를 곁들이면 입안을 개운하게 해주어 식사의 마무리까지 상큼하게 즐길 수 있답니다.\n\nPS. 신선한 상품을 만들기 위해 주문 후 제조합니다. 냉장 제품의 소비기한은 제조일로부터 4일이니, 안전하게 기한 안에 꼭 드세요.",
                "https://image.greating.co.kr/IL/item/202407/10/B_44435D23FE2C4192BB51C35AA029C6F8.jpg",
                "https://postfiles.pstatic.net/MjAyNDA3MzBfMjgy/MDAxNzIyMzM3MTczNjk0.-fbLo3fRBJdY2mI3ILbZQm_ft-y-fwrMFgQxmnQAOLog.ezc235-URAud26sUrGKhI-qe8qXV8xbuluanTlib7acg.PNG/3%EB%AC%B8%EC%96%B4_(1).png?type=w773",
                "377",
                "600",
                "냉장"
        );
        saveDiabetesIfNotExists(
                "버섯팔보채 세트",
                "category4",
                10500L,
                "6가지 버섯을 넣어 한층 담백하고 깔끔한 버섯 팔보채를 준비했어요. 표고와 양송이, 느타리버섯 등 다양한 버섯과 호주산 소고기를 그리팅 특제 굴소스에 볶았어요. 따뜻한 밥 위에 얹어 덮밥으로 즐겨도 좋아요.\n\n오븐에 구워 담백한 돼지고기에 채소를 매콤 새콤하게 볶아낸 라조육도 정갈하게 담았어요. 여기에 누룽지 튀김을 곁들인 샐러드까지 더해, 푸짐한 중국식 한 상을 즐겨 보세요.\n\nPS. 신선한 상품을 만들기 위해 주문 후 제조합니다. 냉장 제품의 소비기한은 제조일로부터 4일인, 안전하게 기한 안에 꼭 드세요.",
                "https://image.greating.co.kr/IL/item/202403/13/B_32A797B3314D423B92229708D9FEDB86.jpg",
                "https://postfiles.pstatic.net/MjAyNDA3MzBfMTE4/MDAxNzIyMzM2OTkyNTMx.o5AUyUbNdLyX8cYmywQ-HEmstEgV_Xg34ze8dB-5oFIg.buqfLn3gtQwrZ1yvrfmLkcLaeFG48EtajZ5fk9BA7wIg.PNG/4%EB%B2%84%EC%84%AF%ED%8C%94.png?type=w773",
                "277",
                "400",
                "냉장"
        );
        saveDiabetesIfNotExists(
                "닭살커리볶음 세트",
                "category5",
                10500L,
                "인도를 대표하는 향신료인 커리는 강황, 후추, 생강 등 존재감 강한 식재료들을 섞어 만드는 만큼 알싸하면서도 짙은 풍미를 뽐내는데요. 오늘은 인도 정통 커리 분말을 넣어 샛노란 비주얼에 한 번, 향긋한 카레 향에 두 번 매료되는 닭볶음 요리를 선보여요.\n\n국산 닭고기와 당근, 호박, 양파 등 각종 채소를 카레 양념에 골고루 볶아냈답니다.\n\n바다의 영양이 가득한 모둠 해초볶음까지, 닭 요리와 잘 어울리는 곁들임 찬도 잊지 말고 즐겨 보세요.\n\nPS. 신선한 상품을 만들기 위해 주문 후 제조합니다. 소비기한은 제조일로부터 4일이니, 안전하게 기한 안에 꼭 드세요.",
                "https://image.greating.co.kr/IL/item/202401/26/B_D4626B7FD7EE4221B1D8DBDA9179FC1B.jpg",
                "https://postfiles.pstatic.net/MjAyNDA3MzBfMTkx/MDAxNzIyMzM3MDAwOTE4.LXhra3ICVWrGG9Tkw9GQIgsLleWNwxLuVdVJerlXI58g.eqfz6d853VMJtENbrZIOd8vvBjXiO_LaYWPdaC74Oskg.PNG/5%EB%8B%AD%EC%82%B4.png?type=w773",
                "282",
                "335",
                "냉장"
        );
        saveDiabetesIfNotExists(
                "두부 오징어 두루치기 세트",
                "category1",
                10500L,
                "기분 좋은 매콤함에 손이 가는 오징어 두루치기 한 상을 준비했어요. 두루치기 양념에 푹 졸여낸 부드러운 두부를 함께 곁들이면 더욱 일품이랍니다.\n\n매운맛을 중화해줄 스크램블 에그는 단백질이 풍성해요.\n\n신선한 꼬시래기 무침의 오독오독한 식감이 더욱 즐거운 식사를 완성해줄거에요.\n\nPS. 신선한 상품을 만들기 위해 주문 후 제조합니다. 소비기한은 제조일로부터 4일이니, 안전하게 기한 안에 꼭 드세요.",
                "https://image.greating.co.kr/IL/item/202401/08/B_CCAEDC4689D147ECB1C785A9BB3280CA.jpg",
                "https://postfiles.pstatic.net/MjAyNDA3MzBfODgg/MDAxNzIyMzM3MDEwNTU4.3Hyox-dUaT998wxpRWTofQVxQr90yH8bx7WdIDozfnog.zeeDS1cIbnND9CIf14H3mhvKVnawJ389ukFknQpbqmkg.PNG/6%EB%91%90%EB%B6%80%EC%98%A4%EC%A7%95%EC%96%B4.png?type=w773",
                "387",
                "570",
                "냉장"
        );


        saveDiabetesIfNotExists(
                "마제덮밥 소스&치킨 가라아게 세트",
                "category2",
                10500L,
                "마제는 일본어로 '비비다 혹은 섞다' 라는 뜻이에요. 그리팅은 잘게 다진 국산 돼지고기와 대파, 부추를 감칠맛이 풍부한 가쓰오부시와 직접 만든 굴소스, 그리고 두반장 소스를 더해 따뜻한 밥과 함께 한 그릇 뚝딱 할 수 있는 덮밥 소스를 준비했어요.\n\n오븐에 구워낸 치킨 가라아게와 양배추볶음을 곁들여 푸짐한 일식 한 상을 맛보세요. 산뜻한 식사를 위해 여주와 호박을 볶은 밑반찬과 양파 피클도 함께 담았어요.\n\nPS. 신선한 상품을 만들기 위해 주문 후 제조합니다. 소비기한은 제조일로부터 4일이니, 안전하게 기한 안에 꼭 드세요.",
                "https://image.greating.co.kr/IL/item/202206/22/B_E55B7AC5B8BB43A193D8FF11765E9C79.jpg",
                "https://postfiles.pstatic.net/MjAyNDA3MzBfMjky/MDAxNzIyMzM3MDIyOTcx.h0JgzjsHGG2v4MBoI3uMT7vOnsIW493HZLEG9rocPFgg.mrg2QGEQEROkrODvYr-wG-MN6IW9Xmd0gXl5-sQIV44g.PNG/7%EB%A7%88%EC%A0%9C%EB%8D%AE.png?type=w773",
                "294",
                "420",
                "냉장"
        );
        saveDiabetesIfNotExists(
                "검은콩 캐슈넛 닭살볶음&황태 불고기 세트",
                "category3",
                10500L,
                "검은콩, 캐슈넛과 국산 닭 안심으로 만들어 맛있게 즐길 수 있는 닭볶음요리를 선보여요. 여기에 단호박찜을 곁들여 든든하게 구성했어요.\n\n쫄깃한 느타리버섯을 볶은 반찬은 입맛을 돋우기에 제격이에요. 매콤한 오리엔탈 드레싱을 곁들인 상추 쑥갓 샐러드까지, 오늘의 건강한 한끼 밥상을 만나보세요.\n\nPS. 신선한 상품을 만들기 위해 주문 후 제조합니다. 소비기한은 제조일로부터 4일이니, 안전하게 기한 안에 꼭 드세요.",
                "https://image.greating.co.kr/IL/item/202404/25/B_E7CF18B1499B4EBB9341909E4A6209DC.jpg",
                "https://postfiles.pstatic.net/MjAyNDA3MzBfMjE0/MDAxNzIyMzM3MDMyMDc3.9bUU_20trLKX1tf4hAZhJKL4JuHfVNEkMKs5ssE5oRcg.ZCYQGlatPUWAVw7ILr0aCD082j5_XNiDfjo5rF0uzRIg.PNG/8%EA%B2%80%EC%9D%80%EC%BD%A9.png?type=w773",
                "267",
                "310",
                "냉장"
        );

        saveDiabetesIfNotExists(
                "대파 듬뿍 두부조림&연자육 소불고기 세트",
                "category4",
                10500L,
                "붉은 양념이 자작하게 배어들어 입맛을 자극하는 두부조림은 언제나 환영받는 집밥 요리죠. 오늘은 대파를 듬뿍 올려 칼칼하게 완성한 두부조림을 선보여요. 두부는 기름에 부치는 대신 오븐에 구워 담백한 맛을 살렸어요. 국산 콩 간장과 고춧가루, 마늘 등을 섞어 만든 매콤한 양념장이 잘 배이도록 조린 후 양파와 대파를 가득 올려 아삭한 식감과 개운한 끝맛을 더했답니다.\n\n곁들임 찬으로는 연꽃의 씨앗인 연자육을 넣은 소불고기와 부드러운 갈릭크림 소스를 입힌 가지볶음을 준비했어요. 시원하고 아삭한 총각무 피클까지 함께 하면, 한층 풍성하고 든든한 집밥 한 끼를 만끽할 수 있을 거예요.\n\nPS. 신선한 상품을 만들기 위해 주문 후 제조합니다. 소비기한은 제조일로부터 4일이니, 안전하게 기한 안에 꼭 드세요.",
                "https://image.greating.co.kr/IL/item/202112/24/B_332C6E394B5D46D588C80FAC5A8E8E03.jpg",
                "https://postfiles.pstatic.net/MjAyNDA3MzBfMTY5/MDAxNzIyMzM3MDQxODg5.NW8VKuNjGILD93-fELu2QyOjQBEI5rOfdURA_5nl8hsg.HPEE0hbReaKf_XFMLANDf165xyGbHh4Mewsikz7AD0Eg.PNG/9%EB%8C%80%ED%8C%8C%EB%93%AC%EB%BF%8D.png?type=w773",
                "287",
                "335",
                "냉장"
        );
        saveDiabetesIfNotExists(
                "진저포크볶음 세트",
                "category5",
                10500L,
                "은은하게 퍼지는 생강향으로 입맛을 확 돋워 줄 진저포크볶음 세트를 소개해요. 국산 돼지고기는 생강 소스에 한번 구운 뒤, 깻잎과 국산 콩간장, 올리고당을 넣고 달큰하게 볶아 내었어요.\n\n고소한 캐슈넛과 조화가 좋은 깐풍두부강정도 준비했어요. 오븐에 구워 쫀득한 두부 강정을 새콤달콤한 깐풍 소스에 버무렸어요. 여기에 소금과 후추를 넣고 볶아 식재 본연의 맛을 살린 버섯 부추 볶음으로 마지막까지 깔끔하게 즐길 수 있답니다. 돼지고기와 두부로 동, 식물성 단백질을 골고루 섭취 가능한 맛있는 식단으로 힘찬 하루를 완성해 보세요.\n\nPS. 신선한 상품을 만들기 위해 주문 후 제조합니다. 냉장 제품의 소비기한은 제조일로부터 4일이니, 안전하게 기한 안에 꼭 드세요.",
                "https://image.greating.co.kr/IL/item/202310/18/B_5498FE446F1E4EE8A44E83C72D41D510.jpg",
                "https://postfiles.pstatic.net/MjAyNDA3MzBfMTQw/MDAxNzIyMzM3MDUwMTIx.uuv2SiK3udgAprf25Zh9Rz1EUvTNOtKqxYdpNm4xOmEg.xeM0kYkgFqsZYacHmu5waKG3knlRfngUG3pzI9iSwR8g.PNG/10%EC%A7%84%EC%A0%80%ED%8F%AC%ED%81%AC.png?type=w773",
                "227",
                "335",
                "냉장"
        );
    }
}