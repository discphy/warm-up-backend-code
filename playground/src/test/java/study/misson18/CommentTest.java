package study.misson18;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CommentTest {

    @BeforeEach
    void setUp() {
        1-1. 2-1. 3-1. 사용자 생성에 필요한 내용 준비
        1-2. 2-2. 3-2. 사용자 생성
        1-3. 2-3. 3-5. 게시물 생성에 필요한 내용 준비
        1-4. 2-4. 3-6. 게시물 생성
    }

    @DisplayName("사용자가 댓글을 작성할 수 있다.")
    @Test
    void writeComment() {
        // given
        1-5. 댓글 생성에 필요한 내용 준비

        // when
        1-6. 댓글 생성

        // then
        검증
    }

    @DisplayName("사용자가 댓글을 수정할 수 있다.")
    @Test
    void updateComment() {
        // given
        2-5. 댓글 생성에 필요한 내용 준비
        2-6. 댓글 생성

        // when
        2-7. 댓글 수정

        // then
        검증
    }

    @DisplayName("자신이 작성한 댓글이 아니면 수정할 수 없다.")
    @Test
    void cannotUpdateCommentWhenUserIsNotWriter() {
        // given
        3-3. 사용자2 생성에 필요한 내용 준비
        3-4. 사용자2 생성

        3-7. 사용자1의 댓글 생성에 필요한 내용 준비
        3-8. 사용자1의 댓글 생성

        // when & then
        예외 검증
        3-9. 사용자2가 사용자1의 댓글 수정 시도
    }
}
