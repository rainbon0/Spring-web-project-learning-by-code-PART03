package org.bong.controller;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


// jUnit : Java에서 독립된 단위테스트를 지원해주는 프레임워크
// 단위테스트 : 소스코드의 특정 모듈이 의도된 대로 정확히 작동하는지 검증하는 절차
// assert 메서드로 테스트 케이스의 수행결과를 판단한다. (assertNotNull, assertEquals, etc...)


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration    // Test for Controller
                        // Servlet의 ServletContext를 사용하기 위함. (Spring의 WebApplicationContext)
@ContextConfiguration({
        "file:web/WEB-INF/spring/root-context.xml",
        "file:web/WEB-INF/spring/appServlet/servlet-context.xml"
})
@Log4j
public class BoardControllerTests {
    @Setter(onMethod_ = {@Autowired})
    private WebApplicationContext ctx;

    //  MockMvc = 가짜 mvc : 가짜로 URL과 파라미터 등을 브라우저에서 사용하는 것처럼 만들어서 Controller를 실행해 볼 수 있게 하는 역할
    private MockMvc mockMvc;

    @Before //  Before 어노테이션이 붙은 메소드는 모든 테스트 전에 매번 실행되는 메소드가 된다.
    public void setup(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
    }

    @Test
    public void testList() throws Exception{
        log.info(
                mockMvc.perform(MockMvcRequestBuilders.get("/board/list"))
                        .andReturn()
                        .getModelAndView()
                        .getModelMap()
        );
    }

    @Test
    public void testRegister() throws Exception{
        String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/board/register")
                .param("title","Test New Board Title")
                .param("content", "Test New Board Content")
                .param("writer" , "user00")
        ).andReturn().getModelAndView().getViewName();

        log.info(resultPage);
    }

    @Test
    public void testGet() throws Exception{
        log.info(mockMvc.perform(MockMvcRequestBuilders.get("/board/get").param("bno","2"))
                .andReturn().getModelAndView());
    }

    @Test
    public void testModify() throws Exception{
        String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/board/modify")
                .param("bno","1")
                .param("title" , "수정된 테스트 새글 제목")
                .param("content", "수정된 테스트 새글 내용")
                .param("writer" , "user00")).andReturn().getModelAndView().getViewName();

        log.info(resultPage);
    }


    @Test
    public void testRemove() throws Exception{
        String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/board/remove")
                .param("bno", "25")).andReturn().getModelAndView().getViewName();
        log.info(resultPage);
    }

    @Test
    public void testListPaging() throws Exception{
        log.info(mockMvc.perform(MockMvcRequestBuilders.get("/board/list")
                .param("pageNum", "2")
                .param("amount" , "10")
        ).andReturn().getModelAndView().getModelMap()
        );
    }


}











