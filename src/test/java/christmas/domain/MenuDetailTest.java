package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MenuDetailTest {

    @DisplayName("메뉴판에 이름이 있다면 해당 메뉴를 반환한다.")
    @Test
    void createMenuDetailByValidTitle() {
        String validTitle = "양송이수프";

        assertThat(MenuDetail.findByTitle(validTitle)).isEqualTo(MenuDetail.MUSHROOM_SOUP);
    }

    @DisplayName("메뉴판에 이름이 없다면 예외가 발생한다.")
    @Test
    void createMenuDetailByInvalidTitle() {
        String invalidTitle = "양송이스프";

        assertThatThrownBy(() -> MenuDetail.findByTitle(invalidTitle))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("[ERROR]");
    }
}