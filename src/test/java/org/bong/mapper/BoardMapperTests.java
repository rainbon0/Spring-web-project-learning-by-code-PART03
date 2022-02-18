package org.bong.mapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.bong.domain.BoardVO;
import org.bong.domain.Criteria;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
// import org.springframework.test.context.web.WebAppConfiguration;


@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:web/WEB-INF/spring/root-context.xml")

// @WebAppConfiguration

// java config
// @ContextConfiguration(classes={org.bong.config.RootConfig.class})

public class BoardMapperTests {
    @Setter(onMethod_ = @Autowired)
    private BoardMapper mapper;

    @Test
    public void testGetList(){
        mapper.getList().forEach(board -> log.info(board));
    }

    @Test
    public void testInsert(){
        BoardVO board = new BoardVO();
        board.setTitle("new Title!");
        board.setContent("New Content!!!");
        board.setWriter("newbie");

        mapper.insert(board);

        log.info(board);
    }

    @Test
    public void testInsertSelectKey(){
        BoardVO board = new BoardVO();
        board.setTitle("New Title _ selectKey");
        board.setContent("New Content _ selectKey");
        board.setWriter("newbie");

        mapper.insertSelectKey(board);
        log.info(board);
    }

    @Test
    public void testRead(){
        // 존재하는 게시물 번호로 테스트
        BoardVO board = mapper.read(5L);

        log.info(board);

        /*
        |----|-----------|-------------|-------|----------------------|----------------------|
        |bno |title      |content      |writer |regdate               |updatedate            |
        |----|-----------|-------------|-------|----------------------|----------------------|
        |5   |Test Title |Test Content |user00 |2022-02-15 14:33:04.0 |2022-02-15 14:33:04.0 |
        |----|-----------|-------------|-------|----------------------|----------------------|
         */
    }

    @Test
    public void testDelete(){
        log.info(
                "Delete Count : " +mapper.delete(3L)
        );
    }

    @Test
    public void testUpdate(){
        BoardVO board = new BoardVO();
        board.setWriter("user11");
        board.setTitle("Modified Title");
        board.setContent("Modified Content!");
        board.setBno(5L);

        int count = mapper.update(board);
        log.info("Update Count : " + count);
    }

    // getListWithPaging Test
    @Test
    public void testPaging() {
        Criteria cri = new Criteria();

        cri.setAmount(10);
        cri.setPageNum(1);

        List<BoardVO> list = mapper.getListWithPaging(cri);

        list.forEach(board -> log.info(board));
    }


    @Test
    public void testSearch(){
        Criteria cri = new Criteria();
        cri.setKeyword("new");
        cri.setType("TC");

        List<BoardVO> list = mapper.getListWithPaging(cri);

        list.forEach(board -> log.info(board));
    }
}

