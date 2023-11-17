package christmas.domain;


import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;
import christmas.Application;
import org.junit.jupiter.api.Test;

class ChristmasIntegrationTest extends NsTest {

    @Test
    void 음료만_주문시_주문이_불가능하다() {
        assertSimpleTest(() -> {
            runException("3", "레드와인-1");
            assertThat(output()).contains("[ERROR] 음료만 주문할 수 없습니다.");
        });
    }

    @Test
    void 메뉴는_21개이상_주문이_불가능하다() {
        assertSimpleTest(() -> {
            runException("3", "양송이수프-3,타파스-3,시저샐러드-3,티본스테이크-3,바비큐립-3,해산물파스타-3,크리스마스파스타-3");
            assertThat(output()).contains("[ERROR] 메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다.");
        });
    }

    @Test
    void 모든_메뉴는_하나씩_주문가능하다() {
        assertSimpleTest(() -> {
            run("25",
                "양송이수프-1, 타파스-1, 시저샐러드-1, 티본스테이크-1, 바비큐립-1, 해산물파스타-1, 크리스마스파스타-1, 초코케이크-1, 아이스크림-1, 제로콜라-1, 레드와인-1, 샴페인-1");
            assertThat(output()).contains(
                "<주문 메뉴>",
                "양송이수프 1개",
                "타파스 1개",
                "시저샐러드 1개",
                "티본스테이크 1개",
                "바비큐립 1개",
                "해산물파스타 1개",
                "크리스마스파스타 1개",
                "초코케이크 1개",
                "아이스크림 1개",
                "제로콜라 1개",
                "레드와인 1개",
                "샴페인 1개",
                "<할인 전 총주문 금액>\n296,500원",
                "<증정 메뉴>\n샴페인 1개",
                "<혜택 내역>",
                "크리스마스 디데이 할인: -3,400원",
                "평일 할인: -4,046원",
                "증정 이벤트: -25,000원",
                "특별 할인: -1,000원",
                "<총혜택 금액>\n-33,446원",
                "<할인 후 예상 결제 금액>\n288,054원",
                "<12월 이벤트 배지>\n산타"
            );
        });
    }

    @Test
    void 메뉴판에_없는_메뉴를_주문할_수_없다() {
        assertSimpleTest(() -> {
            runException("3", "양송이스프-3,타파스-3,티본스테이크-3,바비큐립-3");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
    }

    @Test
    void 메뉴의_갯수는_1이상만_유효하다() {
        assertSimpleTest(() -> {
            runException("3", "양송이수프-0,타파스-3,티본스테이크-3,바비큐립-3");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
    }

    @Test
    void 메뉴의_갯수는_양수만_유효하다() {
        assertSimpleTest(() -> {
            runException("3", "양송이수프-a,타파스-3,티본스테이크-3,바비큐립-3");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
    }

    @Test
    void 중복_메뉴_불가능() {
        assertSimpleTest(() -> {
            runException("3", "시저샐러드-1,시저샐러드-1");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
    }

    @Test
    void 크리스마스_디데이_이벤트는_26일부터_적용되지_않는다() {
        assertSimpleTest(() -> {
            run("26",
                "양송이수프-1, 타파스-1, 시저샐러드-1, 티본스테이크-1, 바비큐립-1");
            assertThat(output()).doesNotContain("크리스마스 디데이 할인");
        });
    }


    @Test
    void 문제_예시_1번() {
        assertSimpleTest(() -> {
            run("26",
                "타파스-1,제로콜라-1");
            assertThat(output()).contains(
                "<할인 전 총주문 금액>\n8,500원",
                "<증정 메뉴>\n없음",
                "<혜택 내역>\n없음",
                "<총혜택 금액>\n0원",
                "<할인 후 예상 결제 금액>\n8,500원",
                "<12월 이벤트 배지>\n없음");
        });
    }

    @Test
    void 문제_예시_2번() {
        assertSimpleTest(() -> {
            run("3",
                "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
            assertThat(output()).contains(
                "<할인 전 총주문 금액>\n142,000원",
                "<증정 메뉴>\n샴페인 1개",
                "크리스마스 디데이 할인: -1,200원",
                "평일 할인: -4,046원",
                "특별 할인: -1,000원",
                "증정 이벤트: -25,000원",
                "<총혜택 금액>\n-31,246원",
                "<할인 후 예상 결제 금액>\n135,754원",
                "<12월 이벤트 배지>\n산타");
        });
    }

    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }
}