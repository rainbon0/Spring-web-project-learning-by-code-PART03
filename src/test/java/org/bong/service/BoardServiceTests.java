package org.bong.service;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

import org.bong.domain.BoardVO;
import org.bong.domain.Criteria;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:web/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardServiceTests {
    @Setter(onMethod_ = @Autowired)
    private BoardService service;

    @Test
    public void testExist(){
        log.info(service);
        assertNotNull(service);
    }

    @Test
    public void testRegister(){
        BoardVO board = new BoardVO();
        board.setTitle("New Title _(test)");
        board.setContent("New Content _(test)");
        board.setWriter("newbie");
        service.register(board);

        log.info("생성된 게시물의 번호 : " + board.getBno());
        /*
        |--------|
        |nextval |
        |--------|
        |18      |
        |--------|
         */
    }

    @Test
    public void testGetList(){
        service.getList(new Criteria(2,10)).forEach(board -> log.info(board));
    }

    @Test
    public void testGet(){
        log.info(service.get(2L));
    }

    @Test
    public void testUpdate(){
        BoardVO board = service.get(10L);
        if(board ==null) return;

        board.setTitle("Title modifying......");
        log.info("Modify result : " + service.modify(board));
    }

    @Test
    public void testDelete(){
        log.info("REMOVE RESULT : " + service.delete(14L));
    }

}
